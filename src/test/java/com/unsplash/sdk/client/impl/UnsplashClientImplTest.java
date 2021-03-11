package com.unsplash.sdk.client.impl;

import com.unsplash.sdk.client.UnsplashClient;
import com.unsplash.sdk.client.config.UnsplashClientConfig;
import com.unsplash.sdk.client.impl.UnsplashClientImpl;
import com.unsplash.sdk.exception.InvalidUnsplashConfigException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.URL;

import static org.junit.Assert.assertNotNull;

/**
 * Tests the {@link UnsplashClient} class methods.
 */
@RunWith(MockitoJUnitRunner.class)
public class UnsplashClientImplTest {
    private static final String BASE_URL = "https://example.unsplash.com";
    private static final String CLIENT_ID = "CLIENT_ID";
    private static final String ACCESS_TOKEN = "ACCESS_TOKEN";

    @Mock
    private UnsplashClientConfig configuration;

    @Before
    public void setUp() throws Exception {
        Mockito.when(configuration.getBaseUrl()).thenReturn(new URL(BASE_URL));
    }

    @Test
    public void testGetClientWithClientId() {
        Mockito.when(configuration.getClientId()).thenReturn(CLIENT_ID);

        final UnsplashClient unsplashClient = new UnsplashClientImpl(configuration);

        assertNotNull(unsplashClient);
        assertNotNull(unsplashClient.getCollectionService());
        assertNotNull(unsplashClient.getPhotoService());
    }

    @Test
    public void testGetClientWithAccessToken() {
        Mockito.when(configuration.getAccessToken()).thenReturn(ACCESS_TOKEN);

        final UnsplashClient unsplashClient = new UnsplashClientImpl(configuration);

        assertNotNull(unsplashClient);
        assertNotNull(unsplashClient.getCollectionService());
        assertNotNull(unsplashClient.getPhotoService());
    }

    @Test(expected = InvalidUnsplashConfigException.class)
    public void testGetClientNoAccessTokenAndClientId() {
        final UnsplashClient unsplashClient = new UnsplashClientImpl(configuration);

        assertNotNull(unsplashClient);
        assertNotNull(unsplashClient.getCollectionService());
        assertNotNull(unsplashClient.getPhotoService());
    }
}
