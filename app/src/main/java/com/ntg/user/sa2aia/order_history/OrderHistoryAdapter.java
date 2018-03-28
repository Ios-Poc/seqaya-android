package com.ntg.user.sa2aia.order_history;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.ntg.user.sa2aia.R;
import com.ntg.user.sa2aia.model.Order;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by devsaad on 3/28/2018.
 */

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryViewHolder> {


    private List<Order> orders;

    public OrderHistoryAdapter(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public OrderHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_history_row, parent, false);
        return new OrderHistoryViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(OrderHistoryViewHolder holder, int position) {
        Order order = orders.get(position);
        OrderHistoryViewHolder orderHistoryViewHolder = (OrderHistoryViewHolder) holder;
        holder.orderId.setText(order.getId());
        holder.expectedDelivery.setText(order.getDeliveryTime());
        holder.orderDate.setText(order.getDeliveryDate());
        holder.prodName.setText(order.getCartItems().get(0).getProduct().getName());
        holder.prodPrice.setText(order.getCartItems().get(0).getProduct().getPrice());
        holder.buttleSize.setText("" + order.getCartItems().get(0).getProduct().getBottleSize());
        holder.bpp.setText(order.getCartItems().get(0).getProduct().getNo_bpp());
//        holder.productImage.set(order.getCartItems().get(0).getProduct().getPhotoUrl());
        holder.orderDetailsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        switch (order.getStatus()) {
            case "inproccessing":
                holder.shippedImage.setImageResource(R.drawable.ic_x_mark);
                holder.deliverdImage.setImageResource(R.drawable.ic_x_mark);
                holder.firstPeace.setBackgroundResource(R.color.colorRed);
                holder.secondPeace.setBackgroundResource(R.color.colorRed);
                holder.checkedState.setText("inProccessing");
                holder.checkedState.setTextColor(R.color.colorBlack);
            case "shipped":
                holder.deliverdImage.setImageResource(R.drawable.ic_x_mark);
                holder.secondPeace.setBackgroundResource(R.color.colorRed);
                holder.checkedState.setText("shipped");
                holder.checkedState.setTextColor(R.color.colorBlack);
            case "deliverd":
                holder.checkedState.setText("deliverd");
                holder.checkedState.setTextColor(R.color.colorgreen);
            case "returned":
                holder.inprossisngImage.setImageResource(R.drawable.ic_x_mark);
                holder.shippedImage.setImageResource(R.drawable.ic_x_mark);
                holder.deliverdImage.setImageResource(R.drawable.ic_x_mark);
                holder.firstPeace.setBackgroundResource(R.color.colorRed);
                holder.secondPeace.setBackgroundResource(R.color.colorRed);
                holder.checkedState.setText("returned");
                holder.checkedState.setTextColor(R.color.colorRed);

        }
        holder.rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    static class OrderHistoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.order_id)
        TextView orderId;
        @BindView(R.id.order_date)
        TextView orderDate;
        @BindView(R.id.inprossisng_image)
        ImageView inprossisngImage;
        @BindView(R.id.first_peace)
        ViewAnimator firstPeace;
        @BindView(R.id.shipped_image)
        ImageView shippedImage;
        @BindView(R.id.second_peace)
        ViewAnimator secondPeace;
        @BindView(R.id.deliverd_image)
        ImageView deliverdImage;
        @BindView(R.id.expected_delivery)
        TextView expectedDelivery;
        @BindView(R.id.product_image)
        ImageView productImage;
        @BindView(R.id.checked_state)
        TextView checkedState;
        @BindView(R.id.order_details_text)
        TextView orderDetailsText;
        @BindView(R.id.right_arrow)
        ImageView rightArrow;
        @BindView(R.id.prod_name)
        TextView prodName;
        @BindView(R.id.prod_price)
        TextView prodPrice;
        @BindView(R.id.buttle_size)
        TextView buttleSize;
        @BindView(R.id.bpp)
        TextView bpp;

        OrderHistoryViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
