package RegistrarControllers;

import Exceptions.DoesNotExist;
import Repository.RepositoryFacade;
import UserManagement.Member;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;

@ManagedBean
/**
 * The controller object for deleting an existing Member instance.
 */
public class DeleteMemberController {

    /**
     * @invariant id != null
     * @invariant rf != null
     */
    RepositoryFacade rf = new RepositoryFacade();
    private int id;

    /**
     * This method removes the given Member instance from the DB.
     *
     * @throws Exceptions.DoesNotExist
     * @post !isMemberExistent(m)
     * @post getNumOfMembers() = @pre.getNumOfMembers() - 1
     * @pre isMemberExistent(m)
     */
    public void deleteMember() throws DoesNotExist {
        Member memberToBeDeleted = rf.getMember(this.id);
        if (rf.isMemberExistent(memberToBeDeleted)) {
            rf.deleteMember(memberToBeDeleted);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Completed", "Member " + memberToBeDeleted.getName() + " has been deleted, successfully.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "The Member with ID: " + memberToBeDeleted.getId() + " could NOT be found!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            throw new DoesNotExist("The Member with ID: " + memberToBeDeleted.getId() + " could NOT be found!");
        }
    }

    /**
     * This method returns the ID of a Member instance.
     *
     * @return Member's ID
     */
    public int getID() {
        return this.id;
    }

    /**
     * This method sets the ID of a Member instance.
     *
     * @param id Member's ID
     */
    public void setID(int id) {
        this.id = id;
    }

}
