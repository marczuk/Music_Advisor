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
    }

}
