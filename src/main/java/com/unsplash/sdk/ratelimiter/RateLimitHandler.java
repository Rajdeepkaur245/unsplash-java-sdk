package com.unsplash.sdk.ratelimiter;

import com.unsplash.sdk.model.ApiOperationType;

/**
 * Rate Limit Handler for API calls.
 */
public interface RateLimitHandler {
    /**
     * Apply rate limit for operation type invoked by client.
     *
     * @param apiOperationType API operation type client is invoking.
     * @return true if client can invoke API.
     */
    boolean applyLimit(ApiOperationType apiOperationType);
}
