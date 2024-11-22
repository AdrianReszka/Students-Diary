package Model;

/**
 * Exception thrown when adding a grade to student with invalid value.
 *
 * @author Adrian Reszka
 * @version 1.0
 */
public class InvalidGradeValueException extends Exception {

    /**
     * Constructs a new InvalidGradeValueException with a message indicating the invalid value.
     */
    public InvalidGradeValueException() {
        super("Invalid grade value!");
    }
}