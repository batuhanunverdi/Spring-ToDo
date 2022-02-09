package todomanager.demo.todo;

/**
 * @author Mert Batuhan UNVERDI
 * @since 26.01.2022
 */
public class TodoNotFoundException extends Throwable{
    public TodoNotFoundException(String message) {
        super(message);
    }
}
