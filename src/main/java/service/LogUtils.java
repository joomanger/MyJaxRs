package service;

/**
 *
 * @author savin
 */
public final class LogUtils {

    private LogUtils() {
    }

    public static synchronized String getClassName() {
        try {
            throw new RuntimeException();
        } catch (RuntimeException ex) {
            return ex.getStackTrace()[1].getClassName();
        }
    }
    
}
