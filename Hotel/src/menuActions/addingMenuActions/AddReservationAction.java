package menuActions.addingMenuActions;

import input.ReservationInput;
import interfaces.MenuAction;

public class AddReservationAction implements MenuAction {
    private ReservationInput reservationInput;

    public AddReservationAction(ReservationInput reservationInput) {
        this.reservationInput = reservationInput;
    }

    @Override
    public void execute() {
        reservationInput.addReservation();
    }
}
