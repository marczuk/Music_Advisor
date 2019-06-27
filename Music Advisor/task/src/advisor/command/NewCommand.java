package advisor.command;

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
        System.out.println(message);
    }
}
