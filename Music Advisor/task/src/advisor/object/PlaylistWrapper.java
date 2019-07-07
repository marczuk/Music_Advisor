package advisor.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaylistWrapper {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("playlists")
    @Expose
    private Playlists playlists;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Playlists getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Playlists playlists) {
        this.playlists = playlists;
    }

}
