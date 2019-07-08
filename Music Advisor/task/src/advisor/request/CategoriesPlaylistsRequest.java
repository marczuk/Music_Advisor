package advisor.request;

import advisor.object.CategoriesWrapper;
import advisor.object.PlaylistWrapper;
import advisor.service.SpotifyService;

import java.net.http.HttpResponse;
import java.util.Map;
import java.util.regex.Pattern;

public class CategoriesPlaylistsRequest extends AbstractRequest {

    private String categoryIdSubstring = "{category_id}";

    {
        path = SpotifyService.getDataUri() + "categories/"+categoryIdSubstring+"/playlists";
    }

    public CategoriesPlaylistsRequest(String categoryId) {
        super();
        path = path.replaceAll(Pattern.quote(categoryIdSubstring), categoryId);
    }

    public CategoriesPlaylistsRequest(String categoryId, Map<String, String> requestParams) {
        super(requestParams);
        path = path.replaceAll(Pattern.quote(categoryIdSubstring), categoryId);
    }

    @Override
    public PlaylistWrapper execute() {
        String result = null;

        try {
            System.out.println(path);
            HttpResponse<String> response = get();
            result = response.body();

        } catch (Exception e) {
            e.printStackTrace();
        }

        PlaylistWrapper wrapper = gson.fromJson(result,
                PlaylistWrapper.class);

        return wrapper;
    }
}
