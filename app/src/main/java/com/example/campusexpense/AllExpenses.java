package com.example.campusexpense;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import database.DatabaseHelper;

public class AllExpenses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_expenses);

        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        String allExpenses = dbHelper.getAllExpenses();

        TextView tv = findViewById(R.id.textView2);
        tv.setText(allExpenses);

    }
}