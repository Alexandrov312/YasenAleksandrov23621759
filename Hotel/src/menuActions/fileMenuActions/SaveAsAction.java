package menuActions.fileMenuActions;

import input.FileInput;
import interfaces.MenuAction;
import java.io.IOException;

public class SaveAsAction implements MenuAction {
    private FileInput fileInput;

    public SaveAsAction(FileInput fileInput) {
        this.fileInput = fileInput;
    }

    @Override
    public void execute() {
        try {
            fileInput.saveAs();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
