package com.example.volleycrudfinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText loginEmail, loginPassword;
    Button loginButton;
    LinearLayout regText;

    String logEmail, logPassword;

    String url = "http://192.168.0.104/volleycrudfinal/volleycrudfinalLogin.php/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.login);
        regText = findViewById(R.id.regText);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginData();
            }
        });

        regText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Registation.class);
                startActivity(intent);
            }
        });

    }

    public void  loginData(){

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait");

        if (loginEmail.getText().toString().equals("")){
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
        }else if (loginPassword.getText().toString().equals("")){
            Toast.makeText(this, "Enter passwor", Toast.LENGTH_SHORT).show();
        } else{
            progressDialog.show();
            logEmail = loginEmail.getText().toString().trim();
            logPassword = loginPassword.getText().toString().trim();

            StringRequest request = new StringRequest(Request.Method.POST , url ,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            if(response.equalsIgnoreCase("Loged in successfully")){

                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();

                            }else{

                                Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
                            }


                        }
                    } , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(Login.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("email", logEmail);
                    params.put("password", logPassword);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
            requestQueue.add(request);
            loginEmail.setText("");
            loginPassword.setText("");
        }


    }
}