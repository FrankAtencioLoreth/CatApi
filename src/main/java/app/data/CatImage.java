package app.data;

import com.google.gson.annotations.SerializedName;

public class CatImage {
    @SerializedName("id")
    private String id;
    @SerializedName("url")
    private String url;

    public CatImage() {}

    public CatImage(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "CatImage{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
