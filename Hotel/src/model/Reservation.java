package model;

import java.util.Objects;

/**
 * Класът {@code Hotel} представлява резервация за стая в хотела.
 */
public class Reservation {
    private Room room;
    private Date startDate;
    private Date endDate;
    private String note;

    public Reservation(Room room, Date startDate, Date endDate, String note) {
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(room, that.room) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room, startDate, endDate, note);
    }

    public Room getRoom() {
        return room;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getNote(){
        return note;
    }

    /**
     * Връща информация за резервацията във формат на текст.
     */
    public String getInfo(){
        StringBuilder builder = new StringBuilder();
        builder.append("Room number: "+room.getRoomNumber()+'\n');
        builder.append("Start date: "+startDate.getDateAsString()+"\n");
        builder.append("End date: "+endDate.getDateAsString()+"\n");
        builder.append("Note: "+note+"\n");
        return builder.toString();
    }
}
