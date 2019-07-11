package SchedulerControllers;

import ActivityManagement.Activity;
import ActivityManagement.SpecialActivity;
import Exceptions.AlreadyExist;
import UserManagement.Instructor;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;

@ManagedBean
public class AddActivityControl {

    /** The name of activity 
     * shouldn't be null. 
     * @invariant name != null 
     */
    private String name;
    
    /** The day of activity 
     * shouldn't be null. 
     * @invariant day != null 
     */
    private String day;
    
    /** The slot of activity 
     * shouldn't be null. 
     * @invariant slot != null 
     */
    private String slot;
    
    /** The name of instructor 
     * shouldn't be null. 
     * @invariant instructor != null 
     */
    private String instructor;
    
    /** It control the 
     * activity is special or not. 
     */
    private boolean Special;

    /** Create a new facade 
     * for access to the database. 
     */
    private ActivityFacade af = new ActivityFacade();
    
    /** The Activity List contains   
     *  references to Activity which 
     * exist in the database. 
     */
    private List<Activity> activities = af.getAllActivities();
    
    /** The Special Activity List contains   
     *  references to Special Activity which 
     * exist in the database. 
     */
    private List<SpecialActivity> special = af.getAllSpecialActivities();
    
    /** The Instructor List contains   
     *  references to Instructor which 
     * exist in the database. 
     */
    private List<Instructor> ins = af.getAllInstructors();

    /** This method creates a new activity and
     * insert to the database if it doesn't exist.
     * 
     * @pre findActivityInArraylist(String name,String slot)
     * @pre findInstructorInArraylist(Instructor instructor)
     * 
     */
    public void checkActivity() throws AlreadyExist {
        MergeDayAndSlot();
        if (!Special && findActivityInArraylist(name, slot)) {
            Instructor i = getInstructorInArraylist(instructor);
            Activity a = new Activity(0, name, i, slot);
            af.insertActivity(a);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Completed","Activity : "+a.getName());
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else if (Special && findSpecialActivityInArraylist(name, slot)) {
            Instructor i = getInstructorInArraylist(instructor);
            Activity a = new SpecialActivity(0, name, i, slot);
            af.insertSpecialActivity(a);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Completed","Special Activity : "+a.getName());
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Activity already exist");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            throw new AlreadyExist("Already Exist");
        }
    }

    /** This method merge the day and slot,
     * and the new value adds to the slot.
     */
    public void MergeDayAndSlot() {
        String s = "";
        switch (this.getDay()) {
            case "Su":
                s = s + "Su-";
                break;
            case "Mo":
                s = s + "Mo-";
                break;
            case "Tu":
                s = s + "Tu-";
                break;
            case "We":
                s = s + "We-";
                break;
            case "Th":
                s = s + "Th-";
                break;
            case "Fr":
                s = s + "Fr-";
                break;
            default:
                s = s + "Sa-";
                break;
        }
        s = s + slot;
        slot = s;
    }

    /** 
     * @param name
     * @param slot
     * @return true if activity doesn't exist 
     * in the list. if it exist, method returns false. 
     * 
     * context: addActivityControl inv:
     * Activity->forAll(activities|activities.name == self.name)
     * and activities.Slot == self.Slot))
     */
    private boolean findActivityInArraylist(String name, String slot) {
        for (int i = 0; i < activities.size(); i++) {
            if (activities.get(i).getName().equals(name) && activities.get(i).getSlot().equals(slot)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param name
     * @param slot
     * @return true if special activity doesn't exist 
     * in the list. if it exist, method returns false. 
     * 
     * context: addActivityControl inv:
     * SpecialActivity->forAll(special|special.name == self.name)
     * and special.Slot == self.Slot))
     */
    private boolean findSpecialActivityInArraylist(String name, String slot) {
        for (int i = 0; i < special.size(); i++) {
            if (special.get(i).getName().equals(name) && special.get(i).getSlot().equals(slot)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param name
     * @return Instructor object if it exist,
     * if it doesn't exist return null.
     * 
     * context: addActivityControl inv:
     * Instructor->forAll(ins|ins.name == self.name)
     */
    private Instructor getInstructorInArraylist(String name) {
        for (int i = 0; i < ins.size(); i++) {
            if (ins.get(i).getName().equals(name)) {
                return ins.get(i);
            }
        }
        return null;
    }

    /** 
     * Returns the name of activity. 
     */
    public String getName() {
        return name;
    }

    /** 
     * Returns the day of activity. 
     */
    public String getDay() {
        return day;
    }

    /** 
     * Returns the slot of activity. 
     */
    public String getSlot() {
        return slot;
    }

    /** 
     * Returns the instructor of activity. 
     */
    public String getInstructor() {
        return instructor;
    }

    /** 
     * Returns true if special checkbox selected.
     * Otherwise, returns false.
     */
    public boolean isSpecial() {
        return Special;
    }

    /** 
     * Returns the all instructor 
     * registered to the system. 
     */
    public List<Instructor> getIns() {
        return ins;
    }

    /**
     * Change the name of 
     * activity with new value. 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Change the name of 
     * activity with new value. 
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * Change the slot of 
     * activity with new value. 
     */
    public void setSlot(String slot) {
        this.slot = slot;
    }

    /**
     * Change the Instructor of 
     * activity with new value. 
     */
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    /**
     * Change the value of 
     * special if checkbox is choosen. 
     */
    public void setSpecial(boolean Special) {
        this.Special = Special;
    }

    /**
     * Change the InstructorList of 
     * activity with new values. 
     */
    public void setIns(List<Instructor> ins) {
        this.ins = ins;
    }

}

