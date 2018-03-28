package com.ntg.user.sa2aia.order_location;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ntg.user.sa2aia.MainActivity;
import com.ntg.user.sa2aia.R;
import com.ntg.user.sa2aia.model.Order;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;


public class SavedLocationsFragment extends Fragment {
    private RecyclerView rvLocations;
    SavedAdsAdapter adsAdapter;
    LinearLayoutManager layoutManager;
    List<String> locations;
    Button cancel;
    PublishSubject<String> addressObservable = PublishSubject.create();
    Order order;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_adresses, container, false);
        order = (Order) getArguments().getSerializable(MainActivity.ORDER);

        rvLocations = view.findViewById(R.id.adress_list);
        layoutManager = new LinearLayoutManager(getActivity());

        rvLocations.setLayoutManager(layoutManager);
        addressObservable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                order.setLocation(s);
                adsAdapter = new SavedAdsAdapter(locations);
                Intent returnIntent = new Intent();
                returnIntent.putExtra(MainActivity.ORDER, order);
                getActivity().setResult(Activity.RESULT_OK, returnIntent);
                getActivity().finish();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        rvLocations.setAdapter(adsAdapter);
        cancel = view.findViewById(R.id.cancel_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getFragmentManager().popBackStack();
            }
        });

        return view;
    }

}
