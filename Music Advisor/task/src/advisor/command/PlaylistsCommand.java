package advisor.command;

public class PlaylistsCommand extends Command{

//    static {
//        name = "/help";
//    }
//    protected static String name = "/help";

    private String categoryName;

    private String title = "---C_NAME PLAYLISTS---";
    private String message =  "Mountains [Sia, Diplo, Labrinth]\n" +
            "Runaway [Lil Peep]\n" +
            "The Greatest Show [Panic! At The Disco]\n" +
            "All Out Life [Slipknot]";


    public PlaylistsCommand(String parametersString) {
        this.categoryName = parametersString;
        title = title.replaceAll("C_NAME", this.categoryName.toUpperCase());
    }

    @Override
    public void handle() {

        System.out.println(title);
        System.out.println(message);
    }
}
