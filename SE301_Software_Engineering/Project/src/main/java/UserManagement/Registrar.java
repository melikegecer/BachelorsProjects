package UserManagement;

import java.util.Date;

public class Registrar extends User {

    /**
     * Constructs a new registrar object with the relative parameters
     *
     * @param id: holds the unique id of the registrar kept in database
     * @param password: holds the password of the registrar
     * @param name: holds the name of the registrar
     * @param surName: holds the surName of the registrar
     * @param phoneNumber: holds the phoneNumber of the registrar
     * @param gender: holds the gender of the registrar as char (F or M)
     * @param birthDate: holds the birth date of the registrar
     * @param address: holds the address of the registrar
     * @param email: holds the email of the registrar
     */
    public Registrar(int id, Password password, String name, String surName, String phoneNumber, char gender, Date birthDate, String address, String email) {
        super(id, password, name, surName, phoneNumber, gender, birthDate, address, email);
    }

}
