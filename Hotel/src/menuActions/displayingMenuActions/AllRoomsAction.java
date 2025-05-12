package menuActions.displayingMenuActions;

import interfaces.MenuAction;
import model.Hotel;

public class AllRoomsAction implements MenuAction {
    @Override
    public void execute() {
        Hotel.getInstance().getRoomService().displayAllRooms();
    }
}
