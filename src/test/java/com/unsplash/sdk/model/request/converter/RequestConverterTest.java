package com.unsplash.sdk.model.request.converter;

import com.unsplash.sdk.model.request.collection.AddPhotoToCollectionRequest;
import com.unsplash.sdk.model.request.collection.CreateCollectionRequest;
import com.unsplash.sdk.model.request.collection.GetCollectionRequest;
import com.unsplash.sdk.model.request.photo.GetPhotoRequest;
import com.unsplash.sdk.util.RequestConverterUtil;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RequestConverterTest {
    private static final RequestConverterUtil converter = new RequestConverterUtil();

    @Test
    public void convertGetCollectionRequest() {
        // Given
        final GetCollectionRequest collectionRequest = GetCollectionRequest.builder()
            .size(10)
            .page(20)
            .build();

        // When
        final Map<String, Object> map = converter.convert(collectionRequest);

        // Then
        assertEquals(map, Map.of("page", 20, "per_page", 10));
    }

    @Test
    public void convertGetPhotoRequest() {
        // Given
        final GetPhotoRequest request = GetPhotoRequest.builder()
            .size(10)
            .page(20)
            .orderBy(GetPhotoRequest.PhotoSortOrder.latest)
            .build();

        // When
        final Map<String, Object> map = converter.convert(request);

        // Then
        assertEquals(map, Map.of("page", 20, "per_page", 10, "order_by", "latest"));
    }

    @Test
    public void convertCreateCollectionRequest() {
        // Given
        final CreateCollectionRequest collectionRequest = CreateCollectionRequest.builder()
            .title("Collection")
            .description("desc")
            .isPrivate(true)
            .build();

        // When
        final Map<String, Object> map = converter.convert(collectionRequest);

        // Then
        assertEquals(map, Map.of("title", "Collection", "description", "desc", "private", true));
    }

    @Test
    public void convertPhotoToCollectionRequest() {
        // Given
        final AddPhotoToCollectionRequest collectionRequest = AddPhotoToCollectionRequest.builder()
            .collectionId("collection")
            .photoId("photo")
            .build();

        // When
        final Map<String, Object> map = converter.convert(collectionRequest);

        // Then
        assertEquals(map, Map.of("photo_id", "photo"));
    }
}
