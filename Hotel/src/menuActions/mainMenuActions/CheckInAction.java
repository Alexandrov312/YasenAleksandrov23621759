package menuActions.mainMenuActions;

import input.HotelInput;
import interfaces.MenuAction;

public class CheckInAction implements MenuAction {
    private HotelInput hotelInput;

    public CheckInAction(HotelInput hotelInput) {
        this.hotelInput = hotelInput;
    }

    @Override
    public void execute() {
        hotelInput.checkInInput();
    }
}
