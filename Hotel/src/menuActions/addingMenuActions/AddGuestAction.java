package menuActions.addingMenuActions;

import input.GuestInput;
import interfaces.MenuAction;

public class AddGuestAction implements MenuAction {
    private GuestInput guestInput;

    public AddGuestAction(GuestInput guestInput) {
        this.guestInput = guestInput;
    }

    @Override
    public void execute() {
        guestInput.addGuest();
    }
}
