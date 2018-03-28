package com.ntg.user.sa2aia.confirm_order;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ntg.user.sa2aia.R;
import com.ntg.user.sa2aia.ViewUtil;
import com.ntg.user.sa2aia.model.CartItem;
import com.ntg.user.sa2aia.model.Order;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryOrderFragment extends Fragment {


    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.deliveryLocation)
    TextView deliveryLocation;
    @BindView(R.id.paymentDetails)
    TextView paymentDetails;
    @BindView(R.id.deliveryTime)
    TextView deliveryTime;
    @BindView(R.id.recyclerList)
    RecyclerView recyclerList;
    ListAdapter listAdapter;
    @BindView(R.id.cardView)
    LinearLayout cardView;
    @BindView(R.id.cardView2)
    LinearLayout cardView2;
    @BindView(R.id.cardView3)
    LinearLayout cardView3;
    Order order;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.done)
    Button done;
    private List<CartItem> cartItems;

    public static SummaryOrderFragment newInstance() {
        // Required empty public constructor
        return new SummaryOrderFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_main, container, false);
        ButterKnife.bind(this, view);
        ((AppCompatActivity) getActivity()).setSupportActionBar(myToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.order_summary);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        cartItems = new ArrayList<>();
        order = new Order(null);
        deliveryLocation.setText(order.getLocation());
        paymentDetails.setText(order.getPaymentMethod());
        deliveryTime.setText(order.getDeliveryTime() + " " + order.getDeliveryDate());
        total.setText("250" + "ريال");
        listAdapter = new ListAdapter(new ArrayList<CartItem>(), getActivity());
        cartItems = order.getCartItems();
        listAdapter.setProductList(cartItems);
        recyclerList.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerList.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
        //
//        ProductService productService = ApiClient.getClient().create(ProductService.class);
//        Call<List<Product>> productListCall = productService.getProducts();
//        productListCall.enqueue(new Callback<List<Product>>() {
//            @Override
//            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
//                if (response.isSuccessful()) {
//                    listAdapter = new ListAdapter(new ArrayList<Product>(), getActivity());
//                    productList = response.body();
//                    listAdapter.setProductList(productList);
//                    recyclerList.setLayoutManager(new LinearLayoutManager(getActivity()));
//                    recyclerList.setAdapter(listAdapter);
//                    listAdapter.notifyDataSetChanged();
//                    Log.e("proSize", productList.size() + "");
//                    Toast.makeText(getActivity(), "res", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Product>> call, Throwable t) {
//                Log.e("proSize", t.getMessage());
//
//            }
//        });
        ViewUtil.addShadowToView(getActivity(), cardView);
        ViewUtil.addShadowToView(getActivity(), cardView2);
        ViewUtil.addShadowToView(getActivity(), cardView3);


        return view;
    }


    @OnClick(R.id.done)
    public void onViewClicked() {
        getActivity().finish();
    }
}
