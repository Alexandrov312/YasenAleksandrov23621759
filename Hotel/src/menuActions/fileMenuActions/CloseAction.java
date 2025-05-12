package menuActions.fileMenuActions;

import fileManage.FileManager;
import interfaces.MenuAction;

public class CloseAction implements MenuAction {
    private FileManager fileManager;

    public CloseAction(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute() {
        fileManager.close();
    }
}
