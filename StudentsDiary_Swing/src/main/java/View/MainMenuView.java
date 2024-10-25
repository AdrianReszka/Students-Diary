package View;

import Controller.MainMenuController;
import Model.Student;
import Model.Grade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * View class that displays the main menu and handles user input in the application.
 * It interacts with the MainMenuController to perform the necessary operations.
 * @author Adrian Reszka
 * @version 2.0
 */
public class MainMenuView {

    /**
     * The controller responsible for handling main menu actions.
     */
    private MainMenuController mainMenuController;

    /**
     * Tree structure displaying students and their grades.
     */
    private JTree studentTree;

    /**
     * Model for the JTree, which represents the students and their grades.
     */
    private DefaultTreeModel treeModel;

    /**
     * Constructs the MainMenuView and associates it with a MainMenuController.
     *
     * @param mainMenuController The controller responsible for handling user actions in the main menu.
     */
    public MainMenuView(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
    }

    /**
     * Displays the main menu GUI with buttons and a tree structure for students.
     * This method initializes the main frame of the application.
     */
    public void displayMainMenu() {
        JFrame frame = new JFrame("Students Diary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 357);

        JPanel buttonPanel = createButtonPanel();

        treeModel = new DefaultTreeModel(createStudentTreeNodes());
        studentTree = new JTree(treeModel);
        JScrollPane treeScrollPane = new JScrollPane(studentTree);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, buttonPanel, treeScrollPane);
        splitPane.setDividerLocation(233);
        frame.getContentPane().add(splitPane);

        frame.setVisible(true);
    }

    /**
     * Creates a JPanel containing buttons for various student-related actions.
     *
     * @return A JPanel with buttons to add, edit, or remove students and grades.
     */
    private JPanel createButtonPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Dimension buttonSize = new Dimension(225, 500);

        JButton addStudentButton = new JButton("Add new student");
        addStudentButton.setMaximumSize(buttonSize);
        addStudentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addStudentButton.setMnemonic(KeyEvent.VK_1);
        addStudentButton.addActionListener(e -> {
            mainMenuController.showAddStudentDialog();
            updateTreeModel();
        });
        mainPanel.add(addStudentButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton addGradeButton = new JButton("Add grade to student");
        addGradeButton.setMaximumSize(buttonSize);
        addGradeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addGradeButton.setMnemonic(KeyEvent.VK_2);
        addGradeButton.addActionListener(e -> {
            mainMenuController.showAddGradeToStudentDialog();
            updateTreeModel();
        });
        mainPanel.add(addGradeButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton removeStudentButton = new JButton("Remove student");
        removeStudentButton.setMaximumSize(buttonSize);
        removeStudentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeStudentButton.setMnemonic(KeyEvent.VK_3);
        removeStudentButton.addActionListener(e -> {
            mainMenuController.showRemoveStudentDialog();
            updateTreeModel();
        });
        mainPanel.add(removeStudentButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton removeGradeButton = new JButton("Remove grade from student");
        removeGradeButton.setMaximumSize(buttonSize);
        removeGradeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeGradeButton.setMnemonic(KeyEvent.VK_4);
        removeGradeButton.addActionListener(e -> {
            mainMenuController.showRemoveGradeDialog();
            updateTreeModel();
        });
        mainPanel.add(removeGradeButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton editStudentButton = new JButton("Edit student data");
        editStudentButton.setMaximumSize(buttonSize);
        editStudentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        editStudentButton.setMnemonic(KeyEvent.VK_5);
        editStudentButton.addActionListener(e -> {
            mainMenuController.showEditStudentDialog();
            updateTreeModel();
        });
        mainPanel.add(editStudentButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton editGradeButton = new JButton("Edit student grade");
        editGradeButton.setMaximumSize(buttonSize);
        editGradeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        editGradeButton.setMnemonic(KeyEvent.VK_6);
        editGradeButton.addActionListener(e -> {
            mainMenuController.showEditGradeDialog();
            updateTreeModel();
        });
        mainPanel.add(editGradeButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton displayStudentsButton = new JButton("Display all students");
        displayStudentsButton.setMaximumSize(buttonSize);
        displayStudentsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        displayStudentsButton.setMnemonic(KeyEvent.VK_7);
        displayStudentsButton.addActionListener(e -> mainMenuController.showStudentListDialog());
        mainPanel.add(displayStudentsButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton exitButton = new JButton("Exit");
        exitButton.setMaximumSize(buttonSize);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setMnemonic(KeyEvent.VK_8);
        exitButton.addActionListener(e -> mainMenuController.showExitDialog());
        mainPanel.add(exitButton);

        mainPanel.add(Box.createVerticalGlue());

        return mainPanel;
    }

    /**
     * Generates the root node of the student tree, with each student and their grades represented as child nodes.
     *
     * @return The root node of the JTree representing the students and their grades.
     */
    private DefaultMutableTreeNode createStudentTreeNodes() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Students");

        for (Student student : mainMenuController.getStudentList().getStudents()) {
            DefaultMutableTreeNode studentNode = new DefaultMutableTreeNode(student.getName() + " " + student.getSurname());
            root.add(studentNode);

            for (Grade grade : student.getGrades()) {
                DefaultMutableTreeNode gradeNode = new DefaultMutableTreeNode("Grade: " + grade.getValue());
                studentNode.add(gradeNode);

                DefaultMutableTreeNode teacherNode = new DefaultMutableTreeNode("Teacher: " + grade.getTeacher());
                DefaultMutableTreeNode subjectNode = new DefaultMutableTreeNode("Subject: " + grade.getSubject());

                gradeNode.add(teacherNode);
                gradeNode.add(subjectNode);
            }
        }
        return root;
    }

    /**
     * Updates the model of the student tree by reloading the tree structure with updated data.
     */
    private void updateTreeModel() {
        treeModel.setRoot(createStudentTreeNodes());
        treeModel.reload();
    }

    /**
     * Creates a JPanel used for adding a new student, which includes fields for student`s ID, name, and surname.
     *
     * @param idField      JTextField for entering the student ID.
     * @param nameField    JTextField for entering the student's name.
     * @param surnameField JTextField for entering the student's surname.
     * @return A JPanel containing the input fields for adding a new student.
     */
    public JPanel createAddStudentPanel(JTextField idField, JTextField nameField, JTextField surnameField) {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        panel.add(new JLabel("Student ID:"));
        panel.add(idField);

        panel.add(new JLabel("Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Surname:"));
        panel.add(surnameField);

        return panel;
    }

    /**
     * Creates a JPanel used for adding a grade to student, which includes fields for student`s ID, grade value, teacher and subject names.
     *
     * @param idField      JTextField for entering the student ID.
     * @param gradeField   JTextField for entering the grade value.
     * @param teacherField JTextField for entering the teacher`s name.
     * @param subjectField JTextField for entering the name of a subject.
     * @return A JPanel containing the input fields for adding a new student.
     */
    public JPanel createAddGradeToStudentPanel(JTextField idField, JTextField gradeField, JTextField teacherField, JTextField subjectField) {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        panel.add(new JLabel("Student ID:"));
        panel.add(idField);

        panel.add(new JLabel("Grade:"));
        panel.add(gradeField);

        panel.add(new JLabel("Teacher:"));
        panel.add(teacherField);

        panel.add(new JLabel("Subject:"));
        panel.add(subjectField);

        return panel;
    }

    /**
     * Creates a JPanel used for removing a student, which include field for student`s ID.
     *
     * @param idField JTextField for entering the student`s ID.
     * @return A JPanel containing the input fields for adding a new student.
     */
    public JPanel createRemoveStudentPanel(JTextField idField) {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

        panel.add(new JLabel("Student ID:"));
        panel.add(idField);

        return panel;
    }

    /**
     * Creates a JPanel used for removing a grade from student, which includes fields for student`s ID and grade index.
     *
     * @param idField         JTextField for entering the student ID.
     * @param gradeIndexField JTextField for entering the student grade index.
     * @return A JPanel containing the input fields for adding a new student.
     */
    public JPanel createRemoveGradePanel(JTextField idField, JTextField gradeIndexField) {

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

        panel.add(new JLabel("Student ID:"));
        panel.add(idField);

        panel.add(new JLabel("Grade index:"));
        panel.add(gradeIndexField);

        return panel;
    }

    /**
     * Creates a JPanel used for editing student data, which includes fields for student ID, new name and surname.
     *
     * @param idField         JTextField for entering the student ID.
     * @param newNameField    JTextField for entering the new student's name.
     * @param newSurnameField JTextField for entering the new student's surname.
     * @return A JPanel containing the input fields for adding a new student.
     */
    public JPanel createEditStudentPanel(JTextField idField, JTextField newNameField, JTextField newSurnameField){
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        panel.add(new JLabel("Student ID:"));
        panel.add(idField);

        panel.add(new JLabel("New name:"));
        panel.add(newNameField);

        panel.add(new JLabel("New surname:"));
        panel.add(newSurnameField);

        return panel;
    }

    /**
     * Creates a JPanel used for editing student`s grade, which includes fields for student ID, grade index, new grade value, teacher and subject name.
     *
     * @param idField         JTextField for entering the student ID.
     * @param gradeIndexField JTextField for entering the student's name.
     * @param newGradeField   JTextField for entering the student's surname.
     * @param newTeacherField JTextField for entering the new teacher`s name.
     * @param newSubjectField JTextField for entering the new subject`s name
     * @return A JPanel containing the input fields for adding a new student.
     */
    public JPanel createEditStudentGradePanel(JTextField idField, JTextField gradeIndexField, JTextField newGradeField, JTextField newTeacherField, JTextField newSubjectField){
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        panel.add(new JLabel("Student ID:"));
        panel.add(idField);

        panel.add(new JLabel("Grade index:"));
        panel.add(gradeIndexField);

        panel.add(new JLabel("New grade:"));
        panel.add(newGradeField);

        panel.add(new JLabel("New teacher:"));
        panel.add(newTeacherField);

        panel.add(new JLabel("New subject:"));
        panel.add(newSubjectField);

        return panel;
    }

}
