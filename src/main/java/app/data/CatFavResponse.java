package app.data;

import com.google.gson.annotations.SerializedName;

import java.awt.*;

public class CatFavResponse {

    @SerializedName("id")
    private String id;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("image_id")
    private String idImage;
    @SerializedName("sub_id")
    private String subId;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("image")
    private CatImage image;

    public CatFavResponse() {}

    public CatFavResponse(String id, String userId, String idImage, String subId, String createdAt, CatImage image) {
        this.id = id;
        this.userId = userId;
        this.idImage = idImage;
        this.subId = subId;
        this.createdAt = createdAt;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public CatImage getImage() {
        return image;
    }

    public void setImage(CatImage image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "CatFavResponse{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", idImage='" + idImage + '\'' +
                ", subId='" + subId + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", image=" + image +
                '}';
    }
}
