package interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileManagerInterface {
    void open(String hotelFile, String roomsFile, String guestsFile,
              String reservationsFile, String activitiesFile) throws FileNotFoundException;
    void save() throws IOException;
    void saveAs(String newHotelFile, String newRoomsFile, String newGuestsFile,
                String newReservationsFile, String newActivitiesFile) throws IOException;
    void close();
    void printHelp();
}
