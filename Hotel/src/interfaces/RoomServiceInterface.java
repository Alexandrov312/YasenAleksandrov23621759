package interfaces;

import model.Date;
import model.Room;

public interface RoomServiceInterface {
    void availability(Date date);
    Room findRoom(int numberOfBeds, Date startDate, Date endDate);
    Room findRoomUrgent(int numberOfBeds, Date startDate, Date endDate);
}
