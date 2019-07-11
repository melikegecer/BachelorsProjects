package Repository;

import ActivityManagement.*;
import RequestManagement.*;
import UserManagement.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBTransactions {

    private static DBTransactions dbtransactions;

    private static Connection connection;

    private static PreparedStatement preparedStatement;

    private final static String MEMBER_INSERT_QUERY = "INSERT INTO member (Password, Name, Surname, PhoneNumber, Gender, BDay, Address, Email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String MEMBER_UPDATE_QUERY = "UPDATE member SET Password = ?, Name = ?, Surname = ?, PhoneNumber = ?, Gender = ?, BDay = ?, Address = ?, Email = ? WHERE ID = ?";
    private final static String MEMBER_DELETE_QUERY = "DELETE FROM member WHERE ID = ?";
    private final static String MEMBER_SELECT_QUERY = "SELECT ID, Password, Name, Surname, PhoneNumber, Gender, BDay, Address, Email FROM member WHERE ID = ?";
    private final static String MEMBER_COUNT_QUERY = "SELECT COUNT(*) FROM member";
    private final static String MEMBER_CHECK_PASSWORD_QUERY = "SELECT Password FROM member WHERE ID = ?";

    private final static String IS_MEMBER_EXIST_QUERY = "SELECT COUNT(*) FROM member WHERE PhoneNumber = ? AND Email = ?";

    private final static String MEMBER_GET_NAME_QUERY = "SELECT Name, Surname FROM member WHERE ID = ?";

    private final static String VIPMEMBER_SELECT_QUERY = "SELECT M_ID FROM vipmember WHERE M_ID = ?";

    private final static String INSTRUCTOR_INSERT_QUERY = "INSERT INTO instructor (Password, Name, Surname, PhoneNumber, Gender, BDay, Address, Email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String INSTRUCTOR_UPDATE_QUERY = "UPDATE instructor SET Password = ?, Name = ?, Surname = ?, PhoneNumber = ?, Gender = ?, BDay = ?, Address = ?, Email = ? WHERE ID = ?";
    private final static String INSTRUCTOR_DELETE_QUERY = "DELETE FROM instructor WHERE ID = ?";
    private final static String INSTRUCTOR_SELECT_QUERY = "SELECT ID, Password, Name, Surname, PhoneNumber, Gender, BDay, Address, Email FROM instructor WHERE ID = ?";
    private final static String INSTRUCTOR_COUNT_QUERY = "SELECT COUNT(*) FROM instructor";
    private final static String INSTRUCTOR_CHECK_PASSWORD_QUERY = "SELECT Password FROM instructor WHERE ID = ?";

    private final static String IS_INSTRUCTOR_EXIST_QUERY = "SELECT COUNT(*) FROM instructor WHERE PhoneNumber = ? AND Email = ?";

    private final static String INSTRUCTOR_ALL_SELECT_QUERY = "SELECT ID FROM instructor";

    private final static String REGISTRAR_INSERT_QUERY = "INSERT INTO registrar (Password, Name, Surname, PhoneNumber, Gender, BDay, Address, Email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String REGISTRAR_UPDATE_QUERY = "UPDATE registrar SET Password = ?, Name = ?, Surname = ?, PhoneNumber = ?, Gender = ?, BDay = ?, Address = ?, Email = ? WHERE ID = ?";
    private final static String REGISTRAR_DELETE_QUERY = "DELETE FROM registrar WHERE ID = ?";
    private final static String REGISTRAR_SELECT_QUERY = "SELECT ID, Password, Name, Surname, PhoneNumber, Gender, BDay, Address, Email FROM registrar WHERE ID = ?";
    private final static String REGISTRAR_CHECK_PASSWORD_QUERY = "SELECT Password FROM registrar WHERE ID = ?";

    private final static String SCHEDULER_INSERT_QUERY = "INSERT INTO scheduler (Password, Name, Surname, PhoneNumber, Gender, BDay, Address, Email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String SCHEDULER_UPDATE_QUERY = "UPDATE scheduler SET Password = ?, Name = ?, Surname = ?, PhoneNumber = ?, Gender = ?, BDay = ?, Address = ?, Email = ? WHERE ID = ?";
    private final static String SCHEDULER_DELETE_QUERY = "DELETE FROM scheduler WHERE ID = ?";
    private final static String SCHEDULER_SELECT_QUERY = "SELECT ID, Password, Name, Surname, PhoneNumber, Gender, BDay, Address, Email FROM scheduler WHERE ID = ?";
    private final static String SCHEDULER_COUNT_QUERY = "SELECT COUNT(*) FROM scheduler";
    private final static String SCHEDULER_CHECK_PASSWORD_QUERY = "SELECT Password FROM scheduler WHERE ID = ?";

    private final static String IS_SCHEDULER_EXIST_QUERY = "SELECT COUNT(*) FROM scheduler WHERE PhoneNumber = ? AND Email = ?";

    private final static String COURT_INSERT_QUERY = "INSERT INTO court (Name) VALUES (?)";
    private final static String COURT_UPDATE_QUERY = "UPDATE court SET Name = ? WHERE ID = ?";
    private final static String COURT_DELETE_QUERY = "DELETE FROM court WHERE ID = ?";
    private final static String COURT_SELECT_QUERY = "SELECT C_ID, Name, VM_ID, Slot FROM vm_r_c AS rc, court AS c WHERE rc.C_ID = c.ID AND rc.C_ID = ?";
    private final static String COURTS_SELECT_QUERY = "SELECT ID, Name FROM court";

    private final static String ACTIVITY_INSERT_QUERY = "INSERT INTO activity (Name) VALUES (?)";
    private final static String ACTIVITY_SELECT_BY_NAME_QUERY = "SELECT ID, Name FROM activity WHERE Name = ?";
    private final static String ACTIVITY_ALL_SELECT_QUERY = "SELECT A_ID, Name, I_ID, Slot FROM activity AS a, i_g_a AS iga WHERE a.ID = iga.A_ID";
    private final static String ACTIVITY_SELECT_QUERY = "SELECT A_ID, Name, I_ID, Slot FROM activity AS a, i_g_a AS iga WHERE a.ID = iga.A_ID AND a.ID = ?";

    private final static String SPECIAL_ACTIVITY_INSERT_QUERY = "INSERT INTO specialactivity (A_ID) VALUES (?)";
    private final static String SPECIAL_ACTIVITY_ALL_SELECT_QUERY = "SELECT sa.A_ID, I_ID, Name, Slot FROM activity AS a, i_g_a AS iga, specialactivity AS sa WHERE sa.A_ID = iga.A_ID AND sa.A_ID = a.ID";

    private final static String VIP_REQUEST_INSERT_QUERY = "INSERT INTO viprequest (FromID) VALUES (?)";
    private final static String VIP_REQUEST_DELETE_QUERY = "DELETE FROM viprequest WHERE ID = ?";
    private final static String VIP_REQUESTS_SELECT_QUERY = "SELECT ID, FromID FROM viprequest";
    private final static String VIP_REQUEST_EXIST_QUERY = "SELECT COUNT(*) FROM viprequest WHERE FromID = ?";

    private final static String ACTIVITY_REQUEST_INSERT_QUERY = "INSERT INTO activityrequest (FromID, ToID, AID) VALUES (?, ?, ?)";
    private final static String ACTIVITY_REQUEST_DELETE_QUERY = "DELETE FROM activityrequest WHERE ID = ?";
    private final static String ACTIVITY_REQUESTS_SELECT_QUERY = "SELECT ID, FromID, ToID, AID FROM activityrequest";
    private final static String ACTIVITY_REQUEST_EXIST_QUERY = "SELECT COUNT(*) FROM activityrequest WHERE FromID = ? AND ToID = ? AND AID = ?";

    private final static String SPECIAL_ACTIVITY_REQUEST_INSERT_QUERY = "INSERT INTO specialactivityrequest (FromID, ToID, SAID) VALUES (?, ?, ?)";
    private final static String SPECIAL_ACTIVITY_REQUEST_DELETE_QUERY = "DELETE FROM specialactivityrequest WHERE ID = ?";
    private final static String SPECIAL_ACTIVITY_REQUESTS_SELECT_QUERY = "SELECT ID, FromID, ToID, SAID FROM specialactivityrequest";
    private final static String SPECIAL_ACTIVITY_REQUEST_EXIST_QUERY = "SELECT COUNT(*) FROM specialactivityrequest WHERE FromID = ? AND ToID = ? AND SAID = ?";

    private final static String MEMBERSHIP_REQUEST_INSERT_QUERY = "INSERT INTO membershiprequest (Name, Surname, PhoneNumber, Email) VALUES (?, ?, ?, ?)";
    private final static String MEMBERSHIP_REQUEST_DELETE_QUERY = "DELETE FROM membershiprequest WHERE ID = ?";
    private final static String MEMBERSHIP_REQUESTS_SELECT_QUERY = "SELECT ID, Name, Surname, Phonenumber, Email FROM membershiprequest";
    private final static String MEMBERSHIP_REQUEST_EXIST_QUERY = "SELECT COUNT(*) FROM membershiprequest WHERE Name = ? AND Surname = ? AND PhoneNumber = ? AND Email = ?";

    private final static String MEMBER_JOINS_ACTIVITY_INSERT_QUERY = "INSERT INTO m_j_a (M_ID, A_ID) VALUES (?, ?)";
    private final static String MEMBER_JOINS_ACTIVITY_DELETE_QUERY = "DELETE FROM m_j_a WHERE M_ID = ? AND A_ID = ?";
    private final static String MEMBER_JOINS_ACTIVITY_EXIST_QUERY = "SELECT COUNT(*) FROM m_j_a WHERE M_ID = ? AND A_ID = ?";
    private final static String MEMBER_JOINS_ACTIVITY_SELECT_QUERY = "SELECT M_ID, A_ID FROM m_j_a";

    private final static String VIPMEMBER_JOINS_SPECIALACTIVITY_INSERT_QUERY = "INSERT INTO vm_j_sa (VM_ID, SA_ID) VALUES (?, ?)";
    private final static String VIPMEMBER_JOINS_SPECIALACTIVITY_DELETE_QUERY = "DELETE FROM vm_j_sa WHERE VM_ID = ? AND SA_ID = ?";
    private final static String VIPMEMBER_JOINS_SPECIALACTIVITY_EXIST_QUERY = "SELECT COUNT(*) FROM vm_j_sa WHERE VM_ID = ? AND SA_ID = ?";
    private final static String VIPMEMBER_JOINS_SPECIAL_ACTIVITY_SELECT_QUERY = "SELECT VM_ID, SA_ID FROM vm_j_sa";

    private final static String BECOME_VIPMEMBER_INSERT_QUERY = "INSERT INTO vipmember (M_ID) VALUES (?)";
    private final static String BECOME_VIPMEMBER_DELETE_QUERY = "DELETE FROM vipmember WHERE M_ID = ?";

    private final static String VIPMEMBER_RESERVES_COURT_INSERT_QUERY = "INSERT INTO vm_r_c (VM_ID, C_ID, Slot) VALUES (?, ?, ?)";
    private final static String VIPMEMBER_RESERVES_COURT_DELETE_QUERY = "DELETE FROM vm_r_c WHERE VM_ID = ? AND C_ID = ? AND Slot = ?";

    private final static String I_G_A_INSERT_QUERY = "INSERT INTO i_g_a (I_ID, A_ID, Slot) VALUES (?, ?, ?)";
    private final static String I_G_A_DELETE_QUERY = "DELETE FROM i_g_a WHERE I_ID = ? AND A_ID = ? AND Slot = ?";

    private DBTransactions() {
        connection = DBConnector.getInstance().getConnection();
    }

    static DBTransactions getInstance() {
        if (dbtransactions == null) {
            dbtransactions = new DBTransactions();
        }
        return dbtransactions;
    }

    static void memberTransaction(char typeOfTransaction, Member member) {
        try {
            switch (typeOfTransaction) {
                case 'd':
                    preparedStatement = connection.prepareStatement(MEMBER_DELETE_QUERY);
                    preparedStatement.setInt(1, member.getId());
                    preparedStatement.executeUpdate();
                    break;
                case 'u':
                case 'i':
                    if (typeOfTransaction == 'u') {
                        preparedStatement = connection.prepareStatement(MEMBER_UPDATE_QUERY);
                        preparedStatement.setInt(9, member.getId());
                    } else if (typeOfTransaction == 'i') {
                        preparedStatement = connection.prepareStatement(MEMBER_INSERT_QUERY);
                    }
                    preparedStatement.setString(1, member.getPassword().getRealPassWord());
                    preparedStatement.setString(2, member.getName());
                    preparedStatement.setString(3, member.getSurName());
                    preparedStatement.setString(4, member.getPhoneNumber());
                    preparedStatement.setString(5, member.getGender() + "");
                    preparedStatement.setDate(6, (Date) member.getBirthDate());
                    preparedStatement.setString(7, member.getAddress());
                    preparedStatement.setString(8, member.getEmail());
                    preparedStatement.executeUpdate();
                    break;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in MEMBER_TRANSACTIONS) " + ex.getMessage());
        }
    }

    static void registrarTransaction(char typeOfTransaction, Registrar registrar) {
        try {
            switch (typeOfTransaction) {
                case 'd':
                    preparedStatement = connection.prepareStatement(REGISTRAR_DELETE_QUERY);
                    preparedStatement.setInt(1, registrar.getId());
                    preparedStatement.executeUpdate();
                    break;
                case 'u':
                case 'i':
                    if (typeOfTransaction == 'u') {
                        preparedStatement = connection.prepareStatement(REGISTRAR_UPDATE_QUERY);
                        preparedStatement.setInt(9, registrar.getId());
                    } else if (typeOfTransaction == 'i') {
                        preparedStatement = connection.prepareStatement(REGISTRAR_INSERT_QUERY);
                    }
                    preparedStatement.setString(1, registrar.getPassword().getRealPassWord());
                    preparedStatement.setString(2, registrar.getName());
                    preparedStatement.setString(3, registrar.getSurName());
                    preparedStatement.setString(4, registrar.getPhoneNumber());
                    preparedStatement.setString(5, registrar.getGender() + "");
                    preparedStatement.setDate(6, (Date) registrar.getBirthDate());
                    preparedStatement.setString(7, registrar.getAddress());
                    preparedStatement.setString(8, registrar.getEmail());
                    preparedStatement.executeUpdate();
                    break;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in REGISTRAR_TRANSACTIONS) " + ex.getMessage());
        }
    }

    static void instructorTransaction(char typeOfTransaction, Instructor instructor) {
        try {
            switch (typeOfTransaction) {
                case 'd':
                    preparedStatement = connection.prepareStatement(INSTRUCTOR_DELETE_QUERY);
                    preparedStatement.setInt(1, instructor.getId());
                    preparedStatement.executeUpdate();
                    break;
                case 'u':
                case 'i':
                    if (typeOfTransaction == 'u') {
                        preparedStatement = connection.prepareStatement(INSTRUCTOR_UPDATE_QUERY);
                        preparedStatement.setInt(9, instructor.getId());
                    } else if (typeOfTransaction == 'i') {
                        preparedStatement = connection.prepareStatement(INSTRUCTOR_INSERT_QUERY);
                    }
                    preparedStatement.setString(1, instructor.getPassword().getRealPassWord());
                    preparedStatement.setString(2, instructor.getName());
                    preparedStatement.setString(3, instructor.getSurName());
                    preparedStatement.setString(4, instructor.getPhoneNumber());
                    preparedStatement.setString(5, instructor.getGender() + "");
                    preparedStatement.setDate(6, (Date) instructor.getBirthDate());
                    preparedStatement.setString(7, instructor.getAddress());
                    preparedStatement.setString(8, instructor.getEmail());
                    preparedStatement.executeUpdate();
                    break;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in INSTRUCTOR_TRANSACTION) " + ex.getMessage());
        }
    }

    static void schedulerTransaction(char typeOfTransaction, Scheduler scheduler) {
        try {
            switch (typeOfTransaction) {
                case 'd':
                    preparedStatement = connection.prepareStatement(SCHEDULER_DELETE_QUERY);
                    preparedStatement.setInt(1, scheduler.getId());
                    preparedStatement.executeUpdate();
                    break;
                case 'u':
                case 'i':
                    if (typeOfTransaction == 'u') {
                        preparedStatement = connection.prepareStatement(SCHEDULER_UPDATE_QUERY);
                        preparedStatement.setInt(9, scheduler.getId());
                    } else if (typeOfTransaction == 'i') {
                        preparedStatement = connection.prepareStatement(SCHEDULER_INSERT_QUERY);
                    }
                    preparedStatement.setString(1, scheduler.getPassword().getRealPassWord());
                    preparedStatement.setString(2, scheduler.getName());
                    preparedStatement.setString(3, scheduler.getSurName());
                    preparedStatement.setString(4, scheduler.getPhoneNumber());
                    preparedStatement.setString(5, scheduler.getGender() + "");
                    preparedStatement.setDate(6, (Date) scheduler.getBirthDate());
                    preparedStatement.setString(7, scheduler.getAddress());
                    preparedStatement.setString(8, scheduler.getEmail());
                    preparedStatement.executeUpdate();
                    break;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in SCHEDULER_TRANSACTION) " + ex.getMessage());
        }
    }

    private static int getResultSetSize(ResultSet resultSet) {
        int size = 0;
        try {
            while (resultSet.next()) {
                size++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBTransactions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return size;
    }

    static void courtTransaction(char typeOfTransaction, Court court) {
        try {
            switch (typeOfTransaction) {
                case 'd':
                    preparedStatement = connection.prepareStatement(COURT_DELETE_QUERY);
                    preparedStatement.setInt(1, court.getId());
                    preparedStatement.executeUpdate();
                    break;
                case 'u':
                case 'i':
                    if (typeOfTransaction == 'u') {
                        preparedStatement = connection.prepareStatement(COURT_UPDATE_QUERY);
                        preparedStatement.setInt(2, court.getId());
                    } else if (typeOfTransaction == 'i') {
                        preparedStatement = connection.prepareStatement(COURT_INSERT_QUERY);
                    }
                    preparedStatement.setString(1, court.getName());
                    preparedStatement.executeUpdate();
                    break;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in COURT_TRANSACTIONS) " + ex.getMessage());
        }
    }

    static void insertActivity(Activity activity) {
        try {
            if (!activityExist(activity)) {
                preparedStatement = connection.prepareStatement(ACTIVITY_INSERT_QUERY);
                preparedStatement.setString(1, activity.getName());
                preparedStatement.executeUpdate();
            }
            int id = getActivityID(activity);
            Activity newActivity = new Activity(id, activity.getName(), activity.getInstructor(), activity.getSlot());
            insertIGA(newActivity);

            if (activity instanceof SpecialActivity) {
                preparedStatement = connection.prepareStatement(SPECIAL_ACTIVITY_INSERT_QUERY);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in INSERT_ACTIVITY) " + ex.getMessage());
        }
    }

    static void deleteActivity(Activity activity) {
        deleteIGA(activity);
    }

    private static int getActivityID(Activity activity) {
        try {
            preparedStatement = connection.prepareStatement(ACTIVITY_SELECT_BY_NAME_QUERY);
            preparedStatement.setString(1, activity.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id_selected = resultSet.getInt("ID");
                return id_selected;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in GET_ACTIVITY_ID) " + ex.getMessage());
        }
        return 0;
    }

    static boolean activityExist(Activity activity) {
        try {
            preparedStatement = connection.prepareStatement(ACTIVITY_SELECT_BY_NAME_QUERY);
            preparedStatement.setString(1, activity.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (getResultSetSize(resultSet) > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in ACTIVITY_EXIST) " + ex.getMessage());
        }
        return false;
    }

    static void updateActivity(Activity oldActivity, Activity newActivity) {
        deleteIGA(oldActivity);
        insertIGA(newActivity);
    }

    private static void insertIGA(Activity activity) {
        try {
            preparedStatement = connection.prepareStatement(I_G_A_INSERT_QUERY);
            preparedStatement.setInt(1, activity.getInstructor().getId());
            preparedStatement.setInt(2, activity.getId());
            preparedStatement.setString(3, activity.getSlot());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in INSERT_IGA) " + ex.getMessage());
        }
    }

    private static void deleteIGA(Activity activity) {
        try {
            preparedStatement = connection.prepareStatement(I_G_A_DELETE_QUERY);
            preparedStatement.setInt(1, activity.getInstructor().getId());
            preparedStatement.setInt(2, activity.getId());
            preparedStatement.setString(3, activity.getSlot());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in DELETE_IGA) " + ex.getMessage());
        }
    }

    static void activityRequestTransaction(char typeOfTransaction, ActivityRequest activityRequest) {
        try {
            switch (typeOfTransaction) {
                case 'd':
                    preparedStatement = connection.prepareStatement(ACTIVITY_REQUEST_DELETE_QUERY);
                    preparedStatement.setInt(1, activityRequest.getId());
                    preparedStatement.executeUpdate();
                    break;
                case 'i':
                    preparedStatement = connection.prepareStatement(ACTIVITY_REQUEST_INSERT_QUERY);
                    preparedStatement.setInt(1, activityRequest.getFrom());
                    preparedStatement.setInt(2, activityRequest.getTo());
                    preparedStatement.setInt(3, activityRequest.getSubject());
                    preparedStatement.executeUpdate();
                    break;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in ACTIVITY_REQUEST_TRANSACTIONS) " + ex.getMessage());
        }
    }

    static void vipRequestTransaction(char typeOfTransaction, VIPRequest vipRequest) {
        try {
            switch (typeOfTransaction) {
                case 'd':
                    preparedStatement = connection.prepareStatement(VIP_REQUEST_DELETE_QUERY);
                    preparedStatement.setInt(1, vipRequest.getId());
                    preparedStatement.executeUpdate();
                    break;
                case 'i':
                    preparedStatement = connection.prepareStatement(VIP_REQUEST_INSERT_QUERY);
                    preparedStatement.setInt(1, vipRequest.getFrom());
                    preparedStatement.executeUpdate();
                    break;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in VIP_REQUEST_TRANSACTIONS) " + ex.getMessage());
        }
    }

    static void specialActivityRequestTransaction(char typeOfTransaction, SpecialActivityRequest specialActivityRequest) {
        try {
            switch (typeOfTransaction) {
                case 'd':
                    preparedStatement = connection.prepareStatement(SPECIAL_ACTIVITY_REQUEST_DELETE_QUERY);
                    preparedStatement.setInt(1, specialActivityRequest.getId());
                    preparedStatement.executeUpdate();
                    break;
                case 'i':
                    preparedStatement = connection.prepareStatement(SPECIAL_ACTIVITY_REQUEST_INSERT_QUERY);
                    preparedStatement.setInt(1, specialActivityRequest.getFrom());
                    preparedStatement.setInt(2, specialActivityRequest.getTo());
                    preparedStatement.setInt(3, specialActivityRequest.getSubject());
                    preparedStatement.executeUpdate();
                    break;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in SELECT_SPECIAL_ACTIVITY_REQUESTS) " + ex.getMessage());
        }
    }

    static void membershipRequestTransaction(char typeOfTransaction, MembershipRequest membershipRequest) {
        try {
            switch (typeOfTransaction) {
                case 'd':
                    preparedStatement = connection.prepareStatement(MEMBERSHIP_REQUEST_DELETE_QUERY);
                    preparedStatement.setInt(1, membershipRequest.getId());
                    preparedStatement.executeUpdate();
                    break;
                case 'i':
                    preparedStatement = connection.prepareStatement(MEMBERSHIP_REQUEST_INSERT_QUERY);
                    preparedStatement.setString(1, membershipRequest.getName());
                    preparedStatement.setString(2, membershipRequest.getSurname());
                    preparedStatement.setString(3, membershipRequest.getPhoneNumber());
                    preparedStatement.setString(4, membershipRequest.getEmail());
                    preparedStatement.executeUpdate();
                    break;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in MEMBERSHIP_REQUEST_TRANSACTIONS) " + ex.getMessage());
        }
    }

    static ArrayList<ActivityRequest> getActivityRequests(int id) {
        ArrayList<ActivityRequest> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(ACTIVITY_REQUESTS_SELECT_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id_selected = resultSet.getInt("ID");
                int from_id_selected = resultSet.getInt("fromID");
                int to_id_selected = resultSet.getInt("toID");
                int a_id_selected = resultSet.getInt("aID");
                String name_surname_selected = getMemberName(from_id_selected);
                if (to_id_selected == id) {
                    list.add(new ActivityRequest(id_selected, from_id_selected, name_surname_selected, to_id_selected, a_id_selected));
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in GET_ACTIVITY_REQUESTS) " + ex.getMessage());
        }
        return list;
    }

    static ArrayList<SpecialActivityRequest> getSpecialActivityRequests(int id) {
        ArrayList<SpecialActivityRequest> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SPECIAL_ACTIVITY_REQUESTS_SELECT_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id_selected = resultSet.getInt("ID");
                int from_id_selected = resultSet.getInt("fromID");
                int to_id_selected = resultSet.getInt("toID");
                int a_id_selected = resultSet.getInt("saID");
                String name_surname_selected = getMemberName(from_id_selected);
                if (to_id_selected == id) {
                    list.add(new SpecialActivityRequest(id_selected, from_id_selected, name_surname_selected, to_id_selected, a_id_selected));
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in GET_SPECIAL_ACTIVITY_REQUESTS) " + ex.getMessage());
        }
        return list;
    }

    private static String getMemberName(int id) {
        try {
            preparedStatement = connection.prepareStatement(MEMBER_GET_NAME_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name_surname_selected = resultSet.getString("name") + resultSet.getString("surname");
                return name_surname_selected;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in GET_MEMBER_NAME) " + ex.getMessage());
        }
        return null;
    }

    static ArrayList<VIPRequest> getVIPRequests() {
        ArrayList<VIPRequest> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(VIP_REQUESTS_SELECT_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id_selected = resultSet.getInt("ID");
                int from_id_selected = resultSet.getInt("fromID");
                String name_surname_selected = getMemberName(from_id_selected);
                list.add(new VIPRequest(id_selected, from_id_selected, name_surname_selected));
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in GET_VIP_REQUESTS) " + ex.getMessage());
        }
        return list;
    }

    static ArrayList<MembershipRequest> getMembershipRequests() {
        ArrayList<MembershipRequest> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(MEMBERSHIP_REQUESTS_SELECT_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id_selected = resultSet.getInt("ID");
                String name_selected = resultSet.getString("name");
                String surname_selected = resultSet.getString("surname");
                String phonenumber_selected = resultSet.getString("phonenumber");
                String email_selected = resultSet.getString("email");
                list.add(new MembershipRequest(id_selected, name_selected, surname_selected, phonenumber_selected, email_selected));
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in GET_MEMBERSHIP_REQUEST) " + ex.getMessage());
        }
        return list;
    }

    static void memberJoinsActivityTransactions(char typeOfTransaction, Member member, Activity activity) {
        try {
            switch (typeOfTransaction) {
                case 'd':
                    preparedStatement = connection.prepareStatement(MEMBER_JOINS_ACTIVITY_DELETE_QUERY);
                    preparedStatement.setInt(1, member.getId());
                    preparedStatement.setInt(2, activity.getId());
                    preparedStatement.executeUpdate();
                    break;
                case 'i':
                    preparedStatement = connection.prepareStatement(MEMBER_JOINS_ACTIVITY_INSERT_QUERY);
                    preparedStatement.setInt(1, member.getId());
                    preparedStatement.setInt(2, activity.getId());
                    preparedStatement.executeUpdate();
                    break;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in MEMBER_JOINS_ACTIVITY_REQUEST_TRANSACTIONS) " + ex.getMessage());
        }

    }

    static void vipmemberJoinsSpecialActivityTransactions(char typeOfTransaction, VIPMember vipmember, SpecialActivity specialActivity) {
        try {
            switch (typeOfTransaction) {
                case 'd':
                    preparedStatement = connection.prepareStatement(VIPMEMBER_JOINS_SPECIALACTIVITY_DELETE_QUERY);
                    preparedStatement.setInt(1, vipmember.getId());
                    preparedStatement.setInt(2, specialActivity.getId());
                    preparedStatement.executeUpdate();
                    break;
                case 'i':
                    preparedStatement = connection.prepareStatement(VIPMEMBER_JOINS_SPECIALACTIVITY_INSERT_QUERY);
                    preparedStatement.setInt(1, vipmember.getId());
                    preparedStatement.setInt(2, specialActivity.getId());
                    preparedStatement.executeUpdate();

                    break;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in VIPMEMBER_JOINS_SPECIALACTIVITY_REQUEST_TRANSACTIONS) " + ex.getMessage());
        }
    }

    static void becomeVIPMemberTransaction(char typeOfTransaction, Member member) {
        try {
            switch (typeOfTransaction) {
                case 'd':
                    preparedStatement = connection.prepareStatement(BECOME_VIPMEMBER_DELETE_QUERY);
                    preparedStatement.setInt(1, member.getId());
                    preparedStatement.executeUpdate();
                    break;
                case 'i':
                    preparedStatement = connection.prepareStatement(BECOME_VIPMEMBER_INSERT_QUERY);
                    preparedStatement.setInt(1, member.getId());
                    preparedStatement.executeUpdate();
                    break;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in BECOME_VIPMEMBER_TRANSACTIONS) " + ex.getMessage());
        }
    }

    static void vipmemberReservesCourtTransactions(char typeOfTransaction, VIPMember vipmember, Court court) {
        try {
            switch (typeOfTransaction) {
                case 'd':
                    preparedStatement = connection.prepareStatement(VIPMEMBER_RESERVES_COURT_DELETE_QUERY);
                    preparedStatement.setInt(1, vipmember.getId());
                    preparedStatement.setInt(2, court.getId());
                    preparedStatement.setString(3, court.getSlot());
                    preparedStatement.executeUpdate();
                    break;
                case 'i':
                    preparedStatement = connection.prepareStatement(VIPMEMBER_RESERVES_COURT_INSERT_QUERY);
                    preparedStatement.setInt(1, vipmember.getId());
                    preparedStatement.setInt(2, court.getId());
                    preparedStatement.setString(3, court.getSlot());
                    preparedStatement.executeUpdate();
                    break;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in BECOME_VIPMEMBER_TRANSACTIONS) " + ex.getMessage());
        }
    }

    static ArrayList<Court> getCourtSchedule(Court court) {
        ArrayList<Court> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(COURT_SELECT_QUERY);
            preparedStatement.setInt(1, court.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int c_id_selected = resultSet.getInt("C_ID");
                String name_selected = resultSet.getString("name");
                String slot_selected = resultSet.getString("Slot");
                list.add(new Court(c_id_selected, name_selected, slot_selected));
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in GET_COURT_SCHEDULE) " + ex.getMessage());
        }

        return list;
    }

    static Member getMember(int ID) {
        try {
            preparedStatement = connection.prepareStatement(MEMBER_SELECT_QUERY);
            preparedStatement.setInt(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String password_selected = resultSet.getString("Password");
                String name_selected = resultSet.getString("Name");
                String surName_selected = resultSet.getString("Surname");
                String phoneNumber_selected = resultSet.getString("PhoneNumber");
                char gender_selected = resultSet.getString("Gender").charAt(0);
                Date date_selected = resultSet.getDate("BDay");
                String address_selected = resultSet.getString("Address");
                String email_selected = resultSet.getString("Email");
                return new Member(ID, new Password(password_selected), name_selected, surName_selected, phoneNumber_selected, gender_selected, date_selected, address_selected, email_selected);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in MEMBER_SELECT) " + ex.getMessage());
        }
        return null;
    }

    static Instructor getInstructor(int ID) {
        try {
            preparedStatement = connection.prepareStatement(INSTRUCTOR_SELECT_QUERY);
            preparedStatement.setInt(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String password_selected = resultSet.getString("Password");
                String name_selected = resultSet.getString("Name");
                String surName_selected = resultSet.getString("Surname");
                String phoneNumber_selected = resultSet.getString("PhoneNumber");
                char gender_selected = resultSet.getString("Gender").charAt(0);
                Date date_selected = resultSet.getDate("BDay");
                String address_selected = resultSet.getString("Address");
                String email_selected = resultSet.getString("Email");
                return new Instructor(ID, new Password(password_selected), name_selected, surName_selected, phoneNumber_selected, gender_selected, date_selected, address_selected, email_selected);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in INSTRUCTOR_SELECT) " + ex.getMessage());
        }
        return null;
    }

    static Registrar getRegistrar(int ID) {
        try {
            preparedStatement = connection.prepareStatement(REGISTRAR_SELECT_QUERY);
            preparedStatement.setInt(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String password_selected = resultSet.getString("Password");
                String name_selected = resultSet.getString("Name");
                String surName_selected = resultSet.getString("Surname");
                String phoneNumber_selected = resultSet.getString("PhoneNumber");
                char gender_selected = resultSet.getString("Gender").charAt(0);
                Date date_selected = resultSet.getDate("BDay");
                String address_selected = resultSet.getString("Address");
                String email_selected = resultSet.getString("Email");
                return new Registrar(ID, new Password(password_selected), name_selected, surName_selected, phoneNumber_selected, gender_selected, date_selected, address_selected, email_selected);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in REGISTRAR_SELECT) " + ex.getMessage());
        }
        return null;
    }

    static Scheduler getScheduler(int ID) {
        try {
            preparedStatement = connection.prepareStatement(SCHEDULER_SELECT_QUERY);
            preparedStatement.setInt(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String password_selected = resultSet.getString("Password");
                String name_selected = resultSet.getString("Name");
                String surName_selected = resultSet.getString("Surname");
                String phoneNumber_selected = resultSet.getString("PhoneNumber");
                char gender_selected = resultSet.getString("Gender").charAt(0);
                Date date_selected = resultSet.getDate("BDay");
                String address_selected = resultSet.getString("Address");
                String email_selected = resultSet.getString("Email");
                return new Scheduler(ID, new Password(password_selected), name_selected, surName_selected, phoneNumber_selected, gender_selected, date_selected, address_selected, email_selected);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in SCHEDULER_SELECT) " + ex.getMessage());
        }
        return null;
    }

    static VIPMember getVIPMember(int ID) {
        if (vipMemberExist(ID)) {
            Member m = getMember(ID);
            return new VIPMember(ID, m.getPassword(), m.getName(), m.getSurName(), m.getPhoneNumber(), m.getGender(), m.getBirthDate(), m.getAddress(), m.getEmail());
        }
        return null;
    }

    static boolean vipMemberExist(int ID) {
        try {
            preparedStatement = connection.prepareStatement(VIPMEMBER_SELECT_QUERY);
            preparedStatement.setInt(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (getResultSetSize(resultSet) > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in VIPMEMBER_EXIST) " + ex.getMessage());
        }
        return false;
    }

    static ArrayList<Court> getAllCourts() {
        ArrayList<Court> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(COURTS_SELECT_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id_selected = resultSet.getInt("ID");
                String name_selected = resultSet.getString("name");
                list.add(new Court(id_selected, name_selected, ""));
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in GET_COURTS) " + ex.getMessage());
        }
        return list;
    }

    static ArrayList<Activity> getAllActivity() {
        ArrayList<Activity> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(ACTIVITY_ALL_SELECT_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id_selected = resultSet.getInt("A_ID");
                String name_selected = resultSet.getString("Name");
                int i_id_selected = resultSet.getInt("I_ID");
                Instructor instructor_selected = getInstructor(i_id_selected);
                String slot_selected = resultSet.getString("Slot");
                list.add(new Activity(id_selected, name_selected, instructor_selected, slot_selected));
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in ACTIVITY_SELECT_ALL_QUERY) " + ex.getMessage());
        }
        return list;
    }

    static ArrayList<SpecialActivity> getAllSpecialActivity() {
        ArrayList<SpecialActivity> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SPECIAL_ACTIVITY_ALL_SELECT_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id_selected = resultSet.getInt("A_ID");
                String name_selected = resultSet.getString("Name");
                int i_id_selected = resultSet.getInt("I_ID");
                Instructor instructor_selected = getInstructor(i_id_selected);
                String slot_selected = resultSet.getString("Slot");
                list.add(new SpecialActivity(id_selected, name_selected, instructor_selected, slot_selected));
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in SPECIAL_ACTIVITY_SELECT_ALL_QUERY) " + ex.getMessage());
        }
        return list;
    }

    static ArrayList<Instructor> getAllInstructors() {
        ArrayList<Instructor> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(INSTRUCTOR_ALL_SELECT_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id_selected = resultSet.getInt("ID");
                Instructor instructor_selected = getInstructor(id_selected);
                list.add(instructor_selected);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in INSTRUCTOR_SELECT_ALL_QUERY) " + ex.getMessage());
        }
        return list;
    }

    static ArrayList<Activity> getMemberActivities(Member member) {
        ArrayList<Activity> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(MEMBER_JOINS_ACTIVITY_SELECT_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int m_id_selected = resultSet.getInt("M_ID");
                int a_id_selected = resultSet.getInt("A_ID");
                if (m_id_selected == member.getId()) {
                    Activity a = getActivity(a_id_selected);
                    list.add(a);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in MEMBER_ACTIVITIES_SELECT_QUERY) " + ex.getMessage());
        }
        return list;
    }

    static ArrayList<SpecialActivity> getVIPMemberSpecialActivities(VIPMember vipmember) {
        ArrayList<SpecialActivity> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(VIPMEMBER_JOINS_SPECIAL_ACTIVITY_SELECT_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int vm_id_selected = resultSet.getInt("VM_ID");
                int sa_id_selected = resultSet.getInt("SA_ID");
                if (vm_id_selected == vipmember.getId()) {
                    SpecialActivity sa = getSpecialActivity(sa_id_selected);
                    list.add(sa);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in VIPMEMBER_SPECIAL_ACTIVITIES_SELECT_QUERY) " + ex.getMessage());
        }
        return list;
    }

    private static Activity getActivity(int id) {
        try {
            preparedStatement = connection.prepareStatement(ACTIVITY_SELECT_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id_selected = resultSet.getInt("A_ID");
                String name_selected = resultSet.getString("Name");
                int i_id_selected = resultSet.getInt("I_ID");
                Instructor instructor_selected = getInstructor(i_id_selected);
                String slot_selected = resultSet.getString("Slot");
                return new Activity(id_selected, name_selected, instructor_selected, slot_selected);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in ACTIVITY_SELECT_QUERY) " + ex.getMessage());
        }
        return null;
    }
    
    private static SpecialActivity getSpecialActivity(int id) {
        try {
            preparedStatement = connection.prepareStatement(ACTIVITY_SELECT_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id_selected = resultSet.getInt("A_ID");
                String name_selected = resultSet.getString("Name");
                int i_id_selected = resultSet.getInt("I_ID");
                Instructor instructor_selected = getInstructor(i_id_selected);
                String slot_selected = resultSet.getString("Slot");
                return new SpecialActivity(id_selected, name_selected, instructor_selected, slot_selected);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in SPECIALACTIVITY_SELECT_QUERY) " + ex.getMessage());
        }
        return null;
    }

    static int getNumberOfMembers() {
        try {
            preparedStatement = connection.prepareStatement(MEMBER_COUNT_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("COUNT(*)");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in MEMBER_COUNT_QUERY");
        }
        return -1;
    }

    static int getNumberOfInstructors() {
        try {
            preparedStatement = connection.prepareStatement(INSTRUCTOR_COUNT_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("COUNT(*)");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in INSTRUCTOR_COUNT_QUERY");
        }
        return -1;
    }

    static int getNumberOfSchedulers() {
        try {
            preparedStatement = connection.prepareStatement(SCHEDULER_COUNT_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("COUNT(*)");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in SCHEDULER_COUNT_QUERY");
        }
        return -1;
    }

    static boolean isMemberExistent(Member m) {
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(IS_MEMBER_EXIST_QUERY);
            preparedStatement.setString(1, m.getPhoneNumber());
            preparedStatement.setString(2, m.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt("COUNT(*)");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in IS_MEMBER_EXIST_QUERY");
        }
        return result >= 1;
    }

    static boolean isInstructorExistent(Instructor i) {
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(IS_INSTRUCTOR_EXIST_QUERY);
            preparedStatement.setString(1, i.getPhoneNumber());
            preparedStatement.setString(2, i.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt("COUNT(*)");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in IS_INSTRUCTOR_EXIST_QUERY");
        }
        return result >= 1;
    }

    static boolean isSchedulerExistent(Scheduler s) {
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(IS_SCHEDULER_EXIST_QUERY);
            preparedStatement.setString(1, s.getPhoneNumber());
            preparedStatement.setString(2, s.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt("COUNT(*)");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in IS_SCHEDULER_EXIST_QUERY");
        }
        return result >= 1;
    }

    static boolean checkPasswordOfMember(int id, String password) {
        String password_selected = "";
        try {
            preparedStatement = connection.prepareStatement(MEMBER_CHECK_PASSWORD_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                password_selected = resultSet.getString("Password");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in MEMBER_CHECK_PASSWORD_QUERY");
        }
        return password.equals(password_selected);
    }

    static boolean checkPasswordOfScheduler(int id, String password) {
        String password_selected = "";
        try {
            preparedStatement = connection.prepareStatement(SCHEDULER_CHECK_PASSWORD_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                password_selected = resultSet.getString("Password");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in SCHEDULER_CHECK_PASSWORD_QUERY");
        }
        return password.equals(password_selected);
    }

    static boolean checkPasswordOfInstructor(int id, String password) {
        String password_selected = "";
        try {
            preparedStatement = connection.prepareStatement(INSTRUCTOR_CHECK_PASSWORD_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                password_selected = resultSet.getString("Password");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in INSTRUCTOR_CHECK_PASSWORD_QUERY");
        }
        return password.equals(password_selected);
    }

    static boolean checkPasswordOfRegistrar(int id, String password) {
        String password_selected = "";
        try {
            preparedStatement = connection.prepareStatement(REGISTRAR_CHECK_PASSWORD_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                password_selected = resultSet.getString("Password");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in REGISTRAR_CHECK_PASSWORD_QUERY");
        }
        return password.equals(password_selected);
    }

    static boolean isActivityRequestExist(ActivityRequest ar) {
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(ACTIVITY_REQUEST_EXIST_QUERY);
            preparedStatement.setInt(1, ar.getFrom());
            preparedStatement.setInt(2, ar.getTo());
            preparedStatement.setInt(3, ar.getSubject());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt("COUNT(*)");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in ACTIVITY_REQUEST_EXIST_QUERY");
        }
        return result >= 1;
    }

    static boolean isMembershipRequestExist(MembershipRequest mr) {
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(MEMBERSHIP_REQUEST_EXIST_QUERY);
            preparedStatement.setString(1, mr.getName());
            preparedStatement.setString(2, mr.getSurname());
            preparedStatement.setString(3, mr.getPhoneNumber());
            preparedStatement.setString(4, mr.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt("COUNT(*)");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in MEMBERSHIP_REQUEST_EXIST_QUERY");
        }
        return result >= 1;
    }

    static boolean isSpecialActivityRequestExist(SpecialActivityRequest sar) {
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(SPECIAL_ACTIVITY_REQUEST_EXIST_QUERY);
            preparedStatement.setInt(1, sar.getFrom());
            preparedStatement.setInt(2, sar.getTo());
            preparedStatement.setInt(3, sar.getSubject());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt("COUNT(*)");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in SPECIAL_ACTIVITY_REQUEST_EXIST_QUERY");
        }
        return result >= 1;
    }
    
    static boolean isVIPRequestExist(VIPRequest vr) {
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(VIP_REQUEST_EXIST_QUERY);
            preparedStatement.setInt(1, vr.getFrom());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt("COUNT(*)");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in VIP_REQUEST_EXIST_QUERY");
        }
        return result >= 1;
    }

    static boolean isMemberJoinedActivity(Member m, Activity a) {
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(MEMBER_JOINS_ACTIVITY_EXIST_QUERY);
            preparedStatement.setInt(1, m.getId());
            preparedStatement.setInt(2, a.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt("COUNT(*)");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in MEMBER_JOINS_ACTIVITY_EXIST_QUERY");
        }
        return result >= 1;
    }

    static boolean isVIPMemberJoinedSpecialActivity(VIPMember vm, SpecialActivity sa) {
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(VIPMEMBER_JOINS_SPECIALACTIVITY_EXIST_QUERY);
            preparedStatement.setInt(1, vm.getId());
            preparedStatement.setInt(2, sa.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt("COUNT(*)");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException (thrown in VIPMEMBER_JOINS_SPECIALACTIVITY_EXIST_QUERY");
        }
        return result >= 1;
    }
}
