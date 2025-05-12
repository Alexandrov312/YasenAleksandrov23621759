package menuActions.displayingMenuActions;

import interfaces.MenuAction;
import model.Hotel;

public class AllOccupiedRoomsAction implements MenuAction {
    @Override
    public void execute() {
        Hotel.getInstance().getRoomService().displayAllOccupiedRooms();
    }
}
