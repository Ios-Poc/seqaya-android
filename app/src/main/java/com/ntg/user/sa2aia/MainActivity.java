package com.ntg.user.sa2aia;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ntg.user.sa2aia.map_package.MapFragment;

public class MainActivity extends AppCompatActivity {
MapFragment mapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapFragment=new MapFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.container_id, mapFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
}
