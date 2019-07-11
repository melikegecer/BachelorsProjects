package UserManagement;

import java.util.Date;

public class Member extends User {

    /**
     * Constructs a new member object with the relative parameters
     *
     * @param id: holds the unique id of the member kept in database
     * @param password: holds the password of the member
     * @param name: holds the name of the member
     * @param surName: holds the surName of the member
     * @param phoneNumber: holds the phoneNumber of the member
     * @param gender: holds the gender of the member as char (F or M)
     * @param birthDate: holds the birth date of the member
     * @param address: holds the address of the member
     * @param email: holds the email of the member
     */
    public Member(int id, Password password, String name, String surName, String phoneNumber, char gender, Date birthDate, String address, String email) {
        super(id, password, name, surName, phoneNumber, gender, birthDate, address, email);
    }
    
}
