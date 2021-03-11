package com.unsplash.sdk.model;

import com.google.gson.annotations.SerializedName;
import lombok.Value;

/**
 * Links Info.
 */
@Value
public class Links {
    /**
     * Link to resource.
     */
    String self;
    /**
     * HTML Link to resource.
     */
    String html;
    /**
     * Download Link to resource.
     */
    String download;
    /**
     * Download Location of resource.
     */
    @SerializedName(value = "download_location")
    String downloadLocation;
    /**
     * Photos Link.
     */
    String photos;
    /**
     * Link to likes.
     */
    String likes;
    /**
     * Link to see portfolio.
     */
    String portfolio;
    /**
     * Link to see following.
     */
    String following;
    /**
     * Link to see followers.
     */
    String followers;
    /**
     * Link to related content.
     */
    String related;
}
