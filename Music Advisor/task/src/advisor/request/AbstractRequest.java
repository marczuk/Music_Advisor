package advisor.request;

import advisor.service.SpotifyService;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

abstract class AbstractRequest {

    protected String path;

    protected Map<String,String> requestParams = new HashMap<>();

    protected Gson gson = new Gson();

    public AbstractRequest() {
    }

    public AbstractRequest(Map<String, String> requestParams) {
        this.requestParams = requestParams;
    }

    public abstract <T> T execute();

    protected String getAuthorizationString() {
        String authorization = SpotifyService.getUserAccessToken();
        return "Bearer " + authorization;
    }

    protected  HttpResponse<String> post() throws Exception {

        //prepare params for post
        String requestBody = getRequestParamsAsString();
//        System.out.println(requestBody);

        HttpRequest request = HttpRequest.newBuilder(URI.create(path))
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("Authorization", getAuthorizationString())
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient client = HttpClient.newBuilder().build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    protected HttpResponse<String> get() throws Exception {

        String requestParams = getRequestParamsAsString();
        if (!requestParams.isEmpty()) {
            path += "?" + requestParams;
        }

        System.out.println(getAuthorizationString());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(path))
                .header("Authorization", getAuthorizationString())
                .build();

        HttpClient client = HttpClient.newHttpClient();

        //System.out.println(response.body());
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private String getRequestParamsAsString() {
        //prepare params for post
        return requestParams.keySet().stream()
                .map(key -> key + "=" + requestParams.get(key))
                .collect(Collectors.joining("&"));
    }
}
