package com.unsplash.sdk.service.oauth.impl;

import com.unsplash.sdk.api.UnsplashOAuthApi;
import com.unsplash.sdk.exception.InvalidUnsplashConfigException;
import com.unsplash.sdk.model.OAuthToken;
import com.unsplash.sdk.model.request.oauth.OAuthTokenRequest;
import com.unsplash.sdk.oauth.config.OAuthConfig;
import com.unsplash.sdk.service.oauth.OAuthService;
import com.unsplash.sdk.util.RequestConverterUtil;
import retrofit2.Call;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public final class OAuthServiceImpl implements OAuthService {
    /**
     * Instance of {@link UnsplashOAuthApi} which handles the HTTP communication with the Unsplash OAuth API.
     */
    private final UnsplashOAuthApi unsplashOAuthApi;
    /**
     * Refer {@link OAuthConfig} for details.
     */
    private OAuthConfig configuration;

    private static final String GRANT_TYPE = "authorization_code";
    private static final String CODE_RESPONSE_TYPE = "code";

    /**
     * Initialises a new instance of the class.
     *
     * @param unsplashOAuthApi Instance to handle the HTTP communication with the Unsplash OAuth API.
     * @param configuration    OAuth Configuration.
     */
    public OAuthServiceImpl(final UnsplashOAuthApi unsplashOAuthApi, final OAuthConfig configuration) {
        this.unsplashOAuthApi = unsplashOAuthApi;
        this.configuration = configuration;
    }

    /**
     * Refer {@link OAuthService} for details.
     */
    @Override
    public URL getAuthorizationUrl(final List<String> scopes) throws MalformedURLException, UnsupportedEncodingException, IllegalArgumentException {
        validateOAuthConfiguration(configuration);

        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(configuration.getOAuthBaseUrl());
        stringBuilder.append("oauth/authorize");
        stringBuilder.append("?client_id=").append(encode(configuration.getClientId()));
        stringBuilder.append("&redirect_uri=")
            .append(encode(configuration.getRedirectUri().toString()));
        stringBuilder.append("&response_type=").append(encode(CODE_RESPONSE_TYPE));
        stringBuilder.append("&scope=").append(encode(String.join(" ", scopes)));

        return new URL(stringBuilder.toString());
    }

    /**
     * Refer {@link OAuthService} for details.
     */
    @Override
    public Call<OAuthToken> generateAccessToken(final String authorizationCode) {
        validateOAuthConfiguration(configuration);

        final OAuthTokenRequest tokenRequest = new OAuthTokenRequest(
            configuration.getClientId(),
            configuration.getClientSecret(),
            configuration.getRedirectUri().toString(),
            authorizationCode,
            GRANT_TYPE);

        return unsplashOAuthApi.generateAccessToken(RequestConverterUtil.convert(tokenRequest));
    }

    private void validateOAuthConfiguration(final OAuthConfig oAuthConfig) {
        if (oAuthConfig == null) {
            throw new InvalidUnsplashConfigException("OAuth configuration are not provided.");
        }
        if (oAuthConfig.getClientId() == null || oAuthConfig.getClientSecret() == null
            || oAuthConfig.getRedirectUri() == null || oAuthConfig.getOAuthBaseUrl() == null) {
            throw new InvalidUnsplashConfigException("Client id, client secret and redirect uri are mandatory");
        }
    }

    private String encode(final String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, StandardCharsets.UTF_8.name());
    }
}
