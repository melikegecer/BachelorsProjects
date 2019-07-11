package SchedulerControllers;

import Exceptions.AlreadyExist;
import UserManagement.Court;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;

@ManagedBean
public class AddCourtControl {

    /** The name of court 
     * shouldn't be null. 
     * @invariant name != null 
     */
    private String name;
    
    /** The Activity List contains   
     *  references to Activity which 
     * exist in the database. 
     */
    private ActivityFacade af = new ActivityFacade();
    
    /** The Court List contains   
     *  references to Court which 
     *  exist in the database. 
     */
    private List<Court> courts = af.getAllCourts();
    
    /** This method creates a new court and
     * insert to the database if it doesn't exist.
     * 
     * @pre findCourt(String name)
     */
    public void addCourtControl() throws AlreadyExist {
        if (findCourt(name)) {
            Court court = new Court(0, name, "");
            af.insertCourt(court);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Completed","Court : "+court.getName());
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR","Court already exist.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            throw new AlreadyExist("Already Exist");
        }
    }

    /**
     * Controls the all courts 
     * 
     * @param name
     * @return true if courts exist 
     * in the list. Otherwise, return false.
     * 
     * context: addCourtControl inv:
     * Court->forAll(courts|courts.name == self.name)
     */
    private boolean findCourt(String name) {
        for (int i = 0; i < courts.size(); i++) {
            if (courts.get(i).getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    /** 
     * Returns the name of activity 
     */
    public String getName() {
        return name;
    }

    /** 
     * Change the name of activity 
     */
    public void setName(String name) {
        this.name = name;
    }

}
