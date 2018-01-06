package com.hons.happybirthday.domain.entity;

/**
 * Created by Dominik on 05.01.2018.
 */

public class Birthday {
    private String name;
    private int year;
    private int month;
    private int day;

    public Birthday() {
    }

    public Birthday(String name, int year, int month, int day) {
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getFormatedBirthday() {
        return String.format("%s.%s.%s", day, month, year);
    }
}
