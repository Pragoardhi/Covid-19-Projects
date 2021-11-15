package com.example.covid19apps.Login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.covid19apps.HomeActivity;
import com.example.covid19apps.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText email;
    TextInputEditText password;
    Button loginButton;
    private LoginJsonPlaceHolderApi loginJsonPlaceHolderApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        loginButton = findViewById(R.id.loginButton);

        email.addTextChangedListener(loginTextWatcher);
        password.addTextChangedListener(loginTextWatcher);


        loginButton.setOnClickListener(v -> {
            Boolean check = validateEmail();
            if(check){
                Login();
            }
        });
    }

    private Boolean validateEmail() {
        String emailInput = email.getText().toString();
        if(!emailInput.isEmpty()&& Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            return true;
        }else{
            Toast.makeText(this,"Invalid Email Addres", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String emailInput = email.getText().toString().trim();
            String passwordInput = password.getText().toString().trim();
            loginButton.setEnabled(!emailInput.isEmpty() && !passwordInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    public void Login(){

        String postUrl = "https://talentpool.oneindonesia.id/api/user/";
        String emailInput = email.getText().toString().trim();
        String passwordInput = password.getText().toString().trim();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(postUrl).addConverterFactory(GsonConverterFactory.create()).build();

        loginJsonPlaceHolderApi = retrofit.create(LoginJsonPlaceHolderApi.class);
        HashMap<String,String> data = new HashMap<>();
        data.put("username", emailInput);
        data.put("password",passwordInput);

        Call<LoginPost> call = loginJsonPlaceHolderApi.createPost(data);

        call.enqueue(new Callback<LoginPost>() {
            @Override
            public void onResponse(Call<LoginPost> call, Response<LoginPost> response) {
                if(response.code() == 200){
                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this,"Invalid email or password",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginPost> call, Throwable t) {

            }
        });
    }
}