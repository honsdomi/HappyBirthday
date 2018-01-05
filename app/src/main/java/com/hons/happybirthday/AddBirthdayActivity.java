package com.hons.happybirthday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.hons.happybirthday.domain.BirthdayBuilder;

public class AddBirthdayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_birthday);

        final EditText nameEditText = findViewById(R.id.name);
        final DatePicker datePicker = findViewById(R.id.date);

        Button addBirthdayButton = findViewById(R.id.addBirthdayButton);
        addBirthdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString();
                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int day = datePicker.getDayOfMonth();

                BirthdayHolder.getInstance().addBirthday(
                        BirthdayBuilder
                                .newInstance()
                                .withName(name)
                                .withYear(year)
                                .withMonth(month)
                                .withDay(day)
                                .build());

                Intent intent = new Intent(view.getContext(), BirthdaysActivity.class);
                startActivity(intent);
            }
        });
    }
}
