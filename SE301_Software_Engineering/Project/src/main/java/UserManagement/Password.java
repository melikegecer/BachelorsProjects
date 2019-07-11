package UserManagement;

public class Password {

    private String password;

    /**
     * Constructs a new password object with the relative parameters
     *
     * @param password
     */
    public Password(String password) {
        if (password != null) {
            this.password = password;
        } else {
            this.password = autoGeneratePassword();

        }
    }

    /**
     * Constructs a password object with auto generated password
     */
    public Password() {
        this.password = autoGeneratePassword();
    }

    /**
     * 
     * @return the password
     */
    public String getRealPassWord() {
        return password;
    }

    /**
     * 
     * @return auto generated password
     */
    private String autoGeneratePassword() {
        String res = "";
        res += (char) (Randomize() + 65);

        for (int i = 0; i < 4; i++) {
            res += (char) (Randomize() + 97);
        }

        for (int i = 0; i < 3; i++) {
            res += (int) (Math.random() * 9);
        }
        return res;
    }

    /**
     * 
     * @return a random number to create password
     */
    private int Randomize() {
        return (int) (Math.random() * 26);
    }
}
