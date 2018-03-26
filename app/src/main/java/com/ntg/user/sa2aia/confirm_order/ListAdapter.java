package com.ntg.user.sa2aia.confirm_order;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ntg.user.sa2aia.R;
import com.ntg.user.sa2aia.model.OrderItem;
import com.ntg.user.sa2aia.model.Product;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    List<Product> orderList;


    public ListAdapter(List<Product> orderList) {
        this.orderList=orderList;

    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_row, parent, false);
        ListViewHolder holder = new ListViewHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {

        Product product=orderList.get(position);
        holder.productName.setText(product.getName());
        //holder.productImage
        holder.bottleSize.setText(product.getBottleSize());
        holder.numberInPackage.setText(product.getNo_bpp());
        holder.price.setText(product.getPrice());

//        IndianMovie movie = orderList.get(position);
//        holder.movietitle.setText(movie.movieName);
//        holder.moviedesc.setText(movie.movieStory);
//        holder.movierate.setText(movie.movieRate);
//        holder.poster.setImageResource(movie.posterImage);

    }

    @Override
    public int getItemCount() {
        return 5;
        //return orderList.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView bottleSize;
        TextView numberInPackage;
        TextView price;

        public ListViewHolder(View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.product_image);
            productName=itemView.findViewById(R.id.product_name);
            bottleSize=itemView.findViewById(R.id.bottle_size);
            numberInPackage=itemView.findViewById(R.id.number_in_package);
            price=itemView.findViewById(R.id.price);

        }
    }

}



