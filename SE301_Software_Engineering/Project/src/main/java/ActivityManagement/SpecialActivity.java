package ActivityManagement;

import UserManagement.Instructor;

public class SpecialActivity extends Activity {

    /**
     * Constructs a new special activity object with the relative parameters
     *
     * @param id: holds the unique id of the special activity kept in database
     * @param name: holds the name of the special activity
     * @param instructor: holds the instructor of the special activity
     * @param slot: holds the slot of the special activity
     */
    public SpecialActivity(int id, String name, Instructor instructor, String slot) {
        super(id, name, instructor, slot);
    }

}
