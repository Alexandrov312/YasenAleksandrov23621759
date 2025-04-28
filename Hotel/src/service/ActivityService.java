package service;

import model.Activity;
import model.Guest;

import java.util.Comparator;
import java.util.TreeSet;

public class ActivityService {
    private TreeSet<Activity> activities;

    public ActivityService() {
        this.activities = new TreeSet<>(Comparator.comparingInt(Activity::getId));
    }

    public TreeSet<Activity> getActivities() {
        return activities;
    }

    public void displayAllActivitiesWithoutGuests(){
        for(Activity activity : activities){
            System.out.println(activity.getInfo());
            System.out.println("-----------------------------\n");
        }
    }

    public void displayAllActivitiesWithGuests(){
        for(Activity activity : activities){
            System.out.println(activity.getInfoWithGuests());
            System.out.println("----------------------------------------------------------\n");
        }
    }

    public String displayAllActivitiesByRoomNumber(int roomNumber){
        StringBuilder builder = new StringBuilder();
        for(Activity activity : activities){
            StringBuilder guestsInfo = new StringBuilder();
            for(Guest guest : activity.getGuests()){
                if(guest.getRoomNumber() == roomNumber){
                    guestsInfo.append(guest.getInfo()+"\n");
                }
            }
            if(!guestsInfo.isEmpty()){
                builder.append(activity.getInfo()+"\n").append(guestsInfo);
                builder.append("-----------------------------\n");
            }
        }
        return builder.toString();
    }
}
