package fileManage;

import input.ValidateInput;
import model.*;
import exceptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Класът {@code FileRead} съдържа методи за четене на данни от файлове в системата.
 */
public class FileRead {

    /**
     * Зарежда информацията за хотела от файл.
     *
     * @param path път до файл
     * @throws FileNotFoundException при липсващ файл
     */
    public static void readHotel(String path) throws FileNotFoundException {
        File file = new File(path);

        Scanner input = new Scanner(file);

        input.findInLine("Name: ");
        String name = input.nextLine();

        input.findInLine("Floors: ");
        int floors = input.nextInt();
        if(floors < 1)
            throw new ErrorByReading("Number of floors must be at least 1");
        input.nextLine();

        input.findInLine("Stars: ");
        int stars = input.nextInt();
        if(stars < 1 || stars > 5)
            throw new ErrorByReading("Stars must be between 1 and 5");

        Hotel hotel = new Hotel(name, floors, stars);
        Hotel.setHotel(hotel);

        input.close();
    }

    /**
     * Зарежда стаи от подадения файл.
     *
     * @param path път до файл
     * @throws FileNotFoundException при липсващ файл
     */
    public static void readRooms(String path) throws FileNotFoundException {
        File file = new File(path);

        Scanner input = new Scanner(file);

        while(input.hasNextLine()){
            input.findInLine("Room number: ");
            int roomNumber = input.nextInt();

            input.nextLine();
            input.findInLine("Floor: ");
            int floor = input.nextInt();
            if(floor > Hotel.getInstance().getFloors() || floor < 1)
                throw new ErrorByReading("Invalid floor");

            input.nextLine();
            input.findInLine("Number of beds: ");
            int numberOfBeds = input.nextInt();

            input.nextLine();
            input.findInLine("Is available: ");
            boolean isAvailable = input.nextBoolean();

            input.nextLine();
            input.findInLine("View: ");
            View view = View.valueOf(input.nextLine());

            input.nextLine();

            Room room = new Room(floor, numberOfBeds, view, roomNumber);
            room.setAvailable(isAvailable);
            Hotel.getInstance().getRoomService().getRooms().add(room);
        }
        input.close();
    }

    /**
     * Зарежда гости от подадения файл и ги добавя към съответната стая.
     *
     * @param path път до файл
     * @throws FileNotFoundException при липсващ файл
     */
    public static void readGuests(String path) throws FileNotFoundException {
        File file = new File(path);

        Scanner input = new Scanner(file);

        while(input.hasNextLine()) {
            input.findInLine("First name: ");
            String firstName = input.nextLine();

            input.findInLine("Last name: ");
            String lastName = input.nextLine();

            input.findInLine("Birth date: ");
            String date = input.nextLine();
            Date birthDate = ValidateInput.isValidDate(date);
            if (birthDate == null)
                throw new ErrorByReading("Invalid birthdate");

            input.findInLine("Personal number: ");
            String personalNumber = input.nextLine();
            if (personalNumber.length() != 10 || !ValidateInput.isNumeric(personalNumber))
                throw new ErrorByReading("Invalid personal number");

            input.findInLine("Country: ");
            String country = input.nextLine();

            input.findInLine("Room number: ");
            int roomNumber = input.nextInt();
            input.nextLine();
            input.nextLine();

            Room room = null;
            for (Room tempRoom : Hotel.getInstance().getRoomService().getRooms()) {
                if (tempRoom.getRoomNumber() == roomNumber) {
                    room = tempRoom;
                    break;
                }
            }
            Guest guest = new Guest(firstName, lastName, birthDate, personalNumber, country, roomNumber);
            if (room == null)
                throw new ErrorByReading("Invalid room number");
            else{
                room.addGuest(guest);
            }
            Hotel.getInstance().getGuestService().getGuests().add(guest);
        }
        input.close();
    }

    /**
     * Зарежда резервации от файл. Добавя ги, ако са актуални, иначе освобождава стаите.
     *
     * @param path път до файл
     * @throws FileNotFoundException при липсващ файл
     */
    public static void readReservations(String path) throws FileNotFoundException {
        File file = new File(path);

        Scanner input = new Scanner(file);

        while(input.hasNextLine()) {
            input.findInLine("Room number: ");
            int roomNumber = input.nextInt();
            Room roomResult = null;
            for(Room room : Hotel.getInstance().getRoomService().getRooms()){
                if(room.getRoomNumber() == roomNumber){
                    roomResult = room;
                    break;
                }
            }
            if(roomResult == null)
                throw  new ErrorByReading("Such room doesn't exists!");
            input.nextLine();

            input.findInLine("Start date: ");
            String date = input.nextLine();
            Date startDate = ValidateInput.isValidDate(date);
            if (startDate == null)
                throw new ErrorByReading("Invalid birthdate");

            input.findInLine("End date: ");
            date = input.nextLine();
            Date endDate = ValidateInput.isValidDate(date);
            if (endDate == null)
                throw new ErrorByReading("Invalid birthdate");

            input.findInLine("Note: ");
            String note = input.nextLine();

            input.nextLine();

            if (Date.today().isBetween(startDate, endDate)) {
                if(roomResult.getNumberOfGuests() != 0) roomResult.setAvailable(false);
                Hotel.getInstance().getReservationService().getReservations().add(new Reservation(roomResult, startDate, endDate, note));
            }
            else{
                Hotel.getInstance().getGuestService().checkOut(roomResult);
            }
        }
        input.close();
    }

    /**
     * Зарежда активности и свързаните с тях гости от файл.
     *
     * @param path път до файл
     * @throws FileNotFoundException при липсващ файл
     */
    public static void readActivities(String path) throws FileNotFoundException {
        File file = new File(path);

        Scanner input = new Scanner(file);

        while (input.hasNextLine()) {
            int id;
            String description, time;
            Date date;

            input.findInLine("Id: ");
            id = input.nextInt();
            input.nextLine();

            input.findInLine("Description: ");
            description = input.nextLine();

            input.findInLine("Date: ");
            String dateString = input.nextLine();
            date = ValidateInput.isValidDate(dateString);
            if (date == null)
                throw new ErrorByReading("Invalid date");

            input.findInLine("Starts at: ");
            time = input.nextLine();
            time = ValidateInput.isValidTime(time);
            if(time.isEmpty())
                throw  new ErrorByReading("Invalid time!");

            input.nextLine();
            Activity activity = new Activity(id, description, date, time);
            do{
                if(input.findInLine("----------------------------------------------------------") != null){
                    break;
                }
                input.findInLine("First name: ");
                String firstName = input.nextLine();

                input.findInLine("Last name: ");
                String lastName = input.nextLine();

                input.findInLine("Birth date: ");
                dateString = input.nextLine();
                Date birthDate = ValidateInput.isValidDate(dateString);
                if (birthDate == null)
                    throw new ErrorByReading("Invalid birthdate");

                input.findInLine("Personal number: ");
                String personalNumber = input.nextLine();
                if (personalNumber.length() != 10 || !ValidateInput.isNumeric(personalNumber))
                    throw new ErrorByReading("Invalid personal number");

                input.findInLine("Country: ");
                String country = input.nextLine();

                input.findInLine("Room number: ");
                int roomNumber = input.nextInt();
                input.nextLine();
                input.nextLine();

                Room room = null;
                for (Room tempRoom : Hotel.getInstance().getRoomService().getRooms()) {
                    if (tempRoom.getRoomNumber() == roomNumber) {
                        room = tempRoom;
                        break;
                    }
                }
                Guest guest = new Guest(firstName, lastName, birthDate, personalNumber, country, roomNumber);
                if (room == null)
                    throw new ErrorByReading("Invalid room number");
                else{
                    if(!Hotel.getInstance().getGuestService().getGuests().contains(guest))
                        continue;
                    activity.getGuests().add(guest);
                }
            }while(true);
            input.nextLine();
            if(!activity.getDate().isBefore(Date.today()))
                Hotel.getInstance().getActivityService().getActivities().add(activity);
        }
        input.close();
    }
}
