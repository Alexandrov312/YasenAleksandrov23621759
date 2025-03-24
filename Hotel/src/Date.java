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

    private boolean isLeapYear(int year) {
        return year % 4 == 0;
    }

    private int daysInMonth(int month, int year) {
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
}
