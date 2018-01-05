package com.hons.happybirthday;

import com.hons.happybirthday.domain.entity.Birthday;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominik on 05.01.2018.
 */

public class BirthdayHolder {
    private static BirthdayHolder instance;
    private List<Birthday> birthdays = new ArrayList<>();

    public static BirthdayHolder getInstance(){
        if (instance == null){
            instance = new BirthdayHolder();
        }
        return instance;
    }

    public List<Birthday> getBirthdays(){
        return birthdays;
    }

    public void addBirthday(Birthday birthday){
        birthdays.add(birthday);
    }
}
