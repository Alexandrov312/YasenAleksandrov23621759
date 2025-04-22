package app;

import fileManage.FileManager;
import hotel.Hotel;

import java.io.IOException;
import java.util.Scanner;

public class MenuClass {
    private static Scanner scanner = new Scanner(System.in);
    private static String input;
    private static boolean mainMenuRunning = true, fileMenuRunning = true;
    private static String[] mainOptions = {"Check in", "Check out", "Available rooms for a given date", "Report rooms", "Find room",
            "Find room - Urgent", "Set room as unavailable", "Outdated reservations", "File manage menu", "Hotel manage menu", "Exit"};
    private static String[] fileOptions = {"Open", "Save", "Save as", "Help", "Close", "Go back"};
    private static String[] hotelOptions = {"Add guest", "Add room", "Show all guests",
            "Show all rooms without guests", "Show all rooms with guests", "Show all reservations", "Show Hotel info", "Go back"};


    public static void MainMenu() throws IOException {
        while(mainMenuRunning){
            System.out.println("MAIN OPTIONS:");
            displayMenu(mainOptions);
            System.out.print("Option: ");

            input = scanner.nextLine();
            if(!UserInput.isNumeric(input)){
                System.out.println("You didn't enter a valid option!");
                continue;
            }
            int option = Integer.parseInt(input);
            switch (option){
                case 1:
                    UserInput.checkInInput();
                    break;
                case 2:
                    UserInput.checkOutInput();
                    break;
                case 3:
                    UserInput.availabilityInput();
                    break;
                case 4:
                    UserInput.reportInput();
                    break;
                case 5:
                    UserInput.findRoomInput(false);
                    break;
                case 6:
                    UserInput.findRoomInput(true);
                    break;
                case 7:
                    UserInput.unavailableInput();
                    break;
                case 8:
                    Hotel.getInstance().outdatedReservations();
                    break;
                case 9:
                    fileMenu();
                    break;
                case 10:
                    hotelMenu();
                    break;
                case 11:
                    FileManager.save();
                    mainMenuRunning = false;
                    break;
                default:
                    System.out.println("You didn't enter a valid option!");
            }
        }
    }

    private static void fileMenu() throws IOException {
        fileMenuRunning = true;
        while(fileMenuRunning) {
            System.out.println("FILE OPTIONS:");
            displayMenu(fileOptions);
            System.out.print("Option: ");

            input = scanner.nextLine();
            if(!UserInput.isNumeric(input)){
                System.out.println("You didn't enter a valid option!");
                continue;
            }
            int option = Integer.parseInt(input);
            switch (option){
                case 1:
                    UserInput.openFile();
                    break;
                case 2:
                    FileManager.save();
                    break;
                case 3:
                    UserInput.saveAs();
                    break;
                case 4:
                    FileManager.printHelp();
                    break;
                case 5:
                    FileManager.close();
                    break;
                case 6:
                    fileMenuRunning = false;
                    break;
                default:
                    System.out.println("You didn't enter a valid option!");
            }
        }
    }

    private static void hotelMenu(){
        boolean hotelMenuRunning = true;
        while(hotelMenuRunning){
            System.out.println("HOTEL OPTIONS");
            displayMenu(hotelOptions);
            System.out.print("Option: ");

            input = scanner.nextLine();
            if(!UserInput.isNumeric(input)){
                System.out.println("You didn't enter a valid option!");
                continue;
            }
            int option = Integer.parseInt(input);
            switch (option){
                case 1:
                    UserInput.addGuest();
                    break;
                case 2:
                    UserInput.addRoom();
                    break;
                case 3:
                    Hotel.getInstance().displayAllGuests();
                    break;
                case 4:
                    Hotel.getInstance().displayAllRoomsWithoutGuests();
                    break;
                case 5:
                    Hotel.getInstance().displayAllRoomsWithGuests();
                    break;
                case 6:
                    Hotel.getInstance().displayAllReservations();
                    break;
                case 7:
                    System.out.println(Hotel.getInstance().getInfo());
                    break;
                case 8:
                    hotelMenuRunning = false;
                    break;
                default:
                    System.out.println("You didn't enter a valid option!");
            }
        }
    }

    private static void displayMenu(String[] options){
        for(int i = 1; i <= options.length; i++){
            System.out.println(i+") "+options[i-1]);
        }
    }
}
