package com.ntg.user.sa2aia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.ntg.user.sa2aia.products.ProductsFragment;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static String ORDER = "order";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, ProductsFragment.newInstance()).commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("CartFragment");
        fragment.onActivityResult(requestCode, resultCode, data);
    }
}
