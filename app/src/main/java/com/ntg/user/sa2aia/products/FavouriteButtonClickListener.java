package com.ntg.user.sa2aia.products;

import com.ntg.user.sa2aia.model.Product;

public interface FavouriteButtonClickListener {

    void onLike(Product product);

    void onUnLike(Product product);
}
