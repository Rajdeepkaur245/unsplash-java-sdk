package com.unsplash.sdk.model.request.collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * Request to create collections.
 */
@Builder
@Getter
public class CreateCollectionRequest {
    /**
     * Title of the collection. (Required)
     */
    private String title;

    /**
     * Optional Description of the collection.
     */
    private String description;

    /**
     * Whether to make this collection private. (Optional; default false).
     */
    @JsonProperty("private")
    private Boolean isPrivate;
}
