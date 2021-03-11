package com.unsplash.sdk.oauth.impl;

import com.unsplash.sdk.client.UnsplashClient;
import com.unsplash.sdk.oauth.UnsplashOAuthProvider;
import com.unsplash.sdk.oauth.config.OAuthConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.URL;

import static org.junit.Assert.assertNotNull;

/**
 * Tests the {@link UnsplashOAuthProvider} class methods.
 */
@RunWith(MockitoJUnitRunner.class)
public class UnsplashOAuthProviderImplTest {
    public static String BASE_URL = "https://example.unsplash.com";

    @Mock
    private OAuthConfig configuration;

    @Before
    public void setUp() throws Exception {
        Mockito.when(configuration.getOAuthBaseUrl()).thenReturn(new URL(BASE_URL));
    }

    @Test
    public void testGetClient() {
        final UnsplashOAuthProvider unsplashOAuthProvider = new UnsplashOAuthProviderImpl(configuration);

        assertNotNull(unsplashOAuthProvider);
        assertNotNull(unsplashOAuthProvider.getOAuthService());
    }
}
