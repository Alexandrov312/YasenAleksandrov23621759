package menuActions.mainMenuActions;

import interfaces.MenuAction;
import menu.FileMenu;

import java.io.FileNotFoundException;

public class FileMenuAction implements MenuAction {
    @Override
    public void execute() {
        try {
            FileMenu fileMenu = new FileMenu();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
