package RequestManagement;

public abstract class Request {

    private int id;
    private int from;
    private String fromName;
    private int to;
    private int subject;

    /**
     * Constructs a new request object with the relative parameters
     *
     * @param id: holds the unique id of the request kept in database
     * @param from: holds the id of a user who send the request
     * @param fromName: holds the name of the requester
     * @param to: if a request is an activity request or a special activity request this holds related instructor's id, otherwise this value will be 0.
     * @param subject: if a request is an activity request or a special activity request this holds its id, otherwise this value will be 0.
     */
    protected Request(int id, int from, String fromName, int to, int subject) {
        this.id = id;
        this.from = from;
        this.fromName = fromName;
        this.to = to;
        this.subject = subject;
    }

    /**
     * @return id of the request
     */
    public int getId() {
        return id;
    }

    /**
     * @return id of the requester
     */
    public int getFrom() {
        return from;
    }

    /**
     * @return name of the requester
     */
    public String getFromName() {
        return fromName;
    }

    /**
     * @return the id of the receiver or zero (0)
     */
    public int getTo() {
        return to;
    }

    /**
     * @return an activity id or a special activity id or zero (0)
     */
    public int getSubject() {
        return subject;
    }

}
