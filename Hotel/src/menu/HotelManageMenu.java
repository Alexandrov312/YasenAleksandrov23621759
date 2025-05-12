package menu;

import menuActions.exitMenuActions.ExitMenuAction;
import menuActions.hotelManageMenuActions.AddingMenuAction;
import menuActions.hotelManageMenuActions.DisplayingMenuAction;

public class HotelManageMenu {
    public HotelManageMenu(){
        MenuModel hotelManageMenu = new MenuModel("HOTEL MANAGE MENU");
        hotelManageMenu.addOption(1, "Adding menu", new AddingMenuAction());
        hotelManageMenu.addOption(2, "Displaying menu", new DisplayingMenuAction());
        hotelManageMenu.addOption(3, "Go back", new ExitMenuAction(hotelManageMenu));
        hotelManageMenu.start();
    }
}
