package com.aditya.emittr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aditya.emittr.Dashboard.UserDashboard;
import com.aditya.emittr.services.RestService;

import org.json.JSONObject;

import okhttp3.Response;

public class Login extends AppCompatActivity {
    Button login,register;
    EditText username,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login_button);
        register = findViewById(R.id.register_button);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Registration.class);
                startActivity(intent);
            }
        });
    }
    private void loginUser() {
        RestService restService = new RestService();
        JSONObject jsonInput = new JSONObject();
        try {
            jsonInput.put("username", username.getText().toString());
            jsonInput.put("password", password.getText().toString());

            String jsonInputString = jsonInput.toString();
            Response response = restService.post(jsonInputString, "/api/users/login");

            if (response != null) {
                if (response.code() == 200) {
                    Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, UserDashboard.class);
                    intent.putExtra("username", username.getText().toString());
                    startActivity(intent);
                } else if (response.code() == 401) {
                    Toast.makeText(getApplicationContext(),"Unauthorized: Check your username and password",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}