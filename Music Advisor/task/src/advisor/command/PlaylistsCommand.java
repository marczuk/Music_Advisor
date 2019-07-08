package advisor.command;

import advisor.object.PlaylistItem;
import advisor.object.PlaylistWrapper;
import advisor.request.CategoriesPlaylistsRequest;

import java.util.stream.Collectors;

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

        //System.out.println(title);

        //get id
        String categoryId = CategoriesCommand.getCategoryIdFromLastCategoryWrapper(categoryName);

        if (categoryId != null) {
            CategoriesPlaylistsRequest request = new CategoriesPlaylistsRequest(categoryId);

            System.out.println("before execute");

            PlaylistWrapper wrapper = request.execute();

            String result = wrapper.getPlaylists().getItems()
                    .stream()
                    .map(PlaylistItem::toString)
                    .collect(Collectors.joining(""));

            System.out.println(result);

        }
        else {
            System.out.println("There is no such category on current category page : [" + categoryName + "]");
        }



    }
}
