package com.example.campusexpense;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import database.DatabaseHelper;
import database.ExpenseEntity;

public class AllExpenses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_expenses);

        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());

        ArrayAdapter adapter = new ArrayAdapter<ExpenseEntity>(this,
                R.layout.activity_listview, dbHelper.getAllExpenses());

        ListView listView = (ListView) findViewById(R.id.listExpense);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ExpenseEntity entry = (ExpenseEntity) parent.getItemAtPosition(position);
                final String[]  options = {"Delete","Update"};
                AlertDialog.Builder builder = new AlertDialog.Builder(AllExpenses.this);
                builder.setItems(options,(dialog,item)->{
                    if (options[item]=="Delete"){
                        Snackbar.make(view, "Delete", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }else if(options[item]=="Update"){
                        Snackbar.make(view, "Update", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });
                builder.show();
            }
        });



    }
}