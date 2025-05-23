package model;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Класът {@code Activity} представя дейност(активите), организирана от хотела, с описание, дата, час и списък с участващи гости.
 */
public class Activity {
    private int id;
    private String description;
    private Date date;
    private String time;
    private TreeSet<Guest> guests;

    public Activity(int id, String description, Date date, String time) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.time = time;
        this.guests = new TreeSet<>(Comparator.comparingInt(Guest::getRoomNumber)
                .thenComparing(Guest::getPersonalNumber));
    }

    public Date getDate() {
        return date;
    }

    public int getId(){
        return id;
    }

    public TreeSet<Guest> getGuests() {
        return guests;
    }

    /**
     * Връща низ с информация за дейностите(активитети).
     *
     * @return низ с данни
     */
    public String getInfo(){
        StringBuilder builder = new StringBuilder();
        builder.append("Id: "+id+"\n");
        builder.append("Description: "+description+"\n");
        builder.append("Date: "+date.getDateAsString()+"\n");
        builder.append("Starts at: "+time+"\n");
        return builder.toString();
    }

    /**
     * Връща низ с информация за дейностите(активитети) и всички гости, които са се записали за дадена дейност.
     *
     * @return низ с данни
     */
    public String getInfoWithGuests(){
        StringBuilder builder = new StringBuilder();
        builder.append("Id: "+id+"\n");
        builder.append("Description: "+description+"\n");
        builder.append("Date: "+date.getDateAsString()+"\n");
        builder.append("Starts at: "+time+"\n");
        builder.append("-----------------------------\n");
        for(Guest guest : guests){
            builder.append(guest.getInfo());
            builder.append("-----------------------------\n");
        }
        return builder.toString();
    }
}
