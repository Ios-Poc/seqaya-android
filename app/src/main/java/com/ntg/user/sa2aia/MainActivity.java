package com.ntg.user.sa2aia.Checkout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

<<<<<<< HEAD:app/src/main/java/com/ntg/user/sa2aia/Checkout/MainActivity.java
import com.ntg.user.sa2aia.R;
=======
import com.ntg.user.sa2aia.catalog.CatalogFragment;
>>>>>>> master:app/src/main/java/com/ntg/user/sa2aia/MainActivity.java

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD:app/src/main/java/com/ntg/user/sa2aia/Checkout/MainActivity.java

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container , cartFragment.newInstance()).commit();
=======
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, CatalogFragment.newInstance())
                .commit();
>>>>>>> master:app/src/main/java/com/ntg/user/sa2aia/MainActivity.java
    }
    }

