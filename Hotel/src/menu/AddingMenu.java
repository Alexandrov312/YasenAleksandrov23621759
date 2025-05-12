package menu;

import input.*;
import menuActions.addingMenuActions.*;
import menuActions.exitMenuActions.ExitMenuAction;

public class AddingMenu {
    GuestInput guestInput = new GuestInput();
    RoomInput roomInput = new RoomInput();
    ReservationInput reservationInput = new ReservationInput();
    ActivityInput activityInput = new ActivityInput();

    private static final String[] addingOptions = {"Add guest", "Add room", "Add reservation", "Add activity",
            "Add guest to activity", "Go back"};

    public AddingMenu(){
        MenuModel addingMenu = new MenuModel("ADDING MENU");
        addingMenu.addOption(1, "Add guest", new AddGuestAction(guestInput));
        addingMenu.addOption(2, "Add room", new AddRoomAction(roomInput));
        addingMenu.addOption(3, "Add reservation", new AddReservationAction(reservationInput));
        addingMenu.addOption(4, "Add activity", new AddActivityAction(activityInput));
        addingMenu.addOption(5, "Add guest to activity", new AddGuestToActivityAction(guestInput));
        addingMenu.addOption(6, "Go back", new ExitMenuAction(addingMenu));
        addingMenu.start();
    }
}
