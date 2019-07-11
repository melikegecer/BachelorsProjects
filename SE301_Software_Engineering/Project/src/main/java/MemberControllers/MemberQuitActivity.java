package MemberControllers;

import ActivityManagement.Activity;
import LoginController.UserLogin;
import Repository.RepositoryFacade;
import UserManagement.Member;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "dtMemberQuitActivity")
@ViewScoped
public class MemberQuitActivity implements Serializable {

    /**
     * The number of activities of a Member must be at least 1
     *
     * @invariant getMembersActivities->size > 0
     */
    private List<Activity> MembersActivities;
    private Activity selectedActivity;
    RepositoryFacade rp = new RepositoryFacade();

    /**
     * This method returns all activities of a certain Member
     *
     * @post MembersActivities -> size > 0
     * @return The list of Member's activities
     */
    public List getMembersActivities() {
        MembersActivities = new ArrayList<>();
        Member m = rp.getMember(UserLogin.getUserLoggedIn());
        MembersActivities = rp.getMemberActivities(m);
        return MembersActivities;
    }

    /**
     * This method causes the Member/VIP Member to quit a selected activity
     *
     * @pre getMembersActivities()-> includes(selectedActivity)
     * @post getMembersActivities()->size = @pre.getMembersActivitie()->size - 1
     * @post !getMembersActivities()-> includes(selectedActivity)
     */
    public void quitActivity() {
        boolean approve = false;
        boolean quitapprove = true;
        MembersActivities = rp.getMemberActivities(rp.getMember(UserLogin.getUserLoggedIn()));
        for (int i = 0; i < MembersActivities.size(); i++) {
            if (MembersActivities.get(i).getSlot().equals(selectedActivity.getSlot())) {
                approve = true;
            }
        }
        if (approve) {
            rp.memberQuitsActivity(rp.getMember(UserLogin.getUserLoggedIn()), selectedActivity);
            MembersActivities = rp.getMemberActivities(rp.getMember(UserLogin.getUserLoggedIn()));

            for (int i = 0; i < MembersActivities.size(); i++) {
                if (MembersActivities.get(i).getSlot().equals(selectedActivity.getSlot())) {
                    quitapprove = false;
                }
            }
            if (quitapprove) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Completed", "You quit the actvity.");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Transaction has failed.");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }

        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Transaction has failed.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }

    }

    /**
     * This method returns the selected activity
     *
     * @post selectedActivity != null
     * @return The Selected Activity
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
