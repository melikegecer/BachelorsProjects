package LoginController;

import Repository.RepositoryFacade;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class UserLogin {

    /**
     * retrieved from the interface, should not be null
     *
     * @invariant userName != null
     */
    private String userName = "";

    /**
     * retrieved from the interface, should not be null
     *
     * @invariant password != null
     */
    private String password = "";
    private RepositoryFacade rf = new RepositoryFacade();

    private static int userLoggedIn = 0;
    private static char userType;

    /**
     *
     * @return the address of a logged in user
     */
    public String login() {
        if (userName == null || password == null) {
            return "/Interfaces/index.xhtml";
        } else {
            char firstChar = userName.charAt(0);
            userLoggedIn = Integer.valueOf(userName.substring(1));

            switch (firstChar) {
                case 'm':
                case 'M':
                    if (rf.checkPasswordOfMember(userLoggedIn, password)) {
                        return "/Interfaces/MemberLoggedIn.xhtml";
                    }
                case 'i':
                case 'Ý':
                case 'ý':
                case 'I':
                    if (rf.checkPasswordOfInstructor(userLoggedIn, password)) {
                        return "/Interfaces/InstructorLoggedIn.xhtml";
                    }
                case 's':
                case 'S':
                    if (rf.checkPasswordOfScheduler(userLoggedIn, password)) {
                        return "/Interfaces/SchedulerLoggedIn.xhtml";
                    }
                case 'r':
                case 'R':
                    if (rf.checkPasswordOfRegistrar(userLoggedIn, password)) {
                        return "/Interfaces/RegistrarLoggedIn.xhtml";
                    }
                case 'v':
                case 'V':
                    if (rf.checkPasswordOfMember(userLoggedIn, password)) {
                        return "/Interfaces/VIPLoggedIn.xhtml";
                    }
                default:
                    System.out.println("index");
                    return "/Interfaces/index.xhtml";
            }
        }
    }

    /**
     *
     * @return the logged in user s type of the user as char
     */
    public static char getUserType() {
        return userType;
    }

    /**
     *
     * @return entered password from the interface
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return entered username from the interface
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @return the logged in user s id
     */
    public static int getUserLoggedIn() {
        return userLoggedIn;
    }

    /**
     *
     * @param password used to take the entered password from the interface
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @param userName used to take the the entered username from the interface
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
