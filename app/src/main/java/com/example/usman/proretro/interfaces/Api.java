package com.example.usman.proretro.interfaces;

import com.example.usman.proretro.models.Fuel;
import com.example.usman.proretro.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

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

    @POST("fuels")
    Call<List<Fuel>> addFuels(@Body Fuel fuel);

    @POST("fuels")
    Call<List<Fuel>> addLocations(@Body Fuel fuel);

    @PUT("users/{id}/")
    Call<List<User>> editUsers(@Path("id")int userId, @Body User user);

    @PUT("fuels/{id}")
    Call<Fuel> editFuels(@Path("id")long id, @Body Fuel fuel);

    @DELETE("fuels/{id}")
    Call<Fuel> deleteFuels(@Path("id")long id);
}
