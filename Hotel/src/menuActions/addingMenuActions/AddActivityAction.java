package menuActions.addingMenuActions;

import input.ActivityInput;
import interfaces.MenuAction;

public class AddActivityAction implements MenuAction {
    private ActivityInput activityInput;

    public AddActivityAction(ActivityInput activityInput) {
        this.activityInput = activityInput;
    }

    @Override
    public void execute() {
        activityInput.addActivity();
    }
}
