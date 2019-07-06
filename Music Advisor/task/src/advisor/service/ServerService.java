package advisor.service;

import advisor.request.AuthRequest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ServerService extends Thread {

    public ServerService() {
        start();
    }

    @Override
    public void run() {
        try {

            final ExecutorService ex = Executors.newSingleThreadExecutor();
            final CountDownLatch c = new CountDownLatch(1);

            HttpServer server = HttpServer.create();
            server.bind(new InetSocketAddress(8765), 0);

            //System.out.println("created server");
            server.createContext("/",
                    new HttpHandler() {
                        public void handle(HttpExchange he) throws IOException {

                            try {
                                Map<String, String> params = queryToMap(he.getRequestURI().getQuery());
                                String appCode = params.get("code");
                                String test = "";
                                if (appCode != null) {
                                    //System.out.println(appCode);
                                    AuthRequest authRequest = new AuthRequest(SpotifyService.getTokenParams(appCode));
                                    test = authRequest.execute();

                                    JsonParser parser = new JsonParser();
                                    JsonObject object = (JsonObject) parser.parse(test);

                                    if (object != null && object.has("access_token")) {
                                        SpotifyService.setUserAccessToken(object.get("access_token").toString().replace("\"", ""));
                                    }
                                    c.countDown();           // count down, letting `c.await()` to return
                                }

//                                System.out.println("test message");


                                String hello = test;
                                he.sendResponseHeaders(200, hello.length());
                                he.getResponseBody().write(hello.getBytes());
                                he.getResponseBody().close();


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