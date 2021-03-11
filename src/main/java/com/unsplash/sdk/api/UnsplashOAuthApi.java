package com.unsplash.sdk.api;

import com.unsplash.sdk.model.OAuthToken;
import com.unsplash.sdk.oauth.config.OAuthConfig;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.Map;

/**
 * Interface of the Unsplash OAuth2 Api to handle the HTTP communication.
 */
public interface UnsplashOAuthApi {

    /**
     * Gets a access token by sending an authorization code.
     *
     * @param params {@link FieldMap} with parameters.
     * @return {@link Call} with the {@link OAuthToken} information.
     */
    @FormUrlEncoded
    @POST("/oauth/token")
    Call<OAuthToken> generateAccessToken(@FieldMap Map<String, Object> params);

    /**
     * Creates an implementation of the Unsplash API endpoints defined in the {@link UnsplashApi}
     * interface.
     *
     * @param configuration {@link OAuthConfig} settings for the HTTP communication with Unsplash.
     * @return Implementation instance of the {@link UnsplashApi} interface.
     */
    static UnsplashOAuthApi getInstance(final OAuthConfig configuration) {
        return new Retrofit.Builder()
            .baseUrl(configuration.getOAuthBaseUrl().toString())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UnsplashOAuthApi.class);
    }
}
