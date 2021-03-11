package com.unsplash.sdk.model;

import com.google.gson.annotations.SerializedName;
import lombok.Value;

@Value
public class Photo {
    /**
     * Photo id.
     */
    String id;
    /**
     * Photo description.
     */
    String description;
    /**
     * Photo width.
     */
    Integer width;
    /**
     * Photo height.
     */
    Integer height;
    /**
     * Photo color.
     */
    String color;
    /**
     * Photo blur hash.
     */
    @SerializedName(value = "blur_hash")
    String blurHash;
    /**
     * Photo downloads.
     */
    Integer downloads;
    /**
     * Photo likes.
     */
    Integer likes;
    /**
     * Photo liked by user.
     */
    @SerializedName(value = "liked_by_user")
    Boolean isLikedByUser;
    /**
     * Date created.
     */
    @SerializedName(value = "created_at")
    String dateCreated;
    /**
     * Date updated.
     */
    @SerializedName(value = "updated_at")
    String dateUpdated;
    /**
     * Photo Location.
     */
    @SerializedName(value = "location")
    Location location;
}
