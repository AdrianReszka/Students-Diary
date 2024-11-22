package Controller;

import Model.*;
import View.MainMenuView;
import View.StudentListView;
import lombok.*;

import javax.swing.*;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Controller class that handles interactions between the view and model.
 * It manages student and grade operations.
 * @author Adrian Reszka
 * @version 2.0
 */
@Getter
public class MainMenuController {

    private StudentList studentList;
    private MainMenuView mainMenuView;

    /**
     * Constructor for MainMenuController.
     *
     * @param studentList     the model containing the list of students
     * @param studentListView the view for displaying student data
     */
    public MainMenuController(StudentList studentList, StudentListView studentListView) {
        this.studentList = new StudentList();
        mainMenuView = new MainMenuView(this);
    }

    /**
     * Shows a dialog to add a new student to the list.
     */
    public void showAddStudentDialog() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField surnameField = new JTextField();

        JPanel panel = mainMenuView.createAddStudentPanel(idField, nameField, surnameField);

        int option = JOptionPane.showConfirmDialog(null, panel, "Add new student", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String surname = surnameField.getText();
                studentList.addStudent(id, name, surname);
                JOptionPane.showMessageDialog(null, "Student added successfully!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid ID format! Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (StudentAlreadyExistsException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Shows a dialog to add a grade to an existing student.
     */
    public void showAddGradeToStudentDialog() {
        JTextField idField = new JTextField();
        JTextField gradeField = new JTextField();
        JTextField teacherField = new JTextField();
        JTextField subjectField = new JTextField();

        JPanel panel = mainMenuView.createAddGradeToStudentPanel(idField, gradeField, teacherField, subjectField);

        int option = JOptionPane.showConfirmDialog(null, panel, "Add grade to student", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int id = Integer.parseInt(idField.getText());
            String grade = gradeField.getText().replace(',', '.');
            String teacher = teacherField.getText();
            String subject = subjectField.getText();
            try {
                studentList.addGradeToStudent(id, grade, teacher, subject);
                JOptionPane.showMessageDialog(null, "Grade added successfully!");
            } catch (InvalidGradeValueException | StudentNotFoundException | InvalidGradeFormatException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Shows a dialog to remove a student from the list.
     */
    public void showRemoveStudentDialog() {
        JTextField idField = new JTextField();

        JPanel panel = mainMenuView.createRemoveStudentPanel(idField);

        int option = JOptionPane.showConfirmDialog(null, panel, "Remove student", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int id = Integer.parseInt(idField.getText());
            try {
                studentList.removeStudent(id);
                JOptionPane.showMessageDialog(null, "Student removed successfully!");
            } catch (StudentNotFoundException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Shows a dialog to remove a grade from a student.
     */
    public void showRemoveGradeDialog() {
        JTextField idField = new JTextField();
        JTextField gradeIndexField = new JTextField();

        JPanel panel = mainMenuView.createRemoveGradePanel(idField, gradeIndexField);

        int option = JOptionPane.showConfirmDialog(null, panel, "Remove grade from student", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int id = Integer.parseInt(idField.getText());
            int gradeIndex = Integer.parseInt(gradeIndexField.getText()) - 1;
            try {
                studentList.removeGradeFromStudent(id, gradeIndex);
                JOptionPane.showMessageDialog(null, "Grade removed successfully!");
            } catch (StudentNotFoundException | InvalidGradeIndexException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Shows a dialog to edit student details.
     */
    public void showEditStudentDialog() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField surnameField = new JTextField();

        JPanel panel = mainMenuView.createEditStudentPanel(idField, nameField, surnameField);

        int option = JOptionPane.showConfirmDialog(null, panel, "Edit student data", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int id = Integer.parseInt(idField.getText());
            String newName = nameField.getText();
            String newSurname = surnameField.getText();
            try {
                studentList.editStudentData(id, newName, newSurname);
                JOptionPane.showMessageDialog(null, "Student data updated successfully!");
            } catch (StudentNotFoundException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Shows a dialog to edit a student's grade.
     */
    public void showEditGradeDialog() {
        JTextField idField = new JTextField();
        JTextField gradeIndexField = new JTextField();
        JTextField newGradeField = new JTextField();
        JTextField newTeacherField = new JTextField();
        JTextField newSubjectField = new JTextField();

        JPanel panel = mainMenuView.createEditStudentGradePanel(idField, gradeIndexField, newGradeField, newTeacherField, newSubjectField);

        int option = JOptionPane.showConfirmDialog(null, panel, "Edit student grade", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int id = Integer.parseInt(idField.getText());
            int gradeIndex = Integer.parseInt(gradeIndexField.getText()) - 1;
            String newGrade = newGradeField.getText().replace(',', '.');
            String newTeacher = newTeacherField.getText();
            String newSubject = newSubjectField.getText();
            try {
                studentList.editStudentGrade(id, gradeIndex, Double.parseDouble(newGrade), newTeacher, newSubject);
                JOptionPane.showMessageDialog(null, "Grade updated successfully!");
            } catch (StudentNotFoundException | InvalidGradeIndexException | InvalidGradeFormatException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Displays a sorted list of students and their grades.
     */
    public void showStudentListDialog() {
        JTextArea textArea = new JTextArea(20, 43);
        textArea.setEditable(false);

        String studentsInfo = studentList.getStudents().stream().sorted(Comparator.comparingInt(Student::getId)).map(student -> {
            String studentInfo = "ID: " + student.getId() + ", Name: " + student.getName() + ", Surname: " + student.getSurname() + "\n";
            String gradesInfo = student.getGrades().stream().map(grade -> "  - Grade: " + grade.getValue() + ", Teacher: " + grade.getTeacher()
                    + ", Subject: " + grade.getSubject() + ", Grade category: " + grade.getCategory()) .collect(Collectors.joining("\n"));
                    return studentInfo + gradesInfo + "\n";
                }).collect(Collectors.joining("\n"));

        textArea.setText(studentsInfo);
        JOptionPane.showMessageDialog(null, new JScrollPane(textArea), "Students List", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Exits the application.
     */
    public void showExitDialog() {
        System.exit(0);
    }
}
