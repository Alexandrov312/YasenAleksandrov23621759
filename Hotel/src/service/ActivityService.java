package service;

import model.Activity;
import model.Guest;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Клас {@code ActivityService} за управление на всички дейности(активитети) в хотела.
 */
public class ActivityService {
    private TreeSet<Activity> activities;

    public ActivityService() {
        this.activities = new TreeSet<>(Comparator.comparingInt(Activity::getId));
    }

    public TreeSet<Activity> getActivities() {
        return activities;
    }

    /**
     * Извежда информация за всички дейности.
     */
    public void displayAllActivitiesWithoutGuests(){
        for(Activity activity : activities){
            System.out.println(activity.getInfo());
            System.out.println("-----------------------------\n");
        }
    }

    /**
     * Извежда информация за всички дейности заедно със записалите се гости.
     */
    public void displayAllActivitiesWithGuests(){
        for(Activity activity : activities){
            System.out.println(activity.getInfoWithGuests());
            System.out.println("----------------------------------------------------------\n");
        }
    }

    /**
     * Извежда всички дейности, в които участват гости от дадена стая.
     * @param roomNumber номер на стаята
     * @return текстова информация за дейности и гостите
     */
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
