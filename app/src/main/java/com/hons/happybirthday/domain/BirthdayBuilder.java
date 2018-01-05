package com.hons.happybirthday.domain;

import com.hons.happybirthday.domain.entity.Birthday;

/**
 * Created by Dominik on 05.01.2018.
 */

public class BirthdayBuilder {
    private String name;
    private int year;
    private int month;
    private int day;

    public static BirthdayBuilder newInstance(){
        return new BirthdayBuilder();
    }
    
    public BirthdayBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public BirthdayBuilder withYear(int year) {
        this.year = year;
        return this;
    }
    
    public BirthdayBuilder withMonth(int month) {
        this.month = month;
        return this;
    }
    
    public BirthdayBuilder withDay(int day) {
        this.day = day;
        return this;
    }

    public Birthday build(){
        Birthday birthday = new Birthday();
        birthday.setName(name);
        birthday.setYear(year);
        birthday.setMonth(month);
        birthday.setDay(day);
        return birthday;
    }
}
