package com.ntg.user.sa2aia.order_location;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.ntg.user.sa2aia.MainActivity;
import com.ntg.user.sa2aia.R;
import com.ntg.user.sa2aia.model.Location;
import com.ntg.user.sa2aia.model.Order;
import com.ntg.user.sa2aia.model.User;
import com.ntg.user.sa2aia.network.ApiClient;
import com.ntg.user.sa2aia.network.ProductService;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SavedLocationsFragment extends Fragment {
    private RecyclerView rvLocations;
    SavedAdsAdapter adsAdapter;
    FrameLayout frameLayout;
    LinearLayoutManager layoutManager;
    Button cancel;
    PublishSubject<Location> addressObservable = PublishSubject.create();
    Order order;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    void getLocations() {
        ProductService productService = ApiClient.getClient().create(ProductService.class);
        final Call<List<Location>> productListCall = productService
                .getSavedLocations(User.getEmail());
        productListCall.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                if (response.isSuccessful()) {
                    List<Location> locations = response.body();
                    if (locations.size() > 0) {
                        adsAdapter = new SavedAdsAdapter(locations, addressObservable);
                        rvLocations.setAdapter(adsAdapter);
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                Log.e("Products", t.getMessage());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_adresses, container, false);
        order = (Order) getArguments().getSerializable(MainActivity.ORDER);

        rvLocations = view.findViewById(R.id.adress_list);
        layoutManager = new LinearLayoutManager(getActivity());
        rvLocations.setLayoutManager(layoutManager);
        rvLocations.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
        getLocations();
        addressObservable.subscribe(new Observer<Location>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Location s) {
                order.setLocation(s);
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
        cancel = view.findViewById(R.id.cancel_button);
        frameLayout = view.findViewById(R.id.fram);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getFragmentManager().popBackStack();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getFragmentManager().popBackStack();
            }
        });

        return view;
    }

}
