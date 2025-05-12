package menuActions.fileMenuActions;

import fileManage.FileManager;
import interfaces.MenuAction;

public class HelpAction implements MenuAction {
    private FileManager fileManager;

    public HelpAction(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute() {
            fileManager.printHelp();
    }
}
