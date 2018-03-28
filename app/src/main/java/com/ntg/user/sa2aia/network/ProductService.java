package com.ntg.user.sa2aia.network;

import com.ntg.user.sa2aia.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by islam on 3/26/2018.
 */

public interface ProductService {
    @GET("/product")
    Call<List<Product>> getProducts();

}
