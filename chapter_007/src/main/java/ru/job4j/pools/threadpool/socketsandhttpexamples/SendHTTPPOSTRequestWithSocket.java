package ru.job4j.pools.threadpool.socketsandhttpexamples;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URLEncoder;

public class SendHTTPPOSTRequestWithSocket {

    public static void main(String[] args) {

        try {

            String params = URLEncoder.encode("param1", "UTF-8")
                    + "=" + URLEncoder.encode("value1", "UTF-8");
            params += "&" + URLEncoder.encode("param2", "UTF-8")
                    + "=" + URLEncoder.encode("value2", "UTF-8");

            String hostname = "localhost";
            int port = 8080;

            InetAddress addr = InetAddress.getByName(hostname);
            Socket socket = new Socket(addr, port);
            String path = "";

            // Send headers
            BufferedWriter wr =
                    new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
            wr.write("POST "+path+" HTTP/1.0rn");
            wr.write("Content-Length: "+params.length()+"rn");
            wr.write("Content-Type: application/x-www-form-urlencodedrn");
            wr.write("rn");

            // Send parameters
            wr.write(params);
            wr.flush();

            // Get response
            BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;

            while ((line = rd.readLine()) != null) {
                System.out.println(line);
            }

            wr.close();
            rd.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}