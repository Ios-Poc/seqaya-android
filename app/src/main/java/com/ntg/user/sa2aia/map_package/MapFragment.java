package com.ntg.user.sa2aia.map_package;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ntg.user.sa2aia.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


public class MapFragment extends Fragment implements OnMapReadyCallback {


    private final int REQUEST_PERMISSION_LOCATION = 101;
    ImageView backImage;
    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;
    //    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    GPSTracker gps;
    public static boolean isMyLocationSet = false;
    LatLng currentLocation;
    double lattiude;
    double longtude;
    Bundle bundle = new Bundle();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_map, container, false);

//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//        gps = new GPSTracker(getActivity());


        return view;
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        //   mGoogleMap.setMyLocationEnabled(true);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSION_LOCATION);
        } else {
            getCurrentLocation();
        }
//        getCurrentLocation();

        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            List<Address> addresses;

            @Override
            public void onMapClick(final LatLng latLng) {


                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();
                // Setting the position for the marker
                markerOptions.position(latLng);
                // Setting the title for the marker.
                // This will be displayed on taping the marker
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                // Clears the previously touched position
                mGoogleMap.clear();

                // Animating to the touched position
                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                // Placing a marker on the touched position
                mGoogleMap.addMarker(markerOptions);


                bundle.putDouble("LAT", latLng.latitude);
                bundle.putDouble("LNG", latLng.longitude);
                Geocoder geocoder;
                addresses = null;

                geocoder = new Geocoder(getActivity(), Locale.getDefault());


                try {
                    addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (addresses.size() > 0) {
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

                }
            }
        });
        mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            List<Address> addresses;
            Geocoder geocoder;

            @Override
            public boolean onMarkerClick(Marker marker) {
//                      latLng.latitude = gps.getLatitude();
                lattiude = marker.getPosition().latitude;
                longtude = marker.getPosition().longitude;
                geocoder = new Geocoder(getActivity(), Locale.getDefault());

                try {
                    addresses = geocoder.getFromLocation(lattiude, longtude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                } catch (IOException e) {
                    e.printStackTrace();
                }

                bundle.putDouble("LAT", lattiude);
                bundle.putDouble("LNG", longtude);
                if (addresses.size() > 0) {
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()


                }

                return false;
            }
        });
    }

    private void getCurrentLocation() {
        if (gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            currentLocation = new LatLng(latitude, longitude);
            mGoogleMap.addMarker(new MarkerOptions().position(currentLocation));
            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(gps.getLatitude(), gps.getLongitude()), 12.0f));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(getActivity(), "!!!!", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

}
