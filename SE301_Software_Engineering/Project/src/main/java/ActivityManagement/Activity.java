package ActivityManagement;

import UserManagement.Instructor;

public class Activity {

    private int id;
    private String name;
    private Instructor instructor;
    private String slot;

    /**
     * Constructs a new activity object with the relative parameters
     *
     * @param id: holds the unique id of the activity kept in database
     * @param name: holds the name of the activity
     * @param instructor: holds the instructor of the activity
     * @param slot: holds the slot of the activity
     */
    public Activity(int id, String name, Instructor instructor, String slot) {
        this.id = id;
        this.name = name;
        this.instructor = instructor;
        this.slot = slot;
    }

    /**
     *
     * @return slot of the activity
     */
    public String getSlot() {
        return slot;
    }

    /**
     *
     * @return id of the activity
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return name of the activity
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return the instructor of the activity
     */
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     *
     * @return a string representation of the values of this Activity object's
     * id, name, slot, instructor fields.
     */
    @Override
    public String toString() {
        return "ID: " + id + " name: " + this.name + " slot: " + slot + " instructor: " + instructor.toString();
    }

    /**
     * Checks whether two Activity objects have equal values.
     *
     * @param o: given object to compare
     * @return result of the comparison
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Activity) {
            Activity a = (Activity) o;
            if (a.getId() == this.getId() && a.getName().equals(this.getName()) && a.getSlot().equals(this.getSlot()) && a.getInstructor().equals(this.instructor)) {
                return true;
            }
        }
        return false;
    }

}
