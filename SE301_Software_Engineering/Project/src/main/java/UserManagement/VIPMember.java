package UserManagement;

import java.util.Date;

public class VIPMember extends Member {

    /**
     * Constructs a new vip member object with the relative parameters
     *
     * @param id: holds the unique id of the vip member kept in database
     * @param password: holds the password of the vip member
     * @param name: holds the name of the vip member
     * @param surName: holds the surName of the vip member
     * @param phoneNumber: holds the phoneNumber of the vip member
     * @param gender: holds the gender of the vip member as char (F or M)
     * @param birthDate: holds the birth date of the vip member
     * @param address: holds the address of the vip member
     * @param email: holds the email of the vip member
     */
    public VIPMember(int id, Password password, String name, String surName, String phoneNumber, char gender, Date birthDate, String address, String email) {
        super(id, password, name, surName, phoneNumber, gender, birthDate, address, email);
    }

}
