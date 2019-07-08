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
            + "\navailable commands: \n" +
            "help - show this message with description of commands\n" +
            "featured - list of Spotify featured playlists with their links fetched from API;\n" +
            "new - list of new albums with artists and links on Spotify;\n" +
            "categories - list of all available categories on Spotify (just their names);\n" +
            "playlists C_NAME,  where C_NAME – name of category. List contains playlists of this category and their links on Spotify;\n" +
            "exit - shut down application";

    @Override
    public void handle() {
        System.out.println(helpMessage);
    }
}
