package com.unsplash.sdk.model;

import com.google.gson.annotations.SerializedName;
import lombok.Value;

/**
 * Tag source details.
 */
@Value
public class TagSource {
    /**
     * Tag ancestry.
     */
    TagAncestry ancestry;
    String title;
    /**
     * Tag type.
     */
    String subtitle;
    /**
     * Tag type.
     */
    String description;
    /**
     * Tag type.
     */
    @SerializedName(value = "meta_title")
    String metaTitle;
    /**
     * Tag type.
     */
    @SerializedName(value = "meta_description")
    String metaDescription;
    /**
     * Tag type.
     */
    @SerializedName(value = "cover_photo")
    Photo coverPhoto;
}
