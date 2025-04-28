package model;
import input.HotelInput;
import service.*;

public class Hotel{
    private String name;
    private int floors;
    private int stars;
    private RoomService roomService;
    private GuestService guestService;
    private ReservationService reservationService;
    private ActivityService activityService;
    private HotelService hotelService;

    private static Hotel hotel;

    public Hotel(String name, int floors, int stars) {
        this.name = name;
        this.floors = floors;
        this.stars = stars;
        this.roomService = new RoomService();
        this.guestService = new GuestService();
        this.reservationService = new ReservationService();
        this.activityService = new ActivityService();
        this.hotelService = new HotelService();
    }

    public int getFloors() {
        return floors;
    }

    public static Hotel getInstance(){
        if(hotel == null){
            Hotel.hotel = HotelInput.enterHotel();
        }
        return hotel;
    }

    public static void setHotel(Hotel hotel){
        Hotel.hotel = hotel;
    }

    public RoomService getRoomService() {
        return roomService;
    }

    public GuestService getGuestService() {
        return guestService;
    }

    public ReservationService getReservationService() {
        return reservationService;
    }

    public ActivityService getActivityService() {
        return activityService;
    }

    public HotelService getHotelService() {
        return hotelService;
    }

    public String getInfo(){
        StringBuilder builder = new StringBuilder();
        builder.append("Name: "+name+'\n');
        builder.append("Floors: "+floors+'\n');
        builder.append("Stars: "+stars+'\n');
        return builder.toString();
    }
}
