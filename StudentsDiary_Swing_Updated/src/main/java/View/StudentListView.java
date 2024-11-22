package View;

import Model.Student;
import java.util.List;

/**
 * The StudentListView class handles the display of student details and their grades in the console.
 * It prints a list of all students with their respective information.
 *
 * @author Adrian Reszka
 * @version 1.0
 */
public class StudentListView {

    /**
     * Prints a list of all students and their grades.
     *
     * @param students The list of students to display.
     */
    public void printStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println("ID: " + student.getId() + ", Name: " + student.getName() + ", Surname: " + student.getSurname());
            student.printGrades();
        }
    }
}