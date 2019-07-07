package advisor.object;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoriesItem {

    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("icons")
    @Expose
    private List<Image> icons = null;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<Image> getIcons() {
        return icons;
    }

    public void setIcons(List<Image> icons) {
        this.icons = icons;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return new StringBuilder()
                .append(getName())
                .append("\n").toString();
    }
}
