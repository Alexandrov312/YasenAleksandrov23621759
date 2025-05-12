package menuActions.mainMenuActions;

import input.ActivityInput;
import interfaces.MenuAction;

public class ActivityByRoomNumberAction implements MenuAction {
    private ActivityInput activityInput;

    public ActivityByRoomNumberAction(ActivityInput activityInput) {
        this.activityInput = activityInput;
    }

    @Override
    public void execute() {
        activityInput.activityByRoomNumberInput();
    }
}
