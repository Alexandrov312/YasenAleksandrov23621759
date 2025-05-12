package menu;

import menuActions.exitMenuActions.ExitMainMenuAction;
import menuActions.mainMenuActions.*;
import fileManage.FileManager;
import input.ActivityInput;
import input.HotelInput;
import input.ReservationInput;
import input.RoomInput;

public class MainMenu {
    HotelInput hotelInput = new HotelInput();
    RoomInput roomInput = new RoomInput();
    ReservationInput reservationInput = new ReservationInput();
    ActivityInput activityInput = new ActivityInput();
    FileManager fileManager = new FileManager();

    public MainMenu(){
        MenuModel mainMenu = new MenuModel("MAIN MENU");
        mainMenu.addOption(1, "Check in", new CheckInAction(hotelInput));
        mainMenu.addOption(2, "Check out", new CheckOutAction(hotelInput));
        mainMenu.addOption(3, "Available rooms for a given date", new AvailableAction(hotelInput));
        mainMenu.addOption(4, "Report rooms", new ReportAction(hotelInput));
        mainMenu.addOption(5, "Find room", new FindRoomAction(roomInput));
        mainMenu.addOption(6, "Find room - Urgent", new FindRoomAction(roomInput));
        mainMenu.addOption(7, "Set room as unavailable", new UnavailableAction(reservationInput));
        mainMenu.addOption(8, "File manage menu", new FileMenuAction());
        mainMenu.addOption(9, "Hotel manage menu", new HotelManageMenuAction());
        mainMenu.addOption(10, "Display activity by room number", new ActivityByRoomNumberAction(activityInput));
        mainMenu.addOption(11, "Display all guests signed for activity", new ActivityByIdAction(activityInput));
        mainMenu.addOption(12, "Exit", new ExitMainMenuAction(fileManager, mainMenu));
        mainMenu.start();
    }
}
