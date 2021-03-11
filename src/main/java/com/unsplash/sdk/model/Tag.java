package com.unsplash.sdk.model;

import lombok.Value;

/**
 * Tag Details.
 */
@Value
public class Tag {
    /**
     * Tag type.
     */
    String type;
    /**
     * Tag title.
     */
    String title;
    /**
     * Tag source.
     */
    TagSource source;
}
