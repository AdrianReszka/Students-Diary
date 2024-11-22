package com.mycompany.studentsdiary_swing;

import Controller.MainMenuController;
import Model.StudentList;
import View.MainMenuView;
import View.StudentListView;

/**
 * Main class for the Students Diary Swing application.
 * It initializes the main components and starts the application.
 * @author Adrian Reszka
 * @version 2.0
 */
public class StudentsDiary_Swing {

    /**
     * Main method to launch the application.
     * @param args Command line arguments
     */
    public static void main(String[] args) {

        StudentList studentList = new StudentList();
        StudentListView studentListView = new StudentListView();
        MainMenuController mainMenuController = new MainMenuController(studentList, studentListView);
        MainMenuView mainMenuView = new MainMenuView(mainMenuController);

        mainMenuView.displayMainMenu();
    }
}
