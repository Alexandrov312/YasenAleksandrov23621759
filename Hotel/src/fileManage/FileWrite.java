package fileManage;

import model.Hotel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import model.*;

/**
 * Класът {@code FileWrite} съдържа методи за запис на данни от файлове в системата.
 */
public class FileWrite {
    /**
     * Записва информацията за стаите във файл.
     *
     * @param path път до файл
     * @throws FileNotFoundException при липсващ файл
     */
    public static void writeRooms(String path) throws IOException {
        File file = new File(path);
        FileOutputStream output = new FileOutputStream(file);
        for(Room room : Hotel.getInstance().getRoomService().getRooms()){
            output.write((room.getInfoWithoutGuests()+"-----------------------------\n").getBytes());
        }
        output.close();
    }

    /**
     * Записва информацията за гостите във файл.
     *
     * @param path път до файл
     * @throws FileNotFoundException при липсващ файл
     */
    public static void writeGuests(String path) throws IOException {
        File file = new File(path);
        FileOutputStream output = new FileOutputStream(file);
        for(Guest guest : Hotel.getInstance().getGuestService().getGuests()){
            output.write((guest.getInfo()+"-----------------------------\n").getBytes());
        }
        output.close();
    }

    /**
     * Записва информацията за хотел във файл.
     *
     * @param path път до файл
     * @throws FileNotFoundException при липсващ файл
     */
    public static void writeHotel(String path) throws IOException {
        File file = new File(path);
        FileOutputStream output = new FileOutputStream(file);
        output.write(Hotel.getInstance().getInfo().getBytes());

        output.close();
    }

    /**
     * Записва информацията за резервации във файл.
     *
     * @param path път до файл
     * @throws FileNotFoundException при липсващ файл
     */
    public static void writeReservations(String path) throws  IOException{
        File file = new File(path);
        FileOutputStream output = new FileOutputStream(file);
        for(Reservation reservation : Hotel.getInstance().getReservationService().getReservations()){
            output.write((reservation.getInfo()+"-----------------------------\n").getBytes());
        }
        output.close();
    }

    /**
     * Записва информацията за дейности във файл.
     *
     * @param path път до файл
     * @throws FileNotFoundException при липсващ файл
     */
    public static void writeActivities(String path) throws  IOException{
        File file = new File(path);
        FileOutputStream output = new FileOutputStream(file);
        for(Activity activity : Hotel.getInstance().getActivityService().getActivities()){
            output.write((activity.getInfoWithGuests()+"----------------------------------------------------------\n").getBytes());
        }
        output.close();
    }
}
