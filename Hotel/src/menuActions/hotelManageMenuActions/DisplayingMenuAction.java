package menuActions.hotelManageMenuActions;

import interfaces.MenuAction;
import menu.DisplayingMenu;

public class DisplayingMenuAction implements MenuAction {
    @Override
    public void execute() {
        DisplayingMenu menu = new DisplayingMenu();
    }
}
