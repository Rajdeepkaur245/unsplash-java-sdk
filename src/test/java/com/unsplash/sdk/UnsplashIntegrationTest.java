package com.unsplash.sdk;

import com.unsplash.sdk.client.UnsplashClient;
import com.unsplash.sdk.client.config.UnsplashClientConfig;
import com.unsplash.sdk.client.impl.UnsplashClientImpl;
import com.unsplash.sdk.model.ApiOperationType;
import com.unsplash.sdk.model.Collection;
import com.unsplash.sdk.model.Photo;
import com.unsplash.sdk.model.request.collection.AddPhotoToCollectionRequest;
import com.unsplash.sdk.model.request.collection.CreateCollectionRequest;
import com.unsplash.sdk.model.request.collection.GetCollectionRequest;
import com.unsplash.sdk.model.request.photo.GetPhotoRequest;
import okhttp3.ResponseBody;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public final class UnsplashIntegrationTest {
    private UnsplashClient unsplashClient;
    private UnsplashClientConfig configuration;

    private UnsplashClient unsplashClientWithAccessToken;
    private UnsplashClientConfig configurationWithAccessToken;

    private static final String CLIENT_ID = "ub3HGjOgF81LstNKCPLbsFvDLcz5sxuahKgSsYrjy-k";
    private static final String ACCESS_TOKEN = "-mOH7Xsrzm4DVTAih1lS3IbDC8J-ZceCZrmkU6A1N1A";
    private static final String BASE_URL = "https://api.unsplash.com/";
    private static final Map<ApiOperationType, Double> RATE_LIMIT_MAP = Map.of(
        ApiOperationType.GET_PHOTOS, 100.0
    );

    @Before
    public void setUp() throws Exception {
        configuration = UnsplashClientConfig.builder()
            .clientId(CLIENT_ID)
            .baseUrl(new URL(BASE_URL))
            .rateLimitMap(RATE_LIMIT_MAP)
            .build();
        unsplashClient = new UnsplashClientImpl(configuration);

        configurationWithAccessToken = UnsplashClientConfig.builder()
            .accessToken(ACCESS_TOKEN)
            .baseUrl(new URL(BASE_URL))
            .build();
        unsplashClientWithAccessToken = new UnsplashClientImpl(configurationWithAccessToken);
    }

    @Test
    public void getPhotos() throws IOException {
        // Given
        final GetPhotoRequest photoRequest = GetPhotoRequest.builder()
            .size(5)
            .page(1)
            .orderBy(GetPhotoRequest.PhotoSortOrder.latest)
            .build();

        // When
        final List<Photo> photos = unsplashClient.getPhotoService().getPhotos(photoRequest).execute().body();

        // Then
        assertEquals(photos.size(), 5);
    }

    @Test
    public void getPhotosWithNoParam() throws IOException {
        // Given
        final GetPhotoRequest photoRequest = GetPhotoRequest.builder().build();

        // When
        final List<Photo> photos = unsplashClient.getPhotoService().getPhotos(photoRequest).execute().body();

        // Then
        assertEquals(photos.size(), 10);
    }

    @Test
    public void getCollections() throws IOException {
        // Given
        final GetCollectionRequest collectionRequest = GetCollectionRequest.builder()
            .size(5)
            .page(1)
            .build();

        // When
        final List<Collection> collections = unsplashClient.getCollectionService().getCollections(collectionRequest).execute().body();

        // Then
        assertEquals(collections.size(), 5);
    }

    @Test
    public void getCollectionsWithNoParam() throws IOException {
        // Given
        final GetCollectionRequest collectionRequest = GetCollectionRequest.builder().build();

        // When
        final List<Collection> collections = unsplashClient.getCollectionService().getCollections(collectionRequest).execute().body();

        // Then
        assertEquals(collections.size(), 10);
    }

    @Test
    public void testCreateCollection() throws IOException {
        // Given
        final CreateCollectionRequest request1 = CreateCollectionRequest.builder().title("Test-Collection1").build();
        final CreateCollectionRequest request2 = CreateCollectionRequest.builder().title("Test-Collection2").build();
        final CreateCollectionRequest request3 = CreateCollectionRequest.builder().title("Test-Collection3").build();

        // When
        final Collection collection1 = unsplashClientWithAccessToken.getCollectionService().createCollection(request1).execute().body();
        final Collection collection2 = unsplashClientWithAccessToken.getCollectionService().createCollection(request2).execute().body();
        final Collection collection3 = unsplashClientWithAccessToken.getCollectionService().createCollection(request3).execute().body();

        // Then
        assertNotNull(collection1);
        assertNotNull(collection2);
        assertNotNull(collection3);
    }

    @Test
    public void testAddPhotoToCollection() throws IOException {
        // Given
        final AddPhotoToCollectionRequest request = AddPhotoToCollectionRequest.builder()
            .collectionId("63328031")
            .photoId("-f72wkYciMA")
            .build();

        // When
        final ResponseBody error = unsplashClientWithAccessToken.getCollectionService().addPhotoToCollection(request).execute().errorBody();

        // Then
        assertNull(error);
    }
}
