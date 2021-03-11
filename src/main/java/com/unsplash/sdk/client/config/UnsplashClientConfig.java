package com.unsplash.sdk.client.config;

import com.unsplash.sdk.model.ApiOperationType;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import okhttp3.logging.HttpLoggingInterceptor;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;

/**
 * Unsplash Client config.
 */
@Data
@Builder
public class UnsplashClientConfig {
    /**
     * Unsplash portal base URL.
     */
    @NonNull
    private final URL baseUrl;
    /**
     * Permanent Access Token used for testing.
     */
    private final String accessToken;
    /**
     * OAuth application client id.
     */
    private final String clientId;
    /**
     * Rate Limit configuration for each operation type.
     */
    private final Map<ApiOperationType, Double> rateLimitMap;
    /**
     * Read timeout duration.
     */
    private final Duration readTimeout;
    /**
     * Write timeout duration.
     */
    private final Duration writeTimeout;
    /**
     * Connect timeout duration.
     */
    private final Duration connectTimeout;
    /**
     * Retry connection in case it fails the first time.
     */
    private final Boolean retryOnConnectionFailure;
    /**
     * Whether or not to enable a {@link HttpLoggingInterceptor} on the HTTP client used,
     * Default true.
     */
    private final Boolean loggingInterceptorEnabled;

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(30);
    private static final String DEFAULT_BASE_URL = "https://api.unsplash.com/";

    /**
     * Returns base url for Unsplash portal.
     */
    public URL getBaseUrl() {
        try {
            return baseUrl == null ? new URL(DEFAULT_BASE_URL) : baseUrl;
        } catch (final MalformedURLException e) {
            throw new RuntimeException("Base Url is not in correct format.", e);
        }
    }

    /**
     * Returns read timeout duration, defaults to 30 seconds.
     */
    public Duration getReadTimeout() {
        return this.readTimeout == null ? DEFAULT_TIMEOUT : this.readTimeout;
    }

    /**
     * Returns read timeout duration, defaults to 30 seconds.
     */
    public Duration getWriteTimeout() {
        return this.writeTimeout == null ? DEFAULT_TIMEOUT : this.writeTimeout;
    }

    /**
     * Returns read timeout duration, defaults to 30 seconds.
     */
    public Duration getConnectTimeout() {
        return this.connectTimeout == null ? DEFAULT_TIMEOUT : this.connectTimeout;
    }

    /**
     * Returns if retry on connection failure, defaults to true.
     */
    public Boolean isRetryOnConnectionFailure() {
        return this.retryOnConnectionFailure == null ? true : this.retryOnConnectionFailure;
    }

    /**
     * Returns if logging interceptor is enabled, defaults to false.
     */
    public Boolean isLoggingInterceptorEnabled() {
        return this.loggingInterceptorEnabled == null ? false : this.loggingInterceptorEnabled;
    }
}
