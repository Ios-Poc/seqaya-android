package com.ntg.user.sa2aia;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ntg.user.sa2aia.model.Order;
import com.ntg.user.sa2aia.network.PaymentFragment;

import java.text.DateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeliveryTimeFragment extends BaseFragment implements View.OnClickListener {
    View view;
    LinearLayout Button;
    private Button btn_dialog_confirm;
    private ImageView btn_dialog_1, btn_dialog_2;
    TextView text_date, text_time;
    Calendar calendar;
    Order order;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_date_and_time, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.time_title));
        Button = view.findViewById(R.id.layout_time);
        btn_dialog_1 = view.findViewById(R.id.btn_dialog_1);
        btn_dialog_2 = view.findViewById(R.id.btn_dialog_2);
        text_date = view.findViewById(R.id.text_date);
        text_time = view.findViewById(R.id.text_time);
        btn_dialog_confirm = view.findViewById(R.id.btn_dialog_confirm);

        order = (Order) getArguments().getSerializable(MainActivity.ORDER);

        ViewUtil.addShadowToView(getActivity(), Button);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        calendar = Calendar.getInstance();
        btn_dialog_1.setOnClickListener(this);
        btn_dialog_2.setOnClickListener(this);
        btn_dialog_confirm.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_dialog_1:
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), R.style.dateDialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String date = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
                        text_date.setText(date);

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;

            case R.id.btn_dialog_2:
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), R.style.timeDialog,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                calendar.set(Calendar.HOUR_OF_DAY, i);
                                calendar.set(Calendar.MINUTE, i1);
                                String time = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime());
                                text_time.setText(time);
                            }
                        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                timePickerDialog.show();
                break;

            case R.id.btn_dialog_confirm:
                PaymentFragment paymentFragment = new PaymentFragment();
                order.setDeliveryDate(text_date.getText().toString());
                order.setDeliveryTime(text_time.getText().toString());
                Bundle bundle = new Bundle();
                bundle.putSerializable(MainActivity.ORDER, order);
                paymentFragment.setArguments(bundle);
                getActivity().getFragmentManager().
                        beginTransaction().addToBackStack(null).replace(R.id.container, paymentFragment).commit();
                break;
        }

    }
}
