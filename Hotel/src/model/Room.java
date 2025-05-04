package model;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Класът {@code Room} представлява хотелска стая.
 */
public class Room {

    private int roomNumber;
    private int floor;
    private int numberOfBeds;
    private boolean isAvailable;
    private View view;
    private TreeSet<Guest> guests;

    public Room(int floor, int numberOfBeds, View view, int roomNumber) {
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.numberOfBeds = numberOfBeds;
        this.isAvailable = true;
        this.view = view;
        guests = new TreeSet<>(Comparator.comparingInt(Guest::getRoomNumber)
                .thenComparing(Guest::getPersonalNumber));
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getFloor() {
        return floor;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public View getView() {
        return view;
    }

    public int getNumberOfGuests() {
        return guests.size();
    }

    public TreeSet<Guest> getGuests() {
        return guests;
    }

    /**
     * Добавя гост в стаята, ако има свободни легла.
     */
    public void addGuest(Guest guest){
        if(numberOfBeds >= guests.size() + 1){
            guests.add(guest);
        }
        else{
            System.out.println("Guests can't be more than beds in the room");
        }
    }

    /**
     * Връща информация за стаята и списък с гостите.
     */
    public String getInfoWithGuests(){
        StringBuilder builder = new StringBuilder();
        builder.append("Room number: "+roomNumber+"\n");
        builder.append("Floor: "+floor+"\n");
        builder.append("Number of beds: "+numberOfBeds+"\n");
        builder.append("Is available: "+isAvailable+"\n");
        builder.append("Hotel View: "+view.getDescription()+"\n");
        builder.append("Number of guests: "+guests.size()+"\n");
        if(guests.size() > 0) builder.append("\n");
        for(Guest guest : guests){
            builder.append(guest.getInfo()+"\n");
        }

        return builder.toString();
    }

    /**
     * Връща информация за стаята без списъка с гости.
     */
    public String getInfoWithoutGuests(){
        StringBuilder builder = new StringBuilder();
        builder.append("Room number: "+roomNumber+"\n");
        builder.append("Floor: "+floor+"\n");
        builder.append("Number of beds: "+numberOfBeds+"\n");
        builder.append("Is available: "+isAvailable+"\n");
        builder.append("Hotel View: "+view+"\n");

        return builder.toString();
    }
}
