package com.unsplash.sdk.model;

import lombok.Value;

/**
 * Url details.
 */
@Value
public class Urls {
    /**
     * Raw Url.
     */
    String raw;
    /**
     * Full Url.
     */
    String full;
    /**
     * Regular Url.
     */
    String regular;
    /**
     * Small Url.
     */
    String small;
    /**
     * Thumb Url.
     */
    String thumb;
}
