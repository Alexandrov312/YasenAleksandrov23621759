package menuActions.addingMenuActions;

import input.RoomInput;
import interfaces.MenuAction;

public class AddRoomAction implements MenuAction {
    private RoomInput roomInput;

    public AddRoomAction(RoomInput roomInput) {
        this.roomInput = roomInput;
    }

    @Override
    public void execute() {
        roomInput.addRoom();
    }
}
