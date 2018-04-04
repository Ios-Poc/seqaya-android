package com.ntg.user.sa2aia.products;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ntg.user.sa2aia.BaseFragment;
import com.ntg.user.sa2aia.Checkout.CartItemsCountListener;
import com.ntg.user.sa2aia.R;
import com.ntg.user.sa2aia.model.Fav;
import com.ntg.user.sa2aia.model.Product;
import com.ntg.user.sa2aia.model.User;
import com.ntg.user.sa2aia.network.ApiClient;
import com.ntg.user.sa2aia.network.ProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jp.wasabeef.recyclerview.animators.OvershootInLeftAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductsFragment extends BaseFragment implements FavouriteButtonClickListener {

    @BindView(R.id.rv_products)
    RecyclerView products_rv;
    Animation checkOutAnimation, toolBarAnimation;
    @BindView(R.id.loading_indicator)
    LinearLayout loadingIndicator;
    private List<Product> productList;
    private List<Product> favProducts;
    private LinearLayoutManager linearLayoutManager;
    private ProductAdapter productAdapter;
    private Unbinder unbinder;
    private CartItemsCountListener countListener;


    public static ProductsFragment newInstance() {

        return new ProductsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFavourites();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);
        unbinder = ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        int res = R.anim.layout_animation_fall_down;
        productList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(getActivity());
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(getString(R.string.app_name));
        }

        checkOutAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.from_bottom);
        toolBarAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.from_top);
        products_rv.setItemAnimator(new OvershootInLeftAnimator());
        products_rv.getItemAnimator().setAddDuration(700);
        getProducts();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            countListener = (CartItemsCountListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    void getProducts() {
        ProductService productService = ApiClient.getClient().create(ProductService.class);
        final Call<List<Product>> productListCall = productService.getProducts();
        productListCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    if (loadingIndicator != null && products_rv != null) {
                        loadingIndicator.setVisibility(View.GONE);
                        productAdapter = new ProductAdapter(productList, favProducts, getActivity(),
                                countListener, ProductsFragment.this);
                        productList = response.body();
                        productAdapter.setProductList(productList);
                        productAdapter.setProductFavouriteList(favProducts);
                        products_rv.setLayoutManager(linearLayoutManager);
                        products_rv.setAdapter(productAdapter);
                        productAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("Products", t.getMessage());
            }
        });
    }

    void getFavourites() {
        ProductService productService = ApiClient.getClient().create(ProductService.class);
        productService.getFavs(User.getEmail()).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(@NonNull Call<List<Product>> call,
                                   @NonNull Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    favProducts = response.body();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {

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
            case R.id.sort: {
                sortCatalog();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void sortCatalog() {
        final BottomSheetDialog mBottomSheetDialog =
                new BottomSheetDialog(getActivity());
        View dialogView = getActivity().getLayoutInflater()
                .inflate(R.layout.dialog_bottom_sheet, null);
        TextView price = dialogView.findViewById(R.id.price);
        price.setOnClickListener(view -> {
            Collections.sort(productList, (product, t1) -> {
                if (product.getPrice() > t1.getPrice()) {
                    return 0;
                } else if (product.getPrice() < t1.getPrice()) {
                    return -1;
                }
                return 1;
            });
            productAdapter.notifyDataSetChanged();
            mBottomSheetDialog.dismiss();
        });
        TextView bottle_size = dialogView.findViewById(R.id.bottle_size);
        bottle_size.setOnClickListener(view -> {
            Collections.sort(productList, (product, t1) -> {
                if (product.getBottleSize() > t1.getBottleSize()) {
                    return 0;
                } else if (product.getBottleSize() < t1.getBottleSize()) {
                    return -1;
                }
                return 1;
            });
            productAdapter.notifyDataSetChanged();
            mBottomSheetDialog.dismiss();
        });
        TextView bottle_per_package = dialogView.findViewById(R.id.bottle_per_package);
        bottle_per_package.setOnClickListener(view -> {
            Collections.sort(productList, (product, t1) -> {
                if (product.getNo_bpp() > t1.getNo_bpp()) {
                    return 0;
                } else if (product.getNo_bpp() < t1.getNo_bpp()) {
                    return -1;
                }
                return 1;
            });
            productAdapter.notifyDataSetChanged();
            mBottomSheetDialog.dismiss();
        });
        mBottomSheetDialog.setContentView(dialogView);
        mBottomSheetDialog.show();
    }

    void search(String keyWord) {

        ProductService productService = ApiClient.getClient().create(ProductService.class);
        final Call<List<Product>> productListCall = productService.getSearchResult(keyWord);
        productListCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    productAdapter = new ProductAdapter(productList, favProducts, getActivity(),
                            countListener, ProductsFragment.this);
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

    @Override
    public void onLike(Product product) {
        Fav fav = new Fav(User.getEmail(), String.valueOf(product.getId()));
        ProductService productService = ApiClient.getClient().create(ProductService.class);
        productService.addFav(fav)
                .enqueue(new Callback<Fav>() {
                    @Override
                    public void onResponse(@NonNull Call<Fav> call,
                                           @NonNull Response<Fav> response) {
                        if (response.isSuccessful()) {
                            Fav fav = response.body();
                            Log.d("fav", fav.getProductId());
                        } else {
                            String error = null;
                            try {
                                error = response.errorBody().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Log.d("fav", error);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Fav> call, @NonNull Throwable t) {
                        Log.d("fav", t.getMessage());
                    }
                });
    }

    @Override
    public void onUnLike(Product product) {
        ApiClient.getClient().create(ProductService.class)
                .deleteFav(String.valueOf(product.getId()))
                .enqueue(new Callback<Fav>() {
                    @Override
                    public void onResponse(@NonNull Call<Fav> call,
                                           @NonNull Response<Fav> response) {
                        if (response.isSuccessful()) {
                            Fav fav = response.body();
                            if (fav != null)
                                Log.d("delete fav", fav.getProductId());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Fav> call, @NonNull Throwable t) {

                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        Log.d("product", "onDestroyView");
    }
}
