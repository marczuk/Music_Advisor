package advisor.command;

public class HelpCommand extends Command{

//    static {
//        name = "/help";
//    }
//    protected static String name = "/help";

    public HelpCommand() {
        this.loginRequired = false;
    }

    private String helpMessage = "The program is connecting to spotify API to get info about your favourite music";

    @Override
    public void handle() {
        System.out.println(helpMessage);
    }
}
