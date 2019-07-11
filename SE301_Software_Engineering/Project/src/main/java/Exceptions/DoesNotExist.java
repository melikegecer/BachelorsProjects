package Exceptions;

public class DoesNotExist extends Exception {

    private String message;

    public DoesNotExist(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

}
