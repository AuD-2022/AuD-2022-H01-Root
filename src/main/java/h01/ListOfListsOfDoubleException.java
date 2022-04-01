package h01;

/**
 * Exception class that indicates that an exception occurred while processing a list of lists.
 */
public class ListOfListsOfDoubleException extends RuntimeException {

    /**
     * Creates a new {@link ListOfListsOfDoubleException} object with the supplied message.
     *
     * @param message the exception message
     */
    public ListOfListsOfDoubleException(String message) {
        super(message);
    }
}
