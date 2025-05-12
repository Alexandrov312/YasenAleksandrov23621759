package menu;

import input.InputHelper;
import interfaces.MenuAction;

import java.util.LinkedHashMap;
import java.util.Map;

public class MenuModel {
    private final String title;
    private final Map<Integer, MenuOption> options = new LinkedHashMap<>();
    private boolean running = true;

    public MenuModel(String title) {
        this.title = title;
    }

    public void addOption(int number, String description, MenuAction action) {
        options.put(number, new MenuOption(description, action));
    }

    public void start() {
        while (running) {
            System.out.println("\n" + title);
            for (Map.Entry<Integer, MenuOption> entry : options.entrySet()) {
                System.out.println(entry.getKey() + ") " + entry.getValue().description);
            }
            int choice = InputHelper.enterInteger("Option: ");
            MenuOption option = options.get(choice);
            if (option != null) {
                option.action.execute();
            } else {
                System.out.println("Invalid option!");
            }
        }
    }

    public void stop() {
        this.running = false;
    }

    private static class MenuOption {
        String description;
        MenuAction action;

        public MenuOption(String description, MenuAction action) {
            this.description = description;
            this.action = action;
        }
    }
}
