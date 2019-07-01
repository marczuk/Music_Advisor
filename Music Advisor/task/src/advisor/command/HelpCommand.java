package advisor.command;

public class HelpCommand extends Command{

//    static {
//        name = "/help";
//    }
//    protected static String name = "/help";

    public HelpCommand() {
        this.loginRequired = false;
    }

    private String helpMessage = "The program is connecting to spotify API to get info about your favourite music"
            + "\navailable commands: auth | help | playlists | new | featured | categories | exit";

    @Override
    public void handle() {
        System.out.println(helpMessage);
    }
}
