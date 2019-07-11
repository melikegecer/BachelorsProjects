package RequestManagement;

public class SpecialActivityRequest extends Request {

    /**
     * Constructs a special activity request with the relative parameters
     *
     * @param id: holds the unique id of the request kept in database
     * @param from: holds the id of a user who send the request
     * @param fromName: holds the name of the requester
     * @param to: if a request is an activity request or a special activity request this holds related instructor's id, otherwise this value will be 0.
     * @param subject: if a request is an activity request or a special activity request this holds its id, otherwise this value will be 0.
     */
    public SpecialActivityRequest(int id, int from, String fromName, int to, int subject) {
        super(id, from, fromName, to, subject);
    }

    /**
     *
     * @return a string representation of the values of this Special Activity Request object's id, fromId, fromName, toID fields.
     */
    @Override
    public String toString() {
        return "Request: ID-" + this.getId() + " FROM: ID-" + this.getFrom() + " Name-" + this.getFromName() + " To: ID-" + this.getTo();
    }

    /**
     * Checks whether two Special Activity Request objects have equal values.
     *
     * @param o: given object to compare
     * @return result of the comparison
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof SpecialActivityRequest) {
            SpecialActivityRequest sar = (SpecialActivityRequest) o;
            if (sar.getId() == this.getId() && sar.getFrom() == this.getFrom() && sar.getFromName().equals(this.getFromName()) && sar.getTo() == this.getTo() && sar.getSubject() == this.getSubject()) {
                return true;
            }
        }
        return false;
    }

}
