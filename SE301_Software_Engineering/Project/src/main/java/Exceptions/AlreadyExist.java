package Exceptions;

public class AlreadyExist extends Exception {

    private String message;

    public AlreadyExist(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

}
