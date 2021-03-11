package com.unsplash.sdk.service.oauth;

import com.unsplash.sdk.model.OAuthToken;
import retrofit2.Call;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public interface OAuthService {

    /**
     * Gets the authorization URL needed to open a web view so the user can login and be
     * authorised with Unsplash.
     *
     * @param scopes Specifies the scopes for which authorization should be requested.
     * @return The authorization URL.
     * @throws MalformedURLException        If no protocol is specified, or an unknown protocol is found,
     *                                      or spec is null while instantiating the URL.
     * @throws UnsupportedEncodingException If unable to encode param.
     * @throws IllegalArgumentException     If state is passed as null or empty.
     */
    URL getAuthorizationUrl(final List<String> scopes)
        throws MalformedURLException, UnsupportedEncodingException, IllegalArgumentException;

    /**
     * Gets an access token using the authorization code grant.
     *
     * @param authorizationCode The code included in the redirect URI.
     * @return {@link Call} with {@link OAuthToken} information.
     */
    Call<OAuthToken> generateAccessToken(final String authorizationCode);
}
