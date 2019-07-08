package advisor.command;

import advisor.object.PlaylistItem;
import advisor.object.PlaylistWrapper;
import advisor.request.FeaturedRequest;

import java.util.stream.Collectors;

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

        PlaylistWrapper wrapper = request.execute();
        String result = wrapper.getPlaylists().getItems()
                .stream()
                .map(PlaylistItem::toString)
                .collect(Collectors.joining(""));

        System.out.println(result);
    }
}
