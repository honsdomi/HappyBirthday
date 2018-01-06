package com.hons.happybirthday.domain;

import android.content.Context;
import android.content.SharedPreferences;

import com.hons.happybirthday.domain.entity.Birthday;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominik on 05.01.2018.
 */

public class BirthdayHolder {
    private static final String BIRTHDAY_PREFERENCES = "birthday_preferences";
    private static BirthdayHolder instance;
    private List<Birthday> birthdays = new ArrayList<>();
    private SharedPreferences sharedPreferences;

    public static BirthdayHolder getInstance(Context context) {
        if (instance == null) {
            instance = new BirthdayHolder();
            instance.initBirthdays(context);
        }
        return instance;
    }

    public List<Birthday> getBirthdays() {
        return birthdays;
    }

    public void addBirthday(Birthday birthday) {
        birthdays.add(birthday);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(birthday.getName(), birthday.getFormatedBirthday());
        editor.apply();
    }

    private void initBirthdays(Context context) {
        sharedPreferences = context.getSharedPreferences(BIRTHDAY_PREFERENCES, Context.MODE_PRIVATE);
        for (String name : sharedPreferences.getAll().keySet()) {
            String[] date = sharedPreferences.getString(name, "1.1.1970").split("\\.");
            int day = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int year = Integer.parseInt(date[2]);
            Birthday birthday = new Birthday(name, year, month, day);
            instance.birthdays.add(birthday);
        }
    }
}
