package com.ntg.user.sa2aia.network;

import com.ntg.user.sa2aia.model.Credential;
import com.ntg.user.sa2aia.model.Fav;
import com.ntg.user.sa2aia.model.Location;
import com.ntg.user.sa2aia.model.Order;
import com.ntg.user.sa2aia.model.Product;
import com.ntg.user.sa2aia.model.User;
import com.ntg.user.sa2aia.model.UserAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by islam on 3/26/2018.
 */

public interface ProductService {

    @GET("product")
    Call<List<Product>> getProducts();

    @GET("search")
    Call<List<Product>> getSearchResult(@Query("q") String searchKeyword);

    @POST("user")
    Call<UserAPI> addNewUser(@Body UserAPI user);

    @POST("login")
    Call<UserAPI> login(@Body Credential credential);

    @POST("order")
    Call<Order> addNewOrder(@Body Order order);

    @GET("order/{userId}")
    Call<List<Order>> getOrderHistory(@Path("userId") String userId);

    @POST("location")
    Call<Location> addNewLocation(@Body Location location);

    @GET("location/{userId}")
    Call<List<Location>> getSavedLocations(@Path("userId") String userId);

    @POST("fav")
    Call<Fav> addFav(@Body Fav fav);

    @GET("fav/{userId}")
    Call<List<Product>> getFavs(@Path("userId") String userId);

    @DELETE("fav/{id}")
    Call<Fav> deleteFav(@Path("id") String productId);
}
