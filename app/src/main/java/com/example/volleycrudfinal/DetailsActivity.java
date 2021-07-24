package com.example.volleycrudfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    TextView detailsId, detailsName, detailsEmail, detailsContact, detailsAddress;

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        detailsId = findViewById(R.id.detailsId);
        detailsName = findViewById(R.id.detailsName);
        detailsEmail = findViewById(R.id.detailsEmail);
        detailsContact = findViewById(R.id.detailsContact);
        detailsAddress = findViewById(R.id.detailsAddress);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        detailsId.setText("ID: " + MainActivity.employeeArryList.get(position).getId());
        detailsName.setText("Name: " + MainActivity.employeeArryList.get(position).getName());
        detailsEmail.setText("Email: " + MainActivity.employeeArryList.get(position).getEmail());
        detailsContact.setText("Contact: " + MainActivity.employeeArryList.get(position).getContact());
        detailsAddress.setText("Address: " + MainActivity.employeeArryList.get(position).getAddress());

    }
}