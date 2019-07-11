package VIPManagement;

public class PaymentValidation {

    /**
     * The name of Credit Card  shouldn't be null.
     *
     * @invariant name != null
     */
    private String name;

    /**
     * The Card Owner of Credit Card  shouldn't be null.
     *
     * @invariant CardOwnerName != null
     */
    private String cardOwnerName;

    /**
     * The number of Credit Card  shouldn't be null.
     *
     * @invariant CreditCardNumber != null
     */
    private String creditCardNumber;

    /**
     * The Security number of Credit Card  shouldn't be null.
     *
     * @invariant SecurityNumber != null
     */
    private String securityNumber;

    /**
     * The Validation Date of Credit Card shouldn't be null. 
     *
     * @invariant SecurityNumber != null
     */
    private String validationDate;

    /**
     * This method creates a new Payment instance and send to request.
     *
     */
    public void PaymentValidationControl() {
        if (!cardOwnerName.equals("") && !creditCardNumber.equals("")
                && !securityNumber.equals("") && validationDate.equals("")
                && PayWithCard(cardOwnerName, creditCardNumber, securityNumber, validationDate)) {
            Payment p = new Payment(cardOwnerName, creditCardNumber, securityNumber, validationDate);
        } else if (!name.equals("")) {
            Payment p = new Payment(name);
        } else {
            System.out.println("Invalid Information !");
        }
    }

    /**
     * This method control the card informations are appropriate.
     *
     * @param cardOwnerName
     * @param creditCardNumber
     * @param SecurityNumber
     * @param ValidationDate
     * @return true if information is appropriate. Otherwise, return false.
     */
    public boolean PayWithCard(String cardOwnerName, String creditCardNumber, String SecurityNumber, String ValidationDate) {
        if (!cardOwnerName.equals("")) {
            if (!(creditCardNumber.length() < 16) && !creditCardNumber.equals("")) {
                if (!(SecurityNumber.length() < 3) && SecurityNumber.equals("")) {
                    if (ValidationDate.equals("")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
