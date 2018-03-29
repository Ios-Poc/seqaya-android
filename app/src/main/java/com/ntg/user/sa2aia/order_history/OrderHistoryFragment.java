package com.ntg.user.sa2aia.order_history;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ntg.user.sa2aia.R;
import com.ntg.user.sa2aia.model.Order;
import com.ntg.user.sa2aia.model.User;
import com.ntg.user.sa2aia.network.ApiClient;
import com.ntg.user.sa2aia.network.ProductService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderHistoryFragment extends Fragment {
    private RecyclerView listOfOldOrders;
    private OrderHistoryAdapter adapter;
    List<Order> orders;
    LinearLayoutManager layoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_history, container, false);
        listOfOldOrders = view.findViewById(R.id.list_of_oders);
        layoutManager = new LinearLayoutManager(this.getContext());
        listOfOldOrders.setLayoutManager(layoutManager);
        ApiClient.getClient().create(ProductService.class).getOrderHistory(User.getEmail())
                .enqueue(new Callback<List<Order>>() {
                    @Override
                    public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                        orders = response.body();
                        adapter = new OrderHistoryAdapter(orders);
                        listOfOldOrders.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<Order>> call, Throwable t) {

                    }
                });
        return view;
    }

}
