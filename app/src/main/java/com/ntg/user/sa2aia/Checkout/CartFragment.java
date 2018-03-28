package com.ntg.user.sa2aia.Checkout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ntg.user.sa2aia.R;
import com.ntg.user.sa2aia.model.CartItem;
import com.ntg.user.sa2aia.model.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CartFragment extends Fragment {


    @BindView(R.id.rv_product)
    RecyclerView products_rv;

    private List<CartItem> cartItemList;
    private LinearLayoutManager linearLayoutManager;
    private CartAdapter cartAdapter;

    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        return fragment;
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

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.checkout));
        cartItemList = User.getCurrentUser().getShoppingCart().getCartItemList();
        linearLayoutManager = new LinearLayoutManager(getActivity());
        products_rv.setLayoutManager(linearLayoutManager);
        cartAdapter = new CartAdapter(cartItemList, getActivity());
        products_rv.setAdapter(cartAdapter);

        return view;
    }
}
