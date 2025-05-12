package menuActions.fileMenuActions;

import fileManage.FileManager;
import interfaces.MenuAction;
import java.io.IOException;

public class SaveAction implements MenuAction {
    private FileManager fileManager;

    public SaveAction(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute() {
        try {
            fileManager.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
