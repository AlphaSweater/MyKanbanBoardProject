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
    protected MyUserClass currentUser;

    public void setCurrentUser(MyUserClass user) {
        this.currentUser = user;
    }


    private boolean tblPopulated = false;
    protected final String[] columnNames = {"Task Status", "Developer Details", "Task Number", "Task Name",
            "Task Description", "Task ID", "Duration"};
    // HomePage object to allow the editing of GUI components
    private HomePage homePage;

    public HomePage getHomePage() {
        return homePage;
    }

    public void setHomePage(HomePage homePage) {
        this.homePage = homePage;
    }

    private MyLoginWorkerClass loginWorker;

    public void setLoginWorker(MyLoginWorkerClass loginWorker) {
        this.loginWorker = loginWorker;
    }

    final MyTasksClass taskWorker = new MyTasksClass();

    //----------------------------------------------------------------------------------------------------------------//
    // Default Constructor
    public MyHomeWorkerClass() {
    }
    //--------------------------------------------------------------------------------------------------------------------//

    /**
     * Summons the home page GUI.
     */
    public void createHomePage() {
        // Set the content pane of the home JFrame to the panel of the HomePage instance
        this.homePage = new HomePage(this);
        this.homePage.homeFrame.setContentPane(homePage.panel);
        this.homePage.homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.homePage.homeFrame.pack();
        this.homePage.homeFrame.setLocationRelativeTo(null);
        this.homePage.homeFrame.setVisible(true);
    }

    //----------------------------------------------------------------------------------------------------------------//
    protected void beginHere() {
        this.homePage.lblTitle.setText("Welcome to EasyKanban.");
        // Set the welcome label text to display the user's first and last name
        this.homePage.lblWelcome.setText(getWelcomeMessage());
    }

    //----------------------------------------------------------------------------------------------------------------//
    protected String getWelcomeMessage() {
        return "Hi " + currentUser.getUserFirstName() + " "
                + currentUser.getUserLastName() + ", it is great to see you.";
    }

    //----------------------------------------------------------------------------------------------------------------//
    protected void beginAddTasks() {
        int numOfTasks = 0;
        try {
            numOfTasks = Integer.parseInt(JOptionPane.showInputDialog("Please enter how many tasks you would like to add"));
            taskWorker.setNumOfTasks(numOfTasks);
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Invalid amount of tasks!");
            throw new RuntimeException(e);
        }

        String[] taskStatusOptions = {"To Do", "Doing", "Done"};

        // Initialize the listOfTasks array with the desired length
        MyTasksClass[] listOfTasks = new MyTasksClass[numOfTasks];

        for (int i = 0; i < taskWorker.getNumOfTasks(); i++) {
            boolean taskInfoNull = false;
            JOptionPane.showMessageDialog(null, "You are now busy with task " + (i + 1) + ".");

            String taskStatus;
            int option = JOptionPane.showOptionDialog(null, "Select Task Status:", "Task Status",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, taskStatusOptions, taskStatusOptions[0]);
            taskStatus = switch (option) {
                case 1 -> taskStatusOptions[1];
                case 2 -> taskStatusOptions[2];
                default -> taskStatusOptions[0];
            };

            String taskName = null;
            try {
                taskName = JOptionPane.showInputDialog("Please enter your task's name.");
                while (taskName.length() < 2) {
                    JOptionPane.showMessageDialog(null, "Your Task's name must be greater than 2 characters, please try again");
                    taskName = JOptionPane.showInputDialog("Please enter your task's name.");
                }
            } catch (NullPointerException e) {
                // User pressed cancel, return to the beginning of the loop iteration
                taskInfoNull = true;
            }

            String taskDescription = "";
            if (!taskInfoNull) {
                try {
                    taskDescription = JOptionPane.showInputDialog("Please enter a description for your task (50 characters max).");
                    while (!taskWorker.checkTaskDescription(taskDescription)) {
                        if (!taskWorker.checkTaskDescription(taskDescription)) {
                            JOptionPane.showMessageDialog(null, "Task Description exceeded 50 characters, please try again");
                            taskDescription = JOptionPane.showInputDialog("Please enter a description for your task (50 characters max).");
                        }
                    }
                } catch (NullPointerException e) {
                    taskInfoNull = true;
                }
            }

            String taskDevInfo = "";
            if (!taskInfoNull) {
                taskDevInfo = JOptionPane.showInputDialog("Please enter the task developer's full name");
                while (taskDevInfo.length() < 2) {
                    JOptionPane.showMessageDialog(null, "The task developer's name must be greater than 3 characters, please try again");
                    if (taskDevInfo == null) {
                        JOptionPane.showMessageDialog(null, "Task developer's name cannot be empty!");
                        taskInfoNull = true;
                    }
                }
            }

            int taskDuration = 0;
            if (!taskInfoNull) {
                while (true) {
                    try {
                        taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Please enter the estimated task duration"));
                        break; // Exit the loop if parsing is successful
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid input for task duration! Please try again.");
                    } catch (NullPointerException e) {
                        // User pressed cancel, return to the beginning of the loop iteration
                        taskInfoNull = true;
                        break;
                    }
                }
            }

            if (taskInfoNull) {
                JOptionPane.showMessageDialog(null, "Invalid input");
                int choice = JOptionPane.showConfirmDialog(null, "Would you like to stop adding tasks?");
                if (choice == 0 || choice == -1) {
                    break;
                } else if (choice == 1) {
                    --i;
                }
            } else {
                MyTasksClass newTask = new MyTasksClass(i, taskName, taskDescription, taskDuration, taskStatus, taskDevInfo);
                listOfTasks[i] = newTask;
                JOptionPane.showMessageDialog(null, listOfTasks[i].printTaskDetails());
            }
        }

        JOptionPane.showMessageDialog(null, "The total number of hours across all tasks is: " + taskWorker.returnTotalHours(listOfTasks) + " hrs");
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
        this.homePage.model = new DefaultTableModel(data, columnNames);
        tblPopulated = true;
        this.homePage.tblTasksList.setVisible(true);
        editComponents();
    }

    //----------------------------------------------------------------------------------------------------------------//
    protected void logOut() {
        // Dispose the home JFrame
        this.homePage.homeFrame.dispose();
        // Create and display the login page
        this.loginWorker.createLoginPage();

        this.setCurrentUser(null);
    }

    //----------------------------------------------------------------------------------------------------------------//
    // Modifying Custom UI components
    protected void editComponents() {
        if (tblPopulated) {
            this.homePage.lblTotalHours.setText("Total Number Of Hours Across All Tasks = " + taskWorker.returnTotalHours(taskWorker.getListOfTasks()) + " hrs");
            this.homePage.tblTasksList.setModel(this.homePage.model);
            this.homePage.tblTasksList.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

            // Set the cell renderer for each column to center the text and wrap the content
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            centerRenderer.setVerticalAlignment(SwingConstants.TOP);

            TableColumnModel columnModel = this.homePage.tblTasksList.getColumnModel();
            for (int i = 0; i < columnModel.getColumnCount(); i++) {
                columnModel.getColumn(i).setCellRenderer(centerRenderer);

                if (i == 1 || i == 3 || i == 4) {
                    // Apply the WordWrapRenderer to columns 1, 3, and 4
                    columnModel.getColumn(i).setCellRenderer(new WordWrapRenderer());
                }
            }

            // Set the preferred column width for wrapping text
            WordWrapRenderer.setColumnWidth(this.homePage.tblTasksList, 1, 10); // Adjust margin value as needed
            WordWrapRenderer.setColumnWidth(this.homePage.tblTasksList, 3, 10); // Adjust margin value as needed
            WordWrapRenderer.setColumnWidth(this.homePage.tblTasksList, 4, 10); // Adjust margin value as needed

            // Set the table width to match the sum of the preferred column widths
            int tableWidth = 0;
            for (int i = 0; i < columnModel.getColumnCount(); i++) {
                tableWidth += columnModel.getColumn(i).getPreferredWidth();
            }
            this.homePage.tblTasksList.setPreferredScrollableViewportSize(new Dimension(tableWidth, this.homePage.tblTasksList.getPreferredSize().height));

            this.homePage.tblScrollPane.setViewportView(this.homePage.tblTasksList);
        }
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//