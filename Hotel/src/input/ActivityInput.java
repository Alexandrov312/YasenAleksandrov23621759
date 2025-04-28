package input;

import model.Activity;
import model.Date;
import model.Hotel;

public class ActivityInput {

    public static void addActivity(){
        int id;
        String description;
        Date date = null;
        String time;

        do{
            id = InputHelper.enterInteger("Enter id: ");
            if(InputHelper.findActivityById(id) != null){
                System.out.println("Activity with such id already exists!");
            }
            else
                break;
        }while(true);

        System.out.print("Enter description: ");
        description = InputHelper.input.nextLine();

        date = InputHelper.enterFutureDate("Enter date ");

        time = InputHelper.enterTime("Enter at what time will the activity start");

        if(Hotel.getInstance().getActivityService().getActivities().add(new Activity(id, description, date, time))){
            System.out.println("The activity was added successfully!");
        }
        else{
            System.out.println("Couldn't add the activity!");
        }

    }

    public static void activityByRoomNumberInput(){
        if(Hotel.getInstance().getActivityService().getActivities().isEmpty()){
            System.out.println("There are no activities!");
            return;
        }

        int roomNumber = RoomInput.enterRoom().getRoomNumber();
        String result = Hotel.getInstance().getActivityService().displayAllActivitiesByRoomNumber(roomNumber);
        if(result.isEmpty()){
            System.out.println("None of the guests from room "+roomNumber+" are going to participate in any of the activities!");
        }
        else{
            System.out.println(result);
        }
    }

    public static void activityByIdInput(){
        if(Hotel.getInstance().getActivityService().getActivities().isEmpty()){
            System.out.println("There are no activities!");
            return;
        }

        do{
            int id = InputHelper.enterInteger("Enter id: ");
            Activity activity = InputHelper.findActivityById(id);
            if(activity != null){
                System.out.println(activity.getInfoWithGuests());
                break;
            }
            else{
                System.out.println("Activity with such id wasn't found!");
            }
        }while(true);
    }
}
