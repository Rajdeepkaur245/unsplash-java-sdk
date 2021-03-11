package com.unsplash.sdk.model;

import com.google.gson.annotations.SerializedName;
import lombok.Value;

/**
 * OAuth token returned by Unsplash OAuth Provider.
 */
@Value
public class OAuthToken {
    /**
     * Access Token.
     */
    @SerializedName(value = "access_token")
    String accessToken;
    /**
     * Token type.
     */
    @SerializedName(value = "token_type")
    String tokenType;
    /**
     * Scopes for which this token is created.
     */
    String scope;
    /**
     * Time at which this token was created.
     */
    @SerializedName(value = "created_at")
    String createdAt;
}
