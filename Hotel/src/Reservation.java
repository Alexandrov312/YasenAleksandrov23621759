import java.util.Set;

public class Reservation {
    private Room room;
    //private Set<Guest> guests;
    private Date startDate;
    private Date endDate;
    private String note;
    //private int numberOfGuests;

    public Reservation(Room room, Date startDate, Date endDate, String note) {
        this.room = room;
        //this.numberOfGuests = numberOfGuests;
        this.startDate = startDate;
        this.endDate = endDate;
        this.note = note;
    }

    public Room getRoom() {
        return room;
    }

    //public Set<Guest> getGuests() {
        //return guests;
    //}

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    public String getNote(){
        return note;
    }
}
