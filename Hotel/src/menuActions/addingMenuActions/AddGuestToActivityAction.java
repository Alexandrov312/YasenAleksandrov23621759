package menuActions.addingMenuActions;

import input.GuestInput;
import interfaces.MenuAction;

public class AddGuestToActivityAction implements MenuAction {
    private GuestInput guestInput;

    public AddGuestToActivityAction(GuestInput guestInput) {
        this.guestInput = guestInput;
    }

    @Override
    public void execute() {
        guestInput.addGuestToActivity(null);
    }
}
