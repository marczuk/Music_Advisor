package advisor.command;

import advisor.request.FeaturedRequest;

public class FeaturedCommand extends Command{

//    static {
//        name = "/help";
//    }
//    protected static String name = "/help";

    private String title = "---FEATURED---";
    private String message = "Mellow Morning\n" +
            "Wake Up and Smell the Coffee\n" +
            "Monday Motivation\n" +
            "Songs to Sing in the Shower";

    @Override
    public void handle() {

        System.out.println(title);
//System.out.println(message);

        FeaturedRequest request = new FeaturedRequest();

        String result = request.execute();

        System.out.println(result);
    }
}
