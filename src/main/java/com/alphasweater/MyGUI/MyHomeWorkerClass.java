package com.alphasweater.MyGUI;

import com.alphasweater.MyTasks.MyTasksClass;
import com.alphasweater.MyUser.MyUserClass;
import com.alphasweater.MyUtil.WordWrapRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;

/* Author: Chad Fairlie
*  Pseudonym: AlphaSweater
*  Student Number: ST10269509
---------------------------------------------------------------------------------------------------------------------*/
public class MyHomeWorkerClass {
    private boolean tblPopulated = false;
    protected final String[] columnNames = {"Task Status", "Developer Details", "Task Number", "Task Name",
            "Task Description", "Task ID", "Duration"};
    // HomePage object to allow the editing of GUI components
    private HomePage homePage;

    MyTasksClass taskWorker = new MyTasksClass();

    //----------------------------------------------------------------------------------------------------------------//
    // Default Constructor
    protected MyHomeWorkerClass() {
    }

    protected MyHomeWorkerClass(HomePage homePage) {
        this.homePage = homePage;
    }
    //--------------------------------------------------------------------------------------------------------------------//

    /**
     * Summons the home page GUI.
     */
    public static void createHomePage() {
        // Set the content pane of the home JFrame to the panel of the HomePage instance
        HomePage.homeFrame.setContentPane(new HomePage().panel);
        HomePage.homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HomePage.homeFrame.pack();
        HomePage.homeFrame.setLocationRelativeTo(null);
        HomePage.homeFrame.setVisible(true);
    }

    //----------------------------------------------------------------------------------------------------------------//
    protected void beginHere() {
        homePage.lblTitle.setText("Welcome to EasyKanban.");
        // Set the welcome label text to display the user's first and last name
        homePage.lblWelcome.setText(getWelcomeMessage());
    }

    //----------------------------------------------------------------------------------------------------------------//
    protected String getWelcomeMessage() {
        return "Hi " + MyUserClass.getCurrentUser().getUserFirstName() + " "
                + MyUserClass.getCurrentUser().getUserLastName() + ", it is great to see you.";
    }

    //----------------------------------------------------------------------------------------------------------------//
    protected void beginAddTasks() {
        int numOfTasks = Integer.parseInt(JOptionPane.showInputDialog("Please enter how many tasks you would like to add"));
        taskWorker.setNumOfTasks(numOfTasks);
        String[] taskStatusOptions = {"To Do", "Doing", "Done"};

        // Initialize the listOfTasks array with the desired length
        MyTasksClass[] listOfTasks = new MyTasksClass[numOfTasks];

        for (int i = 0; i < taskWorker.getNumOfTasks(); i++) {
            JOptionPane.showMessageDialog(null, "You are now busy with task " + (i + 1) + ".");

            String taskStatus;
            int option = JOptionPane.showOptionDialog(null, "Select Task Status:", "Task Status",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, taskStatusOptions, taskStatusOptions[0]);
            switch (option) {
                case 1:
                    taskStatus = taskStatusOptions[1];
                    break;
                case 2:
                    taskStatus = taskStatusOptions[2];
                    break;
                case 0:
                default:
                    taskStatus = taskStatusOptions[0];
                    break;
            }
            String taskName = JOptionPane.showInputDialog("Please name your task's name.");
            String taskDescription = JOptionPane.showInputDialog("Please enter a description for your task (50 characters max).");
            while (!taskWorker.checkTaskDescription(taskDescription)) {
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
        taskWorker.setListOfTasks(listOfTasks);
        populateTableData();
    }

    protected void beginShowReportHere() {
        JOptionPane.showMessageDialog(null, "Coming Soon...");
    }

    //----------------------------------------------------------------------------------------------------------------//
    protected void populateTableData() {
        MyTasksClass[] listOfTasks = taskWorker.getListOfTasks();

        int rows = taskWorker.getNumOfTasks();    // number of rows in the 2D array
        int columns = 7; // number of columns in the 2D array

        Object[][] data = new Object[rows][columns];

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
        homePage.tblTasksList.setVisible(true);
        editComponents();
    }

    //----------------------------------------------------------------------------------------------------------------//
    protected void logOut() {
        // Dispose the home JFrame
        HomePage.homeFrame.dispose();
        // Create and display the login page
        MyLoginWorkerClass.createLoginPage();

        MyUserClass.setCurrentUser(null);
    }

    //----------------------------------------------------------------------------------------------------------------//
    // Modifying Custom UI components
    protected void editComponents() {
        if (tblPopulated) {
            homePage.lblTotalHours.setText("Total Number Of Hours Across All Tasks := " + taskWorker.returnTotalHours(taskWorker.getListOfTasks()) + " hrs");
            homePage.tblTasksList.setModel(homePage.model);
            homePage.tblTasksList.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

            // Set the cell renderer for each column to center the text and wrap the content
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            centerRenderer.setVerticalAlignment(SwingConstants.TOP);

            TableColumnModel columnModel = homePage.tblTasksList.getColumnModel();
            for (int i = 0; i < columnModel.getColumnCount(); i++) {
                columnModel.getColumn(i).setCellRenderer(centerRenderer);

                if (i == 1 || i == 3 || i == 4) {
                    // Apply the WordWrapRenderer to columns 1, 3, and 4
                    columnModel.getColumn(i).setCellRenderer(new WordWrapRenderer());
                }
            }

            // Set the preferred column width for wrapping text
            WordWrapRenderer.setColumnWidth(homePage.tblTasksList, 1, 10); // Adjust margin value as needed
            WordWrapRenderer.setColumnWidth(homePage.tblTasksList, 3, 10); // Adjust margin value as needed
            WordWrapRenderer.setColumnWidth(homePage.tblTasksList, 4, 10); // Adjust margin value as needed

            // Set the table width to match the sum of the preferred column widths
            int tableWidth = 0;
            for (int i = 0; i < columnModel.getColumnCount(); i++) {
                tableWidth += columnModel.getColumn(i).getPreferredWidth();
            }
            homePage.tblTasksList.setPreferredScrollableViewportSize(new Dimension(tableWidth, homePage.tblTasksList.getPreferredSize().height));

            homePage.tblScrollPane.setViewportView(homePage.tblTasksList);
        }
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//