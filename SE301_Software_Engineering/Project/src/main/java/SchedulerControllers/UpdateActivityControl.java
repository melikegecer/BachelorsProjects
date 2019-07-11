package SchedulerControllers;

import ActivityManagement.Activity;
import ActivityManagement.SpecialActivity;
import Repository.RepositoryFacade;
import UserManagement.Instructor;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;

@ManagedBean
public class UpdateActivityControl {

    /**
     * The name of activiy  shouldn't be null. 
     *
     * @invariant activityName != null
     */
    private String activityName;

    /**
     * The name of slot  shouldn't be null. 
     *
     * @invariant slot != null
     */
    private String slot;

    /**
     * The name of activiy  shouldn't be null. 
     *
     * @invariant instructor != null
     */
    private String instructor;

    /**
     * Create a new facade for access to the database. 
     */
    RepositoryFacade rf = new RepositoryFacade();

    /**
     * The Activity List contains    references to Activity which exist in the
     * database.
     */
    private List<Activity> activities = rf.getAllActivities();

    /**
     * The Special Activity List contains    references to Special Activity
     * which exist in the database.
     */
    private List<SpecialActivity> special = rf.getAllSpecialActivities();
    
    public UpdateActivityControl(){
    
    }

    /**
     * The Instructor List contains    references to Instructor which exist in
     * the database.
     */
    private List<Instructor> ins = rf.getAllInstructors();

    /**
     * This method creates a new activity and old activity for update to the
     * database.
     *
     */
    public void updateControl() {
        if (!activityName.equals("") && !slot.equals("") && !instructor.equals("")) {
            Activity ActivityOld = findOldActivityInArraylist(activityName);
            Activity ActivityNew = new Activity(ActivityOld.getId(), ActivityOld.getName(), findInstructorInArraylist(instructor), slot);
            rf.updateActivity(ActivityOld, ActivityNew);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Completed","Success");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR","Slot and Instructor name can't be null.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    /**
     * @param name
     * @return Activity object if it exist, if it doesn't exist return null.
     *
     * context: addActivityControl inv:
     * Activity->forAll(activities|activities.name == self.name)
     */
    private Activity findOldActivityInArraylist(String name) {
        for (int i = 0; i < activities.size(); i++) {
            if (activities.get(i).getName().equals(name)) {
                return activities.get(i);
            }
        }
        return null;
    }

    /**
     * @param name
     * @return Instructor object if it exist, if it doesn't exist return null.
     *
     * context: addActivityControl inv: Instructor->forAll(ins|ins.name ==
     * self.name)
     */
    private Instructor findInstructorInArraylist(String name) {
        for (int i = 0; i < ins.size(); i++) {
            if (ins.get(i).getName().equals(name)) {
                return ins.get(i);
            }
        }
        return null;
    }

    /**
     * Returns the name ofactivity.
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * Returns the slot of activity.
     */
    public String getSlot() {
        return slot;
    }

    /**
     * Returns the Instructor name of activity.
     */
    public String getInstructor() {
        return instructor;
    }

    /**
     * Returns the all activity registered to the system.
     */
    public List<Activity> getActivities() {
        return activities;
    }

    /**
     * Change the name of activity with new value.
     */
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    /**
     * Change the slot of activity with new value.
     */
    public void setSlot(String slot) {
        this.slot = slot;
    }

    /**
     * Change the Instructor name of activity with new value.
     */
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    /**
     * Change the ActivityList of activity with new values.
     */
    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public void change(String activity) {
        setSlot(activity);
    }

}
