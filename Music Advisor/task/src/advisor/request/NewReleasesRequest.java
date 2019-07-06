package advisor.request;

import advisor.object.ResponseWrapper;
import advisor.service.SpotifyService;

import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Base64;
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

        //TODO::create object with releases data
        ResponseWrapper rw = gson.fromJson(result,
                ResponseWrapper.class);
        return rw.getAlbums().getItems()
                .stream()
                .map(n -> n.toString())
                .collect(Collectors.joining(""));
    }
}
