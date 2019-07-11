package MemberControllers;

import LoginController.UserLogin;
import Repository.RepositoryFacade;
import UserManagement.Member;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;

@ManagedBean
public class UpdateMembersInfo implements Serializable {

    /**
     * Member Phone, Member Address and Member E-mail must not be null
     *
     * @invariant memberPhone != null
     * @invariant memberAddress != null
     * @invariant memberEmail != null
     */
    RepositoryFacade rp = new RepositoryFacade();
    Member m = rp.getMember(UserLogin.getUserLoggedIn());
    private String memberPhone = m.getPhoneNumber();
    private String memberAddress = m.getAddress();
    private String memberEmail = m.getEmail();

    /**
     * This method returns the Member's phone number
     *
     * @return Member Phone Number
     */
    public String getMemberPhone() {
        return memberPhone;
    }

    /**
     * This method returns the Member's address
     *
     * @return Member Address
     */
    public String getMemberAddress() {
        return memberAddress;
    }

    /**
     * This method returns the Member's e-mail
     *
     * @return Member Email
     */
    public String getMemberEmail() {
        return memberEmail;
    }

    /**
     * This method sets the Member's phone number
     *
     * @param memberPhone
     */
    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    /**
     * This method sets the Member's address
     *
     * @param memberAddress
     */
    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    /**
     * This method sets the Member's e-mail
     *
     * @param memberEmail
     */
    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    /**
     * This method updates the Member's certain informations
     */
    public void sendUpdateInfo() {       
        m = new Member(m.getId(), m.getPassword(), m.getName(), m.getSurName(), memberPhone, m.getGender(), m.getBirthDate(), memberAddress, memberEmail);
        rp.updateMember(m);
        m = rp.getMember(UserLogin.getUserLoggedIn());
        if (m.getAddress().equals(memberAddress) && m.getPhoneNumber().equals(memberPhone) && m.getEmail().equals(memberEmail)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Completed", "The informations have been updated.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Transaction has failed.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }

    }

}
