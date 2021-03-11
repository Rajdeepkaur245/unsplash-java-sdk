package com.unsplash.sdk.ratelimiter.impl;

import com.unsplash.sdk.model.ApiOperationType;
import com.unsplash.sdk.ratelimiter.RateLimitHandler;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public final class RateLimitHandlerImplTest {
    @Test
    public void testInitWhenMapIsNull() {
        // Given
        final RateLimitHandler rateLimitHandler = new RateLimitHandlerImpl(null);

        // When
        final boolean response = rateLimitHandler.applyLimit(ApiOperationType.GET_PHOTOS);

        // Then
        assertEquals(response, true);
    }

    @Test
    public void testWhenRateLimitNotFoundForOperation() {
        // Given
        final RateLimitHandler rateLimitHandler = new RateLimitHandlerImpl(Map.of(ApiOperationType.GET_PHOTOS, 4.0));

        // When
        final boolean response = rateLimitHandler.applyLimit(ApiOperationType.CREATE_COLLECTION);

        // Then
        assertEquals(response, true);
    }

    @Test
    public void testWhenRateLimitFound() {
        // Given
        final RateLimitHandler rateLimitHandler = new RateLimitHandlerImpl(Map.of(ApiOperationType.GET_PHOTOS, 4.0));

        // When
        final boolean response = rateLimitHandler.applyLimit(ApiOperationType.GET_PHOTOS);

        // Then
        assertEquals(response, true);
    }

    @Test(expected = RuntimeException.class)
    public void testWhenFailedToAcquire() {
        // Given
        final RateLimitHandler rateLimitHandler = new RateLimitHandlerImpl(Map.of(ApiOperationType.GET_PHOTOS, 1.0));

        // When
        rateLimitHandler.applyLimit(ApiOperationType.GET_PHOTOS);
        rateLimitHandler.applyLimit(ApiOperationType.GET_PHOTOS);
    }
}
