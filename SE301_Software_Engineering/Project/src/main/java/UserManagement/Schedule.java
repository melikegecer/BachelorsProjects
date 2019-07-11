package UserManagement;

import ActivityManagement.Activity;
import java.util.ArrayList;

public class Schedule {
    private ArrayList<Activity> activityList = new ArrayList<>();

    /**
     * Constructs a new schedule object with the relative parameters
     *
     * @param activityList: holds the activity list of the schedule
     */
    public Schedule(ArrayList<Activity> activityList) {
        this.activityList = activityList;
    }

    /**
     * 
     * @return list of activities in a schedule
     */
    public ArrayList<Activity> getActivityList() {
        return activityList;
    }
    
   
}
