package service;

import input.GuestInput;
import interfaces.GuestServiceInterface;
import model.*;

import java.util.Comparator;
import java.util.TreeSet;

public class GuestService implements GuestServiceInterface {
    private TreeSet<Guest> guests;

    public GuestService() {
        this.guests = new TreeSet<>(Comparator.comparingInt(Guest::getRoomNumber)
                .thenComparing(Guest::getPersonalNumber));
    }

    public TreeSet<Guest> getGuests() {
        return guests;
    }

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

    @Override
    public void checkOut(Room room) {
        Reservation reservation = null;
        for(Reservation rs : Hotel.getInstance().getReservationService().getReservations()){
            if(rs.getRoom().getRoomNumber() == room.getRoomNumber()){
                if(Date.today().isBetween(rs.getStartDate(), rs.getEndDate())){
                    reservation = rs;
                    break;
                }
            }
        }
        Hotel.getInstance().getReservationService().getReservations().remove(reservation);
        room.setAvailable(true);
        guests.removeAll(room.getGuests());
        Hotel.getInstance().getActivityService().getActivities().removeAll(room.getGuests());
        room.getGuests().clear();
        System.out.println("The guests from room "+room.getRoomNumber()+" have been checked out successfully!");
    }

    public void displayAllGuests(){
        for(Guest guest : guests){
            System.out.println(guest.getInfo());
            System.out.println("-----------------------------\n");
        }
    }

}
