package com.unsplash.sdk.model;

import com.google.gson.annotations.SerializedName;
import com.unsplash.sdk.api.UnsplashApi;
import lombok.Value;

import java.util.Date;
import java.util.List;

/**
 * Collection model returned by {@link UnsplashApi}
 */
@Value
public class Collection {
    /**
     * Collection id.
     */
    String id;
    /**
     * Collection title.
     */
    String title;
    /**
     * Collection description.
     */
    String description;
    /**
     * Date published.
     */
    @SerializedName(value = "published_at")
    Date datePublished;
    /**
     * Date last collected at.
     */
    @SerializedName(value = "last_collected_at")
    Date dateLastCollectedAt;
    /**
     * Date updated at.
     */
    @SerializedName(value = "updated_at")
    Date dateUpdatedAt;
    /**
     * Collection curated.
     */
    Boolean curated;
    /**
     * Collection featured.
     */
    Boolean featured;
    /**
     * Number of photos in the collection.
     */
    @SerializedName(value = "total_photos")
    int totalPhotos;
    /**
     * Collection private.
     */
    @SerializedName(value = "private")
    Boolean isPrivate;
    /**
     * Collection shareKey.
     */
    @SerializedName(value = "share_key")
    String shareKey;
    /**
     * Collection Tags.
     */
    List<Tag> tags;
    /**
     * Collection Links.
     */
    Links links;
    /**
     * Collection owner.
     */
    User user;
    /**
     * Cover photo of collection.
     */
    @SerializedName(value = "cover_photo")
    Photo coverPhoto;
    /**
     * Preview photos of collection.
     */
    @SerializedName(value = "preview_photos")
    List<Photo> previewPhotos;
}
