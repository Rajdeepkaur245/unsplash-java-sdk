package com.unsplash.sdk.model.request.collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * Request to add a photo to a collection.
 */
@Builder
@Getter
public class AddPhotoToCollectionRequest {

    /**
     * Id of the collection to which we want to add photo.
     */
    @JsonIgnore
    String collectionId;

    /**
     * Id of the photo to be added.
     */
    @JsonProperty("photo_id")
    String photoId;
}
