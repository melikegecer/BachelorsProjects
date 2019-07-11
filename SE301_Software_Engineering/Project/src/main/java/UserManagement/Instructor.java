package UserManagement;

import java.util.Date;

public class Instructor extends User {

    /**
     * Constructs a new instructor object with the relative parameters
     *
     * @param id: holds the unique id of the instructor kept in database
     * @param password: holds the password of the instructor
     * @param name: holds the name of the instructor
     * @param surName: holds the surName of the instructor
     * @param phoneNumber: holds the phoneNumber of the instructor
     * @param gender: holds the gender of the instructor as char (F or M)
     * @param birthDate: holds the birth date of the instructor
     * @param address: holds the address of the instructor
     * @param email: holds the email of the instructor
     */
    public Instructor(int id, Password password, String name, String surName, String phoneNumber, char gender, Date birthDate, String address, String email) {
        super(id, password, name, surName, phoneNumber, gender, birthDate, address, email);
    }

}
