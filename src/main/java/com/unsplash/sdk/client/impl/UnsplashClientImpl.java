package com.unsplash.sdk.client.impl;

import com.unsplash.sdk.api.UnsplashApi;
import com.unsplash.sdk.client.UnsplashClient;
import com.unsplash.sdk.client.config.UnsplashClientConfig;
import com.unsplash.sdk.ratelimiter.RateLimitHandler;
import com.unsplash.sdk.ratelimiter.impl.RateLimitHandlerImpl;
import com.unsplash.sdk.service.collection.CollectionService;
import com.unsplash.sdk.service.collection.impl.CollectionServiceImpl;
import com.unsplash.sdk.service.photo.PhotoService;
import com.unsplash.sdk.service.photo.impl.PhotoServiceImpl;

/**
 * Implementation of {@link UnsplashClient}.
 */
public class UnsplashClientImpl implements UnsplashClient {
    /**
     * Instance of {@link UnsplashApi} which handles the HTTP communication with the Unsplash API.
     */
    private final UnsplashApi unsplashApi;
    /**
     * Rate Limit Handler.
     */
    private final RateLimitHandler rateLimitHandler;
    /**
     * Configuration settings needed to instantiate the different interfaces
     * and services of the SDK client.
     */
    private UnsplashClientConfig configuration;
    /**
     * Instance of {@link CollectionService}.
     */
    private CollectionService collectionService;
    /**
     * Instance of {@link PhotoService}.
     */
    private PhotoService photoService;

    /**
     * Initialises a new instance of the class.
     *
     * @param configuration Configuration settings.
     */
    public UnsplashClientImpl(final UnsplashClientConfig configuration) {
        this.configuration = configuration;
        this.rateLimitHandler = new RateLimitHandlerImpl(configuration.getRateLimitMap());
        this.unsplashApi = UnsplashApi.getInstance(configuration);
    }

    /**
     * Check {@link UnsplashClient} for more information.
     */
    @Override
    public CollectionService getCollectionService() {
        if (collectionService == null) {
            collectionService = new CollectionServiceImpl(unsplashApi, rateLimitHandler);
        }
        return collectionService;
    }

    /**
     * Check {@link UnsplashClient} for more information.
     */
    @Override
    public PhotoService getPhotoService() {
        if (photoService == null) {
            photoService = new PhotoServiceImpl(unsplashApi, rateLimitHandler);
        }
        return photoService;
    }
}
