package com.unsplash.sdk.service.collection.impl;

import com.unsplash.sdk.api.UnsplashApi;
import com.unsplash.sdk.model.ApiOperationType;
import com.unsplash.sdk.model.request.collection.AddPhotoToCollectionRequest;
import com.unsplash.sdk.model.request.collection.CreateCollectionRequest;
import com.unsplash.sdk.model.request.collection.GetCollectionRequest;
import com.unsplash.sdk.ratelimiter.RateLimitHandler;
import com.unsplash.sdk.service.collection.CollectionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Tests the {@link CollectionServiceImpl} class methods.
 */
@RunWith(MockitoJUnitRunner.class)
public final class CollectionServiceImplTest {

    private CollectionService collectionService;
    @Mock
    private UnsplashApi unsplashApi;
    @Mock
    private RateLimitHandler rateLimitHandler;

    @Before
    public void setUp() {
        collectionService = new CollectionServiceImpl(unsplashApi, rateLimitHandler);
    }

    @Test
    public void getCollections() {
        // Given
        final GetCollectionRequest collectionRequest = GetCollectionRequest.builder()
            .build();

        // When
        collectionService.getCollections(collectionRequest);

        // Then
        verify(rateLimitHandler).applyLimit(ApiOperationType.GET_COLLECTIONS);
        verify(unsplashApi).getCollections(anyMap());
    }

    @Test
    public void createCollection() {
        // Given
        final CreateCollectionRequest request = CreateCollectionRequest.builder().build();

        // When
        collectionService.createCollection(request);

        // Then
        verify(rateLimitHandler).applyLimit(ApiOperationType.CREATE_COLLECTION);
        verify(unsplashApi).createCollection(anyMap());
    }

    @Test
    public void addPhotoToCollection() {
        // Given
        final AddPhotoToCollectionRequest request = AddPhotoToCollectionRequest.builder()
            .collectionId("test-collection")
            .photoId("test-photo")
            .build();

        // When
        collectionService.addPhotoToCollection(request);

        // Then
        verify(rateLimitHandler).applyLimit(ApiOperationType.ADD_PHOTO_TO_COLLECTION);
        verify(unsplashApi).addPhotoToCollection(eq("test-collection"), anyMap());
    }
}
