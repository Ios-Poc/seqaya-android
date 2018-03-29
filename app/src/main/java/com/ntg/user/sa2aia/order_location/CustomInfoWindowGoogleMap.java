package com.ntg.user.sa2aia.order_location;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.ntg.user.sa2aia.R;

/**
 * Created by Mohamed on 3/27/2018.
 */

public class CustomInfoWindowGoogleMap implements GoogleMap.InfoWindowAdapter {
    private Context context;


    public CustomInfoWindowGoogleMap(Context ctx) {
        context = ctx;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = ((Activity) context).getLayoutInflater()
                .inflate(R.layout.map_custom_infowindow, null);

        TextView address = view.findViewById(R.id.address);
        ImageView img = view.findViewById(R.id.add);


        MarkerInfo markerInfo = (MarkerInfo) marker.getTag();


        address.setText(markerInfo.getAddress());


        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(context, "hiiii", Toast.LENGTH_SHORT).show();

                return false;
            }
        });


        return view;
    }


}
