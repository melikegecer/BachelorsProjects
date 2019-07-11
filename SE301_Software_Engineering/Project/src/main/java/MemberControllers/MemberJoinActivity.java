package MemberControllers;

import ActivityManagement.Activity;
import ActivityManagement.SpecialActivity;
import LoginController.UserLogin;
import Repository.RepositoryFacade;
import RequestManagement.ActivityRequest;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "dtMemberJoinActivity")
@ViewScoped
public class MemberJoinActivity {

    private List<Activity> AllActivities = new ArrayList<>();
    ;
    private Activity selectedActivity;

    RepositoryFacade rp = new RepositoryFacade();

    /**
     * This method returns a list of all activities
     *
     * @post AllActivities -> size > 0
     * @return The list of all activities
     */
    public List<Activity> getActivities() {
        AllActivities = new ArrayList<>();
        AllActivities = rp.getAllActivities();
        List<SpecialActivity> AllSpecialActivities = rp.getAllSpecialActivities();
        for (int i = 0; i < AllActivities.size(); i++) {
            for (int j = 0; j < AllSpecialActivities.size(); j++) {
                if (AllActivities.get(i).getId() == AllSpecialActivities.get(j).getId()) {
                    AllActivities.remove(i);
                }
            }
        }
        return AllActivities;
    }

    /**
     * This method sends a request of joining an activity
     *
     * @pre !getMembersActivities()-> includes(selectedActivity)
     * @pre getMembersActivities -> forAll(ma| !ma.getSlot().equals(selectedActivity.getslot()))
     * @post rp.isRequestExist(m,selectedActivity) 
     */
    public void sendJoinActivityRequest() {
        boolean overlap = false;
        List<Activity> membersActivities = rp.getMemberActivities(rp.getMember(UserLogin.getUserLoggedIn()));
        for (int i = 0; i < membersActivities.size(); i++) {
            if (membersActivities.get(i).getSlot().equals(selectedActivity.getSlot())) {
                overlap = true;
            }
        }

        if (!overlap) {
            rp.insertActivityRequest(new ActivityRequest(0, UserLogin.getUserLoggedIn(), rp.getMember(UserLogin.getUserLoggedIn()).getName(), selectedActivity.getInstructor().getId(), selectedActivity.getId()));
            if (rp.isActivityRequestExist(new ActivityRequest(0, UserLogin.getUserLoggedIn(), rp.getMember(UserLogin.getUserLoggedIn()).getName(), selectedActivity.getInstructor().getId(), selectedActivity.getId()))) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Completed", "Request has been sent.");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }
            else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Request could not have been sent.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Action causes an overlap. Request could not have been sent.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }

    }

    /**
     * This method returns the selected activity
     *
     * @return The selected activity
     */
    public Activity getSelectedActivity() {
        return selectedActivity;
    }

    /**
     * This method sets the selected activity
     *
     * @param selectedActivity
     */
    public void setSelectedActivity(Activity selectedActivity) {
        this.selectedActivity = selectedActivity;
    }

}
