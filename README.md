# Unsplash Java SDK

This is the first version of a JAVA SDK     library for Unsplash (htt
ps://unsplash.com/). Unsplash offers a JSON API, which you can use by creating a free developer account, you can find the instructions and API
Documentation here: https://unsplash.com/documentation.

# Building the client
Before proceeding, install a [JDK](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html) (must be Java 8 or later) and [Apache Maven](https://maven.apache.org/install.html).

Ensure `JAVA_HOME` is set correctly and the `mvn` executable is available on your PATH.

## 1. Building jar file
Run the following command in a terminal/console.
```bash
mvn package
```

This compiles the client into a jar located at `./target/unsplash-java-sdk-1.0.jar`. Note that this jar does not include any dependencies.

## 2. Using it in your project

To depend on this project in Apache Maven (after the package published to an accessible repository), add the following to your pom.xml file.
```xml
<dependency>
  <groupId>com.unsplash.sdk</groupId>
  <artifactId>unsplash-java-sdk</artifactId>
  <version>1.0</version>
</dependency>
```

To depend on this project in Gradle (after the package is published to an accessible repository), add the following to your build.gradle file.

```
dependencies {
  implementation 'com.unsplash.sdk:unsplash-java-sdk:1.0'
}
```

For more information on managing dependencies with Maven and publishing artifacts, see:
* [https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html)
* [http://central.sonatype.org/pages/ossrh-guide.html](http://central.sonatype.org/pages/ossrh-guide.html)

## 3.Run Tests and generate test-coverage report

Project has both unit tests and integration tests defined in /src/test/resources. Code has 95% of line coverage.
      
    mvn clean test
      
Report should exist in the `target/site` folder.

    
# Developer Guide

## Creating a client
To create a UnsplashClient, use the client builder. Before using, configure the client with your access key and secret. If you don't have an access key and secret, follow the steps from the Unsplash API (https://unsplash.com/documentation#creating-a-developer-account) to register your application.

#### Few important points:

- Base Url and OAuth Base Url are optional, if you are not passing them in configuration, they will be defaulted. For scenarios where you want to test your changes in test env of Unsplash, you can use them to pass the urls.
- You can also pass any custom interceptor in configuration. Please refer `HttpConnectionSettings` for more details.

#### Public Authentication
Most actions can be performed without requiring authentication from a specific user. For example, searching, fetching, or downloading a photo does not require a user to log in.

For such use cases, create UnsplashClient by just passing client id as configuration.

```java
UnsplashClient unsplashClient = new UnsplashClient(
    UnsplashClientConfig.builder()
        .clientId("Your client id")
        .baseUrl(new URL("Unsplash Base URL"))
        .build())
```

#### User Authentication
If you’re building an API application which requires that responses be customized per user (i.e. have they liked a photo, fetch their private collections, etc.) or requires taking actions on behalf of users, then you’ll need to use the user authentication workflow to create individual user bearer tokens for authentication.

For such use cases, first create UnsplashOAuthClient by just passing client id as configuration.

```java
UnsplashOAuthProvider unsplashOAuthProvider = new UnsplashOAuthProviderImpl(
    OAuthConfig.builder()
        .clientId("Your client id")
        .clientSecret("Your client Secret")
        .redirectUri("Your redirect URI"))
        .oAuthBaseUrl("Unsplash OAuth Base Url"))
        .build())
```

Direct users to an authorization URL (configuring any scopes before generating the authorization URL), you can get authorization URL as below:

```java
final URL authorizationUrl = unsplashOAuthProvider.getOAuthService().getAuthorizationUrl(List of scopes you need access for);
```

Upon authorization, Unsplash will return to you an authentication code via your OAuth callback handler. Use it to generate an access token:

```java
final OAuthToken oAuthToken = unsplashOAuthProvider.getOAuthService()
            .generateAccessToken("Access Token returned in above response")
            .execute()
            .body();
```

With the token you can now access any additional non-public actions available for the authorized user. Use this token to create an instance of UnsplashClient now.

```java
UnsplashClient unsplashClient = new UnsplashClientImpl(
    UnsplashClientConfig.builder()
        .accessToken("Access Token returned above")
        .baseUrl(new URL("Unsplash Base URL"))
        .build())
```

Since the access token never expire, you dont need to refresh it.

After SDK client has been authorized, you can call any method/API as below.

## API methods

### Photo

To get list of photos:

| Parameter  | Description |
| ------------- | ------------- |
| page | Page number to retrieve. (Optional; default: 1). |
| size | Number of items per page. (Optional; default: 10). |
| orderBy | Sort order for photos Optional. |

```java
Call<List<Photo>> getPhotos(GetPhotoRequest getPhotoRequest);
```

### Collection

To get list of collections:

| Parameter  | Description |
| ------------- | ------------- |
| page | Page number to retrieve. (Optional; default: 1). |
| size | Number of items per page. (Optional; default: 10). |

```java
Call<List<Collection>> getCollections(GetCollectionRequest getCollectionRequest);
```

To create a collections:

| Parameter  | Description |
| ------------- | --------|
| title | Title of the collection. |
| description | Optional Description of the collection. |
| isPrivate | Whether to make this collection private. (Optional; default false). |

```java
Call<Collection> createCollection(CreateCollectionRequest createCollectionRequest);
```

To add photo to collection:

| Parameter  | Description |
| ------------- | ------------- |
| collectionId | Id of the collection to which we want to add photo. |
| photoId | Id of the photo to be added. |

```java
Call<Collection> addPhotoToCollection(AddPhotoToCollectionRequest collectionAddPhotoQuery);
```

#### Get Results from Synchronous Requests

```java
final CollectionService collectionService = unsplashClient.getCollectionService();

final GetCollectionRequest collectionRequest = GetCollectionRequest.builder().build();

final List<Collection>> collections = unsplashClient.getCollectionService().getCollections(collectionRequest).execute().body();
```

#### Get Results from Asynchronous Requests

You can use below code to get results from asynchronous requests:

```java
final CollectionService collectionService = unsplashClient.getCollectionService();

final GetCollectionRequest collectionRequest = GetCollectionRequest.builder().build();

final Call<List<Collection>> callbackResponse = unsplashClient.getCollectionService().getCollections(collectionRequest);
```
You then have to implement a new Callback and define what should be done once the request finishes.

```java
callbackResponse.enqueue(new Callback<List<Collection>>() {  
    @Override
    public void onResponse(final Call<List<Collection>> call, final Response<List<Collection>> response) {
        if (response.isSuccessful()) {
            // do something
        } else {
            // error response, response not successful.
        }
    }

    @Override
    public void onFailure(final Call<List<Collection>> call, final Throwable throwable) {
        // something went completely south (like no internet connection)
        log.error("Failed to call API", throwable.getMessage());
    }
}
```

## Technical Comments

Unsplash Java SDK contains 5 core components:

- OAuth Module
    * Defined in `com.unsplash.sdk.oauth` package.
    * This module has responsibility of creating client for OAuth authentication.
- Data models
    * Defined in `com.unsplash.sdk.model` package.
    * All the models/objects returned by Unsplash Api.
    * Model classes were created from JSON response returned by unsplash Api using JSON to Java converter https://json2csharp.com/json-to-pojo.
- HTTP Client
    * Defined in `com.unsplash.sdk.api` package.
    * Contains `UnsplashApi` and `UnsplashOAuthApi` interfaces to handle the HTTP communication.
    * Retrofit is used for creating HTTP client.
- Api Client
    * Defined in `com.unsplash.sdk.client` package.
    * Contains `UnsplashClient` interface.
    * To serialize request object to map of parameters, `Jackson ObjectMapper class` is used.
- Rate Limiter
    * Defined in `com.unsplash.sdk.ratelimiter` package.
    * Handles rate limiting for a client for each of the operation type, based on configuration set by client.
    * To control rate limit, Guava's RateLimiter class is used which works on the token bucket algorithm.
    * You can pass rate limit configuration while creating Unsplash client, this way your requests will be throttled on SDK itself rather than being throttled on API endpoints.
    * Please note that there is already throttling set on each API call, using rate limiter defined in SDK will just fail fast your request saving you latency for network calls.
    * To set the rate limit for any operation, pass rate limit configuration while creating Unsplash Client. If no configuration found, your calls will be allowed and throttling will be handled at API level itself.

```java
UnsplashClient unsplashClient = new UnsplashClient(
    UnsplashClientConfig.builder()
        .clientId("Your client id")
        .baseUrl(new URL("Unsplash Base URL"))
        .rateLimitMap(Map containing rate limit configuration)
        .build())
```
    
## Additional Enhancements

- Support for calling multiple versions of API - API versioning is a common technique used in industry where API version is changed for any new release with breaking change.
