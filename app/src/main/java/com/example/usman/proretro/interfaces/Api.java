package com.example.usman.proretro.interfaces;

import com.example.usman.proretro.models.Fuel;
import com.example.usman.proretro.models.Location;
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

    @GET("fuels")
    Call<List<Fuel>> getFuels();

    @GET("locations")
    Call<List<Location>> getLocations();

    @POST("fuels")
    Call<List<Fuel>> addFuels(@Body Fuel fuel);

    @POST("locations")
    Call<List<Location>> addLocations(@Body Location location);

    @PUT("fuels/{id}")
    Call<Fuel> editFuels(@Path("id")long id, @Body Fuel fuel);

    @DELETE("fuels/{id}")
    Call<Fuel> deleteFuels(@Path("id")long id);
}
