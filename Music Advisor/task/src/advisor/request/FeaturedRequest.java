package advisor.request;

import advisor.object.Item;
import advisor.object.PlaylistItem;
import advisor.object.PlaylistWrapper;
import advisor.service.SpotifyService;

import java.net.http.HttpResponse;
import java.util.Map;
import java.util.stream.Collectors;

public class FeaturedRequest extends AbstractRequest {

    {
        path = SpotifyService.getDataUri() + "featured-playlists";
    }

    public FeaturedRequest() {
        super();
    }

    public FeaturedRequest(Map<String, String> requestParams) {
        super(requestParams);
    }

    @Override
    public String execute() {
        String result = null;

        try {
            HttpResponse<String> response = get();
            result = response.body();

        } catch (Exception e) {
            e.printStackTrace();
        }

        PlaylistWrapper rw = gson.fromJson(result,
                PlaylistWrapper.class);

        return rw.getPlaylists().getItems()
                .stream()
                .map(PlaylistItem::toString)
                .collect(Collectors.joining(""));
    }
}
