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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ntg.user.sa2aia.model.APIError;
import com.ntg.user.sa2aia.model.Credential;
import com.ntg.user.sa2aia.model.User;
import com.ntg.user.sa2aia.model.UserAPI;
import com.ntg.user.sa2aia.network.ApiClient;
import com.ntg.user.sa2aia.network.ProductService;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
    @BindView(R.id.login_layout)
    ConstraintLayout loginLayout;
    @BindView(R.id.language_change)
    ImageView languageChange;
    @BindView(R.id.language_letter_indicator)
    TextView languageLetterIndicator;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        if (BuildConfig.DEBUG) {
            loginEmailEditText.setText("seqaya@ntgclarity.com");
            loginPasswordEditText.setText("1234");
        }
        String languageToLoad = "ar";
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }

    @OnClick({R.id.login_button, R.id.reg_nav_button, R.id.facebook_button, R.id.twitter_button,
            R.id.google_button, R.id.language_change})
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
            case R.id.language_change:
                if (languageLetterIndicator.getText().toString()
                        .equals(getString(R.string.english))) {
                    languageLetterIndicator.setText(R.string.arabic);
                } else {
                    languageLetterIndicator.setText(R.string.english);
                }

                break;
        }
    }

    private void login() {
        if (getCredential() != null) {
            retrofit = ApiClient.getClient();
            retrofit.create(ProductService.class)
                    .login(getCredential())
                    .enqueue(new Callback<UserAPI>() {
                        @Override
                        public void onResponse(@NonNull Call<UserAPI> call,
                                               @NonNull Response<UserAPI> response) {
                            if (response.isSuccessful()) {
                                UserAPI user = response.body();
                                if (user != null) {
                                    User.setEmail(user.getEmail());
                                    User.setName(user.getName());
                                    User.setPassword(user.getPassword());
                                    navigateToMainActivity();
                                }
                            } else {
                                try {
                                    String errorJson = response.errorBody().string();
                                    Log.d("login error", errorJson);
                                    showErrorMessage(errorJson);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<UserAPI> call, @NonNull Throwable t) {
                            Snackbar.make(loginLayout, "تأكد من اتصال الانترنت",
                                    Snackbar.LENGTH_LONG)
                                    .show();
                            Log.d("connection error", t.getMessage());
                        }
                    });
        }
    }

    private void showErrorMessage(String jsonString) {
        Gson gson = new Gson();
        APIError apiError = gson.fromJson(jsonString, APIError.class);
        Snackbar.make(loginLayout, apiError.getMessage(), Snackbar.LENGTH_LONG).show();
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
