package com.unsplash.sdk.service.photo.impl;

import com.unsplash.sdk.api.UnsplashApi;
import com.unsplash.sdk.model.ApiOperationType;
import com.unsplash.sdk.model.request.photo.GetPhotoRequest;
import com.unsplash.sdk.ratelimiter.RateLimitHandler;
import com.unsplash.sdk.service.photo.PhotoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.verify;

/**
 * Tests the {@link PhotoServiceImplTest} class methods.
 */
@RunWith(MockitoJUnitRunner.class)
public class PhotoServiceImplTest {
    private PhotoService photoService;
    @Mock
    private UnsplashApi unsplashApi;
    @Mock
    private RateLimitHandler rateLimitHandler;

    @Before
    public void setUp() {
        photoService = new PhotoServiceImpl(unsplashApi, rateLimitHandler);
    }

    @Test
    public void getPhotos() {
        // Given
        final GetPhotoRequest photoRequest = GetPhotoRequest.builder()
            .build();

        // When
        photoService.getPhotos(photoRequest);

        // Then
        verify(rateLimitHandler).applyLimit(ApiOperationType.GET_PHOTOS);
        verify(unsplashApi).getPhotos(anyMap());
    }
}

