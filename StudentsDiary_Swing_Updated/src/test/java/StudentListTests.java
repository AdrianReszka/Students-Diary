import Model.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the {@link StudentList} model class.
 * It tests various functionalities including adding, removing, editing,
 * and searching students and their grades. It also checks for exceptions
 * in edge cases like invalid grade values, formats, and indices.
 */
class StudentListTests {
    private StudentList studentList;

    /**
     * Sets up the testing environment before each test.
     * Initializes a new {@link StudentList} object.
     */
    @BeforeEach
    void setUp() {
        studentList = new StudentList();
    }

    /**
     * Tests that an {@link InvalidGradeValueException} is thrown when a grade with an invalid value is added.
     *
     * @param id          Student ID.
     * @param name        Student name.
     * @param surname     Student surname.
     * @param gradeIndex  Grade index (unused in this test).
     * @param gradeInput  Invalid grade value as input.
     * @param teacher     Teacher name.
     * @param subject     Subject name.
     */
    @ParameterizedTest
    @CsvSource({"1, Adrian, Reszka, 0, 0.5, Nowak, SMIW"})
    void testInvalidGradeValueExceptionWasThrown(int id, String name, String surname, int gradeIndex, String gradeInput, String teacher, String subject) {
        try {
            studentList.addStudent(id, name, surname);
            assertThrows(InvalidGradeValueException.class, () -> {
                studentList.addGradeToStudent(id, gradeInput, teacher, subject);
            });
        } catch (StudentAlreadyExistsException e) {
            fail("An exception should be thrown when adding a grade with an incorrect value.");
        }
    }

    /**
     * Tests that no exception is thrown when a valid grade value is added.
     *
     * @param id          Student ID.
     * @param name        Student name.
     * @param surname     Student surname.
     * @param gradeIndex  Grade index (unused in this test).
     * @param gradeInput  Valid grade value as input.
     * @param teacher     Teacher name.
     * @param subject     Subject name.
     */
    @ParameterizedTest
    @CsvSource({"1, Adrian, Reszka, 0, 3.5, Nowak, KUC"})
    void testInvalidGradeValueExceptionWasNotThrown(int id, String name, String surname, int gradeIndex, String gradeInput, String teacher, String subject) {
        try {
            studentList.addStudent(id, name, surname);
            assertDoesNotThrow(() -> {
                studentList.addGradeToStudent(id, gradeInput, teacher, subject);
            });
        } catch (StudentAlreadyExistsException e) {
            fail("An exception should not be thrown when adding a grade with a correct value.");
        }
    }

    /**
     * Tests that an {@link InvalidGradeFormatException} is thrown when a grade with an invalid format is added.
     *
     * @param id          Student ID.
     * @param name        Student name.
     * @param surname     Student surname.
     * @param gradeInput  Invalid grade format as input.
     * @param teacher     Teacher name.
     * @param subject     Subject name.
     */
    @ParameterizedTest
    @CsvSource({"1, Adrian, Reszka, wrongGradeFormat, Nowak, TUC"})
    void testInvalidGradeFormatExceptionWasThrown(int id, String name, String surname, String gradeInput, String teacher, String subject) {
        try {
            studentList.addStudent(id, name, surname);
            assertThrows(InvalidGradeFormatException.class, () ->
                    studentList.addGradeToStudent(id, gradeInput, teacher, subject)
            );
        } catch (StudentAlreadyExistsException e) {
            fail("An exception should be thrown when adding a grade with an invalid format.");
        }
    }

    /**
     * Tests that no exception is thrown when a grade with a valid format is added.
     *
     * @param id          Student ID.
     * @param name        Student name.
     * @param surname     Student surname.
     * @param gradeInput  Valid grade format as input.
     * @param teacher     Teacher name.
     * @param subject     Subject name.
     */
    @ParameterizedTest
    @CsvSource({"1, Adrian, Reszka, 5.0, Nowak, TUC"})
    void testInvalidGradeFormatExceptionWasNotThrown(int id, String name, String surname, String gradeInput, String teacher, String subject) {
        try {
            studentList.addStudent(id, name, surname);
            assertDoesNotThrow(() ->
                    studentList.addGradeToStudent(id, gradeInput, teacher, subject)
            );
        } catch (StudentAlreadyExistsException e) {
            fail("An exception should not be thrown when adding a grade with a valid format.");
        }
    }

    /**
     * Tests that an {@link InvalidGradeIndexException} is thrown when trying to remove a grade at an invalid index.
     *
     * @param id          Student ID.
     * @param name        Student name.
     * @param surname     Student surname.
     * @param gradeIndex  Invalid grade index.
     */
    @ParameterizedTest
    @CsvSource({"1, Adrian, Reszka, 0"})
    void testInvalidGradeIndexExceptionWasThrown(int id, String name, String surname, int gradeIndex) {
        try {
            studentList.addStudent(id, name, surname);
            assertThrows(InvalidGradeIndexException.class, () -> {
                studentList.removeGradeFromStudent(id, gradeIndex);
            });
        } catch (StudentAlreadyExistsException e) {
            fail("An exception should be thrown when trying to delete a grade with an invalid index.");
        }
    }

    /**
     * Tests that no exception is thrown when removing a grade at a valid index.
     *
     * @param id          Student ID.
     * @param name        Student name.
     * @param surname     Student surname.
     * @param gradeIndex  Valid grade index.
     * @param gradeInput  Valid grade value.
     * @param teacher     Teacher name.
     * @param subject     Subject name.
     */
    @ParameterizedTest
    @CsvSource({"1, Adrian, Reszka, 0, 4.0, Nowak, KUC"})
    void testInvalidGradeIndexExceptionWasNotThrown(int id, String name, String surname, int gradeIndex, String gradeInput, String teacher, String subject) {
        try {
            studentList.addStudent(id, name, surname);
            studentList.addGradeToStudent(id, gradeInput, teacher, subject);
            assertDoesNotThrow(() -> {
                studentList.removeGradeFromStudent(id, gradeIndex);
            });
        } catch (StudentAlreadyExistsException | StudentNotFoundException | InvalidGradeValueException | InvalidGradeFormatException e) {
            fail("An exception should not be thrown when deleting a grade with a valid index.");
        }
    }

    /**
     * Tests that a {@link StudentNotFoundException} is thrown when trying to remove a student that does not exist.
     */
    @ParameterizedTest
    @CsvSource({"1, Adrian, Reszka"})
    void testStudentNotFoundExceptionWasThrown(int id, String name, String surname) {
        try {
            studentList.addStudent(id, name, surname);
            assertThrows(StudentNotFoundException.class, () -> studentList.removeStudent(2));
        } catch (StudentAlreadyExistsException e) {
            fail("An exception should be thrown when removing a non-existent student.");
        }
    }

    /**
     * Tests that no exception is thrown when removing an existing student.
     */
    @ParameterizedTest
    @CsvSource({"1, Adrian, Reszka"})
    void testStudentNotFoundExceptionWasNotThrown(int id, String name, String surname) {
        try {
            studentList.addStudent(id, name, surname);
            assertDoesNotThrow(() -> studentList.removeStudent(id));
        } catch (StudentAlreadyExistsException e) {
            fail("An exception should not be thrown when removing an existing student.");
        }
    }

    /**
     * Tests that a {@link StudentAlreadyExistsException} is thrown when adding a student with a duplicate ID.
     */
    @ParameterizedTest
    @CsvSource({"1, Adrian, Reszka, 1, Karol, Ziaja"})
    void testStudentAlreadyExistsExceptionWasThrown(int id1, String name1, String surname1, int id2, String name2, String surname2) {
        assertThrows(StudentAlreadyExistsException.class, () -> {
            studentList.addStudent(id1, name1, surname1);
            studentList.addStudent(id2, name2, surname2);
        });
    }

    /**
     * Tests that no exception is thrown when adding two students with unique IDs.
     */
    @ParameterizedTest
    @CsvSource({"1, Adrian, Reszka, 2, Karol, Ziaja"})
    void testStudentAlreadyExistsExceptionWasNotThrown(int id1, String name1, String surname1, int id2, String name2, String surname2) {
        assertDoesNotThrow(() -> {
            studentList.addStudent(id1, name1, surname1);
            studentList.addStudent(id2, name2, surname2);
        });
    }

    /**
     * Tests adding a student with valid data.
     */
    @ParameterizedTest
    @CsvSource({"1, Adrian, Reszka", "2, Karol, Ziaja", "3, Blazej, Sztefka"})
    void testAddStudent(int id, String name, String surname) {
        try {
            studentList.addStudent(id, name, surname);
            assertEquals(1, studentList.getStudents().size());
            Student student = studentList.getStudents().get(0);
            assertEquals(id, student.getId());
            assertEquals(name, student.getName());
            assertEquals(surname, student.getSurname());
        } catch (StudentAlreadyExistsException e) {
            fail("An exception should not be thrown when adding a unique student.");
        }
    }

    /**
     * Tests removing a student with valid data.
     */
    @ParameterizedTest
    @CsvSource({"1, Adrian, Reszka", "2, Karol, Ziaja"})
    void testRemoveStudent(int id, String name, String surname) {
        try {
            studentList.addStudent(id, name, surname);
            studentList.removeStudent(id);
            assertTrue(studentList.getStudents().isEmpty());
        } catch (StudentAlreadyExistsException | StudentNotFoundException e) {
            fail("An exception should not be thrown when removing an existing student.");
        }
    }

    /**
     * Tests editing a student's data.
     */
    @ParameterizedTest
    @CsvSource({"1, Adrian, Reszka, Karol, Ziaja", "2, Michal, Kaminski, Blazej, Sztefka"})
    void testEditStudentData(int id, String initialName, String initialSurname, String newName, String newSurname) {
        try {
            studentList.addStudent(id, initialName, initialSurname);
            studentList.editStudentData(id, newName, newSurname);
            Student student = studentList.findStudentById(id);
            assertEquals(newName, student.getName());
            assertEquals(newSurname, student.getSurname());
        } catch (StudentAlreadyExistsException | StudentNotFoundException e) {
            fail("An exception should not be thrown when editing an existing student.");
        }
    }

    /**
     * Tests adding a valid grade to a student.
     */
    @ParameterizedTest
    @CsvSource({"1, Adrian, Reszka, 4.5, Nowak, TUC", "2, Karol, Ziaja, 3.5, Kowalski, SMIW"})
    void testAddGradeToStudent(int id, String name, String surname, double gradeValue, String teacher, String subject) {
        try {
            studentList.addStudent(id, name, surname);
            studentList.addGradeToStudent(id, String.valueOf(gradeValue), teacher, subject);
            Student student = studentList.findStudentById(id);
            assertEquals(1, student.getGrades().size());
            Grade grade = student.getGrades().get(0);
            assertEquals(gradeValue, grade.getValue());
            assertEquals(teacher, grade.getTeacher());
            assertEquals(subject, grade.getSubject());
        } catch (StudentAlreadyExistsException | InvalidGradeValueException | StudentNotFoundException | InvalidGradeFormatException e) {
            fail("An exception should not be thrown when adding a valid grade.");
        }
    }

    /**
     * Tests adding a student with null values for name or surname.
     */
    @ParameterizedTest
    @MethodSource("provideStudentDataWithNulls")
    void testAddStudentWithNullValues(int id, String name, String surname) {
        try {
            studentList.addStudent(id, name, surname);
            assertEquals(1, studentList.getStudents().size());
            Student student = studentList.getStudents().get(0);
            assertEquals(id, student.getId());

            if (name == null) {
                assertNull(student.getName());
            } else {
                assertEquals(name, student.getName());
            }
            if (surname == null) {
                assertNull(student.getSurname());
            } else {
                assertEquals(surname, student.getSurname());
            }
        } catch (StudentAlreadyExistsException e) {
            fail("An exception should not be thrown when adding a student with null values.");
        }
    }

    /**
     * Provides test cases with null values for name or surname.
     */
    static Stream<Arguments> provideStudentDataWithNulls() {
        return Stream.of(
                Arguments.of(1, null, "Reszka"),
                Arguments.of(2, "Adrian", null)
        );
    }

    /**
     * Tests removing a grade from a student.
     */
    @ParameterizedTest
    @CsvSource({"1, Adrian, Reszka, 4.5, Nowak, TUC", "2, Karol, Ziaja, 3.5, Kowalski, SMIW"})
    void testRemoveGradeFromStudent(int id, String name, String surname, double gradeValue, String teacher, String subject) {
        try {
            studentList.addStudent(id, name, surname);
            studentList.addGradeToStudent(id, String.valueOf(gradeValue), teacher, subject);
            studentList.removeGradeFromStudent(id, 0);
            Student student = studentList.findStudentById(id);
            assertTrue(student.getGrades().isEmpty());
        } catch (StudentAlreadyExistsException | InvalidGradeValueException | StudentNotFoundException | InvalidGradeFormatException | InvalidGradeIndexException e) {
            fail("An exception should not be thrown when removing an existing grade.");
        }
    }

    /**
     * Tests editing a student's grade.
     */
    @ParameterizedTest
    @CsvSource({
            "1, Adrian, Reszka, 4.0, Nowak, TUC, 5.0, Kaminski, KUC",
            "2, Karol, Ziaja, 3.0, Kowalski, SMIW, 2.0, Sztefka, Java"
    })
    void testEditStudentGrade(int id, String name, String surname, double initialGrade, String initialTeacher, String initialSubject, double newGrade, String newTeacher, String newSubject) {
        try {
            studentList.addStudent(id, name, surname);
            studentList.addGradeToStudent(id, String.valueOf(initialGrade), initialTeacher, initialSubject);
            studentList.editStudentGrade(id, 0, newGrade, newTeacher, newSubject);
            Student student = studentList.findStudentById(id);
            Grade grade = student.getGrades().get(0);
            assertEquals(newGrade, grade.getValue());
            assertEquals(newTeacher, grade.getTeacher());
            assertEquals(newSubject, grade.getSubject());
        } catch (StudentAlreadyExistsException | InvalidGradeValueException | StudentNotFoundException | InvalidGradeIndexException | InvalidGradeFormatException e) {
            fail("An exception should not be thrown when editing an existing grade.");
        }
    }

    /**
     * Tests finding a student by ID.
     */
    @ParameterizedTest
    @CsvSource({"1, Adrian, Reszka", "2, Karol, Ziaja"})
    void testFindStudentById(int id, String name, String surname) {
        try {
            studentList.addStudent(id, name, surname);
            Student student = studentList.findStudentById(id);
            assertNotNull(student);
            assertEquals(id, student.getId());
        } catch (StudentAlreadyExistsException e) {
            fail("An exception should not be thrown when finding an existing student.");
        }
    }

    /**
     * Tests that {@code findStudentById} returns null when the student is not found.
     */
    @Test
    void testFindStudentByIdReturnsNullWhenNotFound() {
        assertNull(studentList.findStudentById(1));
    }
}
