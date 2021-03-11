package com.unsplash.sdk.model.request.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class OAuthTokenRequest {
    /**
     * Application’s access key.
     */
    @JsonProperty("client_id")
    String clientId;
    /**
     * application’s secret key.
     */
    @JsonProperty("client_secret")
    String clientSecret;
    /**
     * application’s redirect URI.
     */
    @JsonProperty("redirect_uri")
    String redirectUri;
    /**
     * The authorization code supplied to the callback by Unsplash.
     */
    String code;
    /**
     * Grant type.
     */
    @JsonProperty("grant_type")
    String grantType;
}
