package SchedulerControllers;

import ActivityManagement.Activity;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class ShowActivityControl {

    /** Create a new facade 
     * for access to the database. 
     */
    private ActivityFacade af = new ActivityFacade();
    
    /** The Activity List contains   
     *  references to Activity which 
     * exist in the database. 
     */
    private List<Activity> activities = af.getAllActivities();

    /** 
     * Returns the name of activities as a list. 
     */
    public List<Activity> getActivities() {
        return activities;
    }

}
