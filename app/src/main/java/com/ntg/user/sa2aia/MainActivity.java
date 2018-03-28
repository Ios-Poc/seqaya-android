package com.ntg.user.sa2aia;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ntg.user.sa2aia.products.ProductsFragment;
import com.ntg.user.sa2aia.products.ShoppingCartItemCount;

import java.util.Locale;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, ShoppingCartItemCount {
    public static String ORDER = "order";
    private FrameLayout badge;
    private TextView countTextView;
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
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart:
                return true;
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void itemsCount(int count) {
     }

    private void setupBadge() {

        if (countTextView != null) {
            if (alertCount == 0) {
                if (badge.getVisibility() != View.GONE) {
                    badge.setVisibility(View.GONE);
                }
            } else {
                countTextView.setText("5");
                if (countTextView.getVisibility() != View.VISIBLE) {
                    countTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}
