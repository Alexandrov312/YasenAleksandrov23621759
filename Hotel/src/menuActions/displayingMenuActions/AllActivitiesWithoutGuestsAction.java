package menuActions.displayingMenuActions;

import interfaces.MenuAction;
import model.Hotel;

public class AllActivitiesWithoutGuestsAction implements MenuAction {
    @Override
    public void execute() {
        Hotel.getInstance().getActivityService().displayAllActivitiesWithoutGuests();
    }
}
