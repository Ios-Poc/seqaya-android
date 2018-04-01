package com.ntg.user.sa2aia.order_history;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.kofigyan.stateprogressbar.StateProgressBar;
import com.ntg.user.sa2aia.R;
import com.ntg.user.sa2aia.ViewUtil;
import com.ntg.user.sa2aia.model.Order;
import com.ntg.user.sa2aia.model.OrderStatus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by devsaad on 3/28/2018.
 */

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryViewHolder> {


    private List<Order> orders;
    private Context context;

    public OrderHistoryAdapter(List<Order> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @Override
    public OrderHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_history_item, parent, false);
        ViewUtil.addShadowToView(context, view);
        return new OrderHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderHistoryViewHolder holder, int position) {
        Order order = orders.get(position);
        OrderHistoryViewHolder orderHistoryViewHolder = (OrderHistoryViewHolder) holder;
        holder.orderId.setText(order.getId());
        holder.expectedDelivery.setText(order.getDeliveryDate());
        holder.orderDate.setText(order.getDeliveryDate());
        holder.prodName.setText(order.getCartItems().get(0).getProduct().getName());
        holder.prodPrice.setText(String.valueOf(order.getCartItems().get(0).getProduct().getPrice()));
        holder.buttleSize.setText(String.valueOf(order.getCartItems().get(0).getProduct().getBottleSize()));
        holder.bpp.setText(String.valueOf(order.getCartItems().get(0).getProduct().getNo_bpp()));
        holder.orderDetailsText.setOnClickListener(v -> {});

        StateProgressBar stateProgressBar = holder.stateProgressBar;
        String[] descriptionData = {"جاري التجهيز","تم التوصيل", "تم اعادة الطلب", "تم إلغاء الطلب"};
        switch (order.getStatus()) {
            case OrderStatus.IN_PROCESSING:
                stateProgressBar.setStateDescriptionData(new String[]{descriptionData[0],descriptionData[1]});
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.ONE);
                stateProgressBar.setMaxStateNumber(StateProgressBar.StateNumber.TWO);
                break;
            case OrderStatus.DELIVERED:
                stateProgressBar.setStateDescriptionData(new String[]{descriptionData[0],descriptionData[1]});
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                stateProgressBar.setMaxStateNumber(StateProgressBar.StateNumber.TWO);
                stateProgressBar.setAllStatesCompleted(true);
                break;
            case OrderStatus.CANCELED:
                stateProgressBar.setStateDescriptionData(new String[]{descriptionData[0],descriptionData[3]});
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                stateProgressBar.setMaxStateNumber(StateProgressBar.StateNumber.TWO);
                break;
            case OrderStatus.RETURNED:
                stateProgressBar.setStateDescriptionData(new String[]{descriptionData[0],descriptionData[2]});
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                stateProgressBar.setMaxStateNumber(StateProgressBar.StateNumber.TWO);
                break;

        }
        holder.rightArrow.setOnClickListener(v -> {

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
        StateProgressBar stateProgressBar;
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
            stateProgressBar = view.findViewById(R.id.state_progress_bar);
            ButterKnife.bind(this, view);
        }
    }
}
