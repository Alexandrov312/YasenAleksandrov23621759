package fileManage;

import model.Hotel;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FileManager {
    private static boolean isOpened = false;
    private static String hotelFilePath;
    private static String roomsFilePath;
    private static String guestsFilePath;
    private static String reservationsFilePath;
    private static String activitiesFilePath;

    public static void open(String hotelFile, String roomsFile, String guestsFile,
                            String reservationsFile, String activitiesFile) throws FileNotFoundException {
        hotelFilePath = hotelFile;
        roomsFilePath = roomsFile;
        guestsFilePath = guestsFile;
        reservationsFilePath = reservationsFile;
        activitiesFilePath = activitiesFile;

        FileRead.readHotel(hotelFile);
        FileRead.readRooms(roomsFile);
        FileRead.readGuests(guestsFile);
        FileRead.readReservations(reservationsFile);
        FileRead.readActivities(activitiesFile);

        isOpened = true;
        System.out.println("The data was successfully loaded!");
    }
    public static void save() throws IOException {
        if(!isOpened){
            System.out.println("No opened files!");
            return;
        }
        FileWrite.writeHotel(hotelFilePath);
        FileWrite.writeRooms(roomsFilePath);
        FileWrite.writeGuests(guestsFilePath);
        FileWrite.writeReservations(reservationsFilePath);
        FileWrite.writeActivities(activitiesFilePath);
        System.out.println("Changes saved!");
    }

    public static void saveAs(String newHotelFile, String newRoomsFile, String newGuestsFile,
                              String newReservationsFile, String newActivitiesFile) throws IOException {
        hotelFilePath = newHotelFile;
        roomsFilePath = newRoomsFile;
        guestsFilePath = newGuestsFile;
        reservationsFilePath = newReservationsFile;
        activitiesFilePath = newActivitiesFile;

        FileWrite.writeHotel(hotelFilePath);
        FileWrite.writeRooms(roomsFilePath);
        FileWrite.writeGuests(guestsFilePath);
        FileWrite.writeReservations(reservationsFilePath);
        FileWrite.writeActivities(activitiesFilePath);

        System.out.println("Changes saved!");
    }

    public static void close(){
        Hotel.setHotel(null);
        isOpened = false;
        System.out.println("Hotel closed!");
    }
    public static void printHelp() {
        System.out.println("Available commands:");
        System.out.println("open - Open hotel, rooms, guests and reservations files");
        System.out.println("save - Save current hotel data to files");
        System.out.println("save as - Save to new files");
        System.out.println("close - Close current hotel data");
        System.out.println("help - List available commands");
        System.out.println("exit - Exit the program");
    }
}
