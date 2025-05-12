package menu;

import menuActions.exitMenuActions.ExitMenuAction;
import menuActions.fileMenuActions.*;
import fileManage.FileManager;
import input.FileInput;

import java.io.FileNotFoundException;

public class FileMenu {
    FileInput fileInput = new FileInput();
    FileManager fileManager = new FileManager();

    public FileMenu() throws FileNotFoundException {
        MenuModel fileMenu = new MenuModel("FILE MENU");
        fileMenu.addOption(1,"Open", new OpenAction(fileInput));
        fileMenu.addOption(2,"Save", new SaveAction(fileManager));
        fileMenu.addOption(3,"Save as", new SaveAsAction(fileInput));
        fileMenu.addOption(4,"Help", new HelpAction(fileManager));
        fileMenu.addOption(5,"Close", new CloseAction(fileManager));
        fileMenu.addOption(6,"Go back", new ExitMenuAction(fileMenu));
        fileMenu.start();
    }
}
