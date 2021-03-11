package com.unsplash.sdk.service.collection;

import com.unsplash.sdk.model.Collection;
import com.unsplash.sdk.model.request.collection.AddPhotoToCollectionRequest;
import com.unsplash.sdk.model.request.collection.CreateCollectionRequest;
import com.unsplash.sdk.model.request.collection.GetCollectionRequest;
import retrofit2.Call;

import java.util.List;

/**
 * Interface to represent operations that can be done to the Unsplash Collections.
 */
public interface CollectionService {

    /**
     * Gets a list of collections using request information.
     *
     * @param getCollectionRequest Information to correctly paginate collections.
     * @return list of {@link Collection}.
     */
    Call<List<Collection>> getCollections(GetCollectionRequest getCollectionRequest);

    /**
     * Creates a collection.
     *
     * @param createCollectionRequest Information about the collection that needs to be created.
     * @return {@link Collection} created.
     */
    Call<Collection> createCollection(CreateCollectionRequest createCollectionRequest);

    /**
     * Adds a Photo to a collection.
     *
     * @param collectionAddPhotoQuery Information needed to add media to a collection.
     * @return
     */
    Call<Collection> addPhotoToCollection(AddPhotoToCollectionRequest collectionAddPhotoQuery);
}
