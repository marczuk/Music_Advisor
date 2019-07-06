package advisor.request;

import advisor.service.SpotifyService;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.Map;

public class AuthRequest extends AbstractRequest {

    {
        path = SpotifyService.getTokenUrl();
    }

    public AuthRequest() {
        super();
    }

    public AuthRequest(Map<String, String> requestParams) {
        super(requestParams);
    }

    @Override
    protected String getAuthorizationString() {
        String authorization = SpotifyService.getClientId() + ":" + SpotifyService.getClientSecret();
        return "Basic " + Base64.getEncoder().encodeToString(authorization.getBytes());
    }

    @Override
    public String execute() {
        String result = null;

        try {
            HttpResponse<String> response = post();
            result = response.body();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
