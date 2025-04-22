package fileManage;

import hotel.Hotel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import hotel.*;

public class FileWrite {
    public static void writeRooms(String path) throws IOException {
        File file = new File(path);
        FileOutputStream output = new FileOutputStream(file);
        for(Room room : Hotel.getInstance().getRooms()){
            output.write((room.getInfoWithoutGuests()+"-----------------------------\n").getBytes());
        }
        output.close();
    }

    public static void writeGuests(String path) throws IOException {
        File file = new File(path);
        FileOutputStream output = new FileOutputStream(file);
        for(Guest guest : Hotel.getInstance().getGuests()){
            output.write((guest.getInfo()+"-----------------------------\n").getBytes());
        }
        output.close();
    }

    public static void writeHotel(String path) throws IOException {
        File file = new File(path);
        FileOutputStream output = new FileOutputStream(file);
        output.write(Hotel.getInstance().getInfo().getBytes());

        output.close();
    }

    public static void writeReservations(String path) throws  IOException{
        File file = new File(path);
        FileOutputStream output = new FileOutputStream(file);
        for(Reservation reservation : Hotel.getInstance().getReservations()){
            output.write((reservation.getInfo()+"-----------------------------\n").getBytes());
        }
        output.close();
    }
}
