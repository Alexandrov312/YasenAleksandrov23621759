package hotel;
import app.*;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Hotel{
    private String name;
    private int floors;
    private int stars;
    private TreeSet<Guest> guests;
    private TreeSet<Room> rooms;
    private TreeSet<Reservation> reservations;

    private static Hotel hotel;

    public Hotel(String name, int floors, int stars) {
        this.name = name;
        this.floors = floors;
        this.stars = stars;
        this.guests = new TreeSet<>(Comparator.comparingInt(Guest::getRoomNumber)
                                    .thenComparing(Guest::getPersonalNumber));
        this.rooms = new TreeSet<>(Comparator.comparingInt(Room::getRoomNumber));
        this.reservations = new TreeSet<>(Comparator.comparing(reservation -> reservation.getStartDate().getDateAsString()));
    }

    public int getStars() {
        return stars;
    }

    public int getFloors() {
        return floors;
    }

    public String getName() {
        return name;
    }

    public TreeSet<Guest> getGuests() {
        return guests;
    }

    public TreeSet<Room> getRooms() {
        return rooms;
    }

    public TreeSet<Reservation> getReservations() {
        return reservations;
    }

    public static Hotel getInstance(){
        if(hotel == null){
            Hotel.hotel = UserInput.enterHotel();
        }
        return hotel;
    }

    public static void setHotel(Hotel hotel){
        Hotel.hotel = hotel;
    }

    public void checkIn(Room room, int numberOfGuests, Date startDate, Date endDate, String note) {

        for(int i = 0; i < numberOfGuests; i++){
            System.out.println("\nEnter personal information for guest "+(i+1)+"!");
            Guest guest = UserInput.enterGuest(room.getRoomNumber());
            room.getGuests().add(guest);
            Hotel.getInstance().getGuests().add(guest);
        }

        reservations.add(new Reservation(room, startDate, endDate, note));
        room.setAvailable(false);
    }

    public void checkIn(Room room, Date startDate, Date endDate, String note) {

        for(int i = 0; i < room.getNumberOfBeds(); i++){
            System.out.println("\nEnter personal information for guest "+(i+1)+"!");
            Guest guest = UserInput.enterGuest(room.getRoomNumber());
            room.getGuests().add(guest);
            Hotel.getInstance().getGuests().add(guest);
        }

        reservations.add(new Reservation(room, startDate, endDate, note));
        room.setAvailable(false);
    }

    public void checkOut(Room room) {
        room.setAvailable(true);
        room.getGuests().clear();
    }

    public boolean isReserved(Room room, Date startDate, Date endDate){
        for(Reservation reservation : reservations){
            if(reservation.getRoom() == room && !isAvailable(reservation, startDate, endDate)){
                return true;
            }
        }
        return false;
    }

    public boolean isAvailable(Reservation reservation, Date startDate, Date endDate){
        Date resStart = reservation.getStartDate();
        Date resEnd = reservation.getEndDate();
        return (endDate.isBefore(resStart) || startDate.isAfter(resEnd));
    }

    public void availability(Date date){
        for(Room room : rooms){
            if(!room.isAvailable()) continue;
            boolean available = true;
            for(Reservation reservation : reservations){
                if(reservation.getRoom() == room){
                    if(!isAvailable(reservation, date, date)){
                        available = false;
                    }
                    break;
                }
            }
            if(available) System.out.println(room.getInfoWithoutGuests());
        }
    }

    public void availability(){
        Date date = Date.today();

        for(Room room : rooms){
            if(!room.isAvailable()) continue;
            boolean available = true;
            for(Reservation reservation : reservations){
                if(reservation.getRoom() == room){
                    if(!isAvailable(reservation, date, date)){
                        available = false;
                    }
                    break;
                }
            }
            if(available) System.out.println(room.getInfoWithoutGuests());
        }
    }

    public void report(Date startDate, Date endDate){
        for(Reservation reservation : reservations){
            if(!isAvailable(reservation, startDate, endDate)){
                Date today = Date.today();
                int days;
                if(Date.today().isBefore(endDate)) {
                    days = reservation.getStartDate().daysBetween(today);
                }
                else{
                    days = reservation.getStartDate().daysBetween(endDate);
                }
                System.out.println("Reservation: ");
                System.out.println(reservation.getInfo());
                System.out.println(reservation.getRoom().getInfoWithoutGuests()+"\nDays in total: "+days);
                System.out.println("-----------------------------");
            }
        }
    }

    public Room findRoom(int numberOfBeds, Date startDate, Date endDate){
        Room result = null;
        for(Room room : rooms){
            if(room.isAvailable() && room.getNumberOfBeds() >= numberOfBeds && !isReserved(room, startDate, endDate)){
                if (result == null || room.getNumberOfBeds() < result.getNumberOfBeds()) {
                    result = room;
                }
            }
        }
        return result;
    }

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
                int totalGuests = roomA.getGuests().size() + roomB.getGuests().size();

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

            bestRoomToFree.getGuests().clear();

            System.out.println("The guests from room "+bestRoomToFree.getRoomNumber()+" were moved to room "+bestTargetRoom.getRoomNumber());
            System.out.println(bestTargetRoom.getInfoWithGuests());

            return bestRoomToFree;
        }

        return null;
    }

    public void unavailable(Room room, Date startDate, Date endDate, String note){
        Reservation reservation = new Reservation(room, startDate, endDate, note);
        reservations.add(reservation);
        if (Date.today().isBetween(startDate, endDate)) {
            room.setAvailable(false);
            /*if(room.getNumberOfGuests() > 0){
                findRoomUrgent(room.getNumberOfBeds(), reservation.getStartDate(), reservation.getEndDate());
            }*/
        }
    }

    public void outdatedReservations(){
        for(Reservation reservation : reservations){
            if(reservation.getEndDate().isBefore(Date.today())){
                reservation.getRoom().setAvailable(true);
            }
        }
        System.out.println("All rooms with outdated reservations are set to available!");
    }

    public String getInfo(){
        StringBuilder builder = new StringBuilder();
        builder.append("Name: "+name+'\n');
        builder.append("Floors: "+floors+'\n');
        builder.append("Stars: "+stars+'\n');
        return builder.toString();
    }

    public void displayAllRoomsWithGuests(){
        for(Room room : rooms){
            System.out.println(room.getInfoWithGuests());
            System.out.println("-----------------------------\n");
        }
    }

    public void displayAllRoomsWithoutGuests(){
        for(Room room : rooms){
            System.out.println(room.getInfoWithoutGuests());
            System.out.println("-----------------------------\n");
        }
    }

    public void displayAllGuests(){
        for(Guest guest : guests){
            System.out.println(guest.getInfo());
            System.out.println("-----------------------------\n");
        }
    }

    public void displayAllReservations(){
        for(Reservation reservation : reservations){
            System.out.println(reservation.getInfo());
            System.out.println("-----------------------------\n");
        }
    }

}
