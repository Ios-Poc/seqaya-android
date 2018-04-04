package com.ntg.user.sa2aia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.ntg.user.sa2aia.Checkout.CartFragment;
import com.ntg.user.sa2aia.Checkout.CartItemsCountListener;
import com.ntg.user.sa2aia.favourites.FavouritesFragment;
import com.ntg.user.sa2aia.order_history.OrderHistoryFragment;
import com.ntg.user.sa2aia.products.ProductsFragment;
import com.ntg.user.sa2aia.settings.SettingsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements CartItemsCountListener {

    public static String ORDER = "order";
    @BindView(R.id.include)
    Toolbar toolbar;
    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);


        AHBottomNavigationItem catalog = new AHBottomNavigationItem(getString(R.string.catalog),
                R.drawable.ic_bottle);
        AHBottomNavigationItem cart = new AHBottomNavigationItem(getString(R.string.shopping_cart),
                R.drawable.ic_shopping_cart);
        AHBottomNavigationItem history = new AHBottomNavigationItem(getString(R.string.history),
                R.drawable.ic_order_history);
        AHBottomNavigationItem fav = new AHBottomNavigationItem(getString(R.string.favourite),
                R.drawable.ic_fav);
        AHBottomNavigationItem settings = new AHBottomNavigationItem(getString(R.string.settings),
                R.drawable.ic_settings);

        bottomNavigationView.addItem(catalog);
        bottomNavigationView.addItem(cart);
        bottomNavigationView.addItem(history);
        bottomNavigationView.addItem(fav);
        bottomNavigationView.addItem(settings);

        bottomNavigationView.setAccentColor(getResources().getColor(R.color.colorAccent));
        bottomNavigationView.setInactiveColor(getResources().getColor(R.color.gray));

        bottomNavigationView.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);

        bottomNavigationView.setOnTabSelectedListener((
                (position, wasSelected) -> {
                    switch (position) {
                        case 0:
                            getFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.container,
                                            ProductsFragment.newInstance())
                                    .commit();
                            return true;

                        case 1:
                            getFragmentManager()
                                    .beginTransaction().addToBackStack(null)
                                    .replace(R.id.container, CartFragment.newInstance(),
                                            "CartFragment").commit();
                            return true;

                        case 2:
                            getFragmentManager().beginTransaction().addToBackStack(null)
                                    .replace(R.id.container, new OrderHistoryFragment()).commit();
                            return true;

                        case 3:
                            getFragmentManager()
                                    .beginTransaction().addToBackStack(null)
                                    .replace(R.id.container, new FavouritesFragment()).commit();
                            return true;

                        case 4:
                            getFragmentManager()
                                    .beginTransaction().addToBackStack(null)
                                    .replace(R.id.container, SettingsFragment.newInstance())
                                    .commit();
                            return true;
                        default:
                            return false;
                    }
                }));

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, ProductsFragment.newInstance())
                .commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("CartFragment");
        fragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void showCartItemsCountNotification(int count) {
        AHNotification notification = new AHNotification.Builder()
                .setText(count + "")
                .setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                        R.color.colorAccent))
                .setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white))
                .build();
        bottomNavigationView.setNotification(notification, 1);
    }

    @Override
    public void onCartItemsCountChanged(int count) {
        if (count == 0){
            bottomNavigationView.setNotification("" , 1);
        } else {
            showCartItemsCountNotification(count);
        }
    }
}
