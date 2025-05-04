package input;

import model.Date;
import model.Hotel;
import model.Room;

/**
 * Класът {@code ValidateInput} предоставя помощни методи за валидиране на входните данни, въведени от потребителя.
 */

public class ValidateInput {

    /**
     * Проверява дали етажът е валиден спрямо броя етажи в хотела.
     *
     * @param floor номер на етажа
     * @return true ако е валиден, иначе false.
     */
    public static boolean validFloor(int floor){
        return floor > 1 && floor <= Hotel.getInstance().getFloors();
    }

    /**
     * Проверява дали броят легла в стаята е валиден (между 1 и 4 включително).
     *
     * @param numberOfBeds брой легла
     * @return true ако е валидно, иначе false.
     */
    public static boolean validNumberOfBeds(int numberOfBeds){
        return (numberOfBeds >= 1 && numberOfBeds <= 4);
    }

    /**
     * Проверява дали стаята е пълна.
     *
     * @param room обект от тип Room
     * @return true ако броят гости е равен на броя легла.
     */
    public static boolean isRoomFull(Room room){
        return room.getNumberOfBeds() == room.getNumberOfGuests();
    }

    /**
     * Проверява дали броят гости е валиден спрямо броя легла.
     *
     * @param numberOfGuests брой гости
     * @param numberOfBeds брой легла
     * @return true ако броят гости е валиден, иначе false.
     */
    public static boolean validNumberOfGuests(int numberOfGuests, int numberOfBeds){
        return numberOfGuests <= numberOfBeds && numberOfGuests >= 1;
    }

    /**
     * Проверява дали дадена дата е валидна.
     *
     * @param year година
     * @param month месец
     * @param day ден
     * @return true ако датата е валидна.
     */
    public static boolean dateCheck(int year, int month, int day){
        if(year < 1908 || year > Date.today().getYear())
            return false;
        else if(month < 1 || month > 12)
            return false;
        else if(day < 1 || day > Date.daysInMonth(month, year))
            return false;

        return true;
    }

    /**
     * Проверява дали датата, въведена от потребителя, е валидна.
     *
     * @param date низ във формат "yyyy/mm/dd"
     * @return {@link Date}, ако е валидна датата, иначе null.
     */
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

    /**
     * Валидира време във формат "HH:mm".
     *
     * @param time низ от типа "час:минути"
     * @return форматирано време, или празен низ при невалиден вход.
     */
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

    /**
     * Проверява дали днешната дата е след зададена дата.
     *
     * @param date обект от тип Date
     * @return true ако днешната дата е след подадената.
     */
    public static boolean isTodayAfterDate(Date date){
        return Date.today().isAfter(date);
    }

    /**
     * Проверява дали низ съдържа само цифри.
     *
     * @param str текстов низ
     * @return true ако съдържа само цифри
     */
    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray())
            if (!Character.isDigit(c)) return false;
        return true;
    }
}
