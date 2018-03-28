package com.ntg.user.sa2aia.Checkout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ntg.user.sa2aia.R;
import com.ntg.user.sa2aia.ViewUtil;
import com.ntg.user.sa2aia.model.CartItem;
import com.ntg.user.sa2aia.model.ShoppingCart;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ProductViewHolder> {
    ShoppingCart shoppingCartClient;

    List<CartItem> cartItemList;
    Context context;
    public CartAdapter(List<CartItem> cartItemList, Context context) {
        this.cartItemList = cartItemList;
        this.context=context;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chart_item , parent , false);
        ViewUtil.addShadowToView(context,view);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        shoppingCartClient=ShoppingCartClient.getShoppingCart();
        shoppingCartClient.getCartItemList().get(position);
        holder.name.setText( shoppingCartClient.getCartItemList().get(position).getProduct().getName());
        holder.manufacturer.setText(shoppingCartClient.getCartItemList().get(position).getProduct().getManufacturer());
        holder.bottleSize.setText(String.valueOf(shoppingCartClient.getCartItemList().get(position).getProduct().getBottleSize()));
        holder.numberInPackage.setText(String.valueOf(shoppingCartClient.getCartItemList().get(position).getProduct().getNo_bpp()));
        holder.price.setText(String.valueOf(shoppingCartClient.getCartItemList().get(position).getProduct().getPrice()));
        holder.numberOfItem.setText(shoppingCartClient.getCartItemList().get(position).getQuantity());

    }


    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.product_image)
        ImageView productImage;
        @BindView(R.id.product_name)
        TextView name;
        @BindView(R.id.product_manufacturer)
        TextView manufacturer;
        @BindView(R.id.bottle_size)
        TextView bottleSize;
        @BindView(R.id.no_in_package)
        TextView numberInPackage;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.number_of_item)
        TextView numberOfItem;




        ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this , itemView);
        }
    }
}
