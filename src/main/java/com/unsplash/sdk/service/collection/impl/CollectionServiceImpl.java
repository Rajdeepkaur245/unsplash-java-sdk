package com.unsplash.sdk.service.collection.impl;

import com.unsplash.sdk.api.UnsplashApi;
import com.unsplash.sdk.model.ApiOperationType;
import com.unsplash.sdk.model.Collection;
import com.unsplash.sdk.model.request.collection.AddPhotoToCollectionRequest;
import com.unsplash.sdk.model.request.collection.CreateCollectionRequest;
import com.unsplash.sdk.model.request.collection.GetCollectionRequest;
import com.unsplash.sdk.ratelimiter.RateLimitHandler;
import com.unsplash.sdk.service.collection.CollectionService;
import com.unsplash.sdk.util.RequestConverterUtil;
import retrofit2.Call;

import java.util.List;

/**
 * Implementation of {@link CollectionService}.
 */
public final class CollectionServiceImpl implements CollectionService {
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
    public CollectionServiceImpl(
        final UnsplashApi unsplashApi,
        final RateLimitHandler rateLimitHandler
    ) {
        this.unsplashApi = unsplashApi;
        this.rateLimitHandler = rateLimitHandler;
    }

    /**
     * Refer {@link CollectionService} for details.
     */
    @Override
    public Call<List<Collection>> getCollections(final GetCollectionRequest getCollectionRequest) {
        rateLimitHandler.applyLimit(ApiOperationType.GET_COLLECTIONS);
        return unsplashApi.getCollections(RequestConverterUtil.convert(getCollectionRequest));
    }

    /**
     * Refer {@link CollectionService} for details.
     */
    @Override
    public Call<Collection> createCollection(final CreateCollectionRequest createCollectionRequest) {
        rateLimitHandler.applyLimit(ApiOperationType.CREATE_COLLECTION);
        return unsplashApi.createCollection(RequestConverterUtil.convert(createCollectionRequest));
    }

    /**
     * Refer {@link CollectionService} for details.
     */
    @Override
    public Call<Collection> addPhotoToCollection(final AddPhotoToCollectionRequest request) {
        rateLimitHandler.applyLimit(ApiOperationType.ADD_PHOTO_TO_COLLECTION);
        return unsplashApi.addPhotoToCollection(request.getCollectionId(), RequestConverterUtil.convert(request));
    }
}
