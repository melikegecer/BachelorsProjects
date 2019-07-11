package RegistrarControllers;

import Exceptions.DoesNotExist;
import Repository.RepositoryFacade;
import UserManagement.Scheduler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;

@ManagedBean
/**
 * The controller object for deleting an existing Scheduler instance.
 */
public class DeleteSchedulerController {

    /**
     * @invariant id != null
     * @invariant rf != null
     */
    RepositoryFacade rf = new RepositoryFacade();
    private int id;

    /**
     * This method removes the given Scheduler instance from the DB.
     *
     * @throws Exceptions.DoesNotExist
     * @post !isSchedulerExistent(i)
     * @post getNumOfSchedulers() = @pre.getNumOfSchedulers() - 1
     * @pre isSchedulerExistent(i)
     */
    public void deleteScheduler() throws DoesNotExist {
        Scheduler schedulerToBeDeleted = rf.getScheduler(this.id);
        if(rf.isSchedulerExistent(schedulerToBeDeleted)){
            rf.deleteScheduler(schedulerToBeDeleted);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Completed", "Scheduler " + schedulerToBeDeleted.getName() + " has been deleted, successfully.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "The Scheduler with ID: " + schedulerToBeDeleted.getId() + " could NOT be found!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            throw new DoesNotExist("The Scheduler with ID: " + schedulerToBeDeleted.getId() + " could NOT be found!");
        }
    }

    /**
     * This method returns the ID of a Scheduler instance.
     *
     * @return Scheduler's ID
     */
    public int getID() {
        return this.id;
    }

    /**
     * This method sets the ID of a Scheduler instance.
     *
     * @param id Scheduler's ID
     */
    public void setID(int id) {
        this.id = id;
    }

}
