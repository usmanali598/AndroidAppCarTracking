package com.example.usman.proretro.interfaces;

import com.example.usman.proretro.models.Fuel;
import com.example.usman.proretro.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by usman on 09/03/2018.
 */

public interface Api {

    @GET("users")
    Call<List<User>> getUsers();

    @GET("fuels")
    Call<List<Fuel>> getFuels();

    @POST("users")
    Call<List<User>> addUsers(@Body User user);
}
