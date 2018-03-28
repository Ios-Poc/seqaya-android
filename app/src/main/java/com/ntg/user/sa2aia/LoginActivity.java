package com.ntg.user.sa2aia;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.ntg.user.sa2aia.model.Credential;
import com.ntg.user.sa2aia.model.User;
import com.ntg.user.sa2aia.model.UserAPI;
import com.ntg.user.sa2aia.network.ApiClient;
import com.ntg.user.sa2aia.network.ProductService;

import java.io.IOException;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ntg.user.sa2aia.StringUtil.isNullOrEmpty;
import static com.ntg.user.sa2aia.StringUtil.isValidEmailAddress;
import static com.ntg.user.sa2aia.StringUtil.notNullOrEmpty;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_email_edit_text)
    EditText loginEmailEditText;
    @BindView(R.id.login_password_edit_text)
    EditText loginPasswordEditText;
    @BindView(R.id.login_button)
    Button loginButton;
    @BindView(R.id.reg_nav_button)
    Button regNavButton;
    @BindView(R.id.facebook_button)
    ImageButton facebookButton;
    @BindView(R.id.twitter_button)
    ImageButton twitterButton;
    @BindView(R.id.google_button)
    ImageButton googleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        String languageToLoad = "ar";
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

    }

    @OnClick({R.id.login_button, R.id.reg_nav_button, R.id.facebook_button, R.id.twitter_button, R.id.google_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                login();
                break;
            case R.id.reg_nav_button:
                navigateToRegistrationActivity();
                break;
            case R.id.facebook_button:
                break;
            case R.id.twitter_button:
                break;
            case R.id.google_button:
                break;
        }
    }

    private void login() {
        if (getCredential() != null)
            ApiClient.getClient().create(ProductService.class)
                    .login(getCredential())
                    .enqueue(new Callback<UserAPI>() {
                        @Override
                        public void onResponse(@NonNull Call<UserAPI> call,
                                               @NonNull Response<UserAPI> response) {
                            if (response.isSuccessful()) {
                                UserAPI user = (UserAPI) response.body();
                                if (user != null) {
                                    User.setEmail(user.getEmail());
                                    User.setName(user.getName());
                                    User.setPassword(user.getPassword());
                                    navigateToMainActivity();
                                }
                            } else {
                                try {
                                    Log.d("login error", response.errorBody().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<UserAPI> call, @NonNull Throwable t) {

                        }
                    });
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void navigateToRegistrationActivity() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
        finish();
    }

    public Credential getCredential() {
        String email = loginEmailEditText.getText().toString();
        String password = loginPasswordEditText.getText().toString();
        if (isValidEmailAddress(email) && notNullOrEmpty(password))
            return new Credential(email, password);
        if (!isValidEmailAddress(email))
            loginEmailEditText.setError("Invalid email");
        if (isNullOrEmpty(password))
            loginPasswordEditText.setError("Password blank");
        return null;
    }
}
