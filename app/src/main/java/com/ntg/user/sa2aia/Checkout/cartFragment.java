package com.ntg.user.sa2aia.Checkout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ntg.user.sa2aia.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class cartFragment extends Fragment {


    @BindView(R.id.rv_product)
    RecyclerView products_rv;
    @BindView(R.id.main_page_toolbar)
    Toolbar toolbar;
    private List<Product> productList;
    private LinearLayoutManager linearLayoutManager;
    private ProductAdapter productAdapter;

    public static cartFragment newInstance() {
        cartFragment fragment = new cartFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        ButterKnife.bind(this , view);

        toolbar.setTitle("Checkout ");
        productList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(getActivity());

        ProductService productService = ApiClient.getClient().create(ProductService.class);
        final Call<List<Product>> productListCall = productService.getProducts();
        productListCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()){
                    productAdapter = new ProductAdapter(productList,getActivity());
                    productList = response.body();
                    productAdapter.setProductList(productList);
                    products_rv.setLayoutManager(linearLayoutManager);
                    products_rv.setAdapter(productAdapter);
                    productAdapter.notifyDataSetChanged();
                    Log.e("proSize" , productList.size() + "");
                    Toast.makeText(getActivity(), "res", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "Bala7", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
        return view;
    }
}
