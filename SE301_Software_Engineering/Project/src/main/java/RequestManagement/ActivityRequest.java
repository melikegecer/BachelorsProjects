package RequestManagement;

public class ActivityRequest extends Request {

    /**
     * Constructs an activity request with the relative parameters
     *
     * @param id: holds the unique id of the request kept in database
     * @param from: holds the id of a user who send the request
     * @param fromName: holds the name of the requester
     * @param to: if a request is an activity request or a special activity request this holds related instructor's id, otherwise this value will be 0.
     * @param subject: if a request is an activity request or a special activity request this holds its id, otherwise this value will be 0.
     */
    public ActivityRequest(int id, int from, String fromName, int to, int subject) {
        super(id, from, fromName, to, subject);
    }

    /**
     *
     * @return a string representation of the values of this Activity Request object's id, fromId, fromName, toId, activityId fields.
     */
    @Override
    public String toString() {
        return "Request: ID-" + this.getId() + " FROM: ID-" + this.getFrom() + " Name-" + this.getFromName() + " To: ID-" + this.getTo() + " Subject: ID-";
    }

    /**
     * Checks whether two Activity Request objects have equal values.
     *
     * @param o: given object to compare
     * @return result of the comparison
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof ActivityRequest) {
            ActivityRequest ar = (ActivityRequest) o;
            if (ar.getId() == this.getId() && ar.getFrom() == this.getFrom() && ar.getFromName().equals(this.getFromName()) && ar.getTo() == this.getTo() && ar.getSubject() == this.getSubject()) {
                return true;
            }
        }
        return false;
    }

}
