package menuActions.mainMenuActions;

import input.ReservationInput;
import interfaces.MenuAction;

public class UnavailableAction implements MenuAction {
    private ReservationInput reservationInput;

    public UnavailableAction(ReservationInput reservationInput) {
        this.reservationInput = reservationInput;
    }

    @Override
    public void execute() {
        reservationInput.unavailableInput();
    }
}
