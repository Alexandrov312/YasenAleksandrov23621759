package app;

import fileManage.FileManager;
import input.*;
import model.Hotel;

import java.io.IOException;

/**
 * Клас {@code MenuClass} управлява главното меню на приложението и всички негови подменюта.
 * Позволява на потребителя да извършва действия, свързани с хотелското управление,
 * като настаняване, освобождаване на стаи, търсене на стаи, управление на файлове и хотелска информация.
 */
public class MenuClass {

    private static boolean mainMenuRunning = true;

    /** Основни опции за менюто */
    private static final String[] mainOptions = {"Check in", "Check out", "Available rooms for a given date", "Report rooms",
            "Find room", "Find room - Urgent", "Set room as unavailable", "File manage menu",
            "Hotel manage menu", "Display activity by room number", "Display all guests signed for activity", "Exit"};

    /** Опции за управление на файлове */
    private static final String[] fileOptions = {"Open", "Save", "Save as", "Help", "Close", "Go back"};

    /** Основни опции за управление на хотела */
    private static final String[] hotelMainOptions = {"Adding Options", "Displaying Options", "Go back"};

    /** Опции за добавяне на данни в системата */
    private static final String[] addingOptions = {"Add guest", "Add room", "Add reservation", "Add activity",
            "Add guest to activity", "Go back"};

    /** Опции за извеждане на данни от системата */
    private static final String[] displayingOptions = {"Show all guests", "Show all rooms", "Show all occupied rooms",
            "Show all free rooms", "Show all reservations", "Show all activities without guests", "Show all activities with guests",
            "Show Hotel info", "Go back"};

    /**
     * Стартира главното меню на приложението.
     * Управлява изборите на потребителя и пренасочва към съответните действия.
     * @throws IOException при грешка при четене или запис на файл.
     */
    public static void MainMenu() throws IOException {
        while(mainMenuRunning){
            System.out.println("MAIN OPTIONS:");
            displayMenu(mainOptions);

            int option = InputHelper.enterInteger("Option: ");
            switch (option){
                case 1:
                    HotelInput.checkInInput();
                    break;
                case 2:
                    HotelInput.checkOutInput();
                    break;
                case 3:
                    HotelInput.availabilityInput();
                    break;
                case 4:
                    HotelInput.reportInput();
                    break;
                case 5:
                    RoomInput.findRoomInput(false);
                    break;
                case 6:
                    RoomInput.findRoomInput(true);
                    break;
                case 7:
                    ReservationInput.unavailableInput();
                    break;
                case 8:
                    fileMenu();
                    break;
                case 9:
                    hotelMenu();
                    break;
                case 10:
                    ActivityInput.activityByRoomNumberInput();
                    break;
                case 11:
                    ActivityInput.activityByIdInput();
                    break;
                case 12:
                    FileManager.save();
                    mainMenuRunning = false;
                    break;
                default:
                    System.out.println("You didn't enter a valid option!");
            }
        }
    }

    /**
     * Извежда меню за управление на файлове и изпълнява избраната опция.
     * @throws IOException при грешка при отваряне или запис.
     */
    private static void fileMenu() throws IOException {
        boolean fileMenuRunning = true;
        while(fileMenuRunning) {
            System.out.println("FILE OPTIONS:");
            displayMenu(fileOptions);

            int option = InputHelper.enterInteger("Option: ");
            switch (option){
                case 1:
                    FileInput.openFile();
                    break;
                case 2:
                    FileManager.save();
                    break;
                case 3:
                    FileInput.saveAs();
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

    /**
     * Извежда подменю за управление на хотел (добавяне/показване на данни).
     */
    private static void hotelMenu() {
        boolean hotelMenuRunning = true;
        while (hotelMenuRunning) {
            System.out.println("HOTEL MAIN MENU");
            displayMenu(hotelMainOptions);

            int option = InputHelper.enterInteger("Option: ");
            switch (option) {
                case 1:
                    addingMenu();
                    break;
                case 2:
                    displayingMenu();
                    break;
                case 3:
                    hotelMenuRunning = false;
                    break;
                default:
                    System.out.println("You didn't enter a valid option!");
            }
        }
    }

    /**
     * Извежда подменю за добавяне на обекти (гости, стаи, резервации и др.).
     */
    private static void addingMenu() {
        boolean menuRunning = true;
        while (menuRunning) {
            System.out.println("ADDING OPTIONS");
            displayMenu(addingOptions);

            int option = InputHelper.enterInteger("Option: ");
            switch (option) {
                case 1:
                    GuestInput.addGuest();
                    break;
                case 2:
                    RoomInput.addRoom();
                    break;
                case 3:
                    ReservationInput.addReservation();
                    break;
                case 4:
                    ActivityInput.addActivity();
                    break;
                case 5:
                    GuestInput.addGuestToActivity(null);
                    break;
                case 6:
                    menuRunning = false;
                    break;
                default:
                    System.out.println("You didn't enter a valid option!");
            }
        }
    }

    /**
     * Извежда подменю за показване на данни от системата.
     */
    private static void displayingMenu() {
        boolean menuRunning = true;
        while (menuRunning) {
            System.out.println("DISPLAYING OPTIONS");
            displayMenu(displayingOptions);

            int option = InputHelper.enterInteger("Option: ");
            switch (option) {
                case 1:
                    Hotel.getInstance().getGuestService().displayAllGuests();
                    break;
                case 2:
                    Hotel.getInstance().getRoomService().displayAllRooms();
                    break;
                case 3:
                    Hotel.getInstance().getRoomService().displayAllOccupiedRooms();
                    break;
                case 4:
                    Hotel.getInstance().getRoomService().displayAllFreeRooms();
                    break;
                case 5:
                    Hotel.getInstance().getReservationService().displayAllReservations();
                    break;
                case 6:
                    Hotel.getInstance().getActivityService().displayAllActivitiesWithoutGuests();
                    break;
                case 7:
                    Hotel.getInstance().getActivityService().displayAllActivitiesWithGuests();
                    break;
                case 8:
                    System.out.println(Hotel.getInstance().getInfo());
                    break;
                case 9:
                    menuRunning = false;
                    break;
                default:
                    System.out.println("You didn't enter a valid option!");
            }
        }
    }

    /**
     * Извежда подаден масив от опции в конзолата.
     * @param options масив със стрингове, които се показват като опции за избор.
     */
    private static void displayMenu(String[] options){
        for(int i = 1; i <= options.length; i++){
            System.out.println(i+") "+options[i-1]);
        }
    }
}
