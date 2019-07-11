package InstructorControllers;

import ActivityManagement.Activity;
import LoginController.UserLogin;
import Repository.RepositoryFacade;
import RequestManagement.ActivityRequest;
import UserManagement.Instructor;
import UserManagement.Member;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class ShowActivityRequestsController implements Serializable {

    private List<ActivityRequest> requestList;
    private List<ActivityRequest> selectedRequestList = new ArrayList<>();
    private RepositoryFacade rf = new RepositoryFacade();
    private String messageToShow;

    /**
     * 
     * @return activity request list in order to show its values in data table
     */
    public List<ActivityRequest> getRequestList() {
        requestList = new ArrayList<>();
        requestList = rf.getActivityRequests(UserLogin.getUserLoggedIn());
//        requestList.add(new ActivityRequest(1, 2, "hey", 2, 3));
//        requestList.add(new ActivityRequest(2, 2, "hey", 2, 3));
//        requestList.add(new ActivityRequest(3, 2, "hey", 2, 3));
//        requestList.add(new ActivityRequest(4, 2, "hey", 2, 3));
//        requestList.add(new ActivityRequest(5, 2, "hey", 2, 3));
        return requestList;
    }

    /**
     * 
     * @return the selected activity request list
     */
    public List<ActivityRequest> getSelectedRequestList() {
        return selectedRequestList;
    }

    /**
     * sets the selected request list which is retrieved from the interface
     * 
     * @param selectedRequestList 
     */
    public void setSelectedRequestList(List<ActivityRequest> selectedRequestList) {
        this.selectedRequestList = selectedRequestList;
    }

    /**
     * gets the selected activity requests (ar) and deletes them from the database, then insert a row to m_j_a (member_joins_activity)
     * 
     * @post isJoinActivityRequestDeleted(ar)
     * @post isMemberJoinedActivity(m, a)
     */
    public void approveRequests() {
        messageToShow = "";
        
        for (ActivityRequest ar : selectedRequestList) {
            Member m = new Member(ar.getFrom(), null, null, null, null, 'x', null, null, null);
            Instructor i = new Instructor(ar.getTo(), null, null, null, null, 'x', null, null, null);
            Activity a = new Activity(ar.getSubject(), null, i, null);
            
            rf.memberJoinsActivity(m, a);
            rf.deleteActivityRequest(ar);
            
            if(rf.isActivityRequestExist(ar) || !rf.isMemberJoinedActivity(m, a)){
                messageToShow += ar.toString();
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
