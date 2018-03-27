package com.ntg.user.sa2aia;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            API.getClient().create(ServiceInterface.class)
                    .addNewUser(getUser())
                    .enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(@NonNull Call<User> call,
                                               @NonNull Response<User> response) {
                            if (response.isSuccessful()) {
                                User user = response.body();
                                if (user != null)
                                    Toast.makeText(RegistrationActivity.this,
                                            user.getName(),
                                            Toast.LENGTH_SHORT)
                                            .show();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {

                        }
                    });
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
        if (notNullOrEmpty(name))
            regNameEditText.setError("This field shouldn't be blank");
        if (isValidEmailAddress(email))
            regEmailEditText.setError("Invalid email address");
        if (notNullOrEmpty(password))
            regPasswordEditText.setError("This field shouldn't be blank");
        if (password.equals(rePassword))
            regRePasswordEditText.setError("password mismatching");
        if (notNullOrEmpty(phone_number))
            regPhoneEditText.setError("This field shouldn't be blank");
        return null;
    }

    private void navigateToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
