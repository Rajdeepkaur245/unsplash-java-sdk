package com.unsplash.sdk.model;

import com.google.gson.annotations.SerializedName;
import lombok.Value;

import java.util.Date;

/**
 * Raw Url.
 */
@Value
public class User {
    /**
     * User id.
     */
    String id;
    /**
     * Updated date.
     */
    @SerializedName(value = "updated_at")
    Date updatedAt;
    /**
     * User name.
     */
    String username;
    /**
     * Name of user.
     */
    String name;
    /**
     * First Name of user.
     */
    @SerializedName(value = "first_name")
    String firstName;
    /**
     * Last Name of user.
     */
    @SerializedName(value = "last_name")
    String lastName;
    /**
     * Twitter username of user.
     */
    @SerializedName(value = "twitter_username")
    String twitterUsername;
    /**
     * Portfolio URL of user.
     */
    @SerializedName(value = "portfolio_url")
    String portfolioUrl;
    /**
     * User Bio.
     */
    String bio;
    /**
     * User Location.
     */
    String location;
    /**
     * User Links
     */
    Links links;
    /**
     * Profile Image of user.
     */
    @SerializedName(value = "profile_image")
    ProfileImage profileImage;
    /**
     * Instagram user name of User.
     */
    @SerializedName(value = "instagram_username")
    String instagramUsername;
    /**
     * Total Collections.
     */
    @SerializedName(value = "total_collections")
    Integer totalCollections;
    /**
     * Total likes received by user.
     */
    @SerializedName(value = "total_likes")
    Integer totalLikes;
    /**
     * Total Photos uploaded by user.
     */
    @SerializedName(value = "total_photos")
    Integer totalPhotos;
    /**
     * User Accepted Tos.
     */
    @SerializedName(value = "accepted_tos")
    Boolean acceptedTos;
    /**
     * User available for hire.
     */
    @SerializedName(value = "for_hire")
    Boolean isAvailableForHire;
}
