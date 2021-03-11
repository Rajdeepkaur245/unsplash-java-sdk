package com.unsplash.sdk.service.oauth.impl;

import com.unsplash.sdk.api.UnsplashOAuthApi;
import com.unsplash.sdk.exception.InvalidUnsplashConfigException;
import com.unsplash.sdk.oauth.config.OAuthConfig;
import com.unsplash.sdk.service.oauth.OAuthService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests the {@link OAuthServiceImpl} class methods.
 */
@RunWith(MockitoJUnitRunner.class)
public final class OAuthServiceImplTest {
    @Mock
    private UnsplashOAuthApi oAuthApi;
    @Mock
    private OAuthConfig oAuthConfig;

    private OAuthService oAuthService;

    private static final String CLIENT_ID = "clientId";
    private static final String CLIENT_SECRET = "clientSecret";
    private static final String OAUTH_BASE_URL = "https://unsplash.com/";
    private static final String REDIRECT_URI = "https://localhost/callback";
    private static final String AUTH_CODE = "authcode";
    private static final List<String> SCOPES = Arrays.asList("scope");

    @Before
    public void setUp() throws Exception {
        when(oAuthConfig.getClientId()).thenReturn(CLIENT_ID);
        when(oAuthConfig.getOAuthBaseUrl()).thenReturn(new URL(OAUTH_BASE_URL));
        when(oAuthConfig.getRedirectUri()).thenReturn(new URI(REDIRECT_URI));
        when(oAuthConfig.getClientSecret()).thenReturn(CLIENT_SECRET);

        oAuthService = new OAuthServiceImpl(oAuthApi, oAuthConfig);
    }

    @Test
    public void getAuthorizationUrl() throws Exception {
        final URL authorizationUrl = oAuthService.getAuthorizationUrl(SCOPES);

        assertNotNull(authorizationUrl);
        assertEquals(new URL(OAUTH_BASE_URL).getHost(), authorizationUrl.getHost());
        assertTrue(authorizationUrl.toString().contains(CLIENT_ID));
        assertTrue(authorizationUrl.toString().contains(encode(REDIRECT_URI)));
    }

    @Test
    public void generateAccessToken() {
        oAuthService.generateAccessToken(AUTH_CODE);

        verify(oAuthApi).generateAccessToken(anyMap());
    }

    @Test(expected = InvalidUnsplashConfigException.class)
    public void getAuthorizationUrlWhenClientIdIsNull() throws Exception {
        when(oAuthConfig.getClientId()).thenReturn(null);

        oAuthService.getAuthorizationUrl(SCOPES);
    }

    @Test(expected = InvalidUnsplashConfigException.class)
    public void getAuthorizationUrlWhenSecretIsNull() throws Exception {
        when(oAuthConfig.getClientSecret()).thenReturn(null);

        oAuthService.getAuthorizationUrl(SCOPES);
    }

    @Test(expected = InvalidUnsplashConfigException.class)
    public void getAuthorizationUrlWhenRedirectUriIsNull() throws Exception {
        when(oAuthConfig.getRedirectUri()).thenReturn(null);

        oAuthService.getAuthorizationUrl(SCOPES);
    }

    @Test(expected = InvalidUnsplashConfigException.class)
    public void getAuthorizationUrlWhenOauthUriIsNull() throws Exception {
        when(oAuthConfig.getOAuthBaseUrl()).thenReturn(null);

        oAuthService.getAuthorizationUrl(SCOPES);
    }

    private String encode(final String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, StandardCharsets.UTF_8.name());
    }
}
