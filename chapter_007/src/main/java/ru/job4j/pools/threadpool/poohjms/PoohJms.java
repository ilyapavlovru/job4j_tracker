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

    private ThreadPoolExecutor threadPoolExecutor;
    private String mode;
    BlockingQueue<Message> queue = new LinkedBlockingQueue<>(10);
    ConcurrentHashMap<String, BlockingQueue<Message>> map = new ConcurrentHashMap<>();

    public PoohJms(String mode) throws IOException, InterruptedException {

        this.mode = mode;

        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
        this.threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        server.createContext("/topic", new PoohJms.PoohJmsHttpHandler());
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
            String requestParamValue = null;
            Message getResponseMessage = null;

            if ("GET".equals(httpExchange.getRequestMethod())) {

                System.out.println("Внутри GET обработчика");
//                requestParamValue = handleGetRequest(httpExchange);
//                System.out.println("requestParamValue = " + requestParamValue);

                String requestUri = httpExchange.getRequestURI().toString();
                // проверка что get запрос в режиме Topic
                if (requestUri.contains("/topic/")) {

                    String topicName = requestUri.split("/")[2];
                    Callable<Message> callable = new Consumer(map, topicName);
                    Future<Message> futureTask1 = threadPoolExecutor.submit(callable);

                    System.out.println("Finished: " + futureTask1.isDone());

                    while (true) {
                        try {
                            if (futureTask1.isDone()) {
                                System.out.println("FutureTask1 Complete");
                                getResponseMessage = futureTask1.get();
                                System.out.println("message after FutureTask1 Complete = " + getResponseMessage);

                                // преобразовать объект message в строку json
//                                messageToJsonString(getResponseMessage);

                                break;
                            }
                        } catch (Exception e) {
                            System.out.println("Exception: " + e);
                            break;
                        }
                    }


                    // после прочтения сообщения консумером, т.е. завершения выполнения процесса
                    // мы должны получить результирующий json и напечатать его в ответе на странице
                    System.out.println("at handleGetResponse");
                    String messageJsonString = messageToJsonString(getResponseMessage);
                    handleGetResponse(httpExchange, messageJsonString);
                }

            } else if ("POST".equals(httpExchange.getRequestMethod())) {

                System.out.println("Внутри POST обработчика");

                StringBuilder sb = new StringBuilder();
                InputStream ios = httpExchange.getRequestBody();
                int i;
                while ((i = ios.read()) != -1) {
                    sb.append((char) i);
                }
//                System.out.println("hm: " + sb.toString());
                String msg = sb.toString();
                System.out.println(msg);

                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(msg);

                if (MsgHelper.isTopicMessage(jsonNode)) {

                    String type = jsonNode.get("topic").asText();
                    String text = jsonNode.get("text").asText();
                    Message message = new Message(type, text);

                    new Thread(new Producer(map, message)).start();

                }


            }

            System.out.println("at handleResponse");

            handleResponse(httpExchange, requestParamValue);
        }

        private String messageToJsonString(Message message) throws IOException {
            if (message != null) {
                String type = message.getTopic();
                String text = message.getText();

                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.valueToTree(message);

                String prettyString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
                System.out.println(prettyString);
                return prettyString;
            }
            return "";
        }

        private String handleGetRequest(HttpExchange httpExchange) {
            return httpExchange.
                    getRequestURI()
                    .toString()
                    .split("\\?")[1]
                    .split("=")[1];
        }

        private void handleGetResponse(HttpExchange httpExchange, String resJson) throws IOException {
            OutputStream outputStream = httpExchange.getResponseBody();
            String htmlResponse = resJson;
            httpExchange.sendResponseHeaders(200, htmlResponse.length());
            outputStream.write(htmlResponse.getBytes());
            outputStream.flush();
            outputStream.close();
        }

        private void handleResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
            OutputStream outputStream = httpExchange.getResponseBody();
            StringBuilder htmlBuilder = new StringBuilder();

            htmlBuilder.append("").
                    append("").
                    append("<h1>").
                    append("Hello ")
                    .append(requestParamValue)
                    .append("</h1>")
                    .append("")
                    .append("");

            // encode HTML content
            String htmlResponse = htmlBuilder.toString();

            // this line is a must
            httpExchange.sendResponseHeaders(200, htmlResponse.length());

            outputStream.write(htmlResponse.getBytes());
            outputStream.flush();
            outputStream.close();
        }

    }
}


