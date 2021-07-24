package com.example.volleycrudfinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    MyAdapter adapter;
    public static ArrayList<EmployeeModel> employeeArryList = new ArrayList<>();
    String url = "http://192.168.0.104/volleycrudfinal/volley_crud_retrive.php/";
    EmployeeModel employeeModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.myListView);
        adapter = new MyAdapter(this, employeeArryList);
        listView.setAdapter(adapter);

        // View Details when click a single item start
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent , View view , int position , long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                CharSequence[] dialogItem = {"View Data", "Edit Data", "Delete Data", "Image upload"};
                builder.setTitle(employeeArryList.get(position).getName());

                builder.setItems(dialogItem , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog , int i) {

                        switch (i){
                            case 0:

                                startActivity(new Intent(getApplicationContext(), DetailsActivity.class)
                                .putExtra("position", position)
                                );

                                break;
                            case 1:

                                startActivity(new Intent(getApplicationContext(), EditActivity.class)
                                .putExtra("position", position));

                                break;
                            case 2:

                                deleteData(employeeArryList.get(position).getId());

                                break;
                            case 3:

                                startActivity(new Intent(getApplicationContext(), ImageUploadActivity.class)
                                        .putExtra("position", position));

                                break;
                        }

                    }
                });

                builder.create().show();

            }
        });

        // View Details when click a single item end

        retriveData();

    }

    public void retriveData(){

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        employeeArryList.clear();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("volley_crud_table");

                            if(sucess.equals("1")){


                                for(int i=0;i<jsonArray.length();i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id = object.getString("id");
                                    String name = object.getString("name");
                                    String email = object.getString("email");
                                    String contact = object.getString("contact");
                                    String address = object.getString("address");

                                    employeeModel = new EmployeeModel(id,name,email,contact,address);
                                    employeeArryList.add(employeeModel);
                                    adapter.notifyDataSetChanged();
                                }
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }


    public void deleteData(final String id){

        StringRequest request = new StringRequest(Request.Method.POST , "http://192.168.0.104/volleycrudfinal/volley_crud_delete.php/" ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("Data Deleted")){
                            Toast.makeText(MainActivity.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                        }
                    }
                } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getParams()  {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("id", id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    public void addDataInsert(View view) {

        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        startActivity(intent);

    }
}