package menuActions.displayingMenuActions;

import interfaces.MenuAction;
import model.Hotel;

public class AllActivitiesWithGuestsAction implements MenuAction {
    @Override
    public void execute() {
        Hotel.getInstance().getActivityService().displayAllActivitiesWithGuests();
    }
}
