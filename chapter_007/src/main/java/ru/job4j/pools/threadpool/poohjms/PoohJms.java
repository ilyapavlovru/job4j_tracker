package ru.job4j.pools.threadpool.poohjms;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import java.util.concurrent.*;

public class PoohJms {

    private final ThreadPoolExecutor threadPoolExecutor;
    private final BlockingQueue<Message> queue = new LinkedBlockingQueue<>(10);
    private final ConcurrentHashMap<String, BlockingQueue<Message>> map = new ConcurrentHashMap<>();

    public PoohJms(String mode) throws IOException, InterruptedException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
        this.threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        server.createContext("/" + mode, new PoohJms.PoohJmsHttpHandler());
        server.setExecutor(this.threadPoolExecutor);
        server.start();
        this.threadPoolExecutor.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
    }

    private class PoohJmsHttpHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            Message responseMessage = null;
            if ("GET".equals(httpExchange.getRequestMethod())) {

                String requestUri = httpExchange.getRequestURI().toString();

                // проверка что get запрос в режиме Topic
                if (requestUri.contains("/topic/")) {
                    String topicName = requestUri.split("/")[2];
                    Callable<Message> callable = new TConsumer(map, topicName);
                    Future<Message> futureTask = threadPoolExecutor.submit(callable);
                    responseMessage = getFutureTaskResponse(futureTask);
                }

                // проверка что get запрос в режиме Queue
                if (requestUri.contains("/queue/")) {
                    Callable<Message> callable = new QConsumer(queue);
                    Future<Message> futureTask = threadPoolExecutor.submit(callable);
                    responseMessage = getFutureTaskResponse(futureTask);
                }

                // после прочтения сообщения консумером, т.е. завершения выполнения процесса
                // мы должны получить результирующий json и напечатать его в ответе на странице
                String messageJsonString = messageToJsonString(responseMessage);
                handleResponse(httpExchange, messageJsonString);

            } else if ("POST".equals(httpExchange.getRequestMethod())) {
                String msg = inputStreamToString(httpExchange.getRequestBody());
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(msg);

                if (MsgHelper.isTopicMessage(jsonNode)) {
                    String type = jsonNode.get("topic").asText();
                    String text = jsonNode.get("text").asText();
                    Message message = new Message(type, text);
                    Callable<Message> callable = new TProducer(map, message);
                    Future<Message> futureTask = threadPoolExecutor.submit(callable);
                    responseMessage = getFutureTaskResponse(futureTask);
                }

                if (MsgHelper.isQueueMessage(jsonNode)) {
                    String type = jsonNode.get("queue").asText();
                    String text = jsonNode.get("text").asText();
                    Message message = new Message(type, text);
                    Callable<Message> callable = new QProducer(queue, message);
                    Future<Message> futureTask = threadPoolExecutor.submit(callable);
                    responseMessage = getFutureTaskResponse(futureTask);
                }
                String messageJsonString = messageToJsonString(responseMessage);
                handleResponse(httpExchange, messageJsonString);
            }
        }

        private String inputStreamToString(InputStream ios) throws IOException {
            StringBuilder sb = new StringBuilder();
            int i;
            while ((i = ios.read()) != -1) {
                sb.append((char) i);
            }
            return sb.toString();
        }

        private String messageToJsonString(Message message) throws IOException {
            if (message != null) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.valueToTree(message);
                String prettyString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
                return prettyString;
            }
            return "";
        }

        private void handleResponse(HttpExchange httpExchange, String resJson) throws IOException {
            OutputStream outputStream = httpExchange.getResponseBody();
            httpExchange.sendResponseHeaders(200, resJson.length());
            outputStream.write(resJson.getBytes());
            outputStream.flush();
            outputStream.close();
        }
    }

    private Message getFutureTaskResponse(Future<Message> futureTask) {
        while (true) {
            try {
                if (futureTask.isDone()) {
                    Message getResponseMessage = futureTask.get();
                    return getResponseMessage;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}


