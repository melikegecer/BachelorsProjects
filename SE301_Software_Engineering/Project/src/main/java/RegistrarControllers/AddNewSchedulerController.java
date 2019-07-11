package RegistrarControllers;

import Exceptions.AlreadyExist;
import Repository.RepositoryFacade;
import UserManagement.Password;
import UserManagement.Scheduler;
import java.sql.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;

@ManagedBean
/**
 * The controller object for adding a new Scheduler.
 */
public class AddNewSchedulerController {

    /**
     * @invariant f_name != null
     * @invariant l_name != null
     * @invariant gender != 0
     * @invariant phoneNumber != null
     * @invariant BDay != null
     * @invariant e_mail != null
     * @invariant address != null
     * @invariant rf != null
     */
    private RepositoryFacade rf = new RepositoryFacade();
    private String f_name;
    private String l_name;
    private int gender;
    private char sex;
    private String phoneNumber;
    private String BDay;
    private String e_mail;
    private String address;

    /**
     * This method generates a Scheduler instance with the given attributes and
     * inserts it into the DB.
     *
     * @throws Exceptions.AlreadyExist
     * @post getNumOfSchedulers = @pre.getNumOfSchedulers + 1
     * @pre !isSchedulerExistent(s)
     */
    public void generateScheduler() throws AlreadyExist {
        String[] birthday = BDay.split("-");
        if (this.gender == 1) {
            this.sex = 'M';
        } else if (this.gender == 2) {
            this.sex = 'F';
        }
        Scheduler s = new Scheduler(0, new Password(null), this.f_name, this.l_name, this.phoneNumber, this.sex, new Date(Integer.parseInt(birthday[2]) - 1900, Integer.parseInt(birthday[1]) - 1, Integer.parseInt(birthday[0])), this.address, this.e_mail);
        if(!(rf.isSchedulerExistent(s))){
            rf.insertScheduler(s);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Completed", "Scheduler " + s.getName() + " has been inserted to Database, successfully.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "The Scheduler with e-mail: " + s.getEmail() + " already exist!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            throw new AlreadyExist("The Scheduler with e-mail: " + s.getEmail() + " already exist!");
        }
    }

    /**
     * This method returns the address of a Scheduler instance.
     *
     * @return Scheduler's address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * This method returns the e-mail name of a Scheduler instance.
     *
     * @return Scheduler's e-mail
     */
    public String getE_mail() {
        return this.e_mail;
    }

    /**
     * This method returns the first name of a Scheduler instance.
     *
     * @return Scheduler's first name
     */
    public String getF_name() {
        return this.f_name;
    }

    /**
     * This method returns the last name of a Scheduler instance.
     *
     * @return Scheduler's last name
     */
    public String getL_name() {
        return this.l_name;
    }

    /**
     * This method returns the phone number of a Scheduler instance.
     *
     * @return Scheduler's phone number
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * This method returns the birth date of a Scheduler instance.
     *
     * @return Scheduler's birth date
     */
    public String getBDay() {
        return this.BDay;
    }

    /**
     * This method returns the gender of a Scheduler instance.
     *
     * @return Scheduler's gender
     */
    public int getGender() {
        return this.gender;
    }

    /**
     * This method sets the first name of a Scheduler instance.
     *
     * @param f_name First name of the Scheduler
     */
    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    /**
     * This method sets the last name of a Scheduler instance.
     *
     * @param l_name Last name of the Scheduler
     */
    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    /**
     * This method sets the gender of a Scheduler instance.
     *
     * @param gender Gender of the Scheduler
     */
    public void setGender(int gender) {
        this.gender = gender;
    }

    /**
     * This method sets the phone number of a Scheduler instance.
     *
     * @param phoneNumber Phone number of the Scheduler
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * This method sets the birth date of a Scheduler instance.
     *
     * @param BDay Birth date of the Scheduler
     */
    public void setBDay(String BDay) {
        this.BDay = BDay;
    }

    /**
     * This method sets the e-mail of a Scheduler instance.
     *
     * @param e_mail E-mail of the Scheduler
     */
    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    /**
     * This method sets the address of a Scheduler instance.
     *
     * @param address Address of the Scheduler
     */
    public void setAddress(String address) {
        this.address = address;
    }

}
