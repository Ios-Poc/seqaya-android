package com.ntg.user.sa2aia.network;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ntg.user.sa2aia.R;
import com.ntg.user.sa2aia.ViewUtil;
import com.ntg.user.sa2aia.model.Order;
import com.ntg.user.sa2aia.model.PaymentMethod;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment implements View.OnClickListener{
    View view;
    LinearLayout bankTransfer,sadad,creditCard;
    Order order;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_payment, container, false);
        bankTransfer=view.findViewById(R.id.bankTransfer);
        sadad=view.findViewById(R.id.sadad);
        creditCard=view.findViewById(R.id.creditCard);
        ViewUtil.addShadowToView(getContext(),bankTransfer);
        ViewUtil.addShadowToView(getContext(),sadad);
        ViewUtil.addShadowToView(getContext(),creditCard);
        Bundle bundle=getArguments();
        order= (Order) bundle.getSerializable("order");
        Log.d("order", String.valueOf(bundle));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bankTransfer.setOnClickListener(this);
        sadad.setOnClickListener(this);
        creditCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bankTransfer:
                order.setPaymentMethod(PaymentMethod.BANK_TRANSFER);
                break;
            case R.id.sadad:
                order.setPaymentMethod(PaymentMethod.SADAD);
                break;
            case  R.id.creditCard:
                order.setPaymentMethod(PaymentMethod.CREDIT_CARD);
                break;

        }
    }
    public void navigateToOrderSumery(){
    }
}
