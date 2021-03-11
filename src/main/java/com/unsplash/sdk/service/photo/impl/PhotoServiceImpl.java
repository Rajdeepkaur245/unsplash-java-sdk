package com.unsplash.sdk.service.photo.impl;

import com.unsplash.sdk.api.UnsplashApi;
import com.unsplash.sdk.model.ApiOperationType;
import com.unsplash.sdk.model.Photo;
import com.unsplash.sdk.model.request.photo.GetPhotoRequest;
import com.unsplash.sdk.ratelimiter.RateLimitHandler;
import com.unsplash.sdk.service.photo.PhotoService;
import com.unsplash.sdk.util.RequestConverterUtil;
import retrofit2.Call;

import java.util.List;

public final class PhotoServiceImpl implements PhotoService {
    /**
     * Instance of {@link UnsplashApi} which handles the HTTP communication with the Unsplash API.
     */
    private final UnsplashApi unsplashApi;
    /**
     * Rate Limit Handler for handling throttle per operation.
     */
    private final RateLimitHandler rateLimitHandler;

    /**
     * Initialises a new instance of the class.
     *
     * @param unsplashApi      Instance to handle the HTTP communication with the Unsplash API.
     * @param rateLimitHandler Rate Limit Handler.
     */
    public PhotoServiceImpl(
        final UnsplashApi unsplashApi,
        final RateLimitHandler rateLimitHandler
    ) {
        this.unsplashApi = unsplashApi;
        this.rateLimitHandler = rateLimitHandler;
    }

    /**
     * Refer {@link PhotoService} for details.
     */
    @Override
    public Call<List<Photo>> getPhotos(final GetPhotoRequest getPhotoRequest) {
        rateLimitHandler.applyLimit(ApiOperationType.GET_PHOTOS);
        return unsplashApi.getPhotos(RequestConverterUtil.convert(getPhotoRequest));
    }
}
