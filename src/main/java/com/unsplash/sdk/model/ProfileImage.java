package com.unsplash.sdk.model;

import lombok.Value;

/**
 * Profile Image Details.
 */
@Value
public class ProfileImage {
    /**
     * Link to small profile image.
     */
    String small;
    /**
     * Link to medium profile image.
     */
    String medium;
    /**
     * Link to large profile image.
     */
    String large;
}
