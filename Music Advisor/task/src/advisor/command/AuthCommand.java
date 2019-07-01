package advisor.command;

import advisor.service.SpotifyService;

public class AuthCommand extends Command{

//    protected static String name = "/help";

    private SpotifyService spotifyService;

    private String title = "";
    private String message =  "";

    public AuthCommand() {
        this.loginRequired = false;
        spotifyService = new SpotifyService();
    }

    @Override
    public void handle() {

        System.out.println(spotifyService.getAuthorizationUrl());
        System.out.println("---SUCCESS---");
        System.out.println("{\"access_token\":\"BQBVgRSv7aduthd8lQF8uKN26W6c2KdqUst2Av1KH43q--BbOaP20oezCt-htyqoVdxVJ-FHauC3CVK-eJc1-6FeaVsZj_4SLgb99jzTKF4yduQ6GqNm-IhY-fY9LO4eX4F1tkfApcNtjdPdiUyJQ6vIdsw0OVXEiUdn\",\"token_type\":\"Bearer\",\"expires_in\":3600,\"refresh_token\":\"AQD1sLO8k2subPA5mzJUqosf1e8ZbTUZZktYaZzcTSM4ySl7W-bYt0zkgRqCiu1i6wJVAIY1F-tlqRbhPjU5q7EuM8ddtMsveGSwl22fiziEGQHTFHENapr1kcF4H0P-W0utwA\",\"scope\":\"\"}\n");
    }

}
