package model;

import input.InputHelper;

import java.time.LocalDate;
import java.util.Objects;

public class Date {
    private int year;
    private int month;
    private int day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return year == date.year && month == date.month && day == date.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public boolean isBefore(Date other) {
        if (this.year != other.year) {
            return this.year < other.year;
        }
        if (this.month != other.month) {
            return this.month < other.month;
        }
        return this.day < other.day;
    }

    public boolean isAfter(Date other) {
        if (this.year != other.year) {
            return this.year > other.year;
        }
        if (this.month != other.month) {
            return this.month > other.month;
        }
        return this.day > other.day;
    }

    public boolean isBetween(Date start, Date end) {
        return (!this.isBefore(start) && !this.isAfter(end));
    }

    public static Date today(){
        LocalDate now = LocalDate.now();
        return new Date(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
    }

    public int daysBetween(Date other) {
        return Math.abs(toDays() - other.toDays());
    }

    private int toDays() {
        int days = year * 365 + day;

        for (int i = 1; i < month; i++) {
            days += daysInMonth(i, year);
        }

        return days;
    }

    private static boolean isLeapYear(int year) {
        return year % 4 == 0;
    }

    public static int daysInMonth(int month, int year) {
        switch (month) {
            case 4: case 6: case 9: case 11: return 30;
            case 2: return isLeapYear(year) ? 29 : 28;
            default: return 31;
        }
    }

    public String getDateAsString() {
        String monthString = (month < 10 ? "0" : "") + month;
        String dayString = (day < 10 ? "0" : "") + day;
        return year + "/" + monthString + "/" + dayString;
    }
}
