package advisor.service;

public class SpotifyService {

    private static String redirect_uri = "https://www.example.com&response_type=code";
    private static String uri = "https://accounts.spotify.com/authorize?";
    private static String client_id = "3c1f0675fb224be6807fdd578f1d84cb";
    private static String client_secret = "97cdf492b8934099a263ddd75f02d3ca";


    private static boolean userLogged = false;

    public static boolean isUserLogged() {
        return userLogged;
    }

    public String getAuthorizationUrl() {

        StringBuilder authUrl = new StringBuilder();
        authUrl.append(uri)
                .append("client_id=").append(client_id)
                .append("&").append("redirect_uri=").append(redirect_uri);

        //for now change flag right after gen auth link
        userLogged = true;

        return authUrl.toString();
    }
}
