package menuActions.exitMenuActions;

import fileManage.FileManager;
import interfaces.MenuAction;
import menu.MenuModel;

import java.io.IOException;

public class ExitMainMenuAction implements MenuAction {
    private final FileManager fileManager;
    private final MenuModel menu;

    public ExitMainMenuAction(FileManager fileManager, MenuModel menu) {
        this.fileManager = fileManager;
        this.menu = menu;
    }

    @Override
    public void execute() {
        try {
            fileManager.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        menu.stop();
    }
}
