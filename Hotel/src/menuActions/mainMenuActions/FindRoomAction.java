package menuActions.mainMenuActions;

import input.RoomInput;
import interfaces.MenuAction;

public class FindRoomAction implements MenuAction {

    private RoomInput roomInput;

    public FindRoomAction(RoomInput roomInput) {
        this.roomInput = roomInput;
    }

    @Override
    public void execute() {
        roomInput.findRoomInput(false);
    }
}
