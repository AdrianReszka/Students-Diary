package Model;

/**
 * Exception thrown when an invalid index is provided for accessing a student's grades.
 *
 * @author Adrian Reszka
 * @version 1.0
 */
public class InvalidGradeIndexException extends Exception {

    /**
     * Constructs a new InvalidGradeIndexException with a message indicating the invalid index.
     */
    public InvalidGradeIndexException() {
        super("Invalid grade index!");
    }
}