package model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Класът {@code Date} представя дата (ден, месец, година), с методи за сравнение, форматиране и изчисления.
 */
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

    /**
     * Проверява дали текущата дата е преди дадена дата.
     *
     * @param other друга дата
     * @return true ако е преди, иначе false
     */
    public boolean isBefore(Date other) {
        if (this.year != other.year) {
            return this.year < other.year;
        }
        if (this.month != other.month) {
            return this.month < other.month;
        }
        return this.day < other.day;
    }

    /**
     * Проверява дали текущата дата е след дадена дата.
     *
     * @param other друга дата
     * @return true ако е след, иначе false
     */
    public boolean isAfter(Date other) {
        if (this.year != other.year) {
            return this.year > other.year;
        }
        if (this.month != other.month) {
            return this.month > other.month;
        }
        return this.day > other.day;
    }

    /**
     * Проверява дали текущата дата е между две други дати.
     *
     * @param start начална дата
     * @param end крайна дата
     * @return true ако е между тях
     */
    public boolean isBetween(Date start, Date end) {
        return (!this.isBefore(start) && !this.isAfter(end));
    }

    /**
     * Връща днешната дата.
     *
     * @return обект от тип {@code Date}
     */
    public static Date today(){
        LocalDate now = LocalDate.now();
        return new Date(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
    }

    /**
     * Проверка дали два периода се покриват
     * @param start1 начална дата на първия период
     * @param end1 крайна дата на първия период
     * @param start2 начална дата на втория период
     * @param end2 крайна дата на втория период
     * @return true ако се покриват
     */
    public static boolean isPeriodOverlapping(Date start1, Date end1, Date start2, Date end2) {
        return !(end1.isBefore(start2) || start1.isAfter(end2));
    }

    /**
     * Изчислява разликата в дни между две дати.
     *
     * @param other друга дата
     * @return брой дни между датите
     */
    public int daysBetween(Date other) {
        return Math.abs(toDays() - other.toDays());
    }

    /**
     * Преобразува датата в приблизителен брой дни от началото на годината 0.
     * Използва се за изчисление на разликата в дни между две дати.
     * Методът отчита броя дни във всеки месец и дали годината е високосна.
     *
     * @return приблизителен брой дни от началото на ерата
     */
    private int toDays() {
        int days = year * 365 + day;

        for (int i = 1; i < month; i++) {
            days += daysInMonth(i, year);
        }

        return days;
    }

    /**
     * Проверява дали дадена година е високосна.
     *
     * @param year година
     * @return true ако е високосна
     */
    private static boolean isLeapYear(int year) {
        return year % 4 == 0;
    }

    /**
     * Връща броя дни в даден месец от дадена година.
     *
     * @param month месец (1–12)
     * @param year година
     * @return брой дни
     */
    public static int daysInMonth(int month, int year) {
        switch (month) {
            case 4: case 6: case 9: case 11: return 30;
            case 2: return isLeapYear(year) ? 29 : 28;
            default: return 31;
        }
    }

    /**
     * Връща датата във формат yyyy/MM/dd.
     *
     * @return текстов низ с дата
     */
    public String getDateAsString() {
        String monthString = (month < 10 ? "0" : "") + month;
        String dayString = (day < 10 ? "0" : "") + day;
        return year + "/" + monthString + "/" + dayString;
    }
}
