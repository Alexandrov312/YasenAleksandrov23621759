package service;

import interfaces.RoomServiceInterface;
import model.Date;
import model.Guest;
import model.Hotel;
import model.Room;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Клас {@code RoomService} за управление на хотелски стаи.
 * Отговаря за търсене, проверка на свободни стаи и преместване на гости.
 */
public class RoomService implements RoomServiceInterface {
    private TreeSet<Room> rooms;

    public RoomService() {
        this.rooms = new TreeSet<>(Comparator.comparingInt(Room::getRoomNumber));
    }

    public TreeSet<Room> getRooms() {
        return rooms;
    }

    /**
     * Извежда всички свободни стаи към дадена дата.
     * @param date Дата за проверка на наличност (ако е null, се използва днешната дата).
     */
    public void availability(Date date){
        if(date == null) date = Date.today();
        for (Room room : rooms) {
            if (!Hotel.getInstance().getReservationService().isReserved(room, date, date)) {
                System.out.println(room.getInfoWithoutGuests());
            }
        }
    }

    /**
     * Намира свободна стая, която удовлетворява зададен брой легла и период.
     * @param numberOfBeds Брой легла.
     * @param startDate Начална дата.
     * @param endDate Крайна дата.
     * @return Подходяща свободна стая или null.
     */
    public Room findRoom(int numberOfBeds, Date startDate, Date endDate){
        Room result = null;
        for(Room room : rooms){
            if(room.isAvailable() && room.getNumberOfBeds() >= numberOfBeds &&
                    !Hotel.getInstance().getReservationService().isReserved(room, startDate, endDate)){
                if (result == null || room.getNumberOfBeds() < result.getNumberOfBeds()) {
                    result = room;
                }
            }
        }
        return result;
    }

    /**
     * Търси спешно стая, като при нужда премества гости между други стаи.
     * @param numberOfBeds Брой легла.
     * @param startDate Начална дата.
     * @param endDate Крайна дата.
     * @return Свободна стая след преместване или null.
     */
    public Room findRoomUrgent(int numberOfBeds, Date startDate, Date endDate) {
        Room result = findRoom(numberOfBeds, startDate, endDate);
        if (result != null)
            return result;

        Room bestRoomToFree = null;
        Room bestTargetRoom = null;

        for (Room roomA : rooms) {
            for (Room roomB : rooms) {
                if (roomA == roomB) continue;

                int totalBeds = roomA.getNumberOfBeds() + roomB.getNumberOfBeds();
                int totalGuests = roomA.getNumberOfGuests() + roomB.getNumberOfGuests();

                if (totalGuests <= totalBeds - numberOfBeds) {

                    Room targetRoom = (roomA.getNumberOfBeds() >= roomB.getNumberOfBeds()) ? roomA : roomB;
                    Room roomToFree = (targetRoom == roomA) ? roomB : roomA;

                    if (roomToFree.getNumberOfBeds() >= numberOfBeds) {
                        if (totalGuests <= targetRoom.getNumberOfBeds()) {
                            if (bestRoomToFree == null || roomToFree.getNumberOfBeds() < bestRoomToFree.getNumberOfBeds()) {
                                bestRoomToFree = roomToFree;
                                bestTargetRoom = targetRoom;
                            }
                        }
                    }
                }
            }
        }

        if (bestRoomToFree != null && bestTargetRoom != null) {
            Set<Guest> guestsToMove = new HashSet<>(bestRoomToFree.getGuests());
            guestsToMove.addAll(bestTargetRoom.getGuests());

            bestTargetRoom.getGuests().clear();
            bestTargetRoom.getGuests().addAll(guestsToMove);
            for(Guest guest : bestTargetRoom.getGuests()){
                guest.setRoomNumber(bestTargetRoom.getRoomNumber());
            }

            bestRoomToFree.getGuests().clear();
            bestRoomToFree.setAvailable(true);

            System.out.println("The guests from room "+bestRoomToFree.getRoomNumber()+" were moved to room "+bestTargetRoom.getRoomNumber());
            System.out.println(bestTargetRoom.getInfoWithGuests());

            return bestRoomToFree;
        }

        return null;
    }

    /**
     * Извежда информация за всички стаи без гости.
     */
    public void displayAllRooms(){
        for(Room room : rooms){
            System.out.println(room.getInfoWithoutGuests());
            System.out.println("-----------------------------\n");
        }
    }

    /**
     * Извежда информация за всички заети стаи (с гости).
     */
    public void displayAllOccupiedRooms() {
        for (Room room : rooms) {
            if (!room.isAvailable()) {
                System.out.println(room.getInfoWithGuests());
                System.out.println("-----------------------------\n");
            }
        }
    }

    /**
     * Извежда информация за всички свободни стаи (с гости, ако има).
     */
    public void displayAllFreeRooms() {
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println(room.getInfoWithGuests());
                System.out.println("-----------------------------\n");
            }
        }
    }
}
