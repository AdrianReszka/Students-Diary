package Model;

/**
 * Exception thrown when student with specified ID already exists.
 *
 * @author Adrian Reszka
 * @version 1.0
 */
public class StudentAlreadyExistsException extends Exception {

    /**
     * Constructs a new StudentAlreadyExistsException with a message indicating the ID of the student in the list.
     */
    public StudentAlreadyExistsException() {
        super("Student with this ID already exists.");
    }
}