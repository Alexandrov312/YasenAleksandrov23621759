package model;

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

    public String getInfo(){
        StringBuilder builder = new StringBuilder();
        builder.append("Room number: "+room.getRoomNumber()+'\n');
        builder.append("Start date: "+startDate.getDateAsString()+"\n");
        builder.append("End date: "+endDate.getDateAsString()+"\n");
        builder.append("Note: "+note+"\n");
        return builder.toString();
    }
}
