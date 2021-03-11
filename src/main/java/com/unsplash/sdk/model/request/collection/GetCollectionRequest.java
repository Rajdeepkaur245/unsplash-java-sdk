package com.unsplash.sdk.model.request.collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * Query to filter collection results.
 */
@Builder
@Getter
public class GetCollectionRequest {
    /**
     * Page number to retrieve. (Optional; default: 1).
     */
    private Integer page;
    /**
     * Number of items per page. (Optional; default: 10).
     */
    @JsonProperty("per_page")
    private Integer size;
}
