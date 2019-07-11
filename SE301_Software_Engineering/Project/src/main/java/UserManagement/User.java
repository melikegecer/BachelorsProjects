package UserManagement;

import java.util.Date;

public abstract class User {

    private int id;
    private Password password;
    private String name;
    private String surName;
    private String phoneNumber;
    private char gender;
    private Date birthDate;
    private String address;
    private String email;

    /**
     * Constructs a new user object with the relative parameters
     *
     * @param id: holds the unique id of the user kept in database
     * @param password: holds the password of the user
     * @param name: holds the name of the user
     * @param surName: holds the surName of the user
     * @param phoneNumber: holds the phoneNumber of the user
     * @param gender: holds the gender of the user as char (F or M)
     * @param birthDate: holds the birth date of the user
     * @param address: holds the address of the user
     * @param email: holds the email of the user
     */
    protected User(int id, Password password, String name, String surName, String phoneNumber, char gender, Date birthDate, String address, String email) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.surName = surName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.birthDate = birthDate;
        this.address = address;
        this.email = email;
    }

    /**
     *
     * @return a string representation of the values of this User object s id,
     * name, surname, phone number, gender, birth date, email, address fields.
     */
    @Override
    public String toString() {
        return "ID: " + getId() + " name: " + this.getName() + " surname: " + this.getSurName() + " phone number: " + this.getPhoneNumber() + " gender: " + getGender() + " birth date: " + getBirthDate().toString() + " email: " + this.getEmail() + " address: " + this.getAddress();
    }

    /**
     * Checks whether two User objects have equal values.
     *
     * @param o: given object to compare
     * @return result of the comparison
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
            User u = (User) o;
            if (u.getId() == this.getId() && u.getName().equals(this.getName()) && u.getSurName().equals(this.getSurName()) && u.getPhoneNumber().equals(this.getPhoneNumber()) && u.getGender() == this.getGender() && u.getBirthDate() == this.getBirthDate() && u.getAddress().equals(this.getAddress()) && u.getEmail().equals(this.getEmail())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the id of the User
     */
    public int getId() {
        return id;
    }

    /**
     * @return the password of the User
     */
    public Password getPassword() {
        return password;
    }

    /**
     * @return the name of the User
     */
    public String getName() {
        return name;
    }

    /**
     * @return the surName of the User
     */
    public String getSurName() {
        return surName;
    }

    /**
     * @return the phoneNumber of the User
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return the gender of the User
     */
    public char getGender() {
        return gender;
    }

    /**
     * @return the birthDate of the User
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * @return the address of the User
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the email of the User
     */
    public String getEmail() {
        return email;
    }

}
