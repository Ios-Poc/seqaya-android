package com.ntg.user.sa2aia;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by devsaad on 3/26/2018.
 */

public class SavedAdsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public SavedAdsAdapter(List<String> locations) {
        this.locations = locations;
    }

    List<String> locations;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.adress_row,parent,false);
        return new AdsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        AdsViewHolder adsViewHolder= (AdsViewHolder) holder;
        ((AdsViewHolder) holder).adressTxt.setText(locations.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("adress",((AdsViewHolder) holder).adressTxt.getText().toString());
            }
        });

    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public class AdsViewHolder extends RecyclerView.ViewHolder {
        TextView adressTxt;
        public AdsViewHolder(View itemView) {
            super(itemView);
            adressTxt=itemView.findViewById(R.id.adress_txt);

        }
    }
}
