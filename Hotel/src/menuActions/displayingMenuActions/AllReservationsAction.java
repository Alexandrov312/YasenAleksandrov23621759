package menuActions.displayingMenuActions;

import interfaces.MenuAction;
import model.Hotel;

public class AllReservationsAction implements MenuAction {
    @Override
    public void execute() {
        Hotel.getInstance().getReservationService().displayAllReservations();
    }
}
