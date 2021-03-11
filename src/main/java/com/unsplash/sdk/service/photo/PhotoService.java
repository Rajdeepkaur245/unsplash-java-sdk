package com.unsplash.sdk.service.photo;

import com.unsplash.sdk.model.Photo;
import com.unsplash.sdk.model.request.photo.GetPhotoRequest;
import retrofit2.Call;

import java.util.List;

public interface PhotoService {
    /**
     * Gets list of Photos.
     *
     * @return {@link Call} with list of {@link Photo}.
     */
    Call<List<Photo>> getPhotos(GetPhotoRequest getPhotoRequest);
}
