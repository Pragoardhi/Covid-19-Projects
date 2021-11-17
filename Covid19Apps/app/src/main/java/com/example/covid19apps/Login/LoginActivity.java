package com.example.covid19apps.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.covid19apps.HomeActivity;
import com.example.covid19apps.R;
import com.example.covid19apps.Session.SessionManagerUtil;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText email;
    TextInputEditText password;
    Button loginButton;
    ProgressDialog pd;
    private LoginJsonPlaceHolderApi loginJsonPlaceHolderApi;
    private Executor backgroundThread = Executors.newSingleThreadExecutor();
    private Executor mainThread = new Executor() {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(Runnable command) {
            mainThreadHandler.post(command);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pd = new ProgressDialog(LoginActivity.this);
        email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        loginButton = findViewById(R.id.loginButton);

        email.addTextChangedListener(loginTextWatcher);
        password.addTextChangedListener(loginTextWatcher);


        loginButton.setOnClickListener(v -> {
            Boolean check = validateEmail();
            pd.setMessage("loading");
            pd.show();
            if(check){
                Login();
            }
        });
    }

    @Override
    protected void onResume() {
        boolean isAllowed = SessionManagerUtil.getInstance().isSessionActive(this, Calendar.getInstance().getTime());
        if (isAllowed) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        super.onResume();
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
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<LoginPost> call, Response<LoginPost> response) {
                if(response.code() == 200){
                    backgroundThread.execute(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void run() {
                            SystemClock.sleep(3000);
                            mainThread.execute(new Runnable() {
                                @Override
                                public void run() {
                                    startAndStoreSession();
                                    pd.cancel();
                                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                }else{
                    Toast.makeText(LoginActivity.this,"Invalid email or password",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginPost> call, Throwable t) {

            }
        });
    }

    private String generateToken(String email, String password){
        String feeds = email+":"+password;
        String token = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            token = Base64.getEncoder().encodeToString(feeds.getBytes());
        } else {
            token = feeds;
        }
        return token;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startAndStoreSession(){
        SessionManagerUtil.getInstance().storeUserToken(this, generateToken(email.getText().toString(), password.getText().toString()));
        SessionManagerUtil.getInstance().startUserSession(this, 1); //expired ketika 1 hari tidak login
    }
}