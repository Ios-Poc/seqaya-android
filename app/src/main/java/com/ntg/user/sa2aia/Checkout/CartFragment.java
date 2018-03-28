package com.ntg.user.sa2aia.Checkout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ntg.user.sa2aia.R;
import com.ntg.user.sa2aia.model.CartItem;
import com.ntg.user.sa2aia.model.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CartFragment extends Fragment implements CartAdapter.TotalListener {

    @BindView(R.id.rv_product)
    RecyclerView products_rv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.price_all)
    TextView total_price;

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

        cartItemList = User.getCurrentUser().getShoppingCart().getCartItemList();
        linearLayoutManager = new LinearLayoutManager(getActivity());
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.checkout));
        products_rv.setLayoutManager(linearLayoutManager);
        cartAdapter = new CartAdapter(cartItemList, getActivity(), this);
        products_rv.setAdapter(cartAdapter);

        return view;
    }

    @Override
    public void onTotalChange(int total) {
        total_price.setText("" + total);
    }
}
