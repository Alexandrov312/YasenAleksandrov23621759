package input;

import model.Date;
import model.Hotel;
import model.Reservation;
import model.Room;

/**
 * Класът {@code ReservationInput} предоставя методи за добавяне на нова резервация
 * и задаване на стая като невалидна за даден период.
 */
public class ReservationInput {

    /**
     * Добавя резервация, по въведени от потребителя данни.
     */
    public  void addReservation(){
        String note;
        Room roomResult;
        Date startDate = null, endDate = null;

        do {
            roomResult = RoomInput.enterRoom();
            startDate = InputHelper.enterFutureDate("Enter start date ");
            endDate = InputHelper.enterEndDateAfterStartDate(startDate, "Enter end date ");

            if (Hotel.getInstance().getReservationService().isReserved(roomResult, startDate, endDate)) {
                System.out.println("The room is reserved for that period!");
            }
            else
                break;
        }while(true);

        System.out.print("Enter a note: ");
        note = InputHelper.input.nextLine();

        if(Hotel.getInstance().getReservationService().getReservations().add(new Reservation(roomResult, startDate, endDate, note))){
            System.out.println("The reservation was added successfully!");
        }
        else{
            System.out.println("Error to add reservation");
        }
    }

    /**
     * Добавя 'резервация', като задава дадена стая като невалидна за определен период по дадена причина.
     */
    public  void unavailableInput(){
        Room roomResult = null;
        Date startDate = null, endDate = null;
        String note;
        roomResult = RoomInput.enterRoom();
        if(!roomResult.isAvailable()) {
            System.out.println("The room is occupied!");
            return;
        }

        startDate = InputHelper.enterFutureDate("Enter start date ");
        endDate = InputHelper.enterEndDateAfterStartDate(startDate, "Enter end date ");

        System.out.print("Enter a note: ");
        note = InputHelper.input.nextLine();

        Hotel.getInstance().getReservationService().unavailable(roomResult, startDate, endDate, note);
    }
}
