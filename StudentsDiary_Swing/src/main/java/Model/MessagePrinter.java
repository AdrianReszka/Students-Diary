package Model;

/**
 * MessagePrinter class used to print error and success messages in the catch statement
 * and in controller classes
 *
 * @author Adrian Reszka
 * @version 1.0
 */

public class MessagePrinter {

    /**
     * Method responsible for printing error messages for specified exception
     *
     * @param message the message to print
     */
    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    /**
     * Method responsible for printing the success message
     */
    public void printSuccessMessage() {
        System.out.println("Operation successful!");
    }
}