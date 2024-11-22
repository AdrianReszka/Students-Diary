package Model;
import lombok.*;

/**
 * The Grade class represents a student's grade, including the value of it, the teacher who gave the grade, and the subject.
 *
 * @author Adrian Reszka
 * @version 1.0
 */
@Getter
@Setter
@ToString
public class Grade {
    
    /**
     * The value of a grade assigned to a student.
     */
    private double value;

    /**
     * The name of the teacher who assigned the grade.
     */
    private String teacher;

    /**
     * The subject for which the grade was given.
     */
    private String subject;

    /**
     * The category of the grade of the student
     */
    private GradeCategory category;

    public Grade(double value, String teacher, String subject) {
        this.value = value;
        this.teacher = teacher;
        this.subject = subject;
        this.category = determineCategory(value);
    }

    /**
     * Custom setter for the value field that updates the category whenever the value changes.
     *
     * @param value the new value for the grade
     */
    public void setValue(double value) {
        this.value = value;
        this.category = determineCategory(value);
    }

    /**
     * Determines the category of the grade based on its value.
     */
    private GradeCategory determineCategory(double value) {
        if (value >= 5.0) {
            return GradeCategory.EXCELLENT;
        } else if (value >= 4.0) {
            return GradeCategory.GOOD;
        } else if (value >= 3.0) {
            return GradeCategory.AVERAGE;
        } else {
            return GradeCategory.POOR;
        }
    }

}