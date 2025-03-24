import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileRead {
    public static void read(String path) throws FileNotFoundException {
        File file = new File(path);

        Scanner input = new Scanner(file);

        while(input.hasNextLine()){
            input.findInLine("RoomNumber: ");
            int roomNumber = input.nextInt();

            input.nextLine();
            input.findInLine("Floor: ");
            int floor = input.nextInt();

            input.nextLine();
            input.findInLine("NumberOfBeds: ");
            int numberOfBeds = input.nextInt();

            input.nextLine();
            input.findInLine("View: ");
            View view = View.valueOf(input.nextLine());
            input.nextLine();

            System.out.println("RoomNumber: " + roomNumber);
            System.out.println("Floor: " + floor);
            System.out.println("NumberOfBeds: " + numberOfBeds);
            System.out.println("View: " + view);
            System.out.println();
        }
    }
}
