package interfaces;

import model.Date;
import model.Room;

public interface GuestServiceInterface {
    void checkIn(Room room, int numberOfGuests, Date startDate, Date endDate, String note);
    void checkOut(Room room);
}
