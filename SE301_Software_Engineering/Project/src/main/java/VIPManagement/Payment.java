package VIPManagement;

public class Payment {

    private String name;
    private String CardOwnerName;
    private String CreditCardNumber;
    private String SecurityNumber;
    private String ValidationDate;

    public Payment(String name){
        this.name = name;
    }

    public Payment(String CardOwnerName, String CreditCardNumber, String SecurityNumber, String ValidationDate){
            this.CardOwnerName = CardOwnerName;
            this.CreditCardNumber = CreditCardNumber;
            this.SecurityNumber = SecurityNumber;
            this.ValidationDate = ValidationDate;
    }

    public String getName() {
        return CardOwnerName;
    }

    public String getCreditCardNumber() {
        return CreditCardNumber;
    }

    public String getSecurityNumber() {
        return SecurityNumber;
    }

    public String getValidationDate() {
        return ValidationDate;
    }

}
