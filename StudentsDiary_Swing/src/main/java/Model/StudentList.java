package Model;

import java.io.*;
import java.util.ArrayList;

/**
 * The StudentList class manages a list of students and provides methods to add, remove, and find students.
 * It also allows saving and loading student data, including their grades, from a file.
 *
 * @author Adrian Reszka
 * @version 1.0
 */
public class StudentList {

    /**
     * A list of all students in the system. It is initialized as an empty list and is filled
     * with student objects when students are added.
     */
    private ArrayList<Student> students = new ArrayList<>();

    /**
     * Method used to get current list of students
     *
     * @return latest list of students
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * Adds a new student to the list.
     *
     * @param id      the student's ID
     * @param name    the student's name
     * @param surname the student's surname
     * @throws StudentAlreadyExistsException if the student with specified id exists
     */
    public void addStudent(int id, String name, String surname) throws StudentAlreadyExistsException{
        Student existingStudent = findStudentById(id);

        if (existingStudent != null) {
            throw new StudentAlreadyExistsException();
        }
        Student student = new Student(name, surname, id);
        students.add(student);
    }

    /**
     * Removes a student from the list based on their ID.
     *
     * @param id the student's ID
     * @throws StudentNotFoundException if student with specified id was not found
     */
    public void removeStudent(int id) throws StudentNotFoundException {
        Student student = findStudentById(id);
        if (student != null) {
            students.remove(student);
            return;
        }
        throw new StudentNotFoundException();
    }

    /**
     * Edits the data of a specific student identified by ID.
     *
     * @param studentId  the ID of the student to be edited
     * @param newName    the new name for the student
     * @param newSurname the new surname for the student
     * @throws StudentNotFoundException if the student with specified id was not found
     */
    public void editStudentData(int studentId, String newName, String newSurname) throws StudentNotFoundException {
        Student student = findStudentById(studentId);
        if (student == null) {
            throw new StudentNotFoundException();
        }
        student.setName(newName);
        student.setSurname(newSurname);
    }

    /**
     * Adds a grade to a specific student identified by ID.
     *
     * @param studentId  the ID of the student
     * @param gradeInput the grade input string
     * @param teacher    the name of the teacher assigning the grade
     * @param subject    the subject for which the grade is given
     * @throws StudentNotFoundException    if the student with specified id was not found
     * @throws InvalidGradeIndexException  if the grade with specified index was not found
     * @throws InvalidGradeFormatException if the grade format is incorrect
     */
    public void addGradeToStudent(int studentId, String gradeInput, String teacher, String subject) throws InvalidGradeIndexException, StudentNotFoundException, InvalidGradeFormatException {
        Student student = findStudentById(studentId);
        if (student == null) {
            throw new StudentNotFoundException();
        }
        try {
            double gradeValue = Double.parseDouble(gradeInput);
            if (gradeValue < 1.0 || gradeValue > 5.0) {
                throw new InvalidGradeIndexException();
            }
            student.addGrade(gradeValue, teacher, subject);
        } catch (NumberFormatException e) {
            throw new InvalidGradeFormatException();
        }
    }

    /**
     * Removes a grade from a student identified by ID and grade index.
     *
     * @param studentId  the ID of the student
     * @param gradeIndex the index of the grade to be removed
     * @throws StudentNotFoundException   if the student with specified id was not found
     * @throws InvalidGradeIndexException if the grade with specified index was not found
     */
    public void removeGradeFromStudent(int studentId, int gradeIndex) throws StudentNotFoundException, InvalidGradeIndexException {
        Student student = findStudentById(studentId);
        if (student == null) {
            throw new StudentNotFoundException();
        }
        boolean isRemoved = student.removeGrade(gradeIndex);
        if (!isRemoved) {
            throw new InvalidGradeIndexException();
        }
    }

    /**
     * Edits a specific grade of a student identified by ID and grade index.
     *
     * @param studentId     the ID of the student
     * @param gradeIndex    the index of the grade to be edited
     * @param newGradeValue the new grade value as a string
     * @param newTeacher    the new teacher for the grade
     * @param newSubject    the new subject for the grade
     * @throws StudentNotFoundException    if the student with specified id was not found
     * @throws InvalidGradeIndexException  if the grade with specified index was not found
     * @throws InvalidGradeFormatException if the grade format is incorrect
     */
    public void editStudentGrade(int studentId, int gradeIndex, double newGradeValue, String newTeacher, String newSubject)
            throws StudentNotFoundException, InvalidGradeIndexException, InvalidGradeFormatException {
        Student student = findStudentById(studentId);
        if (student == null) {
            throw new StudentNotFoundException();
        }
        if (gradeIndex < 0 || gradeIndex >= student.getGrades().size()) {
            throw new InvalidGradeIndexException();
        }
        if (newGradeValue < 1.0 || newGradeValue > 5.0) {
            throw new InvalidGradeFormatException();
        }
        Grade grade = student.getGrades().get(gradeIndex);
        grade.setValue(newGradeValue);
        grade.setTeacher(newTeacher);
        grade.setSubject(newSubject);
    }

    /**
     * Finds a student by their ID.
     *
     * @param id the ID of the student to find
     * @return the Student object if found
     */
    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
}