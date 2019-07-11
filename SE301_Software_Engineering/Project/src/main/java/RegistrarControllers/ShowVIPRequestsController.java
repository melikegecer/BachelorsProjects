package RegistrarControllers;

import Repository.RepositoryFacade;
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
 * The controller object for listing all the VIP Requests.
 */
public class ShowVIPRequestsController implements Serializable {

    /**
     * @invariant rf != null
     */
    private List<VIPRequest> requestList;
    private List<VIPRequest> selectedRequestList = new ArrayList<>();
    private RepositoryFacade rf = new RepositoryFacade();

    /**
     * This method returns the list of VIP Requests which are gathered from the
     * DB.
     *
     * @return requestList List of all current VIP Requests
     */
    public List<VIPRequest> getRequestList() {
        requestList = rf.getVIPRequests();
        //requestList.add(new VIPRequest(1, 2, "Emre"));
        //requestList.add(new VIPRequest(2, 2, "Emre"));
        //requestList.add(new VIPRequest(3, 2, "Emre"));
        //requestList.add(new VIPRequest(4, 2, "Emre"));
        //requestList.add(new VIPRequest(5, 2, "Emre"));
        return requestList;
    }

    /**
     * This method returns the list of VIP Requests that are to be approved by
     * the Registrar.
     *
     * @post getSelectedRequestList() -> size > 0
     * @return The list of VIP Request that are to be approved by the Registrar.
     */
    public List<VIPRequest> getSelectedRequestList() {
        return selectedRequestList;
    }

    /**
     * This method sets the list of VIP Requests that are to be approved by the
     * Registrar.
     *
     * @param selectedRequestList The list of VIP Request that are to be
     * approved by the Registrar.
     */
    public void setSelectedRequestList(List<VIPRequest> selectedRequestList) {
        this.selectedRequestList = selectedRequestList;
    }

    /**
     * This method is used for approving the selected VIP Requests, thus
     * removing them from the DB.
     *
     * @pre getVIPRequests() -> size > 0
     * @post getVIPRequests -> size = (@pre.getVIPRequests -> size) -
     * (selectedRequestList -> size)
     * @post isVIPMember(m)
     */
    public void approveRequests() {
        String messageToShow = "";
        for (VIPRequest vipr : selectedRequestList) {
            rf.deleteVIPRequest(vipr);
            requestList.remove(vipr);
            rf.memberBecomesVIPMember(rf.getMember(vipr.getFrom()));
            if (rf.isVIPRequestExist(vipr) || !(rf.isVIPMember(vipr.getFrom()))) {
                messageToShow += vipr.toString() + "\n";
            }
        }
        if (messageToShow.length() > 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "These transactions could not be completed.\n" + messageToShow);
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Completed", "All transactions approved, successfully.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }
}
