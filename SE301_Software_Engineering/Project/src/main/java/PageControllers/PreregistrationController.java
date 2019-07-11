package PageControllers;

import Repository.RepositoryFacade;
import RequestManagement.MembershipRequest;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;

@ManagedBean
public class PreregistrationController {

    /**
     * name of the membership requester must not be null
     *
     * @invariant name != null
     */
    private String name;

    /**
     * surname of the membership requester must not be null
     *
     * @invariant surname != null
     */
    private String surname;

    /**
     * phone number of the membership requester must not be null
     *
     * @invariant phoneNumber != null
     */
    private String phoneNumber;

    /**
     * email of the email requester must not be null
     *
     * @invariant email != null
     */
    private String email;
    private RepositoryFacade rf = new RepositoryFacade();

    /**
     * sends membership requests (mr) to registrar (inserts to the database)
     *
     * @post isMembershipRequestExist(mr)
     */
    public void sendMembershipRequestToRegistrar() {
        if (name != null && name.length() > 0 && !name.isEmpty()) {
            if (surname != null && surname.length() > 0 && !surname.isEmpty()) {
                if (phoneNumber != null && phoneNumber.length() > 0 && !phoneNumber.isEmpty()) {
                    if (email != null && email.length() > 0 && !email.isEmpty()) {
                        MembershipRequest mr = new MembershipRequest(0, name, surname, phoneNumber, email);
                        if (!rf.isMembershipRequestExist(mr)) {
                            rf.insertMembershipRequest(mr);
                            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Membership Request", "Successfully, sent to registration.");
                            RequestContext.getCurrentInstance().showMessageInDialog(message);
                            return;
                        } else {
                            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Membership Request", "This membership request is already sent.");
                            RequestContext.getCurrentInstance().showMessageInDialog(message);
                            return;
                        }
                    }
                }
            }
        }

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Ensure that you have filled all fields correctly.");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    /**
     *
     * @return email address of the membership requester
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return name of the membership requester
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return phone number of the membership requester
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @return surname of the membership requester
     */
    public String getSurname() {
        return surname;
    }

    /**
     * sets the email of the membership requester
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * sets the name of the membership requester
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sets the phone number of the membership requester
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * sets the surname of the membership requester
     *
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

}
