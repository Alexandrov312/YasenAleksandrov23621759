package menuActions.displayingMenuActions;

import interfaces.MenuAction;
import model.Hotel;

public class AllFreeRoomsAction implements MenuAction {
    @Override
    public void execute() {
        Hotel.getInstance().getRoomService().displayAllFreeRooms();
    }
}
