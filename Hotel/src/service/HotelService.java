package service;

import interfaces.HotelServiceInterface;
import model.Date;
import model.Hotel;
import model.Reservation;

public class HotelService implements HotelServiceInterface {

    @Override
    public void report(Date startDate, Date endDate){
        for(Reservation reservation : Hotel.getInstance().getReservationService().getReservations()){
            if(Hotel.getInstance().getReservationService().isAvailable(reservation, startDate, endDate)){
                Date today = Date.today();
                int days;
                if(Date.today().isBefore(endDate)) {
                    days = reservation.getStartDate().daysBetween(today);
                }
                else{
                    days = reservation.getStartDate().daysBetween(endDate);
                }
                System.out.println("Reservation: ");
                System.out.println(reservation.getInfo());
                System.out.println(reservation.getRoom().getInfoWithoutGuests()+"\nDays in total: "+days);
                System.out.println("-----------------------------");
            }
        }
    }
}
