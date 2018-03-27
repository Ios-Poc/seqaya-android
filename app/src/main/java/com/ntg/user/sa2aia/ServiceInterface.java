package com.ntg.user.sa2aia;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ilias on 27/03/2018.
 */

public interface ServiceInterface {

    @POST("user")
    Call<User> addNewUser(@Body User user);

    @POST("login")
    Call<User> login(@Body Credential credential);
}
