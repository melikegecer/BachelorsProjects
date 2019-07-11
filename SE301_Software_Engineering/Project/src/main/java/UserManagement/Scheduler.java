package UserManagement;

import java.util.Date;

public class Scheduler extends User {

    /**
     * Constructs a new scheduler object with the relative parameters
     *
     * @param id: holds the unique id of the scheduler kept in database
     * @param password: holds the password of the scheduler
     * @param name: holds the name of the scheduler
     * @param surName: holds the surName of the scheduler
     * @param phoneNumber: holds the phoneNumber of the scheduler
     * @param gender: holds the gender of the scheduler as char (F or M)
     * @param birthDate: holds the birth date of the scheduler
     * @param address: holds the address of the scheduler
     * @param email: holds the email of the scheduler
     */
    public Scheduler(int id, Password password, String name, String surName, String phoneNumber, char gender, Date birthDate, String address, String email) {
        super(id, password, name, surName, phoneNumber, gender, birthDate, address, email);
    }

}
