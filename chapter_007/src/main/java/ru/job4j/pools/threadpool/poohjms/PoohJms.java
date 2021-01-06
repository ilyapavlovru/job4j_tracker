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
        System.out.println("Server started on port 8001");
        this.threadPoolExecutor.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
    }

    private class PoohJmsHttpHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

            System.out.println("getRequestURI = " + httpExchange.getRequestURI());

            System.out.println("Внутри handle");
            Message responseMessage = null;

            if ("GET".equals(httpExchange.getRequestMethod())) {

                System.out.println("Внутри GET обработчика");
                String requestUri = httpExchange.getRequestURI().toString();

                // проверка что get запрос в режиме Topic
                if (requestUri.contains("/topic/")) {

                    String topicName = requestUri.split("/")[2];
                    Callable<Message> callable = new TConsumer(map, topicName);
                    Future<Message> futureTask = threadPoolExecutor.submit(callable);

                    System.out.println("Finished: " + futureTask.isDone());

                    responseMessage = getFutureTaskResponse(futureTask);
                }


                // проверка что get запрос в режиме Queue
                if (requestUri.contains("/queue/")) {

                    Callable<Message> callable = new QConsumer(queue);
                    Future<Message> futureTask = threadPoolExecutor.submit(callable);

                    System.out.println("Finished: " + futureTask.isDone());

                    responseMessage = getFutureTaskResponse(futureTask);
                }

                // после прочтения сообщения консумером, т.е. завершения выполнения процесса
                // мы должны получить результирующий json и напечатать его в ответе на странице
                System.out.println("at handleGetResponse");
                String messageJsonString = messageToJsonString(responseMessage);
                handleResponse(httpExchange, messageJsonString);

            } else if ("POST".equals(httpExchange.getRequestMethod())) {

                System.out.println("Внутри POST обработчика");
                String msg = inputStreamToString(httpExchange.getRequestBody());
                System.out.println(msg);

                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(msg);

                if (MsgHelper.isTopicMessage(jsonNode)) {

                    String type = jsonNode.get("topic").asText();
                    String text = jsonNode.get("text").asText();
                    Message message = new Message(type, text);

                    Callable<Message> callable = new TProducer(map, message);
                    Future<Message> futureTask = threadPoolExecutor.submit(callable);

                    System.out.println("Finished: " + futureTask.isDone());
                    responseMessage = getFutureTaskResponse(futureTask);
                }

                if (MsgHelper.isQueueMessage(jsonNode)) {

                    String type = jsonNode.get("queue").asText();
                    String text = jsonNode.get("text").asText();
                    Message message = new Message(type, text);

                    Callable<Message> callable = new QProducer(queue, message);
                    Future<Message> futureTask = threadPoolExecutor.submit(callable);

                    System.out.println("Finished: " + futureTask.isDone());
                    responseMessage = getFutureTaskResponse(futureTask);
                }


                System.out.println("at handleResponse");
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
                System.out.println(prettyString);
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
            //System.out.println("waiting..........");
            try {
                if (futureTask.isDone()) {
                    System.out.println("FutureTask1 Complete");
                    Message getResponseMessage = futureTask.get();
                    System.out.println("message after FutureTask1 Complete = " + getResponseMessage);
                    return getResponseMessage;
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e);
                return null;
            }
        }
    }
}


