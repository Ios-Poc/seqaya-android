package com.ntg.user.sa2aia;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ntg.user.sa2aia.model.Order;
import com.ntg.user.sa2aia.products.ProductsFragment;

import java.util.Locale;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    public static String ORDER = "order";
    public static final int requestCode = 1;
    private int alertCount = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        String languageToLoad = "ar";
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, ProductsFragment.newInstance()).commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == requestCode) {
            if (resultCode == Activity.RESULT_OK) {
                Order order = (Order) data.getSerializableExtra(MainActivity.ORDER);
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.container, CatalogFragment.newInstance());
            }
        }
    }
}
