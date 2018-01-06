package com.hons.happybirthday;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.hons.happybirthday.domain.BirthdayBuilder;
import com.hons.happybirthday.domain.BirthdayHolder;

import java.util.Calendar;

public class AddBirthdayActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText dateEditText;

    private DatePickerDialog datePickerDialog;
    private Button addBirthdayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_birthday);

        nameEditText = findViewById(R.id.name);
        dateEditText = findViewById(R.id.date);
        addBirthdayButton = findViewById(R.id.addBirthdayButton);

        dateEditText.setInputType(InputType.TYPE_NULL);

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                String date = String.format("%s.%s.%s", day, month + 1, year);
                dateEditText.setText(date);
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        addBirthdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString();
                int year = datePickerDialog.getDatePicker().getYear();
                int month = datePickerDialog.getDatePicker().getMonth();
                int day = datePickerDialog.getDatePicker().getDayOfMonth();

                BirthdayHolder.getInstance(view.getContext()).addBirthday(
                        BirthdayBuilder
                                .newInstance()
                                .withName(name)
                                .withYear(year)
                                .withMonth(month + 1)
                                .withDay(day)
                                .build());

                Intent intent = new Intent(view.getContext(), BirthdaysActivity.class);
                startActivity(intent);
            }
        });
    }
}
