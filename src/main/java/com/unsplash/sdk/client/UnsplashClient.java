package com.unsplash.sdk.client;

import com.unsplash.sdk.service.collection.CollectionService;
import com.unsplash.sdk.service.photo.PhotoService;

/**
 * Unsplash client interface.
 */
public interface UnsplashClient {

    /**
     * Gets an instance of the collection service to interact with the collections in Unsplash.
     *
     * @return Instance of {@link CollectionService}.
     */
    CollectionService getCollectionService();

    /**
     * Gets an instance of the photo service to interact with the photos in Unsplash.
     *
     * @return Instance of {@link PhotoService}.
     */
    PhotoService getPhotoService();
}
