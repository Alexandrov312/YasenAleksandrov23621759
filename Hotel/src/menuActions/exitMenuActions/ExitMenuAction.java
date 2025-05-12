package menuActions.exitMenuActions;

import interfaces.MenuAction;
import menu.MenuModel;

public class ExitMenuAction implements MenuAction {
    private MenuModel menu;

    public ExitMenuAction(MenuModel menu) {
        this.menu = menu;
    }

    @Override
    public void execute() {
        menu.stop();
    }
}
