package com.example.campusexpense;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[]  options = {"View All Expense","Option2","Option3"};
                AlertDialog.Builder builder = new AlertDialog.Builder(AddNewExpense.this);
                builder.setItems(options,(dialog,item)->{
                    if (options[item]=="View All Expense"){
                        Intent intent = new Intent(getApplicationContext(), AllExpenses.class);
                        startActivity(intent);
                    }else if(options[item]=="Option2"){
                        Snackbar.make(view, "Option2", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }else if(options[item]=="Option3"){
                        Snackbar.make(view, "Option3", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

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