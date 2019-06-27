package advisor.command;

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
        System.out.println(message);
    }
}
