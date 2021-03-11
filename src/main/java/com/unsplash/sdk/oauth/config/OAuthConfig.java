package com.unsplash.sdk.oauth.config;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * OAuth config.
 */
@Data
@Builder
public class OAuthConfig {
    /**
     * Unsplash OAuth base URL.
     */
    @NonNull
    private final URL oAuthBaseUrl;
    /**
     * OAuth application client id.
     */
    @NonNull
    private String clientId;
    /**
     * OAuth application client secret.
     */
    @NonNull
    private String clientSecret;
    /**
     * URI to redirect to after application has been authorized.
     */
    @NonNull
    private URI redirectUri;

    private static final String DEFAULT_OAUTH_BASE_URL = "https://unsplash.com/";

    /**
     * Returns OAuth base url.
     */
    public URL getOAuthBaseUrl() {
        try {
            return oAuthBaseUrl == null ? new URL(DEFAULT_OAUTH_BASE_URL) : oAuthBaseUrl;
        } catch (final MalformedURLException e) {
            throw new RuntimeException("Base Url is not in correct format.", e);
        }
    }
}
