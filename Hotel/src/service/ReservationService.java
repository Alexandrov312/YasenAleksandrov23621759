package service;

import interfaces.ReservationServiceInterface;
import model.Date;
import model.Reservation;
import model.Room;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Клас {@code ReservationService} за управление на резервации в хотела.
 * Отговаря за съхранение, проверка и добавяне на резервации.
 */
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

    /**
     * Проверява дали дадена стая е резервирана в указания период.
     * @param room Стая.
     * @param startDate Начална дата.
     * @param endDate Крайна дата.
     * @return true ако е резервирана, false в противен случай.
     */
    @Override
    public boolean isReserved(Room room, Date startDate, Date endDate){
        for(Reservation reservation : reservations){
            if(reservation.getRoom().equals(room) && isAvailable(reservation, startDate, endDate)){
                return true;
            }
        }
        return false;
    }

    /**
     * Проверява дали дадената резервация е валидна за зададения период.
     * @param reservation Резервация.
     * @param startDate Начална дата.
     * @param endDate Крайна дата.
     * @return true ако има припокриване, false в противен случай.
     */
    @Override
    public boolean isAvailable(Reservation reservation, Date startDate, Date endDate){
        Date resStart = reservation.getStartDate();
        Date resEnd = reservation.getEndDate();
        return (Date.isPeriodOverlapping(startDate, endDate, resStart, resEnd));
    }

    /**
     * Добавя нова резервация и променя валидността на стаята, ако е необходима.
     * @param room Стая.
     * @param startDate Начална дата.
     * @param endDate Крайна дата.
     * @param note Бележка към резервацията.
     */
    @Override
    public void unavailable(Room room, Date startDate, Date endDate, String note){
        Reservation reservation = new Reservation(room, startDate, endDate, note);
        reservations.add(reservation);
        if (Date.today().isBetween(startDate, endDate)) {
            room.setAvailable(false);
        }
    }

    /**
     * Извежда информация за всички резервации на екрана.
     */
    public void displayAllReservations(){
        for(Reservation reservation : reservations){
            System.out.println(reservation.getInfo());
            System.out.println("-----------------------------\n");
        }
    }
}
