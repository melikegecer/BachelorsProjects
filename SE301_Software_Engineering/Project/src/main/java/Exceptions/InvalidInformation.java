package Exceptions;

public class InvalidInformation extends Exception {

    private String message;

    public InvalidInformation(String message) {
        this.message = message;
    }
    
    public String toString(){
        return message;
    }

}
