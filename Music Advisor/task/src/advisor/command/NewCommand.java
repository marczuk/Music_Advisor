package advisor.command;

import advisor.request.NewReleasesRequest;

public class NewCommand extends Command{

//    static {
//        name = "/help";
//    }
//    protected static String name = "/help";

    private String title = "---NEW RELEASES---";
    private String message = "Mountains [Sia, Diplo, Labrinth]\n" +
            "Runaway [Lil Peep]\n" +
            "The Greatest Show [Panic! At The Disco]\n" +
            "All Out Life [Slipknot]";

    @Override
    public void handle() {

        System.out.println(title);
        //System.out.println(message);

        NewReleasesRequest newReleasesRequest = new NewReleasesRequest();

        String result = newReleasesRequest.execute();

        System.out.println(result);
    }
}
