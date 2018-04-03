package com.ntg.user.sa2aia.favourites;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ntg.user.sa2aia.BaseFragment;
import com.ntg.user.sa2aia.R;
import com.ntg.user.sa2aia.model.Product;
import com.ntg.user.sa2aia.model.User;
import com.ntg.user.sa2aia.network.ApiClient;
import com.ntg.user.sa2aia.network.ProductService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FavouritesFragment extends BaseFragment {

    @BindView(R.id.rv_products)
    RecyclerView favs_rv;
    @BindView(R.id.loading_indicator)
    LinearLayout loadingIndicator;
    private LinearLayoutManager linearLayoutManager;
    private List<Product> favProducts;
    private FavouritesAdapter favouritesAdapter;
    private Unbinder unBinder;


    public static FavouritesFragment newInstance() {
        FavouritesFragment fragment = new FavouritesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);
        unBinder = ButterKnife.bind(this, view);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        favProducts = new ArrayList<>();
        favs_rv.setLayoutManager(linearLayoutManager);
        getFavourites();

        return view;
    }

    void getFavourites() {
        ProductService productService = ApiClient.getClient().create(ProductService.class);
        productService.getFavs(User.getEmail()).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(@NonNull Call<List<Product>> call,
                                   @NonNull Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    loadingIndicator.setVisibility(View.GONE);
                    favProducts = response.body();
                    favouritesAdapter = new FavouritesAdapter(favProducts, getActivity());
                    favs_rv.setAdapter(favouritesAdapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unBinder.unbind();
    }
}
