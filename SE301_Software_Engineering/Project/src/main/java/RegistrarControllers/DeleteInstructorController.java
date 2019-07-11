package RegistrarControllers;

import Exceptions.DoesNotExist;
import Repository.RepositoryFacade;
import UserManagement.Instructor;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;

@ManagedBean
/**
 * The controller object for deleting an existing Instructor instance.
 */
public class DeleteInstructorController {

    /**
     * @invariant id != null
     * @invariant rf != null
     */
    RepositoryFacade rf = new RepositoryFacade();
    private int id;

    /**
     * This method removes the given Instructor instance from the DB.
     *
     * @throws Exceptions.DoesNotExist
     * @post !isInstructorExistent(i)
     * @post getNumOfInstructors() = @pre.getNumOfInstructors() - 1
     * @pre isInstructorExistent(i)
     */
    public void deleteInstructor() throws DoesNotExist {
        Instructor instructorToBeDeleted = rf.getInstructor(this.id);
        if(rf.isInstructorExistent(instructorToBeDeleted)){
            rf.deleteInstructor(instructorToBeDeleted);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Completed", "Instructor " + instructorToBeDeleted.getName() + " has been deleted, successfully.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "The Instructor with ID: " + instructorToBeDeleted.getId() + " could NOT be found!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            throw new DoesNotExist("The Instructor with ID: " + instructorToBeDeleted.getId() + " could NOT be found!");
        }
    }

    /**
     * This method returns the ID of an Instructor instance.
     *
     * @return Instructor's ID
     */
    public int getID() {
        return this.id;
    }

    /**
     * This method sets the ID of an Instructor instance.
     *
     * @param id Instructor's ID
     */
    public void setID(int id) {
        this.id = id;
    }

}
