package com.ntg.user.sa2aia;

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
    public SavedAdsAdapter(List<String> adresses) {
        this.adresses = adresses;
    }

    List<String> adresses;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.adress_row,parent,false);
        return new AdsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AdsViewHolder adsViewHolder= (AdsViewHolder) holder;
        ((AdsViewHolder) holder).adressTxt.setText(adresses.get(position));

    }

    @Override
    public int getItemCount() {
        return adresses.size();
    }

    public class AdsViewHolder extends RecyclerView.ViewHolder {
        TextView adressTxt;
        public AdsViewHolder(View itemView) {
            super(itemView);
            adressTxt=itemView.findViewById(R.id.adress_txt);
        }
    }
}
