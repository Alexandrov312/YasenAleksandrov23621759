package hotel;
import app.*;

import java.time.LocalDate;

public class Date {
    private int year;
    private int month;
    private int day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
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

    private static int daysInMonth(int month, int year) {
        switch (month) {
            case 4: case 6: case 9: case 11: return 30;
            case 2: return isLeapYear(year) ? 29 : 28;
            default: return 31;
        }
    }

    public static boolean isValidBirthDate(int year, int month, int day){
        if(year < 1908 || year > Date.today().getYear())
            return false;
        else if(month < 0 || month > 12)
            return false;
        else if(day < 0 || day > daysInMonth(month, year))
            return false;

        return true;
    }

    public static Date isValidDate(String date){
        int year = 0, month = 0, day = 0;
        for (int i = 0; i < 3; i++) {
            String temp = "";
            for (int j = 0; j < date.length(); j++) {
                if (date.charAt(j) == '/')
                    break;
                temp += date.charAt(j);
            }
            if(!UserInput.isNumeric(temp)) {
                System.out.println("Invalid date!");
                return null;
            }

            if (i == 0) {
                year = Integer.parseInt(temp);
                temp += "/";
                date = date.replace(temp, "");
            } else if (i == 1) {
                month = Integer.parseInt(temp);
                temp += "/";
                date = date.replace(temp, "");
            } else {
                day = Integer.parseInt(temp);
            }
        }
        if(isValidBirthDate(year, month, day))
            return new Date(year, month, day);
        else
            return null;
    }

    public String getDateAsString() {
        String monthString = (month < 10 ? "0" : "") + month;
        String dayString = (day < 10 ? "0" : "") + day;
        return year + "/" + monthString + "/" + dayString;
    }

}
