package interfaces;

import model.Date;
import model.Reservation;
import model.Room;

public interface ReservationServiceInterface {
    boolean isReserved(Room room, Date startDate, Date endDate);
    boolean isAvailable(Reservation reservation, Date startDate, Date endDate);
    void unavailable(Room room, Date startDate, Date endDate, String note);
}
