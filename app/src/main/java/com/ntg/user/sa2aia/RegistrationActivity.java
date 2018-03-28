package com.ntg.user.sa2aia;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ntg.user.sa2aia.model.User;
import com.ntg.user.sa2aia.network.ApiClient;
import com.ntg.user.sa2aia.network.ProductService;

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
                    .enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(@NonNull Call<User> call,
                                               @NonNull Response<User> response) {
                            if (response.isSuccessful()) {
                                User user = response.body();
                                if (user != null) {
                                    User.setCurrentUser(user);
                                    navigateToLogin();
                                }
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {

                        }
                    });
    }

    private void navigateToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private User getUser() {
        String name = regNameEditText.getText().toString();
        String email = regEmailEditText.getText().toString();
        String password = regPasswordEditText.getText().toString();
        String rePassword = regRePasswordEditText.getText().toString();
        String phone_number = regPhoneEditText.getText().toString();

        if (notNullOrEmpty(name) && isValidEmailAddress(email) && notNullOrEmpty(password) &&
                password.equals(rePassword) && notNullOrEmpty(phone_number))
            return new User(name, email, password, phone_number);
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
}
