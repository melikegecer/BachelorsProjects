package RegistrarControllers;

import Repository.RepositoryFacade;
import RequestManagement.MembershipRequest;
import RequestManagement.VIPRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
/**
 * The controller object for listing all the Membership requests.
 */
public class ShowMembershipRequestsController implements Serializable {

    /**
     * @invariant rf != null
     */
    private List<MembershipRequest> requestList;
    private List<MembershipRequest> selectedRequestList = new ArrayList<>();
    private RepositoryFacade rf = new RepositoryFacade();

    /**
     * This method returns the list of Membership Requests which are gathered
     * from the DB.
     * @return requestList List of all current Membership Requests
     */
    public List<MembershipRequest> getRequestList() {
        requestList = new ArrayList<>();
        requestList = rf.getMembershipRequests();
//        requestList.add(new MembershipRequest(0, "mel", "mel", "123", "mel"));
//        requestList.add(new MembershipRequest(1, "mel", "mel", "123", "mel"));
//        requestList.add(new MembershipRequest(2, "mel", "mel", "123", "mel"));
//        requestList.add(new MembershipRequest(3, "mel", "mel", "123", "mel"));
//        requestList.add(new MembershipRequest(4, "mel", "mel", "123", "mel"));
//        requestList.add(new MembershipRequest(5, "mel", "mel", "123", "mel"));
        return requestList;
    }

    /**
     * This method returns the list of Membership Requests that are to be
     * approved by the Registrar.
     *
     * @post getSelectedRequestList() -> size > 0
     * @return The list of Membership Request that are to be approved by the
     * Registrar.
     */
    public List<MembershipRequest> getSelectedRequestList() {
        return selectedRequestList;
    }

    /**
     * This method sets the list of Membership Requests that are to be approved
     * by the Registrar.
     *
     * @param selectedRequestList The list of Membership Request that are to be
     * approved by the Registrar.
     */
    public void setSelectedRequestList(List<MembershipRequest> selectedRequestList) {
        this.selectedRequestList = selectedRequestList;
    }

//    /**
//     * This method is used for approving the selected Membership Requests, thus
//     * removing them from the DB.
//     *
//     * @pre getMembershipRequests() -> size > 0
//     * @post getMembershipRequests -> size = (@pre.getMembershipRequests ->
//     * size) - (selectedRequestList -> size)
//     * @post isMemberExistent(m)
//     */
//    public void approveRequests() {
//        String messageToShow = "";
//        for (MembershipRequest membershipRequest : selectedRequestList) {
//            rf.deleteMembershipRequest(membershipRequest);
//            requestList.remove(membershipRequest);
//            if (rf.isMembershipRequestExist(membershipRequest) || rf.isMemberExistent(membershipRequest.)) {
//                messageToShow += vipr.toString() + "\n";
//            }
//        }
//        if (messageToShow.length() > 0) {
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "These transactions could not be completed.\n" + messageToShow);
//            RequestContext.getCurrentInstance().showMessageInDialog(message);
//        } else {
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Completed", "All transactions approved, successfully.");
//            RequestContext.getCurrentInstance().showMessageInDialog(message);
//        }
//    }

}
