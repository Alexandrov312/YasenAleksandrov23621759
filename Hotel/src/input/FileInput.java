package input;

import fileManage.FileManager;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Клас {@code FileInput} предоставя методи за управление на файлов вход и изход.
 */

public class FileInput {

    FileManager fileManager = new FileManager();

    /**
     * Зарежда файлове с информация за хотел, стаи, гости, резервации и дейности.
     *
     * @throws FileNotFoundException ако някой от файловете не бъде намерен.
     */
    public  void openFile() throws FileNotFoundException {
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
        fileManager.open(hotelFile, roomsFile, guestsFile, reservationsFile, activitiesFile);
    }

    /**
     * Записва текущото състояние на хотела в нови файлове, посочени от потребителя.
     *
     * @throws IOException ако възникне грешка при запис.
     */
    public  void saveAs() throws IOException {
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
        fileManager.saveAs(newHotelFile, newRoomsFile, newGuestsFile, newReservationsFile, newActivitiesFile);
    }
}
