package com.ntg.user.sa2aia.products;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ntg.user.sa2aia.R;
import com.ntg.user.sa2aia.ViewUtil;
import com.ntg.user.sa2aia.model.CartItem;
import com.ntg.user.sa2aia.model.ShoppingCart;
import com.ntg.user.sa2aia.model.ShoppingCartClient;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
   private List<Product> productList;
   private Context context;
   private List<CartItem> cartItems = new CopyOnWriteArrayList<>();
   private ShoppingCartItemCount shoppingCartItemCount;

    public ProductAdapter(List<Product> productList, Context context, ShoppingCartItemCount shoppingCartItemCount) {
        this.productList = productList;
        this.context = context;
        this.shoppingCartItemCount = shoppingCartItemCount;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_row , parent , false);
        view.setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
        ViewUtil.addShadowToView(context , view);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final Product product = productList.get(position);
        holder.name.setText(product.getName());
        holder.manufacturer.setText(product.getManufacturer());
        holder.bottleSize.setText(String.valueOf(product.getBottleSize()));
        holder.numberInPackage.setText(String.valueOf(product.getNo_bpp()));
        holder.price.setText(String.valueOf(product.getPrice())+" ريال");
        holder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(holder.numberOfItem.getText().toString());
                number++;
                holder.numberOfItem.setText(String.valueOf(number));
            }
        });
        holder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(holder.numberOfItem.getText().toString());
                if (number>1){
                    number--;
                    holder.numberOfItem.setText(String.valueOf(number));
                }

            }
        });
        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartItem cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setQuantity(Integer.parseInt(holder.numberOfItem.getText().toString()));

                for (CartItem item:cartItems){
                    if (item.getProduct().getId() == product.getId()){
                        cartItems.remove(item);
                        Toast.makeText(context, "exist", Toast.LENGTH_SHORT).show();
                    }
                }
                cartItems.add(cartItem);
                ShoppingCart shoppingCart = ShoppingCartClient.getShoppingCart();
                shoppingCart.setCartItemList(cartItems);
                shoppingCartItemCount.itemsCount(cartItems.size());
            }
        });
    }
    public void setProductList(List<Product> productList){
        this.productList.clear();
        this.productList = productList;
    }
    public void clear(){
        this.productList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return productList.size();
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
    }
}