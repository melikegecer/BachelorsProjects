package UserManagement;

public class Court {

    private int id;
    private String name;
    private String slot;

    /**
     * Constructs a new instructor object with the relative parameters
     *
     * @param id: holds the unique id of the court kept in database
     * @param name: holds the name of the court
     * @param slot: holds the slot of the court
     */
    public Court(int id, String name, String slot) {
        this.id = id;
        this.name = name;
        this.slot = slot;
    }

    /**
     *
     * @return a string representation of the values of this Court object s id, name fields.
     */
    @Override
    public String toString() {
        return "Court ID=" + getId() + ", Court Name=" + getName();
    }

    /**
     * Checks whether two Court objects have equal values.
     *
     * @param o: given object to compare
     * @return result of the comparison
     */
    @Override
    public boolean equals(Object o) {
        Court c;
        if (o instanceof Court) {
            c = (Court) o;
        } else {
            return false;
        }

        return this.getId() == c.getId();
    }

    /**
     * @return the id of the court
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name of the court
     */
    public String getName() {
        return name;
    }

    /**
     * @return the slot of the court
     */
    public String getSlot() {
        return slot;
    }
}
