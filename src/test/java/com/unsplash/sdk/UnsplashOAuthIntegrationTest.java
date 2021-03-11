package com.unsplash.sdk;

import com.unsplash.sdk.oauth.UnsplashOAuthProvider;
import com.unsplash.sdk.oauth.config.OAuthConfig;
import com.unsplash.sdk.oauth.impl.UnsplashOAuthProviderImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class UnsplashOAuthIntegrationTest {
    private UnsplashOAuthProvider unsplashOAuthProvider;
    private OAuthConfig oAuthConfig;

    private static final String CLIENT_ID = "iYR83uR68rURoLl1MV4l0-AKGfqGVlO24I5vd3mervQ";
    private static final String CLIENT_SECRET = "74wI5kpdBvjmjKGWujBZdjNdvIfUkJA1cjHHdRGeUMM";
    private static final String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";
    private static final String OAUTH_BASE_URL = "https://unsplash.com/";

    @Before
    public void setUp() throws Exception {
        oAuthConfig = OAuthConfig.builder()
            .clientId(CLIENT_ID)
            .clientSecret(CLIENT_SECRET)
            .redirectUri(new URI(REDIRECT_URI))
            .oAuthBaseUrl(new URL(OAUTH_BASE_URL))
            .build();
        unsplashOAuthProvider = new UnsplashOAuthProviderImpl(oAuthConfig);
    }

    @Test
    public void testGetAuthorizationUrl() throws MalformedURLException, UnsupportedEncodingException {
        // When
        final URL authorizationUrl = unsplashOAuthProvider.getOAuthService().getAuthorizationUrl(List.of("write_collections"));

        // Then
        assertNotNull(authorizationUrl);
    }

    /*@Test
    public void testGenerateOAuthToken() {
        // When
        final OAuthToken oAuthToken = unsplashOAuthClient.getOAuthService()
            .generateAccessToken("f_Raa6r-EKzNSi1fseTFLbfILxTOquEIjilFFprTo6A")
            .blockingSingle();

        // Then
        assertNotNull(oAuthToken);
    }*/
}
