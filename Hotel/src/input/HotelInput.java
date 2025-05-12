package input;

import model.Date;
import model.Hotel;
import model.Reservation;
import model.Room;

/**
 * Клас {@code HotelInput} отговаря за обработка на входни данни, свързани с хотела – създаване на хотел,
 * настаняване, освобождаване на стаи и справки.
 */
public class HotelInput {

    /**
     * Създава нов хотел по въведени от потребителя данни.
     *
     * @return {@link Hotel} нов обект от тип Hotel.
     */
    public  Hotel enterHotel(){

        System.out.print("Enter hotel name: ");
        String name = InputHelper.input.nextLine();

        int floors = -1;
        while(floors < 1) {
            floors = InputHelper.enterInteger("Enter number of floors: ");
            if(floors < 1) System.out.println("Number of floors must be at least 1!");
        }
        int stars = -1;
        while(stars < 1 || stars > 5){
            stars = InputHelper.enterInteger("Enter number of stars: ");
            if(stars < 1 || stars > 5){
                System.out.println("Number of stars must be between 1 and 5");
            }
        }
        System.out.println("The hotel was added successfully");
        return new Hotel(name, floors, stars);
    }

    /**
     * Обработва настаняване на гости – с или без резервация.
     * Позволява добавяне на гости към дейности при настаняването.
     */
    public  void checkInInput(){
        String note;
        Room roomResult = null;
        Date startDate = null, endDate = null;
        boolean hasReservation = false;

        hasReservation = InputHelper.confirm("Do you have reservation? ");

        roomResult = RoomInput.enterRoom();
        if(!roomResult.isAvailable()) {
            System.out.println("The room is occupied!");
            return;
        }

        do {
            if(!hasReservation) {
                startDate = Date.today();
                System.out.println("Start date: "+Date.today().getDateAsString());
            }
            else {
                    startDate = InputHelper.enterDate("Enter start date ");
                    if(startDate.isAfter(Date.today())){
                        System.out.println("The start date can not be after today's date!");
                        return;
                    }
            }
            endDate = InputHelper.enterEndDateAfterStartDate(startDate, "Enter end date ");

            if(!hasReservation) {
                if (Hotel.getInstance().getReservationService().isReserved(roomResult, startDate, endDate)) {
                    System.out.println("The room is reserved for that period!");
                    continue;
                }
            }
            break;
        }while(true);

        System.out.print("Enter a note: ");
        note = InputHelper.input.nextLine();

        Reservation reservation = new Reservation(roomResult, startDate, endDate, note);
        boolean found = false;

        if(hasReservation){
            for(Reservation res : Hotel.getInstance().getReservationService().getReservations()){
                if(reservation.equals(res)){
                    found = true;
                    break;
                }
            }
            if(!found){
                System.out.println("Such reservation doesn't exist!");
                return;
            }
        }

        if(InputHelper.confirm("The beds in the room are "+roomResult.getNumberOfBeds()+".\nAre you going to use them all?)")){
            Hotel.getInstance().getGuestService().checkIn(roomResult, roomResult.getNumberOfBeds(), startDate, endDate, note);
        }
        else {
            do{
                int numberOfGuests = InputHelper.enterInteger("Enter number of people: ");
                if(!ValidateInput.validNumberOfGuests(numberOfGuests, roomResult.getNumberOfBeds())){
                    System.out.println("The number of guests must be between 1 and "+roomResult.getNumberOfBeds());
                    continue;
                }
                Hotel.getInstance().getGuestService().checkIn(roomResult, numberOfGuests, startDate, endDate, note);
                break;
            }while(true);
        }

        if(InputHelper.confirm("Would you like to add guests to activity?")){
            GuestInput guestInput = new GuestInput();
            guestInput.addGuestToActivityByCheckIn(roomResult.getGuests());
        }
    }

    /**
     * Освобождава стая, като изпраща гостите от хотела.
     */
    public  void checkOutInput(){
        Room roomResult = RoomInput.enterRoom();
        Hotel.getInstance().getGuestService().checkOut(roomResult);
    }

    /**
     * Показва информация за налични стаи към избрана дата или днешна дата.
     */
    public  void availabilityInput(){
        if(InputHelper.confirm("Would you like to use today's date? ")){
            Hotel.getInstance().getRoomService().availability(null);
        }
        else {
            Date date = InputHelper.enterDate("Enter date ");
            Hotel.getInstance().getRoomService().availability(date);
        }
    }

    /**
     * Генерира отчет за използване на стаите в хотела в определен период.
     */
    public  void reportInput(){
        Date startDate = null, endDate = null;

        startDate = InputHelper.enterDate("Enter start date ");
        endDate = InputHelper.enterEndDateAfterStartDate(startDate, "Enter end date ");

        Hotel.getInstance().getHotelService().report(startDate, endDate);
    }
}
