package com.unsplash.sdk.ratelimiter.impl;

import com.google.common.util.concurrent.RateLimiter;
import com.unsplash.sdk.model.ApiOperationType;
import com.unsplash.sdk.ratelimiter.RateLimitHandler;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementaion of {@link RateLimitHandler}.
 */
public final class RateLimitHandlerImpl implements RateLimitHandler {
    private final Map<ApiOperationType, RateLimiter> rateLimiterMap;

    /**
     * Initialises a new instance of the class.
     *
     * @param limiterMap Rate Limit Map for each operation type.
     */
    public RateLimitHandlerImpl(final Map<ApiOperationType, Double> limiterMap) {
        this.rateLimiterMap = Optional.ofNullable(limiterMap)
            .map(limitMap -> limitMap.entrySet()
                .stream()
                .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    entry -> RateLimiter.create(entry.getValue()))))
            .orElse(Map.of());
    }

    @Override
    public boolean applyLimit(final ApiOperationType apiOperationType) {
        // If configuration does not exists, allowing user to call.
        if (rateLimiterMap.containsKey(apiOperationType)) {
            final RateLimiter rateLimiter = rateLimiterMap.get(apiOperationType);
            if (!rateLimiter.tryAcquire()) {
                throw new RuntimeException("Too many requests being processed.");
            }
        }
        return true;
    }
}
