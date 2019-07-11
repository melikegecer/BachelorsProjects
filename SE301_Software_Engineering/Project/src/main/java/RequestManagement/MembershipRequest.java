package RequestManagement;

public class MembershipRequest {

    private int id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;

    /**
     * Constructor creates a membership request by using information of the requester
     *
     * @param id: holds the unique id of the request kept in database
     * @param name: holds the name of the requester
     * @param surname: holds the sur name of the requester
     * @param phoneNumber: holds the phone number of the requester
     * @param email: holds the email address of the requester
     */
    public MembershipRequest(int id, String name, String surname, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

     /**
     *
     * @return a string representation of the values of this Membership Request object's id, from(name, surname, phoneNumber, email) fields.
     */
    @Override
    public String toString() {
        return "Request: ID-" + this.id + " FROM: Name-" + this.name + " Surname-" + this.surname + " PhoneNumber-" + this.phoneNumber + " Email-" + this.email;
    }

    /**
     * Checks whether two Membership Request objects have equal values.
     *
     * @param o: given object to compare
     * @return result of the comparison
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof MembershipRequest) {
            MembershipRequest mr = (MembershipRequest) o;
            if (mr.getId() == this.id && mr.getName().equals(this.name) && mr.getSurname().equals(this.surname) && mr.getPhoneNumber().equals(this.phoneNumber) && mr.getEmail().equals(this.email)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @return id of the request
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @return email of the requester
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @return name of the requester
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return phone number of the requester
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 
     * @return surname of the requester
     */
    public String getSurname() {
        return surname;
    }

}
