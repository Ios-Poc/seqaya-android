package com.ntg.user.sa2aia.map_package;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private final int REQUEST_PERMISSION_LOCATION = 101;
    ImageView backImage;
    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    GPSTracker gps;
    public static boolean isMyLocationSet = false;
    LatLng currentLocation;
    double lattiude, longtude;
    Bundle bundle = new Bundle();
    Button nextButton, savedLocationButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        nextButton = findViewById(R.id.next_button_id);
        savedLocationButton = findViewById(R.id.saved_location_button_id);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        gps = new GPSTracker(this);

        if (!isNetworkConnected()) {
            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
            builder.setTitle("هناك خطأ");
            builder.setMessage("تأكد من الاتصال الأنترنت");
            builder.setPositiveButton("تم", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    MapsActivity.this.finish();
                }
            });
            builder.show();
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        savedLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mGoogleMap = googleMap;
        //   mGoogleMap.setMyLocationEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MapsActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSION_LOCATION);
        } else {
            getCurrentLocation();
            MarkerInfo markerInfo = new MarkerInfo();

        }

        mGoogleMap.setOnInfoWindowClickListener(this);

        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            List<Address> addresses;

            @Override
            public void onMapClick(final LatLng latLng) {


                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);

                mGoogleMap.clear();

                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                mGoogleMap.addMarker(markerOptions);


                bundle.putDouble("LAT", latLng.latitude);
                bundle.putDouble("LNG", latLng.longitude);
                Geocoder geocoder;
                addresses = null;

                geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());


                try {
                    addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (addresses.size() > 0) {
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    Toast.makeText(MapsActivity.this, address, Toast.LENGTH_SHORT).show();

                }

            }
        });

        mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            List<Address> addresses;
            Geocoder geocoder;

            @Override
            public boolean onMarkerClick(Marker marker) {
                lattiude = marker.getPosition().latitude;
                longtude = marker.getPosition().longitude;
                geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());

                try {
                    addresses = geocoder.getFromLocation(lattiude, longtude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                } catch (IOException e) {
                    e.printStackTrace();
                }

                bundle.putDouble("LAT", lattiude);
                bundle.putDouble("LNG", longtude);
                if (addresses.size() > 0) {
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()


                    MarkerInfo markerInfo = new MarkerInfo();
                    markerInfo.setAddress(address);
                    marker.setTag(markerInfo);
                    marker.showInfoWindow();
                    CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(MapsActivity.this);
                    googleMap.setInfoWindowAdapter(customInfoWindow);
                    onInfoWindowClick(marker);
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
                    finish();
                    startActivity(getIntent());
                } else {
                    Toast.makeText(MapsActivity.this, "!!!!", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }


    @Override
    public void onInfoWindowClick(Marker marker) {
      MarkerInfo markerInfo= (MarkerInfo) marker.getTag();
      String finaladdress=markerInfo.getAddress();
    }
}