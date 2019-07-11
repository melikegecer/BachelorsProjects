package MemberControllers;

import LoginController.UserLogin;
import Repository.RepositoryFacade;
import RequestManagement.VIPRequest;
import UserManagement.Member;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;

@ManagedBean
public class MemberPayWithCash {

    /**
     * Member informations must not be null
     *
     * @invariant memberName != null
     * @invariant memberSurname != null
     * @invariant memberPhone != null
     * @invariant memberAddress != null
     * @invariant memberEmail != null
     */
    RepositoryFacade rp = new RepositoryFacade();
    Member m = rp.getMember(UserLogin.getUserLoggedIn());
    private String memberName = m.getName();
    private String memberSurname = m.getSurName();
    private String memberPhone = m.getPhoneNumber();
    private String memberAddress = m.getAddress();
    private String memberEmail = m.getEmail();

    /**
     * This method sends a request of becoming a VIP Member
     *
     * @pre !rp.isVIPMember()
     * @post isRequestExist()
     */
    public void sendVIPRequest() {
        if (!rp.isVIPMember(UserLogin.getUserLoggedIn())) {
            rp.insertVIPRequest(new VIPRequest(0, UserLogin.getUserLoggedIn(), m.getName()));
            if (rp.isVIPRequestExist(new VIPRequest(0, UserLogin.getUserLoggedIn(), m.getName()))) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Completed", "Request has been sent.");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Request could not have been sent.");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Request could not have been sent.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    /**
     * This method returns the member name
     *
     * @return Member Name
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * This method sets the member name
     *
     * @param memberName
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    /**
     * This method returns the member surname
     *
     * @return Member Surname
     */
    public String getMemberSurname() {
        return memberSurname;
    }

    /**
     * This method sets the member surname
     *
     * @param memberSurname
     */
    public void setMemberSurname(String memberSurname) {
        this.memberSurname = memberSurname;
    }

    /**
     * This method returns the member phone
     *
     * @return Member Phone
     */
    public String getMemberPhone() {
        return memberPhone;
    }

    /**
     * This method sets the member phone
     *
     * @param memberPhone
     */
    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    /**
     * This method returns the member address
     *
     * @return Member Address
     */
    public String getMemberAddress() {
        return memberAddress;
    }

    /**
     * This method sets the member address
     *
     * @param memberAddress
     */
    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    /**
     * This method returns the member e-mail
     *
     * @return Member E-mail
     */
    public String getMemberEmail() {
        return memberEmail;
    }

    /**
     * This method sets the member e-mail
     *
     * @param memberEmail
     */
    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

}
