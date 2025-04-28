package input;

import model.*;

import java.util.TreeSet;

public class GuestInput {

    public static Guest enterGuest(int roomNumber){
        String firstName;
        String lastName;
        Date birthDate = null;

        String personalNumber;
        String country;

        System.out.print("First name: ");
        firstName = InputHelper.input.nextLine();
        System.out.print("Last name: ");
        lastName = InputHelper.input.nextLine();

        birthDate = InputHelper.enterDate("Enter birthday ");

        personalNumber = InputHelper.enterPersonalNumber("Personal number: ");

        System.out.print("Country: ");
        country = InputHelper.input.nextLine();

        return new Guest(firstName, lastName, birthDate, personalNumber, country, roomNumber);
    }

    public static void addGuest(){
        Room roomResult;
        do {
            roomResult = RoomInput.enterRoom();
            if(ValidateInput.isRoomFull(roomResult)){
                System.out.println("The room is full!");
                continue;
            }
            break;
        }while(true);

        if(roomResult.getNumberOfGuests() == 0){
            System.out.println("You can only add guests to the room if a reservation was made!");
            return;
        }

        Guest guest = GuestInput.enterGuest(roomResult.getRoomNumber());
        roomResult.getGuests().add(guest);
        if(Hotel.getInstance().getGuestService().getGuests().add(guest)){
            System.out.println(guest.getInfo());
            System.out.println("The guest was added successfully!");
        }
        else{
            System.out.println("Error to add guest!");
        }
    }

    public static void addGuestToActivity(TreeSet<Guest> guests){
        Guest guest = null;
        Activity activity = null;
        String personalNumber;
        int id = -1;
        if(guests == null){
            guests = Hotel.getInstance().getGuestService().getGuests();
        }

        do {
            if(guests != Hotel.getInstance().getGuestService().getGuests()) {
                for (Guest g : guests) {
                    System.out.println(g.getInfo());
                    System.out.println("-----------------------------\n");
                }
            }

            personalNumber = InputHelper.enterPersonalNumber("Enter personal number of guest: ");
            for(Guest g : guests){
                if(g.getPersonalNumber().equals(personalNumber)){
                    guest = g;
                    break;
                }
            }
            if(guest != null) break;
            else System.out.println("Guest with such personal number doesn't exist!");
        }while(true);

        Reservation reservation = null;
        for(Reservation rs : Hotel.getInstance().getReservationService().getReservations()){
            if(rs.getRoom().getRoomNumber() == guest.getRoomNumber()){
                if(Date.today().isBetween(rs.getStartDate(), rs.getEndDate())){
                    reservation = rs;
                    break;
                }
            }
        }

        do{
            Hotel.getInstance().getActivityService().displayAllActivitiesWithoutGuests();
            id = InputHelper.enterInteger("Enter the id of the activity you want to sign for: ");
            for(Activity act : Hotel.getInstance().getActivityService().getActivities()){
                if(act.getId() == id){
                    activity = act;
                    break;
                }
            }
            if(activity != null) {
                if(reservation != null && activity.getDate().isAfter(reservation.getEndDate())){
                    System.out.println("The guest won't be in the hotel anymore for the activity!");
                    System.out.println("The guest is in the hotel until "+reservation.getEndDate());
                    return;
                }
                break;
            }
            else System.out.println("Activity with such id doesn't exist!");
        }while(true);

        if(activity.getGuests().contains(guest)){
            System.out.println("The guest was already added to the activity!");
            return;
        }

        if(activity.getGuests().add(guest))
            System.out.println(guest.getFirstName()+" "+guest.getLastName()+" was added successfully to the activity!");
        else
            System.out.println("Error to add guest to activity!");
    }

    public static void addGuestToActivityByCheckIn(TreeSet<Guest> guests){
        if(Hotel.getInstance().getActivityService().getActivities().isEmpty()){
            System.out.println("There are no activities!");
            return;
        }
        do {
            addGuestToActivity(guests);
        } while (InputHelper.confirm("Would you like to add another guest? "));
    }
}
