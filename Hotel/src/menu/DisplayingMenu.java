package menu;

import menuActions.displayingMenuActions.*;
import menuActions.exitMenuActions.ExitMenuAction;

public class DisplayingMenu {
    public DisplayingMenu(){
        MenuModel displayingMenu = new MenuModel("DISPLAYING MENU");
        displayingMenu.addOption(1, "Show all guests", new AllGuestsAction());
        displayingMenu.addOption(2, "Show all rooms", new AllRoomsAction());
        displayingMenu.addOption(3, "Show all occupied rooms", new AllOccupiedRoomsAction());
        displayingMenu.addOption(4, "Show all free rooms", new AllFreeRoomsAction());
        displayingMenu.addOption(5, "Show all reservations", new AllReservationsAction());
        displayingMenu.addOption(6, "Show all activities without guests", new AllActivitiesWithoutGuestsAction());
        displayingMenu.addOption(7, "Show all activities with guests", new AllActivitiesWithGuestsAction());
        displayingMenu.addOption(8, "Show Hotel info", new HotelInfoAction());
        displayingMenu.addOption(9, "Go back", new ExitMenuAction(displayingMenu));
        displayingMenu.start();
    }
}
