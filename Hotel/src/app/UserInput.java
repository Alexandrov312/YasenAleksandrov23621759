package app;

import fileManage.FileManager;
import hotel.*;

import hotel.Hotel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class UserInput {

    private static Scanner input = new Scanner(System.in);
    private static String temp;

    public static Guest enterGuest(int roomNumber){
        String firstName;
        String lastName;

        Date birthDate;
        int year = 0;
        int month = 0;
        int day = 0;

        String personalNumber;
        String country;

        System.out.print("First name: ");
        firstName = input.nextLine();
        System.out.print("Last name: ");
        lastName = input.nextLine();

        do {
            System.out.print("Birth date (example: 2005/01/25): ");
            String date = input.nextLine();
            birthDate = Date.isValidDate(date);
            if(birthDate != null) break;
            else System.out.println("Invalid birth date");
        }while(true);

        do {
            System.out.print("Personal number: ");
            personalNumber = input.nextLine();

            if(personalNumber.length() == 10 && isNumeric(personalNumber))
                break;
            else
                System.out.println("Invalid personal number!");

        }while(true);

        System.out.print("Country: ");
        country = input.nextLine();

        return new Guest(firstName, lastName, birthDate, personalNumber, country, roomNumber);
    }

    public static void addGuest(){
        int roomNumber;
        do{
            System.out.print("Enter a room number: ");
            temp = input.nextLine();
            if(!UserInput.isNumeric(temp)){
                System.out.println("You didn't enter a valid room number!");
                continue;
            }
            roomNumber = Integer.parseInt(temp);
            Room roomResult = null;
            for(Room room : Hotel.getInstance().getRooms()){
                if(room.getRoomNumber() == roomNumber) {
                    roomResult = room;
                    break;
                }
            }
            if(roomResult != null){
                if(roomResult.getNumberOfBeds() == roomResult.getNumberOfGuests()){
                    System.out.println("The room is full!");
                    continue;
                }
                Guest guest = UserInput.enterGuest(roomNumber);
                roomResult.getGuests().add(guest);
                Hotel.getInstance().getGuests().add(guest);
                System.out.println(guest.getInfo());

                break;
            }
            else{
                System.out.println("You didn't enter a valid room number");
            }
        }while(true);

        System.out.println("The guest was added successfully");
    }

    public static void addRoom(){
        int roomNumber = -1, floor = -1, numberOfBeds = -1;
        View view = null;

        do {
            System.out.print("Enter a room number: ");
            temp = input.nextLine();
            if(!UserInput.isNumeric(temp)){
                System.out.println("You didn't enter a valid room number");
                continue;
            }
            boolean flag = true;
            roomNumber = Integer.parseInt(temp);
            for(Room room : Hotel.getInstance().getRooms()){
                if(room.getRoomNumber() == roomNumber){
                    flag = false;
                    System.out.println("Such room number already exists!");
                    break;
                }
            }
            if(!flag) continue;
            break;
        }while(true);

        do {
            System.out.print("Enter a floor: ");
            temp = input.nextLine();
            if(!UserInput.isNumeric(temp)){
                System.out.println("You didn't enter a valid floor!");
                continue;
            }
            floor = Integer.parseInt(temp);
            if(floor < 1 || floor > Hotel.getInstance().getFloors()){
                System.out.println("You didn't enter a valid floor!");
                continue;
            }
            break;
        }while(true);

        do{
            System.out.print("Enter number of beds: ");
            temp = input.nextLine();
            if(!UserInput.isNumeric(temp)){
                System.out.println("You didn't enter a valid number!");
                continue;
            }
            numberOfBeds = Integer.parseInt(temp);
            if(numberOfBeds < 1) {
                System.out.println("You didn't enter a valid floor!");
                continue;
            }
            break;
        }while(true);

        do{
            System.out.print("Enter view type:\n1) Side sea view\n2) Direct sea view\nOption: ");
            temp = input.nextLine();
            if(temp.equals("1")) {
                view = View.SIDE_SEA_VIEW;
                break;
            }
            else if(temp.equals("2")) {
                view = View.DIRECT_SEA_VIEW;
                break;
            }
            else {
                System.out.println("You didn't enter a valid option!11111");
            }
        }while(true);

        Hotel.getInstance().getRooms().add(new Room(floor, numberOfBeds, view, roomNumber));
        System.out.println("The room was added successfully!");
    }

    public static Hotel enterHotel(){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter hotel name: ");
        String name = input.nextLine();

        int floors = -1;
        while(floors < 1) {
            System.out.print("Enter number of floors: ");
            floors = input.nextInt();
            if(floors < 1){
                System.out.println("Number of floors must be at least 1");
            }
        }
        int stars = -1;
        while(stars < 1 || stars > 5){
            System.out.print("Enter number of stars: ");
            stars = input.nextInt();
            if(stars < 1 || stars > 5){
                System.out.printf("Number of stars must be between 1 and 5");
            }
        }
        System.out.println("The hotel was added successfully");
        return new Hotel(name, floors, stars);
    }

    public static void checkInInput(){
        String note;
        Room roomResult = null;
        int roomNumber;
        Date startDate, endDate;
        do {
            System.out.print("Enter a room number: ");
            temp = input.nextLine();
            if(!UserInput.isNumeric(temp)){
                System.out.println("Invalid room number!");
                continue;
            }
            roomNumber = Integer.parseInt(temp);
            for(Room room : Hotel.getInstance().getRooms()){
                if(room.getRoomNumber() == roomNumber){
                    roomResult = room;
                }
            }
            if(roomResult == null){
                System.out.println("Invalid room number!");
                continue;
            }
            if(!roomResult.isAvailable()) {
                System.out.println("The room is occupied!");
            }

        }while(roomResult == null);

        do {
            do {
                System.out.print("Enter start date (example: 2005/01/25): ");
                temp = input.nextLine();
                startDate = Date.isValidDate(temp);
                if (startDate != null) break;
                else System.out.println("You didn't enter a valid date");
            } while (true);

            do {
                System.out.print("Enter end date (example: 2005/01/25): ");
                temp = input.nextLine();
                endDate = Date.isValidDate(temp);
                if (endDate != null) break;
                else System.out.println("You didn't enter a valid date");
            } while (true);

            if (Hotel.getInstance().isReserved(roomResult, startDate, endDate))
                System.out.println("The room is reserved for that period!");
            else
                break;

        }while(true);

        System.out.print("Enter a note: ");
        note = input.nextLine();

        char option;
        do{
            System.out.println("The beds in the room are "+roomResult.getNumberOfBeds()+".\nAre you going to use them all?");
            System.out.print("(Y/N):");
            temp = input.nextLine();
            option = Character.toLowerCase(temp.charAt(0));
            if(option == 'y'){
                Hotel.getInstance().checkIn(roomResult, startDate, endDate, note);
                break;
            }
            else if(option == 'n'){
                do{
                    System.out.print("Enter number of people: ");
                    temp = input.nextLine();
                    if(!UserInput.isNumeric(temp)){
                        System.out.println("You didn't enter a valid value");
                        continue;
                    }
                    int numberOfGuests = Integer.parseInt(temp);
                    if(numberOfGuests > roomResult.getNumberOfBeds() || numberOfGuests < 1){
                        System.out.println("The number of guests must be between 1 and "+roomResult.getNumberOfBeds());
                        continue;
                    }
                    Hotel.getInstance().checkIn(roomResult, numberOfGuests, startDate, endDate, note);
                    break;
                }while(true);
                break;
            }
            else{
                System.out.println("You didn't enter a valid option!");
            }
        }while(true);
    }

    public static void checkOutInput(){
        do {
            System.out.println("Enter a room number: ");
            temp = input.nextLine();
            if(!UserInput.isNumeric(temp)){
                System.out.println("You didn't enter a valid room number!");
                continue;
            }
            int roomNumber = Integer.parseInt(temp);
            Room roomResult = null;
            for(Room room : Hotel.getInstance().getRooms()){
                if(room.getRoomNumber() == roomNumber){
                    roomResult = room;
                    break;
                }
            }
            if(roomResult != null){
                Hotel.getInstance().checkOut(roomResult);
                break;
            }
            else{
                System.out.println("Room with such number wasn't found!");
            }
        }while(true);
    }

    public static void availabilityInput(){
        do {
            System.out.println("Would you like to use today's date?");
            System.out.print("(Y/N):");
            temp = input.nextLine();
            if(Character.toLowerCase(temp.charAt(0)) == 'y'){
                Hotel.getInstance().availability();
                break;
            }
            else if(Character.toLowerCase(temp.charAt(0)) == 'n'){
                do{
                    System.out.print("Enter date (example: 2005/01/25): ");
                    temp = input.nextLine();
                    Date date = Date.isValidDate(temp);
                    if(date != null){
                        Hotel.getInstance().availability(date);
                    }
                    else{
                        System.out.println("You didn't enter a valid date!");
                        continue;
                    }
                    break;
                }while(true);
                break;
            }
            else{
                System.out.println("You didn't enter a valid option!");
            }
        }while(true);
    }

    public static void reportInput(){
        Date startDate, endDate;
        do{
            System.out.print("Enter start date (example: 2005/01/25): ");
            temp = input.nextLine();
            startDate = Date.isValidDate(temp);
            if(startDate != null)
                break;
            else{
                System.out.println("You didn't enter a valid option!");
            }
        }while(true);

        do{
            System.out.print("Enter end date (example: 2005/01/25): ");
            temp = input.nextLine();
            endDate = Date.isValidDate(temp);
            if(endDate != null)
                break;
            else{
                System.out.println("You didn't enter a valid option!");
            }
        }while(true);

        Hotel.getInstance().report(startDate, endDate);
    }

    public static void findRoomInput(boolean isUrgent){
        int numberOfBeds = -1;
        Date startDate, endDate;
        do{
            System.out.print("Enter number of beds: ");
            temp = input.nextLine();
            if(!UserInput.isNumeric(temp)){
                System.out.println("You didn't enter a valid value!");
                continue;
            }
            numberOfBeds = Integer.parseInt(temp);
            if(numberOfBeds < 1){
                System.out.println("You didn't enter a valid value!");
                continue;
            }
            break;
        }while(true);

        do{
            System.out.print("Enter start date (example: 2005/01/25): ");
            temp = input.nextLine();
            startDate = Date.isValidDate(temp);
            if(startDate != null)
                break;
            else{
                System.out.println("You didn't enter a valid option!");
            }
        }while(true);

        do{
            System.out.print("Enter end date (example: 2005/01/25): ");
            temp = input.nextLine();
            endDate = Date.isValidDate(temp);
            if(endDate != null)
                break;
            else{
                System.out.println("You didn't enter a valid option!");
            }
        }while(true);

        Room roomResult = null;
        if(isUrgent) {
            roomResult = Hotel.getInstance().findRoom(numberOfBeds, startDate, endDate);
        }
        else{
            roomResult = Hotel.getInstance().findRoomUrgent(numberOfBeds, startDate, endDate);
        }

        if(roomResult == null)
            System.out.println("Unfortunately the hotel is full!");
        else
            System.out.println("Found!\n"+roomResult.getInfoWithoutGuests());
    }

    public static void unavailableInput(){
        int roomNumber;
        Room roomResult = null;
        Date startDate, endDate;
        String note;
        do {
            System.out.print("Enter a room number: ");
            temp = input.nextLine();
            if(!UserInput.isNumeric(temp)){
                System.out.println("Invalid room number!");
                continue;
            }
            roomNumber = Integer.parseInt(temp);
            for(Room room : Hotel.getInstance().getRooms()){
                if(room.getRoomNumber() == roomNumber){
                    roomResult = room;
                }
            }
            if(roomResult == null){
                System.out.println("Invalid room number!");
                continue;
            }
            if(!roomResult.isAvailable()) {
                System.out.println("The room is occupied!");
                continue;
            }
            break;
        }while(true);

        do{
            System.out.print("Enter start date (example: 2005/01/25): ");
            temp = input.nextLine();
            startDate = Date.isValidDate(temp);
            if(startDate != null)
                break;
            else{
                System.out.println("You didn't enter a valid option!");
            }
        }while(true);

        do{
            System.out.print("Enter end date (example: 2005/01/25): ");
            temp = input.nextLine();
            endDate = Date.isValidDate(temp);
            if(endDate != null)
                break;
            else{
                System.out.println("You didn't enter a valid option!");
            }
        }while(true);

        System.out.print("Enter a note: ");
        note = input.nextLine();

        Hotel.getInstance().unavailable(roomResult, startDate, endDate, note);
    }

    public static void openFile() throws FileNotFoundException {
        String hotelFile, roomsFile, guestsFile, reservationsFile;
        System.out.print("Enter hotel file path: ");
        hotelFile = input.nextLine();
        System.out.print("Enter rooms file path: ");
        roomsFile = input.nextLine();
        System.out.print("Enter guests file path: ");
        guestsFile = input.nextLine();
        System.out.print("Enter reservations file path: ");
        reservationsFile = input.nextLine();
        FileManager.open(hotelFile, roomsFile, guestsFile, reservationsFile);
    }

    public static void saveAs() throws IOException {
        String newHotelFile, newRoomsFile, newGuestsFile, newReservationsFile;
        System.out.print("Enter new hotel file path: ");
        newHotelFile = input.nextLine();
        System.out.print("Enter new rooms file path: ");
        newRoomsFile = input.nextLine();
        System.out.print("Enter new guests file path: ");
        newGuestsFile = input.nextLine();
        System.out.print("Enter new reservations file path: ");
        newReservationsFile = input.nextLine();
        FileManager.saveAs(newHotelFile, newRoomsFile, newGuestsFile, newReservationsFile);
    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}