package com.unsplash.sdk.model;

import com.google.gson.annotations.SerializedName;
import lombok.Value;

/**
 * Tag ancestry detail.
 */
@Value
public class TagAncestry {
    /**
     * Tag ancestry type.
     */
    TagAncestryDetail type;
    /**
     * Tag ancestry category.
     */
    TagAncestryDetail category;
    /**
     * Tag ancestry subcategory.
     */
    TagAncestryDetail subcategory;

    @Value
    public static class TagAncestryDetail {
        /**
         * Tag ancestry slug.
         */
        String slug;
        /**
         * Tag ancestry pretty slug.
         */
        @SerializedName(value = "pretty_slug")
        String prettySlug;
    }

}
