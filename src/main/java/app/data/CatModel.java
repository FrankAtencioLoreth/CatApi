package app.data;

public class CatModel {

    private String id;
    private String url;
    private Integer width;
    private Integer height;
    public static final String API_KEY = "live_vmdBw8R2HYTk60awPfYxMA7Wh5NTnqJhHxkWjWPxUuXJXvwYRwx1OHTBh0i4t7pT";

    public CatModel(String id, String url, Integer width, Integer height) {
        this.id = id;
        this.url = url;
        this.width = width;
        this.height = height;
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

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "CatsModel{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
