package Model;

/**
 * The Grade class represents a student's grade, including the value of it, the teacher who gave the grade, and the subject.
 *
 * @author Adrian Reszka
 * @version 1.0
 */
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
     * Constructs a new Grade object with the specified value, teacher, and subject.
     *
     * @param value the value of the grade
     * @param teacher the teacher who gave the grade
     * @param subject the subject for which the grade was given
     */
    public Grade(double value, String teacher, String subject) {
        this.value = value;
        this.teacher = teacher;
        this.subject = subject;
    }

    /**
     * Gets the value of the grade.
     *
     * @return the value of the grade
     */
    public double getValue() {
        return value;
    }

    /**
     * Gets the teacher who gave the grade.
     *
     * @return the teacher's name
     */
    public String getTeacher() {
        return teacher;
    }

    /**
     * Gets the subject for which the grade was given.
     *
     * @return the subject's name
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the value of the grade.
     *
     * @param value the new value for the grade
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Sets the teacher who gave the grade.
     *
     * @param teacher the new teacher for the grade
     */
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    /**
     * Sets the subject for which the grade was given.
     *
     * @param subject the new subject for the grade
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Returns a string representation of the grade, including its value, teacher, and subject.
     *
     * @return a string representation of the grade
     */
    public String toString() {
        return "Grade: " + value + ", Teacher: " + teacher + ", Subject: " + subject;
    }
}