package Model;

/**
 * Exception thrown when an invalid grade format is encountered.
 *
 * @author Adrian Reszka
 * @version 1.0
 */
public class InvalidGradeFormatException extends Exception {

    /**
     * Constructs a new InvalidGradeFormatException with a message indicating the invalid grade input.
     */
    public InvalidGradeFormatException() {
        super("Invalid grade format! Please use a valid number with a dot or comma.");
    }
}