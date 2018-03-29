package com.ntg.user.sa2aia.confirm_order;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ntg.user.sa2aia.MainActivity;
import com.ntg.user.sa2aia.R;
import com.ntg.user.sa2aia.ViewUtil;
import com.ntg.user.sa2aia.model.CartItem;
import com.ntg.user.sa2aia.model.Order;
import com.ntg.user.sa2aia.model.PaymentMethod;

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
        order = (Order) getArguments().getSerializable(MainActivity.ORDER);
        deliveryLocation.setText(order.getLocation());
        switch (order.getPaymentMethod()) {
            case PaymentMethod.BANK_TRANSFER:
                paymentDetails.setText(getString(R.string.bank_transfer));
                break;

            case PaymentMethod.CREDIT_CARD:
                paymentDetails.setText(getString(R.string.credit_card));
                break;
            case PaymentMethod.SADAD:
                paymentDetails.setText(getString(R.string.sadad));
                break;
        }
        Log.i("Order", order.toString());
        deliveryTime.setText(order.getDeliveryTime() + " , " + order.getDeliveryDate());
        total.setText(String.valueOf(order.getTotal()));
        listAdapter = new ListAdapter(order.getCartItems(), getActivity());
        recyclerList.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerList.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();

        ViewUtil.addShadowToView(getActivity(), cardView);
        ViewUtil.addShadowToView(getActivity(), cardView2);
        ViewUtil.addShadowToView(getActivity(), cardView3);

        return view;
    }


    @OnClick(R.id.done)
    public void onViewClicked() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }
}
