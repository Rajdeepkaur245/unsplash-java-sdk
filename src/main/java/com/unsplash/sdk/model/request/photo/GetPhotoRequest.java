package com.unsplash.sdk.model.request.photo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetPhotoRequest {
    /**
     * Page number to retrieve. (Optional; default: 1).
     */
    private Integer page;
    /**
     * Number of items per page. (Optional; default: 10).
     */
    @JsonProperty("per_page")
    private Integer size;
    /**
     * Sort order for photos Optional. (Valid values: latest, oldest, popular; default: latest).
     */
    @JsonProperty("order_by")
    private PhotoSortOrder orderBy;

    public enum PhotoSortOrder {
        latest,
        oldest,
        popular
    }
}
