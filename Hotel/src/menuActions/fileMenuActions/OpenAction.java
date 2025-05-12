package menuActions.fileMenuActions;

import input.FileInput;
import interfaces.MenuAction;
import java.io.FileNotFoundException;

public class OpenAction implements MenuAction {
    private FileInput fileInput;

    public OpenAction(FileInput fileInput) {
        this.fileInput = fileInput;
    }

    @Override
    public void execute() {
        try {
            fileInput.openFile();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
