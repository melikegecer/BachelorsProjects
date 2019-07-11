package MemberControllers;

import ActivityManagement.SpecialActivity;
import LoginController.UserLogin;
import Repository.RepositoryFacade;
import RequestManagement.SpecialActivityRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "dtVIPJoinsSpecialActivity")
@ViewScoped
public class VIPJoinsSpecialActivity implements Serializable {

    private List<SpecialActivity> AllSpecialActivities = new ArrayList<>();
    private SpecialActivity selectedSpecialActivity;

    RepositoryFacade rp = new RepositoryFacade();

    /**
     * This method returns a list of all speacial activities
     *
     * @post AllSpecialActivities -> size > 0
     * @return A list of all Special Activities
     */
    public List<SpecialActivity> getAllSpecialActivities() {
        AllSpecialActivities = new ArrayList<>();
        AllSpecialActivities = rp.getAllSpecialActivities();

        return AllSpecialActivities;
    }

    /**
     * This method sends a request of joining a special activity
     *
     * @pre !getVIPMemberSpecialActvities()-> includes(selectedSpecialActivity)
     * @pre getVIPMemberSpecialActvities() -> forAll (sa| !sa.getSlot().equals(selectedSpecialActivity.getSlot()))
     * @post rp.isRequestExist(m,selectedSpecialActivity)
     */
    public void sendJoinSpecialActivityRequest() {
        boolean overlap = false;
        List<SpecialActivity> VIPSpecialActivities = rp.getVIPMemberSpecialActvities(rp.getVipMember(UserLogin.getUserLoggedIn()));

        for (int i = 0; i < VIPSpecialActivities.size(); i++) {
            if (VIPSpecialActivities.get(i).getSlot().equals(selectedSpecialActivity.getSlot())) {
                overlap = true;
            }
        }
        if (!overlap) {
            rp.insertSpecialActivityRequest(new SpecialActivityRequest(0, UserLogin.getUserLoggedIn(), rp.getMember(UserLogin.getUserLoggedIn()).getName(), selectedSpecialActivity.getInstructor().getId(), selectedSpecialActivity.getId()));
            if (rp.isSpecialActivityRequestExist(new SpecialActivityRequest(0, UserLogin.getUserLoggedIn(), rp.getMember(UserLogin.getUserLoggedIn()).getName(), selectedSpecialActivity.getInstructor().getId(), selectedSpecialActivity.getId()))) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Completed", "Request has been sent.");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Action causes an overlap. Request could not have been sent.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);

        }

    }

    /**
     * This method returns the selected special activity
     *
     * @post selectedSpecialActivity =! null
     * @return The Selected Special Activity
     */
    public SpecialActivity getSelectedSpecialActivity() {
        return selectedSpecialActivity;
    }

    /**
     * This method sets the selected special activity
     *
     * @param selectedSpecialActivity
     */
    public void setSelectedSpecialActivity(SpecialActivity selectedSpecialActivity) {
        this.selectedSpecialActivity = selectedSpecialActivity;
    }

}
