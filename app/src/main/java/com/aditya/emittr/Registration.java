package com.aditya.emittr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aditya.emittr.services.RestService;

import org.json.JSONObject;

import okhttp3.Response;

public class Registration extends AppCompatActivity {
    Button bt,login;
    EditText name, email, password, username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);
        EditText name = (EditText) findViewById(R.id.name);
        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.password);
        EditText username = (EditText) findViewById(R.id.username);
        Button bt = (Button) findViewById(R.id.register_button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestService restService = new RestService();
                JSONObject jsonInput = new JSONObject();
                try {
                    // Put key-value pairs into the JSON object
                    jsonInput.put("username", username.getText().toString());
                    jsonInput.put("password", password.getText().toString());
                    jsonInput.put("email", email.getText().toString());
                    jsonInput.put("name", name.getText().toString());

                    // Convert the JSONObject to a string
                    String jsonInputString = jsonInput.toString();
                    Response response = restService.post(jsonInputString,
                            "/api/users/register");
                    if (response != null && response.code() == 200) {
                        Toast.makeText(getApplicationContext(), "User Registered Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Registration.this, Login.class);
                        startActivity(intent);
                    } else if (response != null && response.code() == 400) {
                        JSONObject respJSON = new JSONObject(response.body().toString());
                        if (respJSON.has("errors")) {
                            Toast.makeText(getApplicationContext(), respJSON.getString("errors"), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "User Registration Failed", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "User Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        login = findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this, Login.class);
                startActivity(intent);
            }
        });
    }

}