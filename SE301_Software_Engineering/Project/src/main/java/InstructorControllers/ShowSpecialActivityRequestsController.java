package InstructorControllers;

import ActivityManagement.SpecialActivity;
import LoginController.UserLogin;
import Repository.RepositoryFacade;
import RequestManagement.SpecialActivityRequest;
import UserManagement.Instructor;
import UserManagement.VIPMember;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class ShowSpecialActivityRequestsController implements Serializable {

    private List<SpecialActivityRequest> requestList;
    private List<SpecialActivityRequest> selectedRequestList = new ArrayList<>();
    private RepositoryFacade rf = new RepositoryFacade();
    private String messageToShow;

    /**
     *
     * @return special activity request list which is retrieved from database
     */
    public List<SpecialActivityRequest> getRequestList() {
        requestList = new ArrayList<>();
        requestList = rf.getSpecialActivityRequests(UserLogin.getUserLoggedIn());
        return requestList;
    }

    /**
     *
     * @return selected request list
     */
    public List<SpecialActivityRequest> getSelectedRequestList() {
        return selectedRequestList;
    }

    /**
     * sets the selected request list which is retrieved from the interface
     *
     * @param selectedRequestList
     */
    public void setSelectedRequestList(List<SpecialActivityRequest> selectedRequestList) {
        this.selectedRequestList = selectedRequestList;
    }

    /**
     * gets the selected special activity requests (sar) and deletes them from the database, then inserts a row to vm_j_sa (vipmember_joins_specialactivity)
     *
     * @post isJoinSpecialActivityRequestDeleted(sar)
     * @post isVIPMemberJoinedSpecialActivity(vm, sa)
     */
    public void approveRequests() {
        messageToShow = "";
        
        for (SpecialActivityRequest sar : selectedRequestList) {
            VIPMember vm = new VIPMember(sar.getFrom(), null, null, null, null, 'x', null, null, null);
            Instructor i = new Instructor(sar.getTo(), null, null, null, null, 'x', null, null, null);
            SpecialActivity sa = new SpecialActivity(sar.getSubject(), null, i, null);

            rf.vipmemberJoinsSpecialActivity(vm, sa);
            rf.deleteSpecialActivityRequest(sar);
            
            if(rf.isSpecialActivityRequestExist(sar) || !rf.isVIPMemberJoinedSpecialActivity(vm, sa)){
                messageToShow += sar.toString() + "\n";
            }
        }
        
        if(messageToShow.length()>0){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "These transactions could not be completed.\n" + messageToShow);
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Completed", "All transactions approved, successfully.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

}
