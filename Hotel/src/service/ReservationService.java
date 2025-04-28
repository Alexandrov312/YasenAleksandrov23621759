package service;

import interfaces.ReservationServiceInterface;
import model.Date;
import model.Reservation;
import model.Room;

import java.util.Comparator;
import java.util.TreeSet;

public class ReservationService implements ReservationServiceInterface {
    private TreeSet<Reservation> reservations;

    public ReservationService() {
        this.reservations = new TreeSet<>(
                Comparator
                        .comparing((Reservation r) -> r.getRoom().getRoomNumber())
                        .thenComparing(r -> r.getStartDate().getDateAsString())
                        .thenComparing(r -> r.getEndDate().getDateAsString())
                        .thenComparing(Reservation::getNote)
        );
    }

    public TreeSet<Reservation> getReservations() {
        return reservations;
    }

    @Override
    public boolean isReserved(Room room, Date startDate, Date endDate){
        for(Reservation reservation : reservations){
            if(reservation.getRoom().equals(room) && isAvailable(reservation, startDate, endDate)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isAvailable(Reservation reservation, Date startDate, Date endDate){
        Date resStart = reservation.getStartDate();
        Date resEnd = reservation.getEndDate();
        return (!endDate.isBefore(resStart) && !startDate.isAfter(resEnd));
    }

    @Override
    public void unavailable(Room room, Date startDate, Date endDate, String note){
        Reservation reservation = new Reservation(room, startDate, endDate, note);
        reservations.add(reservation);
        if (Date.today().isBetween(startDate, endDate)) {
            room.setAvailable(false);
        }
    }

    public void displayAllReservations(){
        for(Reservation reservation : reservations){
            System.out.println(reservation.getInfo());
            System.out.println("-----------------------------\n");
        }
    }
}
