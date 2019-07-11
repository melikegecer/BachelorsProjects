package RegistrarControllers;

import Exceptions.AlreadyExist;
import Repository.RepositoryFacade;
import UserManagement.Instructor;
import UserManagement.Password;
import java.sql.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;

@ManagedBean
/**
 * The controller object for adding a new Instructor.
 */
public class AddNewInstructorController {
    
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
     * This method generates an Instructor instance with the given attributes
     * and inserts it into the DB.
     * @throws Exceptions.AlreadyExist
     * @post getNumOfInstructors = @pre.getNumOfInstructors + 1
     * @pre !isInstructorExistent(i)
     */
    public void generateInstructor() throws AlreadyExist {
        String[] birthday = BDay.split("-");
        if (this.gender == 1) {
            this.sex = 'M';
        } else if (this.gender == 2) {
            this.sex = 'F';
        }
        Instructor i = new Instructor(0, new Password(null), this.f_name, this.l_name, this.phoneNumber, this.sex, new Date(Integer.parseInt(birthday[2]) - 1900, Integer.parseInt(birthday[1]) - 1, Integer.parseInt(birthday[0])), this.address, this.e_mail);
        if(!(rf.isInstructorExistent(i))){
            rf.insertInstructor(i);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Completed", "Instructor " + i.getName() + " has been inserted to Database, successfully.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "The Instructor with e-mail: " + i.getEmail() + " already exist!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            throw new AlreadyExist("The Instructor with e-mail: " + i.getEmail() + " already exist!");
        }
    }

    /**
     * This method returns the address of an Instructor instance.
     * @return Instructor's address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * This method returns the e-mail address of an Instructor instance.
     * @return Instructor's e-mail 
     */
    public String getE_mail() {
        return this.e_mail;
    }

    /**
     * This method returns the first name of an Instructor instance.
     * @return Instructor's first name 
     */
    public String getF_name() {
        return this.f_name;
    }

    /**
     * This method returns the last name of an Instructor instance.
     * @return Instructor's last name  
     */
    public String getL_name() {
        return this.l_name;
    }
    
    /**
     * This method returns the phone number of an Instructor instance.
     * @return Instructor's phone number  
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

     /**
     * This method returns the birth date of an Instructor instance.
     * @return Instructor's birth date  
     */
    public String getBDay() {
        return BDay;
    }

    /**
     * This method returns the birth date of an Instructor instance.
     * @return Instructor's birth date  
     */
    public int getGender() {
        return this.gender;
    }

    /**
     * This method sets the first name attribute of an Instructor instance.
     * @param f_name Instructor's f_name
     */
    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    /**
     * This method sets the last name attribute of an Instructor instance.
     * @param l_name Instructor's l_name
     */
    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    /**
     * This method sets the gender attribute of an Instructor instance.
     * @param gender Instructor's gender
     */
    public void setGender(int gender) {
        this.gender = gender;
    }

    /**
     * This method sets the phone number attribute of an Instructor instance.
     * @param phoneNumber Instructor's phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * This method sets the birth date attribute of an Instructor instance.
     * @param BDay Instructor's birth date
     */
    public void setBDay(String BDay) {
        this.BDay = BDay;
    }

    /**
     * This method sets the e-mail attribute of an Instructor instance.
     * @param e_mail Instructor's e-mail
     */
    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    /**
     * This method sets the address attribute of an Instructor instance.
     * @param address Instructor's address
     */
    public void setAddress(String address) {
        this.address = address;
    }

}
