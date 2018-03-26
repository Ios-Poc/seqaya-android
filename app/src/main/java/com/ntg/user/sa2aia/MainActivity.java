package com.ntg.user.sa2aia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ntg.user.sa2aia.catalog.CatalogFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, CatalogFragment.newInstance())
                .commit();
    }
}
