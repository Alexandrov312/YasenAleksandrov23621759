package input;

import model.Date;
import model.Hotel;
import model.Room;
import model.View;

/**
 * Клас {@code RoomInput} съдържа методи за търсене на стая, добавяне на стая
 * и търсене на свободна стая за дадени период и брой легла.
 */
public class RoomInput {

    /**
     * Търси стая по номер, въведен от потребителя.
     * @return {@link Room} Намерения обект.
     */
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

    /**
     * Проверява номер на стая и, ако не съществува вече такава стая с такъв номер, го връща.
     * @return Връща номер на стая, въведен от потребителя, като цяло число.
     */
    private  int enterNewRoomNumber(){
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

    /**
     * Добавя нова стая с информация въведена от потребителя.
     */
    public  void addRoom(){
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

    /**
     * Търси свободна стая за даден период, въведен от потребителя. Ако се търси спешно стая
     * и не се намери свободна стая, се прави проверка, дали е възможно разместване на гости от най-много две стаи.
     * Ако е възможно, се прави разместване и се извежда информация за освободената стая.
     * @param isUrgent Булева стойност, която оказва дали се търси спешно стая, или не.
     */
    public  void findRoomInput(boolean isUrgent){
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
