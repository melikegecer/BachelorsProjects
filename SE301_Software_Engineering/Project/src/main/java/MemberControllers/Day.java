package MemberControllers;

public class Day {

    private String slot;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday;
    private String sunday;

    public Day(String slot, String monday, String tuesday, String wednesday, String thursday, String friday, String saturday, String sunday) {
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    public Day(String[] days) {
        slot=days[0];
        
        if (days[1] == null) {
            monday = "";
        } else {
            monday = days[1];
        }

        if (days[2] == null) {
            tuesday = "";
        } else {
            tuesday = days[2];
        }

        if (days[3] == null) {
            wednesday = "";
        } else {
            wednesday = days[3];
        }

        if (days[4] == null) {
            thursday = "";
        } else {
            thursday = days[4];
        }

        if (days[5] == null) {
            friday = "";
        } else {
            friday = days[5];
        }

        if (days[6] == null) {
            saturday = "";
        } else {
            saturday = days[6];
        }

        if (days[7] == null) {
            sunday = "";
        } else {
            sunday = days[7];
        }

    }

    /**
     * This method returns slot
     *
     * @return slot
     */
    public String getSlot() {
        return slot;
    }

    /**
     * This method sets the slot
     *
     * @param slot
     */
    public void setSlot(String slot) {
        this.slot = slot;
    }

    /**
     * This method returns monday
     *
     * @return monday
     */
    public String getMonday() {
        return monday;
    }

    /**
     * This method sets monday
     *
     * @param monday
     */
    public void setMonday(String monday) {
        this.monday = monday;
    }

    /**
     * This method returns tuesday
     *
     * @return tuesday
     */
    public String getTuesday() {
        return tuesday;
    }

    /**
     * This method sets tuesday
     *
     * @param tuesday
     */
    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    /**
     * This method returns wednesday
     *
     * @return wednesday
     */
    public String getWednesday() {
        return wednesday;
    }

    /**
     * This method sets wednesday
     *
     * @param wednesday
     */
    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    /**
     * This method returns thursday
     *
     * @return thursday
     */
    public String getThursday() {
        return thursday;
    }

    /**
     * This method sets thursday
     *
     * @param thursday
     */
    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    /**
     * This method returns friday
     *
     * @return friday
     */
    public String getFriday() {
        return friday;
    }

    /**
     * This method sets friday
     *
     * @param friday
     */
    public void setFriday(String friday) {
        this.friday = friday;
    }

    /**
     * This method returns saturday
     *
     * @return saturday
     */
    public String getSaturday() {
        return saturday;
    }

    /**
     * This method sets saturday
     *
     * @param saturday
     */
    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    /**
     * This method returns sunday
     *
     * @return sunday
     */
    public String getSunday() {
        return sunday;
    }

    /**
     * This method sets sunday
     *
     * @param sunday
     */
    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

}
