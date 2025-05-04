package service;

import input.GuestInput;
import interfaces.GuestServiceInterface;
import model.*;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Клас {@code GuestService} за управление на гости в хотела – настаняване, напускане и извеждане на информация.
 */
public class GuestService implements GuestServiceInterface {
    private TreeSet<Guest> guests;

    public GuestService() {
        this.guests = new TreeSet<>(Comparator.comparingInt(Guest::getRoomNumber)
                .thenComparing(Guest::getPersonalNumber));
    }

    public TreeSet<Guest> getGuests() {
        return guests;
    }

    /**
     * Настанява определен брой гости в дадена стая и създава резервация.
     * @param room стаята, в която се настаняват гостите
     * @param numberOfGuests брой гости за настаняване
     * @param startDate начална дата на престоя
     * @param endDate крайна дата на престоя
     * @param note бележка към резервацията
     */
    @Override
    public void checkIn(Room room, int numberOfGuests, Date startDate, Date endDate, String note) {
        for(int i = 0; i < numberOfGuests; i++){
            System.out.println("\nEnter personal information for guest "+(i+1)+"!");
            Guest guest = GuestInput.enterGuest(room.getRoomNumber());
            room.getGuests().add(guest);
            guests.add(guest);
        }

        Hotel.getInstance().getReservationService().getReservations().add(new Reservation(room, startDate, endDate, note));
        room.setAvailable(false);
    }

    /**
     * Освобождава стая и премахва свързаната с нея резервация и гости.
     * @param room стаята, която се освобождава
     */
    @Override
    public void checkOut(Room room) {
        if(room.isAvailable()){
            System.out.println("The room is already available!");
            return;
        }
        Reservation reservation = null;
        for(Reservation rs : Hotel.getInstance().getReservationService().getReservations()){
            if(rs.getRoom().getRoomNumber() == room.getRoomNumber()){
                if(Date.today().isBetween(rs.getStartDate(), rs.getEndDate())){
                    reservation = rs;
                    break;
                }
            }
        }
        if(reservation != null)
            Hotel.getInstance().getReservationService().getReservations().remove(reservation);
        room.setAvailable(true);

        guests.removeAll(room.getGuests());

        for(Activity activity : Hotel.getInstance().getActivityService().getActivities()){
            for(Guest guest : guests){
                activity.getGuests().remove(guest);
            }
        }
        room.getGuests().clear();
        System.out.println("The guests from room "+room.getRoomNumber()+" have been checked out successfully!");
    }

    /**
     * Извежда информация за всички гости в хотела.
     */
    public void displayAllGuests(){
        for(Guest guest : guests){
            System.out.println(guest.getInfo());
            System.out.println("-----------------------------\n");
        }
    }

}
