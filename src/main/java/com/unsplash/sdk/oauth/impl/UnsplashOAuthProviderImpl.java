package com.unsplash.sdk.oauth.impl;

import com.unsplash.sdk.api.UnsplashOAuthApi;
import com.unsplash.sdk.oauth.UnsplashOAuthProvider;
import com.unsplash.sdk.oauth.config.OAuthConfig;
import com.unsplash.sdk.service.oauth.OAuthService;
import com.unsplash.sdk.service.oauth.impl.OAuthServiceImpl;

/**
 * Implementation of {@link UnsplashOAuthProvider}
 */
public final class UnsplashOAuthProviderImpl implements UnsplashOAuthProvider {
    /**
     * Instance of {@link UnsplashOAuthApi} which handles the HTTP communication with the OAuth2 API.
     */
    private final UnsplashOAuthApi oAuthApi;
    /**
     * OAuth Configuration settings needed to authenticate.
     */
    private OAuthConfig configuration;
    /**
     * Instance of {@link OAuthService}.
     */
    private OAuthService oAuthService;

    /**
     * Initialises a new instance of the class.
     *
     * @param configuration Configuration settings.
     */
    public UnsplashOAuthProviderImpl(final OAuthConfig configuration) {
        this.configuration = configuration;
        this.oAuthApi = UnsplashOAuthApi.getInstance(configuration);
    }

    @Override
    public OAuthService getOAuthService() {
        if (oAuthService == null) {
            oAuthService = new OAuthServiceImpl(oAuthApi, configuration);
        }
        return oAuthService;
    }
}
