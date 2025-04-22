package fileManage;

import hotel.*;
import exceptions.*;
import app.UserInput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileRead {
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
            Hotel.getInstance().getRooms().add(room);
        }
        input.close();
    }

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
            Date birthDate = Date.isValidDate(date);
            if (birthDate == null)
                throw new ErrorByReading("Invalid birthdate");

            input.findInLine("Personal number: ");
            String personalNumber = input.nextLine();
            if (personalNumber.length() != 10 || !UserInput.isNumeric(personalNumber))
                throw new ErrorByReading("Invalid personal number");

            input.findInLine("Country: ");
            String country = input.nextLine();

            input.findInLine("Room number: ");
            int roomNumber = input.nextInt();
            input.nextLine();
            input.nextLine();

            Room room = null;
            for (Room tempRoom : Hotel.getInstance().getRooms()) {
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
            Hotel.getInstance().getGuests().add(guest);
        }
        input.close();
    }

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

    public static void readReservations(String path) throws FileNotFoundException {
        File file = new File(path);

        Scanner input = new Scanner(file);

        while(input.hasNextLine()) {
            input.findInLine("Room number: ");
            int roomNumber = input.nextInt();
            Room roomResult = null;
            for(Room room : Hotel.getInstance().getRooms()){
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
            Date startDate = Date.isValidDate(date);
            if (startDate == null)
                throw new ErrorByReading("Invalid birthdate");

            input.findInLine("End date: ");
            date = input.nextLine();
            Date endDate = Date.isValidDate(date);
            if (endDate == null)
                throw new ErrorByReading("Invalid birthdate");

            input.findInLine("Note: ");
            String note = input.nextLine();

            input.nextLine();

            Hotel.getInstance().getReservations().add(new Reservation(roomResult, startDate, endDate, note));
            if (Date.today().isBetween(startDate, endDate))
                roomResult.setAvailable(false);
        }
    }
}
