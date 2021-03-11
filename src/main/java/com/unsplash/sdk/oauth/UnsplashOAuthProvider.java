package com.unsplash.sdk.oauth;

import com.unsplash.sdk.service.oauth.OAuthService;

/**
 * Unsplash OAuth Provider.
 */
public interface UnsplashOAuthProvider {
    /**
     * Gets an instance of the oauth service to authorize the SDK instance with Unsplash.
     *
     * @return Instance of {@link OAuthService}.
     */
    OAuthService getOAuthService();
}
