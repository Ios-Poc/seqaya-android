package com.ntg.user.sa2aia.order_location;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ntg.user.sa2aia.R;
import com.ntg.user.sa2aia.model.Location;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by devsaad on 3/26/2018.
 */

public class SavedAdsAdapter extends RecyclerView.Adapter<SavedAdsAdapter.AdsViewHolder> {
    PublishSubject<String> addressObservable;

    public SavedAdsAdapter(List<Location> locations, PublishSubject<String> addressObservable) {
        this.locations = locations;
        this.addressObservable = addressObservable;
    }

    List<Location> locations = new ArrayList<>();


    @Override
    public AdsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adress_row, parent, false);
        return new AdsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdsViewHolder holder, int position) {
        AdsViewHolder adsViewHolder = (AdsViewHolder) holder;
        holder.adressTxt.setText(locations.get(position).getAddress());
        ((AdsViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressObservable.onNext(((AdsViewHolder) holder).adressTxt.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    class AdsViewHolder extends RecyclerView.ViewHolder {
        TextView adressTxt;

        public AdsViewHolder(View itemView) {
            super(itemView);
            adressTxt = itemView.findViewById(R.id.adress_txt);

        }
    }
}
