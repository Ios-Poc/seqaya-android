package com.ntg.user.sa2aia.Checkout;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ntg.user.sa2aia.BaseFragment;
import com.ntg.user.sa2aia.DeliveryTimeFragment;
import com.ntg.user.sa2aia.MainActivity;
import com.ntg.user.sa2aia.R;
import com.ntg.user.sa2aia.confirm_order.ClearList;
import com.ntg.user.sa2aia.model.CartItem;
import com.ntg.user.sa2aia.model.Order;
import com.ntg.user.sa2aia.model.User;
import com.ntg.user.sa2aia.order_location.OrderMapActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class CartFragment extends BaseFragment implements CartAdapter.TotalListener {

    public static final int REQUEST_CODE = 1;

    @BindView(R.id.rv_product)
    RecyclerView products_rv;
    @BindView(R.id.price_all)
    TextView total_price;
    @BindView(R.id.confirmBtn)
    TextView confirmBtn;
    @BindView(R.id.no_items_layout)
    LinearLayout noItemLayout;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.reyal_saudi)
    TextView reyalSaudi;
    Unbinder unbinder;
    ClearList clearList;

    private List<CartItem> cartItemList;
    private LinearLayoutManager linearLayoutManager;
    private CartAdapter cartAdapter;
    int total = 0;

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
        unbinder = ButterKnife.bind(this, view);
        cartItemList = User.getShoppingCart().getCartItemList();
        linearLayoutManager = new LinearLayoutManager(getActivity());
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getString(R.string.shopping_cart));
        }
        products_rv.setLayoutManager(linearLayoutManager);
        if (cartItemList.isEmpty())
            showNoItems();
        cartAdapter = new CartAdapter(cartItemList, getActivity(), this);
        products_rv.setAdapter(cartAdapter);

        confirmBtn.setOnClickListener(view1 -> {
            Order order = new Order(User.getEmail());
            order.setTotal(total);
            List<CartItem> cartItems = new ArrayList<>();
            cartItems.addAll(cartItemList);
            order.setCartItems(cartItems);
            Intent i = new Intent(CartFragment.this.getActivity(), OrderMapActivity.class);
            i.putExtra(MainActivity.ORDER, order);
            startActivityForResult(i, REQUEST_CODE);
        });

        return view;
    }

    private void showNoItems() {
        noItemLayout.setVisibility(View.VISIBLE);
        products_rv.setVisibility(View.GONE);
        confirmBtn.setVisibility(View.GONE);
        total_price.setVisibility(View.GONE);
        tv.setVisibility(View.GONE);
        reyalSaudi.setVisibility(View.GONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            DeliveryTimeFragment deliveryTimeFragment = new DeliveryTimeFragment();
            Bundle args = new Bundle();
            args.putSerializable(MainActivity.ORDER, data.getSerializableExtra(MainActivity.ORDER));
            deliveryTimeFragment.setArguments(args);
            getActivity().getFragmentManager().beginTransaction().addToBackStack(null)
                    .replace(R.id.container, deliveryTimeFragment).commitAllowingStateLoss();
        }
    }

    @Override
    public void onTotalChange(int total) {
        this.total = total;
        total_price.setText("" + total);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.history_menu, menu);
        MenuItem item = menu.findItem(R.id.back);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
