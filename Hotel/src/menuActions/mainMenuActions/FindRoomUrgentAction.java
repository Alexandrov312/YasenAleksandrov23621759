package menuActions.mainMenuActions;

import input.RoomInput;
import interfaces.MenuAction;

public class FindRoomUrgentAction implements MenuAction {

    private RoomInput roomInput;

    public FindRoomUrgentAction(RoomInput roomInput) {
        this.roomInput = roomInput;
    }

    @Override
    public void execute() {
        roomInput.findRoomInput(true);
    }
}
