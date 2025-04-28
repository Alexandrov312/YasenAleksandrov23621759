package input;

import model.Date;
import model.Hotel;
import model.Room;

public class ValidateInput {

    public static boolean validFloor(int floor){
        return floor > 1 && floor <= Hotel.getInstance().getFloors();
    }

    public static boolean validNumberOfBeds(int numberOfBeds){
        return (numberOfBeds >= 1 && numberOfBeds <= 4);
    }

    public static boolean isRoomFull(Room room){
        return room.getNumberOfBeds() == room.getNumberOfGuests();
    }

    public static boolean validNumberOfGuests(int numberOfGuests, int numberOfBeds){
        return numberOfGuests <= numberOfBeds && numberOfGuests >= 1;
    }

    public static boolean dateCheck(int year, int month, int day){
        if(year < 1908 || year > Date.today().getYear())
            return false;
        else if(month < 1 || month > 12)
            return false;
        else if(day < 1 || day > Date.daysInMonth(month, year))
            return false;

        return true;
    }

    public static Date isValidDate(String date){
        int year = 0, month = 0, day = 0;

        String[] parts = date.split("/");

        if (parts.length != 3) {
            return null;
        }

        for (int i = 0; i < 3; i++) {
            if(!ValidateInput.isNumeric(parts[i])){
                return null;
            }
            if(i == 0) year = Integer.parseInt(parts[i]);
            else if(i == 1) month = Integer.parseInt(parts[i]);
            else day = Integer.parseInt(parts[i]);
        }
        if(dateCheck(year, month, day))
            return new Date(year, month, day);
        else
            return null;
    }

    public static String isValidTime(String time){
        int hours = -1, minutes = -1;
        String[] parts = time.split(":");

        if (parts.length != 2) {
            return "";
        }

        for(int i = 0; i < 2; i++){
            if(!ValidateInput.isNumeric(parts[i])){
                return "";
            }
            if(i == 0) hours = Integer.parseInt(parts[i]);
            else minutes = Integer.parseInt(parts[i]);
        }
        if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
            return "";
        }
        String res = hours < 10 ? ("0" + hours) : String.valueOf(hours);
        res += ":";
        res += minutes < 10 ? ("0" + minutes) : String.valueOf(minutes);
        return res;
    }

    public static boolean isTodayAfterDate(Date date){
        return Date.today().isAfter(date);
    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray())
            if (!Character.isDigit(c)) return false;
        return true;
    }
}
