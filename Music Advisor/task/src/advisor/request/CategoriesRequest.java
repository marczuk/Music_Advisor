package advisor.request;

import advisor.object.CategoriesItem;
import advisor.object.CategoriesWrapper;
import advisor.object.PlaylistItem;
import advisor.object.PlaylistWrapper;
import advisor.service.SpotifyService;

import java.net.http.HttpResponse;
import java.util.Map;
import java.util.stream.Collectors;

public class CategoriesRequest extends AbstractRequest {

    {
        path = SpotifyService.getDataUri() + "categories";
    }

    public CategoriesRequest() {
        super();
    }

    public CategoriesRequest(Map<String, String> requestParams) {
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

        CategoriesWrapper rw = gson.fromJson(result,
                CategoriesWrapper.class);

        return rw.getCategories().getItems()
                .stream()
                .map(CategoriesItem::toString)
                .collect(Collectors.joining(""));
    }
}
