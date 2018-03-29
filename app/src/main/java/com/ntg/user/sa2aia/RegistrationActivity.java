package com.ntg.user.sa2aia;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.ntg.user.sa2aia.model.APIError;
import com.ntg.user.sa2aia.model.User;
import com.ntg.user.sa2aia.model.UserAPI;
import com.ntg.user.sa2aia.network.ApiClient;
import com.ntg.user.sa2aia.network.ProductService;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.ntg.user.sa2aia.StringUtil.isNullOrEmpty;
import static com.ntg.user.sa2aia.StringUtil.isValidEmailAddress;
import static com.ntg.user.sa2aia.StringUtil.notNullOrEmpty;

public class RegistrationActivity extends AppCompatActivity {

    @BindView(R.id.reg_name_edit_text)
    EditText regNameEditText;
    @BindView(R.id.reg_email_edit_text)
    EditText regEmailEditText;
    @BindView(R.id.reg_phone_edit_text)
    EditText regPhoneEditText;
    @BindView(R.id.reg_password_edit_text)
    EditText regPasswordEditText;
    @BindView(R.id.reg_rePassword_edit_text)
    EditText regRePasswordEditText;
    @BindView(R.id.registration_button)
    Button registrationButton;
    @BindView(R.id.login_nav_button)
    Button loginNavButton;
    @BindView(R.id.registration_layout)
    ConstraintLayout registrationLayout;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        String languageToLoad = "ar";
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

    }

    @OnClick({R.id.registration_button, R.id.login_nav_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.registration_button:
                addNewUser();
                break;
            case R.id.login_nav_button:
                navigateToLoginActivity();
                break;
        }
    }

    private void addNewUser() {
        if (getUser() != null)
            ApiClient.getClient().create(ProductService.class)
                    .addNewUser(getUser())
                    .enqueue(new Callback<UserAPI>() {
                        @Override
                        public void onResponse(@NonNull Call<UserAPI> call,
                                               @NonNull Response<UserAPI> response) {
                            if (response.isSuccessful()) {
                                UserAPI user = response.body();
                                if (user != null) {
                                    User.setName(user.getName());
                                    User.setPassword(user.getPassword());
                                    User.setEmail(user.getEmail());
                                    navigateToLogin();
                                }
                            } else {
                                try {
                                    String errorJson = response.errorBody().string();
                                    showErrorMessage(errorJson);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<UserAPI> call, @NonNull Throwable t) {
                            Snackbar.make(registrationLayout, "Check your connection",
                                    Snackbar.LENGTH_LONG)
                                    .show();
                        }
                    });
    }

    private void navigateToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private UserAPI getUser() {
        String name = regNameEditText.getText().toString();
        String email = regEmailEditText.getText().toString();
        String password = regPasswordEditText.getText().toString();
        String rePassword = regRePasswordEditText.getText().toString();
        String phone_number = regPhoneEditText.getText().toString();

        if (notNullOrEmpty(name) && isValidEmailAddress(email) && notNullOrEmpty(password) &&
                password.equals(rePassword) && notNullOrEmpty(phone_number)) {
            UserAPI userAPI = new UserAPI();
            userAPI.setEmail(email);
            userAPI.setPassword(password);
            userAPI.setName(name);
            return userAPI;
        }

        // return new User(name, email, password, phone_number);
        if (isNullOrEmpty(name))
            regNameEditText.setError("This field shouldn't be blank");
        if (!isValidEmailAddress(email))
            regEmailEditText.setError("Invalid email address");
        if (isNullOrEmpty(password))
            regPasswordEditText.setError("This field shouldn't be blank");
        if (!password.equals(rePassword))
            regRePasswordEditText.setError("password mismatching");
        if (isNullOrEmpty(phone_number))
            regPhoneEditText.setError("This field shouldn't be blank");
        return null;
    }

    private void navigateToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void showErrorMessage(String jsonString) {
        Gson gson = new Gson();
        APIError apiError = gson.fromJson(jsonString, APIError.class);
        Snackbar.make(registrationLayout, apiError.getMessage(), Snackbar.LENGTH_LONG).show();
    }
}
