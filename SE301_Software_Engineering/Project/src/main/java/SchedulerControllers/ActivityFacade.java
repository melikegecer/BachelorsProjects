package SchedulerControllers;

import ActivityManagement.SpecialActivity;
import ActivityManagement.Activity;
import Repository.RepositoryFacade;
import UserManagement.Instructor;
import UserManagement.Court;
import java.util.List;

public class ActivityFacade {

    /**
     * Reference to the database 
     */
    RepositoryFacade rf;

    /**
     * Creates the instance of ActivityFacade
     */
    public ActivityFacade() {
        rf = new RepositoryFacade();
    }

    /** This method inserts the activity
     * to the database.
     * 
     * @param activity 
     */
    public void insertActivity(Activity activity) {
        rf.insertActivity(activity);
    }

    /** This method inserts the special
     * activity to the database.
     * 
     * @param activity 
     */
    public void insertSpecialActivity(Activity activity) {
        rf.insertActivity(activity);
    }
    
    /** This method inserts the court
     * to the database.
     * 
     * @param court 
     */
    public void insertCourt(Court court) {
        rf.insertCourt(court);
    }
    
    /** This method update the activities
     * in the database.
     * 
     * @param activityOld
     * @param activityNew 
     */
    public void updateActivity(Activity activityOld,Activity activityNew){
        rf.updateActivity(activityOld, activityNew);
    }

    /** This method delete the activity
     * in the database.
     * 
     * @param activity 
     */
    public void deleteActivity(Activity activity) {
        rf.deleteActivity(activity);
    }
    
    /** This method delete the special
     * activity in the database.
     * 
     * @param activity 
     */
    public void deleteSpecialActivity(Activity activity){
        rf.deleteActivity(activity);
    }
    
    /** This method gets the all
     * activities from the database.
     * 
     * @return rf.getAllActivities
     */
    public List<Activity> getAllActivities(){
        return rf.getAllActivities();
    }
    
    /** This method gets the all special
     * activities from the database.
     * 
     * @return rf.getAllSpecialActivities
     */
    public List<SpecialActivity> getAllSpecialActivities(){
        return rf.getAllSpecialActivities();
    }
    
    /** This method gets the all
     * Instructor from the database.
     * 
     * @return rf.getAllInstructor
     */
    public List<Instructor> getAllInstructors(){
        return rf.getAllInstructors();
    }
    
    /** This method gets the all
     * Courts from the database.
     * 
     * @return rf.getAllInstructor
     */
    public List<Court> getAllCourts(){
        return rf.getAllCourts();
    }

}