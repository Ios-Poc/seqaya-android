package com.ntg.user.sa2aia.network;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ntg.user.sa2aia.R;
import com.ntg.user.sa2aia.ViewUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment {
    View view;
    LinearLayout bankTransfer,sadad,creditCard;


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

        return view;
    }

}
