package com.ntg.user.sa2aia;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class dateAndTimeFragment extends Fragment implements View.OnClickListener {
    View view;
    private Button btn_dialog_1, btn_dialog_2, btn_dialog_confirm;
    Calendar calendar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_date_and_time, container, false);
        btn_dialog_1 = view.findViewById(R.id.btn_dialog_1);
        btn_dialog_2 = view.findViewById(R.id.btn_dialog_2);
        btn_dialog_confirm = view.findViewById(R.id.btn_dialog_confirm);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        calendar=Calendar.getInstance();
        btn_dialog_1.setOnClickListener(this);
        btn_dialog_2.setOnClickListener(this);
        btn_dialog_confirm.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_dialog_1:
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String date = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
                        btn_dialog_1.setText(date);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;

            case R.id.btn_dialog_2:
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        calendar.set(Calendar.HOUR_OF_DAY, i);
                        calendar.set(Calendar.MINUTE, i1);
                        String time = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime());
                        btn_dialog_2.setText(time);
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                timePickerDialog.show();
                break;
        }

    }
}
