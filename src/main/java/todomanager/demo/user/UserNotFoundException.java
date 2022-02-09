package todomanager.demo.user;

/**
 * @author Mert Batuhan UNVERDI
 * @since 25.01.2022
 */
public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String message) {
        super(message);
    }
}
