package Model;

/**
 * Exception thrown when a student with a specific ID cannot be found in the student list.
 *
 * @author Adrian Reszka
 * @version 1.0
 */
public class StudentNotFoundException extends Exception {

    /**
     * Constructs a new StudentNotFoundException with a message indicating the ID of the student that was not found.
     */
    public StudentNotFoundException() {
        super("Student with this ID does not exist.");
    }
}