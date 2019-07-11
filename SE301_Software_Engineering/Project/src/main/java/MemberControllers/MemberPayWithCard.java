package MemberControllers;

import LoginController.UserLogin;
import Repository.RepositoryFacade;
import RequestManagement.VIPRequest;
import UserManagement.Member;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class MemberPayWithCard implements Serializable {

    /**
     * All of the card informations must not be null
     *
     * @invariant cardOwnerName != null
     * @invariant cardNumber != null
     * @invariant cardValidationDate != null
     * @invariant cardSecurityNumber != null
     */
    RepositoryFacade rp = new RepositoryFacade();
    Member m = rp.getMember(UserLogin.getUserLoggedIn());

    private String cardOwnerName;
    private String cardNumber;
    private String cardValidationDate;
    private String cardSecurityNumber;

    /**
     * This method sends a request of becoming a VIP Member
     *
     * @pre !rp.isVIPMember()
     * @post isRequestExist() // ???????????????
     */
    public void sendRequestToRegistrar() {
        rp.insertVIPRequest(new VIPRequest(0, UserLogin.getUserLoggedIn(), m.getName()));

    }

    /**
     * This method returns the name of the card owner
     *
     * @return Card Owner Name
     */
    public String getCardOwnerName() {
        return cardOwnerName;
    }

    /**
     * This method sets the card owner name
     *
     * @param cardOwnerName
     */
    public void setCardOwnerName(String cardOwnerName) {
        this.cardOwnerName = cardOwnerName;
    }

    /**
     * This method returns the card number
     *
     * @return Card Number
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * This method sets the card number
     *
     * @param cardNumber
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * This method returns the card validation date
     *
     * @return Card Validation Date
     */
    public String getCardValidationDate() {
        return cardValidationDate;
    }

    /**
     * This method sets the card validation date
     *
     * @param cardValidationDate
     */
    public void setCardValidationDate(String cardValidationDate) {
        this.cardValidationDate = cardValidationDate;
    }

    /**
     * This method returns the card security number
     *
     * @return Card Security Number
     */
    public String getCardSecurityNumber() {
        return cardSecurityNumber;
    }

    /**
     * This method sets the card security number
     *
     * @param cardSecurityNumber
     */
    public void setCardSecurityNumber(String cardSecurityNumber) {
        this.cardSecurityNumber = cardSecurityNumber;
    }

}
