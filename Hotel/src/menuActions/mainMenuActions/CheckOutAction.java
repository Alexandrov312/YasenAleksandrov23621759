package menuActions.mainMenuActions;

import input.HotelInput;
import interfaces.MenuAction;

public class CheckOutAction implements MenuAction {

    private HotelInput hotelInput;

    public CheckOutAction(HotelInput hotelInput) {
        this.hotelInput = hotelInput;
    }

    @Override
    public void execute() {
        hotelInput.checkInInput();
    }
}
