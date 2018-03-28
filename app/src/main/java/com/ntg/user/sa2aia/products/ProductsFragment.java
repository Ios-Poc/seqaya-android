package com.ntg.user.sa2aia.products;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ntg.user.sa2aia.Checkout.CartFragment;
import com.ntg.user.sa2aia.R;
import com.ntg.user.sa2aia.model.Product;
import com.ntg.user.sa2aia.network.ApiClient;
import com.ntg.user.sa2aia.network.ProductService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class ProductsFragment extends Fragment implements ShoppingCartItemCount {

    @BindView(R.id.my_toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_products)
    RecyclerView products_rv;
    @BindView(R.id.check_out)
    Button checkOut;
    private List<Product> productList;
    private LinearLayoutManager linearLayoutManager;
    private ProductAdapter productAdapter;
    private FrameLayout redCircle;
    private TextView countTextView;
    private int alertCount = 0;

    public static ProductsFragment newInstance() {
        ProductsFragment fragment = new ProductsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        productList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(getActivity());
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        getProducts();

        return view;
    }

    @Override
    public void itemsCount(int count) {
        updateAlertIcon(String.valueOf(count));
    }

    void getProducts() {
        ProductService productService = ApiClient.getClient().create(ProductService.class);
        final Call<List<Product>> productListCall = productService.getProducts();
        productListCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    productAdapter = new ProductAdapter(productList, getActivity(), ProductsFragment.this);
                    productList = response.body();
                    productAdapter.setProductList(productList);
                    products_rv.setLayoutManager(linearLayoutManager);
                    products_rv.setAdapter(productAdapter);
                    productAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("Products", t.getMessage());
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                productAdapter.clear();
                if (s.equals("")) {
                    getProducts();
                } else {
                    search(s);
                }
                return false;
            }
        });


        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort:
                sortCatalog();
                break;
            case R.id.cart: {

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, CartFragment.newInstance());
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void sortCatalog() {
        final android.support.design.widget.BottomSheetDialog mBottomSheetDialog = new android.support.design.widget.BottomSheetDialog(getActivity());
        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_bottom_sheet, null);
        TextView price = dialogView.findViewById(R.id.price);
        price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(productList, new Comparator<Product>() {
                    @Override
                    public int compare(Product product, Product t1) {
                        if (product.getPrice() > t1.getPrice()) {
                            return 0;
                        } else if (product.getPrice() < t1.getPrice()) {
                            return -1;
                        }
                        return 1;
                    }
                });
                productAdapter.notifyDataSetChanged();
                mBottomSheetDialog.dismiss();
            }
        });
        TextView bottle_size = dialogView.findViewById(R.id.bottle_size);
        bottle_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(productList, new Comparator<Product>() {
                    @Override
                    public int compare(Product product, Product t1) {
                        if (product.getBottleSize() > t1.getBottleSize()) {
                            return 0;
                        } else if (product.getBottleSize() < t1.getBottleSize()) {
                            return -1;
                        }
                        return 1;
                    }
                });
                productAdapter.notifyDataSetChanged();
                mBottomSheetDialog.dismiss();
            }
        });
        TextView bottle_per_package = dialogView.findViewById(R.id.bottle_per_package);
        bottle_per_package.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(productList, new Comparator<Product>() {
                    @Override
                    public int compare(Product product, Product t1) {
                        if (product.getNo_bpp() > t1.getNo_bpp()) {
                            return 0;
                        } else if (product.getNo_bpp() < t1.getNo_bpp()) {
                            return -1;
                        }
                        return 1;
                    }
                });
                productAdapter.notifyDataSetChanged();
                mBottomSheetDialog.dismiss();
            }
        });
        mBottomSheetDialog.setContentView(dialogView);
        mBottomSheetDialog.show();
    }

    private void updateAlertIcon(String count) {
        // if alert count extends into two digits, just show the red circle
        if (Integer.parseInt(count) > 0) {
            countTextView.setText(count);
        } else {
            countTextView.setText("");
            redCircle.setVisibility(GONE);
        }

        redCircle.setVisibility((Integer.parseInt(count) > 0) ? VISIBLE : GONE);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        final MenuItem alertMenuItem = menu.findItem(R.id.cart);
        FrameLayout rootView = (FrameLayout) alertMenuItem.getActionView();
        redCircle = (FrameLayout) rootView.findViewById(R.id.view_alert_red_circle);
        countTextView = (TextView) rootView.findViewById(R.id.view_alert_count_textview);
        countTextView.setText(String.valueOf(alertCount));
        updateAlertIcon("0");
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(alertMenuItem);
            }
        });

    }

    void search(String keyWord) {

        ProductService productService = ApiClient.getClient().create(ProductService.class);
        final Call<List<Product>> productListCall = productService.getSearchResult(keyWord);
        productListCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    productAdapter = new ProductAdapter(productList, getActivity(), ProductsFragment.this);
                    productList = response.body();
                    productAdapter.setProductList(productList);
                    products_rv.setLayoutManager(linearLayoutManager);
                    products_rv.setAdapter(productAdapter);
                    productAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }
}
