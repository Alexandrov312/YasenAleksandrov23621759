package fileManage;

import model.Hotel;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Класът {@code FileManager} управлява отваряне, записване, затваряне на файлове с данни за хотела.
 */
public class FileManager {
    private static boolean isOpened = false;
    private static String hotelFilePath;
    private static String roomsFilePath;
    private static String guestsFilePath;
    private static String reservationsFilePath;
    private static String activitiesFilePath;

    /**
     * Отваря всички необходими файлове и зарежда данните в системата.
     *
     * @param hotelFile         път до файл с данни за хотел
     * @param roomsFile         път до файл с данни за  стаи
     * @param guestsFile        път до файл с данни за гости
     * @param reservationsFile  път до файл с данни за резервации
     * @param activitiesFile    път до файл с данни за активности
     * @throws FileNotFoundException ако някой от файловете не бъде намерен
     */
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

    /**
     * Записва текущото състояние на хотела във вече отворените файлове, ако има такива.
     *
     * @throws IOException при грешка при запис
     */
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

    /**
     * Записва състоянието на хотела в нови файлове.
     *
     * @param newHotelFile нов път до файл с данни за хотел
     * @param newRoomsFile нов път до файл с данни за стаи
     * @param newGuestsFile нов път до файл с данни за гости
     * @param newReservationsFile нов път до файл с данни за резервации
     * @param newActivitiesFile нов път до файл с данни за активности
     * @throws IOException при грешка при запис
     */
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

        isOpened = true;

        System.out.println("Changes saved!");
    }

    /**
     * Затваря текущо заредения хотел и изчиства данните.
     */
    public static void close(){
        Hotel.setHotel(null);
        isOpened = false;
        System.out.println("Hotel closed!");
    }

    /**
     * Извежда помощна информация за командите, налични за потребителя.
     */
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
