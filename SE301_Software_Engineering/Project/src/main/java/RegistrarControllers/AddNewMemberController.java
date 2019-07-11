package RegistrarControllers;

import Exceptions.AlreadyExist;
import Repository.RepositoryFacade;
import UserManagement.Member;
import UserManagement.Password;
import java.sql.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;

@ManagedBean
/**
 * The controller object for adding a new Member.
 */
public class AddNewMemberController {

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
     * This method generates a Member instance with the given attributes and
     * inserts it into the DB.
     * @throws Exceptions.AlreadyExist
     * @post getNumOfMembers = @pre.getNumOfMembers + 1
     * @pre !isMemberExistent(m)
     */
    public void generateMember() throws AlreadyExist {
        String[] birthday = BDay.split("-");
        if (this.gender == 1) {
            this.sex = 'M';
        } else if (this.gender == 2) {
            this.sex = 'F';
        }
        Member m = new Member(0, new Password(null), this.f_name, this.l_name, this.phoneNumber, this.sex, new Date(Integer.parseInt(birthday[2]) - 1900, Integer.parseInt(birthday[1]) - 1, Integer.parseInt(birthday[0])), this.address, this.e_mail);
        if(!(rf.isMemberExistent(m))){
            rf.insertMember(m);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Completed", "Member " + m.getName() + " has been inserted to Database, successfully.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "The Member with e-mail: " + m.getEmail() + " already exist!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            throw new AlreadyExist("The Member with e-mail: " + m.getEmail() + " already exist!");
        }
    }

    /**
     * This method returns the address of a Member instance.
     *
     * @return Member's address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * This method returns the e-mail of a Member instance.
     *
     * @return Member's e-mail
     */
    public String getE_mail() {
        return this.e_mail;
    }

    /**
     * This method returns the first name of a Member instance.
     *
     * @return Member's first name
     */
    public String getF_name() {
        return this.f_name;
    }

    /**
     * This method returns the last name of a Member instance.
     *
     * @return Member's last name
     */
    public String getL_name() {
        return this.l_name;
    }

    /**
     * This method returns the phone number of a Member instance.
     *
     * @return Member's phone number
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * This method returns the birth date of a Member instance.
     *
     * @return Member's birth date
     */
    public String getBDay() {
        return this.BDay;
    }

    /**
     * This method returns the gender of a Member instance.
     *
     * @return Member's gender
     */
    public int getGender() {
        return this.gender;
    }

    /**
     * This method sets the first name of a Member instance.
     *
     * @param f_name First name of the Member
     */
    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    /**
     * This method sets the last name of a Member instance.
     *
     * @param l_name Last name of the Member
     */
    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    /**
     * This method sets the gender of a Member instance.
     *
     * @param gender Member's gender
     */
    public void setGender(int gender) {
        this.gender = gender;
    }

    /**
     * This method sets the phone number of a Member instance.
     *
     * @param phoneNumber Member's phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * This method sets the birth date of a Member instance.
     *
     * @param BDay Member's birth date
     */
    public void setBDay(String BDay) {
        this.BDay = BDay;
    }

    /**
     * This method sets the e-mail of a Member instance.
     *
     * @param e_mail Member's e-mail
     */
    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    /**
     * This method sets the address of a Member instance.
     *
     * @param address Member's address
     */
    public void setAddress(String address) {
        this.address = address;
    }

}
