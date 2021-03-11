package com.unsplash.sdk.exception;

/**
 * Exception thrown when invalid configuration sent to Unsplash client.
 */
public final class InvalidUnsplashConfigException extends RuntimeException {

    public InvalidUnsplashConfigException(final String message) {
        super(message);
    }
}
