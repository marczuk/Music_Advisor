package advisor.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ServerService {

    public ServerService() {
        try {

            final ExecutorService ex = Executors.newSingleThreadExecutor();
            final CountDownLatch c = new CountDownLatch(1);

            HttpServer server = HttpServer.create();
            server.bind(new InetSocketAddress(8765), 0);

            System.out.println("created server");
            server.createContext("/",
                    new HttpHandler() {
                        public void handle(HttpExchange he) throws IOException {
                            //HttpURLConnection conn = (HttpURLConnection) new URL("https://www.google.com").openConnection();


                            try {
                                Map<String, String> params = queryToMap(he.getRequestURI().getQuery());
                                String appCode = params.get("code");
                                String test = "";
                                if (appCode != null) {
                                    //System.out.println(appCode);
                                    test = post(SpotifyService.getTokenUrl(), SpotifyService.getTokenParams(appCode));

                                    JsonParser parser = new JsonParser();
                                    JsonObject object = (JsonObject) parser.parse(test);

                                    SpotifyService.setUserAccessToken(object.get("access_token").toString());
                                }

//                                System.out.println("test message");


                                String hello = test;
                                he.sendResponseHeaders(200, hello.length());
                                he.getResponseBody().write(hello.getBytes());
                                he.getResponseBody().close();

                                c.countDown();           // count down, letting `c.await()` to return

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }
            );
//            server.setExecutor(null); // creates a default executor
//            server.start();
            server.setExecutor(ex);      // set up a custom executor for the server
            server.start();              // start the server
//            System.out.println("HTTP server started");
            c.await();                   // wait until `c.countDown()` is invoked
            ex.shutdown();               // send shutdown command to executor
            // wait until all tasks complete (i. e. all responses are sent)
            ex.awaitTermination(5, TimeUnit.SECONDS);
            server.stop(0);
//            System.out.println("HTTP server stopped");

        } catch (IOException | InterruptedException e) {
            System.out.println("bada bad bad");
            e.printStackTrace();
        }
    }

    public String post(String uri, Map<String,String> map) throws Exception {

        String authorization = map.get("client_id") + ":" + map.get("client_secret");
        authorization = "Basic " + Base64.getEncoder().encodeToString(authorization.getBytes());

        map.remove("client_id");
        map.remove("client_secret");

//            ObjectMapper objectMapper = new ObjectMapper();
//            String requestBody = objectMapper
//                    .writerWithDefaultPrettyPrinter()
//                    .writeValueAsString(map);

        String requestBody = map.keySet().stream()
                .map(key -> key + "=" + map.get(key))
                .collect(Collectors.joining("&"));



//        System.out.println(requestBody);


            HttpRequest request = HttpRequest.newBuilder(URI.create(uri))
                    .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .header("Authorization", authorization)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());

            return response.body();

    }

    static public String get(String uri) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        //System.out.println(response.body());
        return response.body();
    }


    private Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<>();
        if (query != null) {
            for (String param : query.split("&")) {
                String[] entry = param.split("=");
                if (entry.length > 1) {
                    result.put(entry[0], entry[1]);
                } else {
                    result.put(entry[0], "");
                }
            }
        }
        return result;
    }
}