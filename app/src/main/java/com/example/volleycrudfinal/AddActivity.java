package com.example.volleycrudfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

public class AddActivity extends AppCompatActivity {

    ImageView imageView;
    EditText addName, addEmil, addContact, addAddress;
    Button addBtn;

    String inName, inEmail, inContact, inAddress;
    String url = "http://192.168.0.104/volleycrudfinal/volley_crud_add.php/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        imageView = findViewById(R.id.imgUpload);
        addName = findViewById(R.id.addName);
        addEmil = findViewById(R.id.addEmail);
        addContact = findViewById(R.id.addContuct);
        addAddress = findViewById(R.id.addAddress);

        addBtn = findViewById(R.id.addBtn);

        addBtn.setOnClickListener(v -> addData());
    }

    public void addData(){

        inName = addName.getText().toString().trim();
        inEmail = addEmil.getText().toString().trim();
        inContact = addContact.getText().toString().trim();
        inAddress = addAddress.getText().toString().trim();

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait");


        if (inName.isEmpty()){
            Toast.makeText(AddActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
        }else if (inEmail.isEmpty()){
            Toast.makeText(AddActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
        }else if (inContact.isEmpty()){
            Toast.makeText(AddActivity.this, "Enter Contact", Toast.LENGTH_SHORT).show();
        }else if (inAddress.isEmpty()){
            Toast.makeText(AddActivity.this, "Enter Address", Toast.LENGTH_SHORT).show();
        }else{
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST , url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();;
                            Toast.makeText(AddActivity.this, response, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    } , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(AddActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
            ){
                @Override
                public Map<String, String> getParams() {

                    Map<String, String> params = new HashMap<String , String>();

                    params.put("name", inName);
                    params.put("email", inEmail);
                    params.put("contact", inContact);
                    params.put("address", inAddress);

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(AddActivity.this);
            requestQueue.add(stringRequest);
            addName.setText("");
            addEmil.setText("");
            addContact.setText("");
            addAddress.setText("");

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}