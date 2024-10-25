package Model;

import java.util.ArrayList;

/**
 * The Student class represents a student with a unique ID, name, surname, and a list of grades.
 * It provides methods to manage the student's data, including adding and removing grades.
 *
 * @author Adrian Reszka
 * @version 1.0
 */
public class Student {

    /**
     * The name of the student.
     */
    private String name;

    /**
     * The surname of the student.
     */
    private String surname;

    /**
     * The unique identifier of the student.
     */
    private int id;

    /**
     * A list containing the grades assigned to this student.
     */
    private ArrayList<Grade> grades;

    /**
     * Constructs a new Student object with the specified name, surname, and ID.
     * Initializes an empty list of grades.
     *
     * @param name the name of the student
     * @param surname the surname of the student
     * @param id the unique identifier of the student
     */
    public Student(String name, String surname, int id) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.grades = new ArrayList<>();
    }

    /**
     * Gets the student's ID.
     *
     * @return the student's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the student's name.
     *
     * @return the student's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the student's surname.
     *
     * @return the student's surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the student's ID.
     *
     * @param id the new ID for the student
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the student's name.
     *
     * @param name the new name for the student
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the student's surname.
     *
     * @param surname the new surname for the student
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Adds a grade to the student's list of grades.
     *
     * @param value the value of the grade
     * @param teacher the name of the teacher who gave the grade
     * @param subject the subject for which the grade was given
     */
    public void addGrade(double value, String teacher, String subject) {
        Grade newGrade = new Grade(value, teacher, subject);
        grades.add(newGrade);
    }

    /**
     * Removes a grade from the student's list of grades based on the index.
     *
     * @param index the index of the grade to remove
     * @return true if the grade was successfully removed, false if the index is invalid
     */
    public boolean removeGrade(int index) {
        if (index >= 0 && index < grades.size()) {
            grades.remove(index);
            return true;
        }
        return false;
    }

    /**
     * Prints all the student's grades in a readable format.
     * If the student has no grades, a message is displayed indicating this.
     */
    public void printGrades() {
        if (grades.isEmpty()) {
            System.out.println("Student " + name + " " + surname + " has no grades.");
        } else {
            System.out.println("Grades for " + name + " " + surname + ":");
            for (int i = 0; i < grades.size(); i++) {
                Grade grade = grades.get(i);
                System.out.println((i + 1) + ". " + String.format("%.2f", grade.getValue()) +
                        " (Teacher: " + grade.getTeacher() + ", Subject: " + grade.getSubject() + ")");
            }
        }
    }

    /**
     * Gets the list of grades assigned to the student.
     *
     * @return the list of grades
     */
    public ArrayList<Grade> getGrades() {
        return grades;
    }
}