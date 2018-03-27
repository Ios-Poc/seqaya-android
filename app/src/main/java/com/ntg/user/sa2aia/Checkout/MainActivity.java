package com.ntg.user.sa2aia.Checkout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ntg.user.sa2aia.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container , cartFragment.newInstance()).commit();
    }
    }

