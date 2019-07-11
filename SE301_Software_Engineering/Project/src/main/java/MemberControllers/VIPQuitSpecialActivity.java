package MemberControllers;

import ActivityManagement.SpecialActivity;
import LoginController.UserLogin;
import Repository.RepositoryFacade;
import UserManagement.VIPMember;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "dtVIPQuitSpecialActivity")
@ViewScoped
public class VIPQuitSpecialActivity implements Serializable {

    /**
     * The number of special activities of a VIPMember must be at least 1
     *
     * @invariant getVIPMembersSpecialActivities()->size > 0
     */
    private List<SpecialActivity> VIPMembersSpecialActivities = new ArrayList<>();
    private SpecialActivity selectedSpecialActivity;
    RepositoryFacade rp = new RepositoryFacade();

    /**
     * This method returns all activities of a certain VIP Member
     *
     * @post VIPMembersSpecialActivities->size > 0
     * @return The list of VIPMember's Special Activities
     */
    public List getVIPMembersSpecialActivities() {
        VIPMembersSpecialActivities = new ArrayList<>();
        VIPMember vip = rp.getVipMember(UserLogin.getUserLoggedIn());
        VIPMembersSpecialActivities = rp.getVIPMemberSpecialActvities(vip);
        return VIPMembersSpecialActivities;
    }

    /**
     * This method causes the VIP Member to quit a special activity
     *
     * @pre getVIPMemberSpecialActvities()-> includes(selectedSpecialActivity)
     * @post getVIPMemberSpecialActvities()->size =
     * @pre.getVIPMemberSpecialActvities()->size - 1
     * @post !getVIPMemberSpecialActvities()-> includes(selectedSpecialActivity)
     */
    public void quitSpecialActivity() {
        boolean approve = false;
        boolean quitapprove = true;
        VIPMembersSpecialActivities = rp.getVIPMemberSpecialActvities(rp.getVipMember(UserLogin.getUserLoggedIn()));
        for (int i = 0; i < VIPMembersSpecialActivities.size(); i++) {
            if (VIPMembersSpecialActivities.get(i).getSlot().equals(selectedSpecialActivity.getSlot())) {
                approve = true;
            }
        }
        
        if (approve) {
            rp.memberQuitsActivity(rp.getMember(UserLogin.getUserLoggedIn()), selectedSpecialActivity);
            VIPMembersSpecialActivities = rp.getVIPMemberSpecialActvities(rp.getVipMember(UserLogin.getUserLoggedIn()));

            for (int i = 0; i < VIPMembersSpecialActivities.size(); i++) {
                if (VIPMembersSpecialActivities.get(i).getSlot().equals(selectedSpecialActivity.getSlot())) {
                    quitapprove = false;
                }
            }
            if (quitapprove) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Completed", "You quit the special actvity.");
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
     * This method returns the selected special activity
     *
     * @post selectedSpecialActivity != null
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
