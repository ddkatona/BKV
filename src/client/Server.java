package client;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;

/**
 * Created by ddkatona on 7/14/2017.
 */
public class Server {

    public void start() {
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(2000), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.createContext("/", new Home());
        server.createContext("/assets", new MyHandler());
        server.createContext("/get", new GetHandler());
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            // add the required response header for a PDF file
            Headers h = t.getResponseHeaders();
            h.add("Content-Type", "text/plain");

            // a txt (you provide your own!)
            File file = new File ("web/res.txt");
            byte [] bytearray  = new byte [(int)file.length()];
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(bytearray, 0, bytearray.length);

            // ok, we are ready to send the response.
            t.sendResponseHeaders(200, file.length());
            OutputStream os = t.getResponseBody();
            os.write(bytearray,0,bytearray.length);
            os.close();
        }
    }

    static class Home implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "<b>Home</b>";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class GetHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {

            // add the required response header for a PDF file
            Headers h = t.getResponseHeaders();
            h.add("Content-Type", "text/html");

            // a txt (you provide your own!)
            File file = new File ("web/index.html");
            byte [] bytearray  = new byte [(int)file.length()];
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(bytearray, 0, bytearray.length);

            // ok, we are ready to send the response.
            t.sendResponseHeaders(200, file.length());
            OutputStream os = t.getResponseBody();
            os.write(bytearray,0,bytearray.length);
            os.close();
        }
    }

}
