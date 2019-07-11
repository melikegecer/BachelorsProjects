package SchedulerControllers;

import ActivityManagement.Activity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;

@ManagedBean
public class RemoveActivityControl {

    /**
     * Create a new facade for access to the database. 
     */
    private ActivityFacade af = new ActivityFacade();

    /**
     * The Activity List contains    references to Activity which exist in the
     * database.
     */
    private List<Activity> activities = af.getAllActivities();

    /**
     * The Activity List contains    references to Activity which choose in the
     * datatable list.
     */
    private List<Activity> selectedList = new ArrayList<Activity>();

    /**
     * Removes the all activities in the database.
     */
    public void removeActivity() {
        if (selectedList != null) {
            for (int i = 0; i < selectedList.size(); i++) {
                af.deleteActivity(selectedList.get(i));
            }
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Completed","Done");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR","Select an activity");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    /**
     * Returns the name of activities as a list.
     */
    public List<Activity> getActivities() {
        return activities;
    }

    /**
     * Returns selected activities as a list.
     */
    public List<Activity> getSelectedList() {
        return selectedList;
    }
    /**
     * Change the names of activities
     */
    public void setSelectedList(List<Activity> selectedList) {
        this.selectedList = selectedList;
    }
}
