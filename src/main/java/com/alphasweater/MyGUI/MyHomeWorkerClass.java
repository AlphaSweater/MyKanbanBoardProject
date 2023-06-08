package com.alphasweater.MyGUI;

import com.alphasweater.MyTasks.MyTasksClass;
import com.alphasweater.MyUser.MyUserClass;
import com.alphasweater.MyUtil.WordWrapRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.Component;

/* Author: Chad Fairlie
*  Pseudonym: AlphaSweater
*  Student Number: ST********
---------------------------------------------------------------------------------------------------------------------*/
public class MyHomeWorkerClass {
    private boolean tblPopulated = false;
    private final String[] columnNames = {"Task Status", "Developer Details", "Task Number", "Task Name",
            "Task Description", "Task ID", "Duration"};
    private Object[][] data = {
            // Replace this sample data with your actual data
            {"To Do", "Chad Fairlie", 5, "Test Task", "This is a test task and it is going on very long here", "CH:12:DDC", 6},
            {"Doing", "John Doe", 8, "Task 2", "This is task 2", "JD:34:ABC", 4},
            // Add more rows if needed
    };

    // HomePage object to allow the editing of GUI components
    private HomePage homePage;

    // Default Constructor
    protected MyHomeWorkerClass() {
    }

    protected MyHomeWorkerClass(HomePage homePage) {
        this.homePage = homePage;
    }

    protected void beginHere() {
        homePage.lblTitle.setText(getTitleHeading());
        // Set the welcome label text to display the user's first and last name
        homePage.lblWelcome.setText(getWelcomeMessage());
    }

    protected String getTitleHeading() {
        return "Welcome to EasyKanban.";
    }

    protected String getWelcomeMessage() {
        return "Hi " + MyUserClass.getCurrentUser().getUserFirstName() + " "
                + MyUserClass.getCurrentUser().getUserLastName() + ", it is great to see you.";
    }

    protected void beginAddTasks() {
        int numOfTasks = Integer.parseInt(JOptionPane.showInputDialog("Please enter how many tasks you would like to add"));
        MyTasksClass.setNumOfTasks(numOfTasks);
        MyTasksClass checkTask = new MyTasksClass();
        String[] taskStatusOptions = {"To Do", "Doing", "Done"};

        // Initialize the listOfTasks array with the desired length
        MyTasksClass[] listOfTasks = new MyTasksClass[numOfTasks];

        for (int i = 0; i < MyTasksClass.getNumOfTasks(); i++) {
            JOptionPane.showMessageDialog(null, "You are now busy with task " + (i + 1) + ".");

            String taskStatus;
            int option = JOptionPane.showOptionDialog(null, "Select Task Status:", "Task Status",
                    0, 3, null, taskStatusOptions, taskStatusOptions[0]);
            switch (option) {
                case 0:
                    taskStatus = taskStatusOptions[0];
                    break;
                case 1:
                    taskStatus = taskStatusOptions[1];
                    break;
                case 2:
                    taskStatus = taskStatusOptions[2];
                    break;
                default:
                    taskStatus = taskStatusOptions[0];
                    break;
            }
            String taskName = JOptionPane.showInputDialog("Please name your task's name.");
            String taskDescription = JOptionPane.showInputDialog("Please enter a description for your task (50 characters max).");
            while (!checkTask.checkTaskDescription(taskDescription)) {
                JOptionPane.showMessageDialog(null, "Task Description exceeded 50 characters, please try again");
                taskDescription = JOptionPane.showInputDialog("Please enter a description for your task (50 Characters MAX).");
            }
            String taskDevInfo = JOptionPane.showInputDialog("Please enter the task developers full name");
            int taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Please enter the estimated task duration"));

            MyTasksClass newTask = new MyTasksClass(i, taskName, taskDescription, taskDuration, taskStatus, taskDevInfo);
            listOfTasks[i] = newTask;
            JOptionPane.showMessageDialog(null, "Your Task has been successfully captured.");

        }
        // Update the listOfTasks in MyTasksClass
        MyTasksClass.setListOfTasks(listOfTasks);
        populateTableData();
    }


    protected void populateTableData() {
        MyTasksClass[] listOfTasks = MyTasksClass.getListOfTasks();

        int rows = MyTasksClass.getNumOfTasks();    // number of rows in the 2D array
        int columns = 7; // number of columns in the 2D array

        data = new Object[rows][columns];

        for (int i = 0; i < rows; i++) {
            MyTasksClass task = listOfTasks[i];
            data[i][0] = task.getTaskStatus();
            data[i][1] = task.getTaskDevInfo();
            data[i][2] = task.getTaskNumber();
            data[i][3] = task.getTaskName();
            data[i][4] = task.getTaskDescription();
            data[i][5] = task.getTaskID();
            data[i][6] = task.getTaskDuration();
        }

        // Call the method to update the table UI components
        homePage.model = new DefaultTableModel(data, columnNames);
        tblPopulated = true;
        editComponents();
    }



    protected void logOut() {
        // Dispose the home JFrame
        HomePage.homeFrame.dispose();
        // Create and display the login page
        LoginPage.createLoginPage();

        MyUserClass.setCurrentUser(null);
    }

    // Modifying Custom UI components
    protected void editComponents() {

        if (tblPopulated) {
            homePage.tblTasksList.setModel(homePage.model);

            homePage.tblTasksList.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS); // Set auto resize mode to OFF

            // Set the cell renderer for each column to center the text
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            centerRenderer.setVerticalAlignment(SwingConstants.TOP);

            TableColumnModel columnModel = homePage.tblTasksList.getColumnModel();
            for (int i = 0; i < columnModel.getColumnCount(); i++) {
                columnModel.getColumn(i).setCellRenderer(centerRenderer);
            }

            columnModel.getColumn(0).setPreferredWidth(80); // Set width for the first column (Task Status)
            columnModel.getColumn(1).setPreferredWidth(110); // Set width for the second column (Developer Details)
            columnModel.getColumn(1).setCellRenderer(new WordWrapRenderer()); // Apply word wrap renderer to the fifth column (Developer Details)
            columnModel.getColumn(2).setPreferredWidth(70); // Set width for the third column (Task Number)
            columnModel.getColumn(3).setPreferredWidth(120); // Set width for the fourth column (Task Name)
            columnModel.getColumn(3).setCellRenderer(new WordWrapRenderer()); // Apply word wrap renderer to the fourth column (Task Name)
            columnModel.getColumn(4).setPreferredWidth(250); // Set width for the fifth column (Task Description)
            columnModel.getColumn(4).setCellRenderer(new WordWrapRenderer()); // Apply word wrap renderer to the fifth column (Task Description)
            columnModel.getColumn(5).setPreferredWidth(70); // Set width for the sixth column (Task ID)
            columnModel.getColumn(6).setPreferredWidth(60); // Set width for the seventh column (Task Duration)

            homePage.tblScrollPane.setViewportView(homePage.tblTasksList);

        }
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//