package com.alphasweater.MyGUI;

import com.alphasweater.MyTasks.MyTasksClass;
import com.alphasweater.MyTasks.MyTaskListController;
import com.alphasweater.MyUser.MyUserClass;
import com.alphasweater.MyUtil.WordWrapRenderer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.Dimension;
import java.awt.HeadlessException;

/* Author: Chad Fairlie
*  Pseudonym: AlphaSweater
*  Student Number: ST10269509
----------------------------------------------------------------------------------------------------------------------*/
public class MyHomeWorkerClass {
    protected MyUserClass currentUser;

    public void setCurrentUser(MyUserClass user) {
        this.currentUser = user;
    }

    private boolean tblPopulated = false;
    protected final String[] columnNames = {"Task Status", "Developer Details", "Task Number", "Task Name",
            "Task Description", "Task ID", "Duration"};
    //----------------------------------------------------------------------------------------------------------------//
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
    private final MyTasksClass taskWorker = new MyTasksClass();
    private final MyTaskListController taskListController = new MyTaskListController();
    //----------------------------------------------------------------------------------------------------------------//
    // Default Constructor
    public MyHomeWorkerClass() {
    }
    //----------------------------------------------------------------------------------------------------------------//
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
        resetTaskList();
    }
    //----------------------------------------------------------------------------------------------------------------//
    protected String getWelcomeMessage() {
        return "Hi " + this.currentUser.getUserFirstName() + " "
                + this.currentUser.getUserLastName() + ", it is great to see you.";
    }
    //----------------------------------------------------------------------------------------------------------------//
    protected void beginAddTasks() {
        int numOfTasks;
        try {
            numOfTasks = Integer.parseInt(JOptionPane.showInputDialog("Please enter how many tasks you would like to" +
                    " add"));
            if (numOfTasks <= 0) {
                JOptionPane.showMessageDialog(null, "Invalid amount of tasks! Number of tasks" +
                        " must be greater than 0.");
                return;
            }
            this.taskListController.setNumOfTasks(numOfTasks);
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Invalid amount of tasks!");
            throw new RuntimeException(e);
        }

        String[] taskStatusOptions = {"To Do", "Doing", "Done"};

        for (int i = 0; i < this.taskListController.getNumOfTasks(); i++) {
            JOptionPane.showMessageDialog(null, "You are now busy with task "
                    + (this.taskListController.getListOfTasks().size() + 1) + ".");

            String taskStatus;
            int option = JOptionPane.showOptionDialog(null, "Select Task Status:", "Task Status",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, taskStatusOptions, taskStatusOptions[0]);
            taskStatus = switch (option) {
                case 1 -> taskStatusOptions[1];
                case 2 -> taskStatusOptions[2];
                default -> taskStatusOptions[0];
            };

            String taskName = null;
            while (taskName == null || taskName.length() < 2) {
                taskName = JOptionPane.showInputDialog("Please enter your task's name.");
                if (taskName == null) {
                    int choice = JOptionPane.showConfirmDialog(null, "Cancel entering tasks?");
                    if (choice == JOptionPane.YES_OPTION || choice == JOptionPane.CLOSED_OPTION) {
                        return; // Exit the method if the user cancels entering tasks
                    }
                } else if (taskName.length() < 2) {
                    JOptionPane.showMessageDialog(null, "Your task's name must be greater than 2 characters, please try again");
                }
            }

            String taskDescription;
            while (true) {
                taskDescription = JOptionPane.showInputDialog("Please enter a description for your task (50 characters max).");
                if (taskDescription == null) {
                    int choice = JOptionPane.showConfirmDialog(null, "Cancel entering tasks?");
                    if (choice == JOptionPane.YES_OPTION || choice == JOptionPane.CLOSED_OPTION) {
                        return; // Exit the method if the user cancels entering tasks
                    }
                } else if (!taskDescription.isEmpty() && this.taskWorker.checkTaskDescription(taskDescription)) {
                    break; // Exit the loop if the task description is valid
                } else {
                    JOptionPane.showMessageDialog(null, "Task description is either empty or exceeded 50 characters, please try again");
                }
            }

            String taskDevInfo;
            while (true) {
                taskDevInfo = JOptionPane.showInputDialog("Please enter the task developer's full name");
                if (taskDevInfo == null) {
                    int choice = JOptionPane.showConfirmDialog(null, "Cancel entering tasks?");
                    if (choice == JOptionPane.YES_OPTION || choice == JOptionPane.CLOSED_OPTION) {
                        return; // Exit the method if the user cancels entering tasks
                    }
                } else if (taskDevInfo.length() < 2) {
                    JOptionPane.showMessageDialog(null, "The task developer's name must be greater than 2 characters, please try again");
                } else {
                    break; // Exit the loop if the task developer info is valid
                }
            }

            int taskDuration = 0;
            while (true) {
                try {
                    taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Please enter the estimated task duration"));
                    if (taskDuration <= 0) {
                        JOptionPane.showMessageDialog(null, "Invalid input for task duration! Duration must be a positive number.");
                        continue;
                    }
                    break; // Exit the loop if parsing is successful and duration is positive
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input for task duration! Please try again.");
                } catch (NullPointerException e) {
                    // User pressed cancel, return to the beginning of the loop iteration
                    break;
                }
            }

            int taskNum = this.taskListController.getListOfTasks().size();
            MyTasksClass newTask = new MyTasksClass(taskNum, taskName, taskDescription, taskDuration, taskStatus, taskDevInfo);
            this.taskListController.getListOfTasks().add(newTask);
            JOptionPane.showMessageDialog(null, this.taskListController.getListOfTasks().get(i).printTaskDetails());
        }

        JOptionPane.showMessageDialog(null, "The total number of hours across all tasks is: " +
                this.taskListController.returnTotalHours(this.taskListController.getListOfTasks()) + " hrs");
        // Update the listOfTasks in MyTasksClass

        populateTableData();
    }
    //----------------------------------------------------------------------------------------------------------------//
    protected void beginShowReportHere() {
        JOptionPane.showMessageDialog(null, "Coming Soon...");
    }
    //----------------------------------------------------------------------------------------------------------------//
    protected void resetTaskList() {
        this.taskListController.getListOfTasks().clear();
        populateTableData();
    }
    //----------------------------------------------------------------------------------------------------------------//
    protected void populateTableData() {
        int rows = this.taskListController.getListOfTasks().size(); // number of rows in the 2D array
        int columns = 7; // number of columns in the 2D array

        Object[][] data = new Object[rows][columns];

        for (int i = 0; i < rows; i++) {
            MyTasksClass task = this.taskListController.getListOfTasks().get(i);
            data[i][0] = task.getTaskStatus();
            data[i][1] = task.getTaskDevInfo();
            data[i][2] = task.getTaskNumber();
            data[i][3] = task.getTaskName();
            data[i][4] = task.getTaskDescription();
            data[i][5] = task.getTaskID();
            data[i][6] = task.getTaskDuration();
        }

        // Call the method to update the table UI components
        this.homePage.model = new DefaultTableModel(data, this.columnNames);
        this.tblPopulated = true;
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
        if (this.tblPopulated) {
            this.homePage.lblTotalHours.setText("Total Number Of Hours Across All Tasks = " + this.taskListController.returnTotalHours(this.taskListController.getListOfTasks()) + " hrs");
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
            WordWrapRenderer.setColumnWidth(this.homePage.tblTasksList, 1, 30); // Adjust margin value as needed
            WordWrapRenderer.setColumnWidth(this.homePage.tblTasksList, 3, 20); // Adjust margin value as needed
            WordWrapRenderer.setColumnWidth(this.homePage.tblTasksList, 4, 30); // Adjust margin value as needed

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