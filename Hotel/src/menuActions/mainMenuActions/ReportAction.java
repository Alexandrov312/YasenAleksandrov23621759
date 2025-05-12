package menuActions.mainMenuActions;

import input.HotelInput;
import interfaces.MenuAction;

public class ReportAction implements MenuAction {

    private HotelInput hotelInput;

    public ReportAction(HotelInput hotelInput) {
        this.hotelInput = hotelInput;
    }

    @Override
    public void execute() {
        hotelInput.reportInput();
    }
}
