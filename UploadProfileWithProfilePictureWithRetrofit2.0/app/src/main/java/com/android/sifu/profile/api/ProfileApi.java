package com.android.sifu.profile.api;


import com.android.sifu.profile.model.SampleResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ProfileApi {

    @Multipart
    @POST("product/report")
    //Todo: Please map your profile data accordintly
    Call<SampleResponse> uploadImage(@Part("firstname") RequestBody firstname,
                                     @Part("lastname") RequestBody lastname,
                                     @Part("address") RequestBody address,
                                     @Part MultipartBody.Part profilePicture);
}
