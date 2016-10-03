package service;

/**
 *
 * @author savin
 */
public class AccessDeniedException extends Exception {

    private String message;

    public AccessDeniedException(String message) {
        this.message = message;

    }

    @Override
    public String toString() {
        return "AccessDeniedException: " + message;
    }

}
