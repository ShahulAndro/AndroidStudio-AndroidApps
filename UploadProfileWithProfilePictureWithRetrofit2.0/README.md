# Sample Profile upload with retrofit2.0 

This is the sample source code of upload profile  using retrofit in android

Here i am uploading firstname, lastname, address and picture


Please note the following
1. Please change ROOT_URL accordingly with your server url in RetrofitClient.java 

2. SampleResponse.java is sample Response object, you can replace with this class with your response accordingly

3. Call<SampleResponse> uploadImage(@Part("firstname") RequestBody firstname,
                                        @Part("lastname") RequestBody lastname,
                                        @Part("address") RequestBody address,
                                        @Part MultipartBody.Part profilePicture);

   Please change the fields with your profile data request.
   MultipartBody.Part is useful to upload multiform data in your server
   I am using it here to upload profile picture.
   

I tested with my local server, it has been working fine

I hope it will be useful to someone
