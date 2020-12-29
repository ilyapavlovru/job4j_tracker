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

    private ExecutorService pool;
    private String mode;
    BlockingQueue<Message> queue = new LinkedBlockingQueue<>(10);

    public PoohJms(String mode) throws IOException, InterruptedException {

        this.mode = mode;

        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        server.createContext("/test", new PoohJms.PoohJmsHttpHandler());
        server.setExecutor(threadPoolExecutor);
        server.start();
        System.out.println("Server started on port 8001");
        threadPoolExecutor.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
    }

    private class PoohJmsHttpHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

            System.out.println("getRequestURI = " + httpExchange.getRequestURI());

            System.out.println("Внутри handle");
            String requestParamValue = null;

            if ("GET".equals(httpExchange.getRequestMethod())) {

                System.out.println("Внутри GET обработчика");
                requestParamValue = handleGetRequest(httpExchange);
                System.out.println("requestParamValue = " + requestParamValue);

                String requestUri = httpExchange.getRequestURI().toString();
                // проверка что get запрос в режиме Queue
                if (requestUri.contains("/queue/")) {
                    System.out.println("lastIndexOf = " + requestUri.lastIndexOf("/queue/", 0));
                }

                // получатель читает сообщение и удаляет его из очереди

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

                if (MsgHelper.isQueueMessage(jsonNode)) {

                    String type = jsonNode.get("queue").asText();
                    String text = jsonNode.get("text").asText();
                    Message message = new Message(type, text);

                    new Thread(new Producer(queue, message)).start();

                }


            }

            handleResponse(httpExchange, requestParamValue);
        }

        private String handleGetRequest(HttpExchange httpExchange) {
            return httpExchange.
                    getRequestURI()
                    .toString()
                    .split("\\?")[1]
                    .split("=")[1];
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


