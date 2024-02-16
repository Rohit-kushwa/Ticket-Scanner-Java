package com.example.e_ticketscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class LoginActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://eticket-uat.ckcloud.in/api/";
    interface apiInterface{
        @GET("Get/username={username}/password={password}")
        Call<ArrayOfLoginModel> login(@Path("username") String username, @Path("password") String password);
    }

    Button BtnLogin;
    EditText userId, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        BtnLogin = findViewById(R.id.loginBtn);
        userId = findViewById(R.id.userText);
        password = findViewById(R.id.passwordText);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create()).build();

        apiInterface apiInter = retrofit.create(apiInterface.class);
        Call<ArrayOfLoginModel> call = apiInter.login("verify", "123");

        call.enqueue(new Callback<ArrayOfLoginModel>() {
            @Override
            public void onResponse(Call<ArrayOfLoginModel> call, Response<ArrayOfLoginModel> response) {
                if (response.isSuccessful()) {

                    String trimmedResponse = null;
                    try {
                        trimmedResponse = response.raw().body().string().trim();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    // Parse trimmed response
                    Serializer serializer = new Persister();
                    try {
                        ArrayOfLoginModel arrayOfLoginModel = serializer.read(ArrayOfLoginModel.class, trimmedResponse);
                        if (arrayOfLoginModel != null && arrayOfLoginModel.getLoginModels() != null) {
                            for (LoginModel loginModel : arrayOfLoginModel.getLoginModels()) {
                                Log.d("MainActivity", "AppVersion: " + loginModel.getAppVersion());
                                Log.d("MainActivity", "MONUMENTNAME_ID: " + loginModel.getMonumentNameId());
                                Log.d("MainActivity", "PASSWORD: " + loginModel.getPassword());
                                Log.d("MainActivity", "Rememberme: " + loginModel.isRememberMe());
                                Log.d("MainActivity", "USERNAME: " + loginModel.getUsername());
                                Log.d("MainActivity", "id: " + loginModel.getId());
                                Log.d("MainActivity", "loginStatus: " + loginModel.isLoginStatus());
                            }
                        } else {
                            Log.e("MainActivity", "Response body or loginModels is null");
                        }
                    } catch (Exception e) {
                        Log.e("MainActivity", "Error parsing XML: " + e.getMessage(), e);
                    }

                } else {
                    Log.e("MainActivity", "Unsuccessful response: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ArrayOfLoginModel> call, Throwable t) {
                Log.e("MainActivity", "Error: " + t.getMessage(), t);
            }
        });


        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ScanActivity.class);
                startActivity(i);
            }
        });

    }
}