import java.util.Set;

public class Hotel{
    private String name;
    private int floors;
    private int stars;
    private Set<Guest> guests;
    private Set<Room> rooms;
    private Set<Reservation> reservations;

    public Hotel(String name, int floors, int stars, Set<Guest> guests, Set<Room> rooms) {
        this.name = name;
        this.floors = floors;
        this.stars = stars;
        this.guests = guests;
        this.rooms = rooms;
    }


    public void checkin(Room room, int numberOfGuests, Date startDate, Date endDate, String note) {
        if(!room.isAvailable())
            throw new NotAvailableException("The room is occupied!");
        else if(isReserved(room, startDate, endDate))
            throw new NotAvailableException("The room is reserved for that period!");

        if(numberOfGuests > room.getNumberOfBeds())
            throw new GuestsMoreThanBedsException("The number of guests can not be more than the number of beds!");

        for(int i = 0; i < numberOfGuests; i++){
            room.getGuests().add(UserInput.enterGuest(room.getRoomNumber()));
        }

        reservations.add(new Reservation(room, startDate, endDate, note));
        room.setAvailable(false);
    }

    public void checkin(Room room, Date startDate, Date endDate, String note) {
        if(room.isAvailable())
            throw new NotAvailableException("The room is occupied!");
        else if(isReserved(room, startDate, endDate))
            throw new NotAvailableException("The room is reserved for that period!");

        for(int i = 0; i < room.getNumberOfBeds(); i++){
            room.getGuests().add(UserInput.enterGuest(room.getRoomNumber()));
        }

        reservations.add(new Reservation(room, startDate, endDate, note));
        room.setAvailable(false);
    }

    public void checkout(Room room) {
        room.setAvailable(true);
    }

    public boolean isReserved(Room room, Date startDate, Date endDate){
        for(Reservation reservation : reservations){
            if(reservation.getRoom() == room && !isAvailable(reservation, startDate, endDate)){
                return true;
            }
        }
        return false;
    }

    public boolean isAvailable(Reservation reservation, Date startDate, Date endDate){
        Date resStart = reservation.getStartDate();
        Date resEnd = reservation.getEndDate();
        return (endDate.isBefore(resStart) || startDate.isAfter(resEnd));
    }

    public void availability(Date date){
        for(Reservation reservation : reservations){
            if(isAvailable(reservation, date, date)){
                System.out.println(reservation.getRoom().toString());
            }
        }
    }
    public void availability(){
        Date date = Date.today();

        for(Reservation reservation : reservations){
            if(isAvailable(reservation, date, date)){
                System.out.println(reservation.getRoom().toString());
            }
        }
    }

    public void report(Date startDate, Date endDate){
        for(Reservation reservation : reservations){
            if(!isAvailable(reservation, startDate, endDate)){
                Date today = Date.today();
                int days = reservation.getStartDate().daysBetween(today);
                System.out.println(reservation.getRoom().toString()+"\nDays in total: "+days);
            }
        }
    }

    public Room findRoom(int numberOfBeds, Date startDate, Date endDate){
        Room result = null;
        for(Room room : rooms){
            if(room.getNumberOfBeds() >= numberOfBeds && !isReserved(room, startDate, endDate)){
                if(result != null && result.getNumberOfBeds() > room.getNumberOfBeds()){
                    result = room;
                }
                else{
                    result = room;
                }
            }
        }
        if(result == null)
            System.out.println("Unfortunately the hotel is full!");
        else
            System.out.println("Found!\n"+result.toString());
        return result;
    }

    public Room findRoomUrgent(int numberOfBeds, Date startDate, Date endDate){

        //Проверка, ако все пак има свободна стая
        Room result = findRoom(numberOfBeds, startDate, endDate);
        if(result != null)
            return result;

        for(Room room : rooms){
            if(room.getNumberOfBeds() == room.getNumberOfGuests())
                continue;

            for(Room room1 : rooms){
                if(room.getNumberOfBeds() + room1.getNumberOfBeds() <= numberOfBeds){
                    if(room.getNumberOfBeds() > room1.getNumberOfBeds()){
                        for(Guest guest : room.getGuests()){
                            room1.getGuests().add(guest);
                            room.getGuests().remove(guest);
                        }
                        result = room1;
                    }
                    else{
                        for(Guest guest : room1.getGuests()){
                            room.getGuests().add(guest);
                            room1.getGuests().remove(guest);
                        }
                        result = room;
                    }
                    break;
                }
            }
        }
        return result;
    }

    public void unavailable(Room room, Date startDate, Date endDate, String note){
        reservations.add(new Reservation(room, startDate, endDate, note));
        if(Date.today().isBefore(endDate) && Date.today().isAfter(startDate))
            room.setAvailable(false);
    }

}
