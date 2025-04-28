package input;

import fileManage.FileManager;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInput {

    public static void openFile() throws FileNotFoundException {
        String hotelFile, roomsFile, guestsFile, reservationsFile, activitiesFile;
        System.out.print("Enter hotel file path: ");
        hotelFile = InputHelper.input.nextLine();
        System.out.print("Enter rooms file path: ");
        roomsFile = InputHelper.input.nextLine();
        System.out.print("Enter guests file path: ");
        guestsFile = InputHelper.input.nextLine();
        System.out.print("Enter reservations file path: ");
        reservationsFile = InputHelper.input.nextLine();
        System.out.print("Enter activities file path: ");
        activitiesFile = InputHelper.input.nextLine();
        FileManager.open(hotelFile, roomsFile, guestsFile, reservationsFile, activitiesFile);
    }

    public static void saveAs() throws IOException {
        String newHotelFile, newRoomsFile, newGuestsFile, newReservationsFile, newActivitiesFile;
        System.out.print("Enter new hotel file path: ");
        newHotelFile = InputHelper.input.nextLine();
        System.out.print("Enter new rooms file path: ");
        newRoomsFile = InputHelper.input.nextLine();
        System.out.print("Enter new guests file path: ");
        newGuestsFile = InputHelper.input.nextLine();
        System.out.print("Enter new reservations file path: ");
        newReservationsFile = InputHelper.input.nextLine();
        System.out.print("Enter new activities file path: ");
        newActivitiesFile = InputHelper.input.nextLine();
        FileManager.saveAs(newHotelFile, newRoomsFile, newGuestsFile, newReservationsFile, newActivitiesFile);
    }
}
