package com.example.volleycrudfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter extends ArrayAdapter<EmployeeModel> {

    Context context;
    List<EmployeeModel> arrayListEmployee;



    public MyAdapter(@NonNull Context context , List<EmployeeModel> arrayListEmployee) {
        super(context , R.layout.custom_list_item, arrayListEmployee);

        this.context = context;
        this.arrayListEmployee = arrayListEmployee;

    }

    @NonNull
    @Override
    public View getView(int position , @Nullable View convertView , @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item, null, true);

        TextView tvId = view.findViewById(R.id.txt_id);
        TextView tvName = view.findViewById(R.id.txt_name);

        tvId.setText(arrayListEmployee.get(position).getId());
        tvName.setText(arrayListEmployee.get(position).getName());

        return view;
    }
}
