package com.unsplash.sdk.util;

import com.unsplash.sdk.client.config.UnsplashClientConfig;
import com.unsplash.sdk.exception.InvalidUnsplashConfigException;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

/**
 * Builder to create OkHttpClient.
 */
public class HttpClientBuilder {

    private static final String CLIENT_ID_TOKEN_TYPE = "Client-ID";
    private static final String BEARER_TOKEN_TYPE = "Bearer";

    /**
     * Creates an instance of {@link OkHttpClient}.
     *
     * @param configuration Configuration settings for the HTTP communication with Unsplash.
     * @return {@link OkHttpClient} instance used for API requests.
     */
    public static OkHttpClient build(final UnsplashClientConfig configuration) {
        final OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        if (configuration.getAccessToken() != null) {
            setHeaderTokenInterceptor(httpClientBuilder, BEARER_TOKEN_TYPE, configuration.getAccessToken());
        } else if (configuration.getClientId() != null) {
            setHeaderTokenInterceptor(httpClientBuilder, CLIENT_ID_TOKEN_TYPE, configuration.getClientId());
        } else {
            throw new InvalidUnsplashConfigException("Either client id or access token is mandatory.");
        }

        setHttpConnectionSettings(httpClientBuilder, configuration);
        return httpClientBuilder.build();
    }

    /**
     * Sets the token in header interceptor for the HTTP client.
     *
     * @param httpClientBuilder Builder instance of the HTTP client.
     * @param tokenType         Type of token - either Client-ID or Bearer
     * @param token             Token value.
     */
    private static void setHeaderTokenInterceptor(
        final OkHttpClient.Builder httpClientBuilder,
        final String tokenType,
        final String token
    ) {
        httpClientBuilder.addInterceptor(chain ->
            chain.proceed(chain.request().newBuilder()
                .header("Authorization", String.format("%s %s", tokenType, token))
                .build()));
    }

    /**
     * Sets the HTTP connection settings for the HTTP client.
     *
     * @param httpClientBuilder    Builder instance of the HTTP client.
     * @param unsplashClientConfig Configuration settings for the HTTP communication with Unsplash.
     */
    private static void setHttpConnectionSettings(
        final OkHttpClient.Builder httpClientBuilder,
        final UnsplashClientConfig unsplashClientConfig
    ) {
        if (unsplashClientConfig.isLoggingInterceptorEnabled()) {
            httpClientBuilder.addInterceptor(
                new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            );
        }

        httpClientBuilder
            .retryOnConnectionFailure(unsplashClientConfig.isRetryOnConnectionFailure())
            .readTimeout(unsplashClientConfig.getReadTimeout().toSeconds(), TimeUnit.SECONDS)
            .connectTimeout(unsplashClientConfig.getConnectTimeout().toSeconds(), TimeUnit.SECONDS)
            .writeTimeout(unsplashClientConfig.getWriteTimeout().toSeconds(), TimeUnit.SECONDS);
    }
}
