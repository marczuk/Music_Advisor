package advisor.request;

import advisor.object.AlbumsWrapper;
import advisor.object.Item;
import advisor.service.SpotifyService;

import java.net.http.HttpResponse;
import java.util.Map;
import java.util.stream.Collectors;

public class NewReleasesRequest extends AbstractRequest {

    {
        path = SpotifyService.getDataUri() + "new-releases";
    }

    public NewReleasesRequest() {
        super();
    }

    public NewReleasesRequest(Map<String, String> requestParams) {
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

        AlbumsWrapper rw = gson.fromJson(result,
                AlbumsWrapper.class);

        return rw.getAlbums().getItems()
                .stream()
                .map(Item::toString)
                .collect(Collectors.joining(""));
    }
}
