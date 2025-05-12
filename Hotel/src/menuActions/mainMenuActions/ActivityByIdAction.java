package menuActions.mainMenuActions;

import input.ActivityInput;
import interfaces.MenuAction;

public class ActivityByIdAction implements MenuAction {
    private ActivityInput activityInput;

    public ActivityByIdAction(ActivityInput activityInput) {
        this.activityInput = activityInput;
    }

    @Override
    public void execute() {
        activityInput.activityByIdInput();
    }
}
