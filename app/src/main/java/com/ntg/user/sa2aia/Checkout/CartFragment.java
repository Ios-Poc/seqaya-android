package com.ntg.user.sa2aia.Checkout;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ntg.user.sa2aia.MainActivity;
import com.ntg.user.sa2aia.R;
import com.ntg.user.sa2aia.model.CartItem;
import com.ntg.user.sa2aia.model.Order;
import com.ntg.user.sa2aia.model.User;
import com.ntg.user.sa2aia.order_location.OrderMapActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CartFragment extends Fragment implements CartAdapter.TotalListener {

    @BindView(R.id.rv_product)
    RecyclerView products_rv;
    @BindView(R.id.price_all)
    TextView total_price;
    @BindView(R.id.confirmBtn)
    TextView confirmBtn;


    private List<CartItem> cartItemList;
    private LinearLayoutManager linearLayoutManager;
    private CartAdapter cartAdapter;

    public static CartFragment newInstance() {
        return new CartFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        ButterKnife.bind(this, view);
        cartItemList = User.getShoppingCart().getCartItemList();
        linearLayoutManager = new LinearLayoutManager(getActivity());
        ((AppCompatActivity) getActivity()).getSupportActionBar();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.checkout));
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        products_rv.setLayoutManager(linearLayoutManager);
        cartAdapter = new CartAdapter(cartItemList, getActivity(), this);
        products_rv.setAdapter(cartAdapter);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Order order = new Order(User.getEmail());
                List<CartItem> cartItems = new ArrayList<>();
                cartItems.addAll(cartItemList);
                order.setCartItems(cartItems);
                Intent i = new Intent(CartFragment.this.getActivity(), OrderMapActivity.class);
                i.putExtra(MainActivity.ORDER, order);
                startActivityForResult(i, MainActivity.requestCode);
            }
        });

        return view;
    }

    @Override
    public void onTotalChange(int total) {
        total_price.setText("" + total);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.history_menu, menu);
        MenuItem item = menu.findItem(R.id.back);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
