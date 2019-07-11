package RequestManagement;

public class VIPRequest extends Request {

    /**
     * Constructs a vip request with the relative parameters
     *
     * @param id: holds the unique id of the request kept in database
     * @param from: holds the id of a user who send the request
     * @param fromName: holds the name of the requester
     */
    public VIPRequest(int id, int from, String fromName) {
        super(id, from, fromName, 0, 0);
    }

     /**
     *
     * @return a string representation of the values of this VIP Request object's id, fromId, fromName fields.
     */
    @Override
    public String toString() {
        return "Request: ID-" + this.getId() + " FROM: ID-" + this.getFrom() + " Name-" + this.getFromName();
    }

    /**
     * Checks whether two VIP Request objects have equal values.
     *
     * @param o: given object to compare
     * @return result of the comparison
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof VIPRequest) {
            VIPRequest vipr = (VIPRequest) o;
            if (vipr.getId() == this.getId() && vipr.getFrom() == this.getId() && vipr.getFromName().equals(this.getFromName())) {
                return true;
            }
        }
        return false;
    }
}
