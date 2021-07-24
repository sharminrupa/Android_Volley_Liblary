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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Registation extends AppCompatActivity {

    EditText name, email, password;
    Button regBtn;
    LinearLayout loginText;

    String inesrtName, inesrtEmail, inesrtPassword;

    String url = "http://192.168.0.104/volleycrudfinal/volleycrudregistation.php/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registation);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        regBtn = findViewById(R.id.regBtn);
        loginText = findViewById(R.id.loginText);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InsertData();

            }

        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registation.this, Login.class);
                startActivity(intent);
            }
        });
    }


    public void InsertData() {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait");

        if (name.getText().toString().equals("")){
            Toast.makeText(this, "Enter username", Toast.LENGTH_SHORT).show();
        }else if (email.getText().toString().equals("")){
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
        }else if (password.getText().toString().equals("")){
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
        }else{
            progressDialog.show();
            inesrtName = name.getText().toString().trim();
            inesrtEmail = email.getText().toString().trim();
            inesrtPassword = password.getText().toString().trim();

            StringRequest request = new StringRequest(Request.Method.POST , url ,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            Toast.makeText(Registation.this, response, Toast.LENGTH_SHORT).show();

                        }
                    } , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(Registation.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("name", inesrtName);
                    params.put("email", inesrtEmail);
                    params.put("password", inesrtPassword);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(Registation.this);
            requestQueue.add(request);
            name.setText("");
            email.setText("");
            password.setText("");
        }


    }
}