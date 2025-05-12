package menuActions.mainMenuActions;

import interfaces.MenuAction;
import menu.HotelManageMenu;

public class HotelManageMenuAction implements MenuAction {
    @Override
    public void execute() {
        HotelManageMenu menu = new HotelManageMenu();
    }
}
