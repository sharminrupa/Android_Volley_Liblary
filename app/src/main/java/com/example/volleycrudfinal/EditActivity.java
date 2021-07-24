package com.example.volleycrudfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;

import java.util.HashMap;
import java.util.Map;

public class EditActivity extends AppCompatActivity {

    EditText editId, editName, editEmail, editContact, editAddress;
    Button updateBtn;
    String url = "http://192.168.0.104/volleycrudfinal/volley_crud_update.php/";

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editId = findViewById(R.id.editId);
        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editContact = findViewById(R.id.editContuct);
        editAddress = findViewById(R.id.editAddress);
        updateBtn = findViewById(R.id.updateBtn);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 updateData();
            }
        });


        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        editId.setText(MainActivity.employeeArryList.get(position).getId());
        editName.setText(MainActivity.employeeArryList.get(position).getName());
        editEmail.setText(MainActivity.employeeArryList.get(position).getEmail());
        editContact.setText(MainActivity.employeeArryList.get(position).getContact());
        editAddress.setText(MainActivity.employeeArryList.get(position).getAddress());

    }

    public void updateData(){

        String edId = editId.getText().toString();
        String edName = editName.getText().toString();
        String edEmail = editEmail.getText().toString();
        String edContace = editContact.getText().toString();
        String edAddress = editAddress.getText().toString();

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updated.............");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST , url ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(EditActivity.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                        progressDialog.dismiss();
                    }
                } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }
        ){
            @Override
            public Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();

                params.put("id", edId);
                params.put("name", edName);
                params.put("email", edEmail);
                params.put("contact", edContace);
                params.put("address", edAddress);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

}