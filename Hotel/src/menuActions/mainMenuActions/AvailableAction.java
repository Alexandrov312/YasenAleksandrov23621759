package menuActions.mainMenuActions;

import input.HotelInput;
import interfaces.MenuAction;

public class AvailableAction implements MenuAction {
    private HotelInput hotelInput;

    public AvailableAction(HotelInput hotelInput) {
        this.hotelInput = hotelInput;
    }

    @Override
    public void execute() {
        hotelInput.availabilityInput();
    }
}
