package com.ntg.user.sa2aia;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ntg.user.sa2aia.model.Order;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderHistoryFragment extends Fragment {
   private RecyclerView listOfOldOrders;
   private OrderHistoryAdapter adapter;
   List<Order> orders;
   LinearLayoutManager layoutManager;


    public OrderHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        orders=getOrders();
        View view=inflater.inflate(R.layout.fragment_order_history, container, false);
        listOfOldOrders=view.findViewById(R.id.list_of_oders);
        adapter=new OrderHistoryAdapter(orders);
        layoutManager=new LinearLayoutManager(this.getContext());
        listOfOldOrders.setAdapter(adapter);
        listOfOldOrders.setLayoutManager(layoutManager);
        return view;
    }

}
