package menuActions.displayingMenuActions;

import interfaces.MenuAction;
import model.Hotel;

public class HotelInfoAction implements MenuAction {
    @Override
    public void execute() {
        System.out.println(Hotel.getInstance().getInfo());
    }
}
