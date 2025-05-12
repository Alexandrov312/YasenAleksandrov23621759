package menuActions.hotelManageMenuActions;

import interfaces.MenuAction;
import menu.AddingMenu;

public class AddingMenuAction implements MenuAction {
    @Override
    public void execute() {
        AddingMenu menu = new AddingMenu();
    }
}
