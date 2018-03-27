package com.ntg.user.sa2aia.catalog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ntg.user.sa2aia.R;
import com.ntg.user.sa2aia.model.CartItem;
import com.ntg.user.sa2aia.model.Product;
import com.ntg.user.sa2aia.model.ShoppingCart;
import com.ntg.user.sa2aia.model.ShoppingCartClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    List<Product> productList;
    List<CartItem> cartItems = new ArrayList<>();
    private Context context;

    public ProductAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_row , parent , false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final Product product = productList.get(position);
        holder.name.setText(product.getName());
        holder.manufacturer.setText(product.getManufacturer());
        holder.bottleSize.setText(String.valueOf(product.getBottleSize()));
        holder.numberInPackage.setText(String.valueOf(product.getNo_bpp()));
        holder.price.setText(String.valueOf(product.getPrice()));
        holder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.product_image)
        ImageView productImage;
        @BindView(R.id.product_name)
        TextView name;
        @BindView(R.id.product_manufacturer)
        TextView manufacturer;
        @BindView(R.id.bottle_size)
        TextView bottleSize;
        @BindView(R.id.number_in_package)
        TextView numberInPackage;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.decrease)
        ImageButton decrease;
        @BindView(R.id.number_of_item)
        TextView numberOfItem;
        @BindView(R.id.increase)
        ImageButton increase;
        @BindView(R.id.add_to_cart)
        Button addToCart;


        ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this , itemView);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.add_to_cart:
                    Log.e( "cartitems" , "added");
                    break;
                case R.id.increase:
                    Log.i( "cartitems" , "increase");
                    break;
                default:
                    break;
            }
        }
    }
}
