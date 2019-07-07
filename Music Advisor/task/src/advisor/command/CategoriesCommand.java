package advisor.command;

import advisor.request.CategoriesRequest;
import advisor.request.FeaturedRequest;

public class CategoriesCommand extends Command{

//    static {
//        name = "/help";
//    }
//    protected static String name = "/help";

    private String title = "---CATEGORIES---";
    private String message =  "Top Lists\n" +
            "Pop\n" +
            "Mood\n" +
            "Latin";

    @Override
    public void handle() {

        System.out.println(title);
        //System.out.println(message);

        CategoriesRequest request = new CategoriesRequest();

        String result = request.execute();

        System.out.println(result);
    }
}
