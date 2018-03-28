package com.ntg.user.sa2aia.products;

import com.ntg.user.sa2aia.products.Product;

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
    @GET("search")
    Call<List<Product>> search(@Query("q") String keyWord);
}
