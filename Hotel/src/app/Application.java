package app;

import fileManage.FileRead;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {

        FileRead.readHotel("1.txt");
        FileRead.readRooms("2.txt");
        FileRead.readGuests("3.txt");
        FileRead.readReservations("4.txt");
        FileRead.readActivities("5.txt");
        MenuClass.MainMenu();

    }
}
