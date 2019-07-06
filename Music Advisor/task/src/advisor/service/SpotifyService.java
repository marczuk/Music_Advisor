package advisor.service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class SpotifyService {

    private static  String redirect_uri = "http://localhost:8765/";
    private static  String uri = "https://accounts.spotify.com/";
    private static  String dataUri = "https://api.spotify.com/";
    private static  String dataUriVersion = "v1/browse/";
    private static  String authorize = "authorize";
    private static  String token = "api/token";

    private static  String clientId = "3c1f0675fb224be6807fdd578f1d84cb";
    private static  String clientSecret = "97cdf492b8934099a263ddd75f02d3ca";

    private static String userAccessToken = "";

    private static boolean userLogged = false;

    public static boolean isUserLogged() {
        return !userAccessToken.isEmpty();
    }

    public static String getAuthorizationUrl() {
        //
        //
        //setUserAccessToken("testestest");
        //
        //

        StringBuilder authUrl = new StringBuilder();
        authUrl.append(uri)
                .append(authorize)
                .append("?response_type=code")
                .append("&").append("client_id=").append(getClientId())
                .append("&").append("redirect_uri=").append(redirect_uri);

        return authUrl.toString();
    }

    public static String getDataUri() {
        return dataUri + dataUriVersion;
    }

    public static String getTokenUrl() {

        StringBuilder authUrl = new StringBuilder();
        authUrl.append(uri)
                .append(token)
        ;

        return authUrl.toString();
    }

    public static Map<String,String> getTokenParams(String appCode) {
        Map<String, String> returnParams = new HashMap<>();

        returnParams.put("grant_type", "authorization_code");
        returnParams.put("code", appCode);
        returnParams.put("redirect_uri", redirect_uri);

        return returnParams;
    }

    public static void setUserAccessToken(String accessToken){

        userAccessToken = accessToken;
    }

    public static String getUserAccessToken() {
        return getUserAccessToken(false);
    }

    public static String getUserAccessToken(boolean refreshToken){
        if (refreshToken) {
            //TODO::handle autorefreshing token process
            //setUserAccessToken();
        }

        return userAccessToken;
    }

    public static String getClientId() {
        return clientId;
    }

    public static String getClientSecret() {
        return clientSecret;
    }
}
