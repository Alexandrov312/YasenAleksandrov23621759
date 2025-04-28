package input;

import model.Date;
import model.Hotel;
import model.Room;
import model.View;

public class RoomInput {

    public static Room enterRoom(){
        int roomNumber;
        do {
            roomNumber = InputHelper.enterInteger("Enter room number: ");
            for (Room room : Hotel.getInstance().getRoomService().getRooms()) {
                if (room.getRoomNumber() == roomNumber) {
                    return room;
                }
            }
            System.out.println("Room with such number wasn't found!");
        }while(true);
    }

    public static int enterNewRoomNumber(){
        int roomNumber;
        do {
            roomNumber = InputHelper.enterInteger("Enter new room number: ");
            boolean exists = false;
            for (Room room : Hotel.getInstance().getRoomService().getRooms()) {
                if (room.getRoomNumber() == roomNumber) {
                    exists = true;
                    System.out.println("Room with such number already exists!");
                    break;
                }
            }
            if (!exists) {
                break;
            }
        } while (true);
        return roomNumber;
    }

    public static void addRoom(){
        int roomNumber = -1, floor = -1, numberOfBeds = -1;
        View view = null;

        roomNumber = enterNewRoomNumber();

        do {
            floor = InputHelper.enterInteger("Enter a floor: ");
            if(!ValidateInput.validFloor(floor)){
                System.out.println("The floor must be between 1 and "+Hotel.getInstance().getFloors()+"!");
                continue;
            }
            break;
        }while(true);

        do{
            numberOfBeds = InputHelper.enterInteger("Enter number of beds: ");
            if(!ValidateInput.validNumberOfBeds(numberOfBeds)){
                System.out.println("The number of beds must be between 1 and 4");
                continue;
            }
            break;
        }while(true);

        view = InputHelper.enterView();

        if(Hotel.getInstance().getRoomService().getRooms().add(new Room(floor, numberOfBeds, view, roomNumber))){
            System.out.println("The room was added successfully!");
        }
        else{
            System.out.println("Error to add room!");
        }
    }

    public static void findRoomInput(boolean isUrgent){
        int numberOfBeds;
        Date startDate, endDate;
        do{
            numberOfBeds = InputHelper.enterInteger("Enter number of beds: ");
            if(!ValidateInput.validNumberOfBeds(numberOfBeds)){
                System.out.println("Number of beds must be between 1 and 4");
                continue;
            }
            break;
        }while(true);

        startDate = InputHelper.enterDate("Enter start date ");
        endDate = InputHelper.enterDate("Enter end date ");

        Room roomResult = null;
        if(!isUrgent) {
            roomResult = Hotel.getInstance().getRoomService().findRoom(numberOfBeds, startDate, endDate);
        }
        else{
            roomResult = Hotel.getInstance().getRoomService().findRoomUrgent(numberOfBeds, startDate, endDate);
        }

        if(roomResult == null)
            System.out.println("Unfortunately the hotel is full!");
        else
            System.out.println("Found!\n"+roomResult.getInfoWithoutGuests());
    }

}
