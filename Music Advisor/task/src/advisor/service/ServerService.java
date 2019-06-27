package advisor.service;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServerService {

    public ServerService() {
        try {
            HttpServer server = HttpServer.create();
            server.bind(new InetSocketAddress(8765), 0);

            System.out.println("created server");
            server.createContext("/",
                    new HttpHandler() {
                        public void handle(HttpExchange he) throws IOException {
                            //HttpURLConnection conn = (HttpURLConnection) new URL("https://www.google.com").openConnection();

                            String test = null;
                            try {
                                test = get("https://www.google.com");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            System.out.println("test message");
                            String hello = test;
                            he.sendResponseHeaders(200, hello.length());
                            he.getResponseBody().write(hello.getBytes());
                            he.getResponseBody().close();
                        }
                    }
            );
            server.setExecutor(null); // creates a default executor
            server.start();

        } catch (IOException e) {
            System.out.println("bada bad bad");
            e.printStackTrace();
        }
    }

    public String post(String uri) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        //System.out.println(response.body());
        return response.body();
    }

    public String get(String uri) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        //System.out.println(response.body());
        return response.body();
    }
}