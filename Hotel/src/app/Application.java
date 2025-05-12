package app;

import menu.MainMenu;

import java.io.IOException;

/**
 * Клас {@code Application} представлява началната точка на изпълнение на приложението.
 *
 * @author [Ясен Росенов Александров]
 */
public class Application {
    public static void main(String[] args) throws IOException {
        MainMenu menu = new MainMenu();
    }
}
