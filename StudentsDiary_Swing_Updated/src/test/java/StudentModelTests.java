import Model.Student;
import Model.Grade;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * This class contains unit tests for the {@link Student} model class.
 * It tests the functionality of adding, removing, and printing grades,
 * as well as handling edge cases like invalid indices and empty grade lists.
 */
class StudentModelTests {
    private Student student;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    /**
     * Sets up the testing environment before each test.
     * Initializes a {@link Student} object and redirects system output for testing purposes.
     */
    @BeforeEach
    void setUp() {
        student = new Student("Adrian", "Reszka", 1);
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Cleans up the testing environment after each test.
     * Restores the original system output.
     */
    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    /**
     * Tests the addition of grades to a student.
     * Verifies that the grade details (value, teacher, subject) are correctly stored.
     *
     * @param value   The value of the grade.
     * @param teacher The teacher associated with the grade.
     * @param subject The subject for which the grade was given.
     */
    @ParameterizedTest
    @CsvSource({ "4.5, Nowak, KUC", "3.0, Kowalski, TUC", "5.0, Kaminski, SMIW"})
    void testAddGrade(double value, String teacher, String subject) {
        student.addGrade(value, teacher, subject);
        List<Grade> grades = student.getGrades();

        assertEquals(1, grades.size());
        Grade grade = grades.get(0);
        assertEquals(value, grade.getValue());
        assertEquals(teacher, grade.getTeacher());
        assertEquals(subject, grade.getSubject());
    }

    /**
     * Tests the removal of a grade by a valid index.
     * Ensures the grade is successfully removed and the grade list becomes empty.
     *
     * @param value          The value of the grade to be added before removal.
     * @param teacher        The teacher associated with the grade.
     * @param subject        The subject of the grade.
     * @param indexToRemove  The index of the grade to remove.
     */
    @ParameterizedTest
    @CsvSource({ "4.5, Kowalski, TUC, 0", "3.5, Nowak, KUC, 0"})
    void testRemoveGradeValidIndex(double value, String teacher, String subject, int indexToRemove) {
        student.addGrade(value, teacher, subject);
        assertTrue(student.removeGrade(indexToRemove));
        assertTrue(student.getGrades().isEmpty());
    }

    /**
     * Tests the removal of a grade using an invalid index.
     * Ensures that the operation fails and returns {@code false}.
     *
     * @param indexToRemove The invalid index of the grade to remove.
     */
    @ParameterizedTest
    @CsvSource({"0", "-1", "1"})
    void testRemoveGradeInvalidIndex(int indexToRemove) {
        assertFalse(student.removeGrade(indexToRemove));
    }

    /**
     * Tests the behavior of the {@link Student#printGrades()} method when the student has no grades.
     * Verifies that the appropriate message is printed.
     */
    @Test
    void testPrintGradesWhenEmpty() {
        student.printGrades();
        String expectedOutput = "Student Adrian Reszka has no grades." + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    /**
     * Tests the behavior of the {@link Student#printGrades()} method when the student has grades.
     * Verifies that the grades are printed in the correct format, including teacher, subject, and category.
     *
     * @param value    The value of the grade.
     * @param teacher  The teacher associated with the grade.
     * @param subject  The subject for which the grade was given.
     * @param category The category of the grade (e.g., GOOD, AVERAGE).
     */
    @ParameterizedTest
    @CsvSource({"4.5, Nowak, KUC, GOOD", "3.0, Kowalski, TUC, AVERAGE"})
    void testPrintGradesWhenNotEmpty(double value, String teacher, String subject, String category) {
        student.addGrade(value, teacher, subject);
        student.printGrades();

        String expectedOutput = "Grades for Adrian Reszka:" + System.lineSeparator() +
                "1. " + String.format("%.2f", value).replace('.', ',') +
                " (Teacher: " + teacher + ", Subject: " + subject + ", Category: " + category + ")" +
                System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }
}
