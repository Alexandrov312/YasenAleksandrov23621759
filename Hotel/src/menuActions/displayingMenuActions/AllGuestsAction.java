package menuActions.displayingMenuActions;

import interfaces.MenuAction;
import model.Hotel;

public class AllGuestsAction implements MenuAction {
    @Override
    public void execute() {
        Hotel.getInstance().getGuestService().displayAllGuests();
    }
}
