package com.unsplash.sdk.api;

import com.unsplash.sdk.client.config.UnsplashClientConfig;
import com.unsplash.sdk.model.Collection;
import com.unsplash.sdk.model.Photo;
import com.unsplash.sdk.util.HttpClientBuilder;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

import java.util.List;
import java.util.Map;

/**
 * Interface of the Unsplash API to handle the HTTP communication.
 */
public interface UnsplashApi {

    /**
     * Gets list of the collections.
     *
     * @param params {@link QueryMap} with parameters.
     * @return {@link Call} with list of {@link Collection}.
     */
    @GET("/collections/")
    Call<List<Collection>> getCollections(@QueryMap Map<String, Object> params);

    /**
     * Creates a collection.
     *
     * @param params {@link FieldMap} with parameters.
     * @return {@link Call} with the {@link Collection}.
     */
    @FormUrlEncoded
    @POST("/collections/")
    Call<Collection> createCollection(@FieldMap Map<String, Object> params);


    /**
     * Adds a photo to a collection.
     *
     * @param collectionId Collection id to which we want to add photo.
     * @param params       {@link FieldMap} with parameters.
     * @return {@link Call} with the {@link Collection}.
     */
    @FormUrlEncoded
    @POST("/collections/{collection_id}/add/")
    Call<Collection> addPhotoToCollection(
        @Path("collection_id") String collectionId,
        @FieldMap Map<String, Object> params);

    /**
     * Gets list of the photos.
     *
     * @param params {@link QueryMap} with parameters.
     * @return {@link Call} with the list of {@link Photo}.
     */
    @GET("/photos/")
    Call<List<Photo>> getPhotos(@QueryMap Map<String, Object> params);

    /**
     * Creates an implementation of the Unsplash API endpoints.
     *
     * @param configuration {@link UnsplashClientConfig} settings for the HTTP communication with Unsplash.
     * @return Implementation instance of the {@link UnsplashApi} interface.
     */
    static UnsplashApi getInstance(final UnsplashClientConfig configuration) {
        return new Retrofit.Builder()
            .baseUrl(configuration.getBaseUrl().toString())
            .addConverterFactory(GsonConverterFactory.create())
            .client(HttpClientBuilder.build(configuration))
            .build()
            .create(UnsplashApi.class);
    }
}
