package com.ntg.user.sa2aia;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ntg.user.sa2aia.model.Order;
import com.ntg.user.sa2aia.order_location.OrderMapActivity;

public class MainActivity extends AppCompatActivity {

    public static final String ORDER = "order";
    public static final int requestCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.container, new SavedLocationsFragment())
//                .commit();

        Intent i = new Intent(this, OrderMapActivity.class);
        i.putExtra(MainActivity.ORDER,new Order("aa"));
        startActivityForResult(i, requestCode);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == requestCode) {
            if (resultCode == Activity.RESULT_OK) {
                Order order = (Order) data.getSerializableExtra(MainActivity.ORDER);
                Toast.makeText(this, order.getLocation(), Toast.LENGTH_LONG).show();
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.container, CatalogFragment.newInstance());
            }
        }
    }
}
