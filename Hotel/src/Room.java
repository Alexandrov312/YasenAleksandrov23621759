import java.util.HashSet;
import java.util.Set;

public class Room {

    private int roomNumber;
    private int floor;
    private int numberOfBeds;
    private boolean isAvailable;
    private View view;
    //private int numberOfGuests;
    private Set<Guest> guests;

    public Room(int floor, int numberOfBeds, View view, int roomNumber) {
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.numberOfBeds = numberOfBeds;
        this.isAvailable = false;
        this.view = view;
        //numberOfGuests = 0;
        guests = new HashSet<>();
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder("Room description - ");
        builder.append("room number: ").append(roomNumber);
        builder.append(", floor: ").append(floor);
        builder.append(", number of beds: ").append(numberOfBeds);
        builder.append(", occupied: ").append(isAvailable);
        builder.append(", view: ").append(view.getDescription());
        return builder.toString();
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

    public Set<Guest> getGuests() {
        return guests;
    }
}
