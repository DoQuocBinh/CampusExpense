package com.example.campusexpense;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Date;
import java.util.Calendar;


import database.DatabaseHelper;
import database.ExpenseEntity;


public class AddNewExpense extends AppCompatActivity {
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        public EditText editText;
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker.
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it.
            return new DatePickerDialog(requireContext(), this, year, month, day);
        }
        public void onDateSet(DatePicker view, int year, int month, int day) {
           editText.setText(day + "/" + month + "/" + year);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_expense);
        EditText editTextExpenseDate = findViewById(R.id.editTextText2);
        editTextExpenseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePicker = new DatePickerFragment();
                datePicker.editText = editTextExpenseDate;
                datePicker.show(getSupportFragmentManager(), "datePicker");
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText expenseNameControl = findViewById(R.id.editTextText);
                String expenseName = expenseNameControl.getText().toString();
                Spinner expenseTypeControl = findViewById(R.id.spinner);
                String expenseType  = expenseTypeControl.getSelectedItem().toString();
                EditText expenseAmountControl = findViewById(R.id.editTextText3);
                String expenseAmount = expenseAmountControl.getText().toString();
                EditText expenseDateControl = findViewById(R.id.editTextText2);
                String expenseDate = expenseDateControl.getText().toString();

                ExpenseEntity expense = new ExpenseEntity();
                expense.expenseName = expenseName;
                expense.amount = expenseAmount;
                expense.expenseType = expenseType;
                expense.expenseDate = expenseDate;
                DatabaseHelper dbHelper = new DatabaseHelper(getApplication());
                long id = dbHelper.insertExpense(expense);
                Toast.makeText(getApplication(),String.valueOf(id),Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), AllExpenses.class);
                startActivity(intent);

            }
        });

    }
}