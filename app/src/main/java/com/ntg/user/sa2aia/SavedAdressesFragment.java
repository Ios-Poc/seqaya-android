package com.ntg.user.sa2aia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;


public class SavedAdressesFragment extends Fragment {
    private RecyclerView listOfAdresses;
    SavedAdsAdapter adsAdapter;
    LinearLayoutManager layoutManager;
    List<String> locations;
    Button cancel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_adresses, container, false);
        listOfAdresses = view.findViewById(R.id.adress_list);
        layoutManager = new LinearLayoutManager(this.getContext());
        adsAdapter = new SavedAdsAdapter(locations);
        listOfAdresses.setLayoutManager(layoutManager);
        listOfAdresses.setAdapter(adsAdapter);
        cancel = view.findViewById(R.id.cancel_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }

}
