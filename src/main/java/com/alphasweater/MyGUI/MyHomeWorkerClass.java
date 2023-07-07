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
import java.util.ArrayList;

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
    protected void setHomePage(HomePage homePage) {
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
        // Get the number of tasks from the user
        int numOfTasks = getNumberOfTasks();
        if (numOfTasks <= 0) {
            JOptionPane.showMessageDialog(null, "Invalid amount of tasks! Number of tasks must be greater than 0.");
            return;
        }
        this.taskListController.setNumOfTasks(numOfTasks);

        String[] taskStatusOptions = {"To Do", "Doing", "Done"};

        // Iterate over the number of tasks
        for (int i = 0; i < this.taskListController.getNumOfTasks(); i++) {
            // Display task number to the user
            JOptionPane.showMessageDialog(null, "You are now busy with task " + (this.taskListController.getListOfTasks().size() + 1) + ".");

            // Select the task status
            String taskStatus = selectTaskStatus(taskStatusOptions);

            // Enter the task name
            String taskName = enterTaskName();
            if (taskName == null) {
                int choice = JOptionPane.showConfirmDialog(null, "Cancel entering tasks?");
                if (choice == JOptionPane.YES_OPTION || choice == JOptionPane.CLOSED_OPTION) {
                    return;
                }
            }

            // Enter the task description
            String taskDescription = enterTaskDescription();
            if (taskDescription == null) {
                int choice = JOptionPane.showConfirmDialog(null, "Cancel entering tasks?");
                if (choice == JOptionPane.YES_OPTION || choice == JOptionPane.CLOSED_OPTION) {
                    return;
                }
            }

            // Enter the task developer's information
            String taskDevInfo = enterTaskDeveloperInfo();
            if (taskDevInfo == null) {
                int choice = JOptionPane.showConfirmDialog(null, "Cancel entering tasks?");
                if (choice == JOptionPane.YES_OPTION || choice == JOptionPane.CLOSED_OPTION) {
                    return;
                }
            }

            // Enter the task duration
            int taskDuration = enterTaskDuration();
            if (taskDuration <= 0) {
                continue;
            }

            int taskNum = this.taskListController.getListOfTasks().size();
            MyTasksClass newTask = new MyTasksClass(taskNum, taskName, taskDescription, taskDuration, taskStatus, taskDevInfo);
            this.taskListController.getListOfTasks().add(newTask);

            // Display the task details to the user
            JOptionPane.showMessageDialog(null, this.taskListController.getListOfTasks().get(i).printTaskDetails());
        }

        // Display the total number of hours across all tasks
        JOptionPane.showMessageDialog(null, "The total number of hours across all tasks is: " +
                this.taskListController.returnTotalHours(this.taskListController.getListOfTasks()) + " hrs");

        // Update the table data
        populateTableData();
    }

    // Method to get the number of tasks from the user
    private int getNumberOfTasks() {
        int numOfTasks;
        try {
            numOfTasks = Integer.parseInt(JOptionPane.showInputDialog("Please enter how many tasks you would like to add"));
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Invalid amount of tasks!");
            return 0;
        }
        return numOfTasks;
    }

    // Method to select the task status
    private String selectTaskStatus(String[] taskStatusOptions) {
        int option = JOptionPane.showOptionDialog(null, "Select Task Status:", "Task Status",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, taskStatusOptions, taskStatusOptions[0]);
        // Map the option selected by the user to the corresponding task status
        return switch (option) {
            case 1 -> taskStatusOptions[1];
            case 2 -> taskStatusOptions[2];
            default -> taskStatusOptions[0];
        };
    }

    // Method to enter the task name
    private String enterTaskName() {
        String taskName = null;
        while (taskName == null || taskName.length() < 2) {
            taskName = JOptionPane.showInputDialog("Please enter your task's name.");
            if (taskName == null) {
                // User canceled task entry
                int choice = JOptionPane.showConfirmDialog(null, "Cancel entering tasks?");
                if (choice == JOptionPane.YES_OPTION || choice == JOptionPane.CLOSED_OPTION) {
                    return null;
                }
            } else if (taskName.length() < 2) {
                JOptionPane.showMessageDialog(null, "Your task's name must be greater than 2 characters, please try again");
            }
        }
        return taskName;
    }

    // Method to enter the task description
    private String enterTaskDescription() {
        while (true) {
            String taskDescription = JOptionPane.showInputDialog("Please enter a description for your task (50 characters max).");
            if (taskDescription == null) {
                // User canceled task entry
                int choice = JOptionPane.showConfirmDialog(null, "Cancel entering tasks?");
                if (choice == JOptionPane.YES_OPTION || choice == JOptionPane.CLOSED_OPTION) {
                    return null;
                }
            } else if (!taskDescription.isEmpty() && this.taskWorker.checkTaskDescription(taskDescription)) {
                // Check if the task description is valid
                return taskDescription;
            } else {
                JOptionPane.showMessageDialog(null, "Task description is either empty or exceeded 50 characters, please try again");
            }
        }
    }

    // Method to enter the task developer's information
    private String enterTaskDeveloperInfo() {
        while (true) {
            String taskDevInfo = JOptionPane.showInputDialog("Please enter the task developer's full name");
            if (taskDevInfo == null) {
                // User canceled task entry
                int choice = JOptionPane.showConfirmDialog(null, "Cancel entering tasks?");
                if (choice == JOptionPane.YES_OPTION || choice == JOptionPane.CLOSED_OPTION) {
                    return null;
                }
            } else if (taskDevInfo.length() < 2) {
                JOptionPane.showMessageDialog(null, "The task developer's name must be greater than 2 characters, please try again");
            } else {
                return taskDevInfo;
            }
        }
    }

    // Method to enter the task duration
    private int enterTaskDuration() {
        int taskDuration = 0;
        while (true) {
            try {
                taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Please enter the estimated task duration"));
                if (taskDuration <= 0) {
                    JOptionPane.showMessageDialog(null, "Invalid input for task duration! Duration must be a positive number.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input for task duration! Please try again.");
            } catch (NullPointerException e) {
                // User canceled task entry
                break;
            }
        }
        return taskDuration;
    }
    //----------------------------------------------------------------------------------------------------------------//
    protected void beginShowReportHere() {
        boolean closeReport = false;
        while (!closeReport) {
            // Display options in a dialog
            String[] options = {"Search For", "Find Task By Duration", "Delete Tasks", "Show all Tasks", "QUIT"};
            int choice = JOptionPane.showOptionDialog(null, "Choose an option:", "Options",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);

            // Execute different actions based on the user's choice
            switch (choice) {
                case 0 -> searchTasks(); // Option: Search for tasks
                case 1 -> findTasksByDuration(); // Option: Find tasks by duration
                case 2 -> {
                    deleteTasks(); // Option: Delete tasks
                    populateTableData(); // Update table data after deletion
                }
                case 3 -> showAllTasks(); // Option: Show all tasks
                default -> closeReport = true; // Option: Quit the program
            }
        }
    }

    private void searchTasks() {
        boolean closeSearch = false;
        while (!closeSearch) {
            // Display search options in a dialog
            String[] searchOptions = {"By Task Name", "By Task Developer", "By Task Status", "BACK"};
            int searchChoice = JOptionPane.showOptionDialog(null, "How would you like to Search:",
                    "Searching", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, searchOptions,
                    null);

            // Execute different search actions based on the user's choice
            switch (searchChoice) {
                case 0 -> {
                    // Search by task name
                    String inTaskName = JOptionPane.showInputDialog(null,
                            "Please Enter the name of the task:");
                    // Perform the search and display the result
                    String result = taskListController.searchForTask(inTaskName);
                    if (result != null) {
                        JOptionPane.showMessageDialog(null, "Task \"" + inTaskName
                                + "\" successfully found" + "\n"
                                + "-------------------------------------------------------------------------"
                                + "\n" + result);
                    } else {
                        JOptionPane.showMessageDialog(null, "No Tasks found");
                    }
                }
                case 1 -> {
                    // Search by task developer
                    String inDevName = JOptionPane.showInputDialog(null,
                            "Please Enter the name of the Developer:");
                    // Perform the search and display the results
                    ArrayList<String> results = taskListController.findAllDevsTasks(inDevName);
                    if (!results.isEmpty()) {
                        int numTasks = results.size();
                        String message = (numTasks == 1) ? "1 Task has been found!" : numTasks
                                + " Tasks have been found!";
                        JOptionPane.showMessageDialog(null, message);
                        for (int i = 0; i < results.size(); i++) {
                            String taskInfo = results.get(i);
                            JOptionPane.showMessageDialog(null, "Here is Task "
                                    + (i + 1)
                                    + " for Developer: " + inDevName + "\n"
                                    + "-------------------------------------------------------------------------"
                                    + "\n" + taskInfo);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No Tasks found");
                    }
                }
                case 2 -> searchTasksByStatus(); // Option: Search by task status
                default -> closeSearch = true; // Option: Go back
            }
        }
    }

    private void searchTasksByStatus() {
        boolean closeStatusSearch = false;
        while (!closeStatusSearch) {
            // Display status search options in a dialog
            String[] statusOptions = {"By \"To Do\"", "By \"Doing\"", "By \"Done\"", "BACK"};
            int statusChoice = JOptionPane.showOptionDialog(null,
                    "Which Task Status would you like to search for?:",
                    "Searching", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, statusOptions, null);

            // Perform search actions based on the user's choice
            switch (statusChoice) {
                case 0 -> {
                    // Search tasks with status "To Do"
                    displayTasks(taskListController.findAllToDoTasks());
                    closeStatusSearch = true;
                }
                case 1 -> {
                    // Search tasks with status "Doing"
                    displayTasks(taskListController.findAllDoingTasks());
                    closeStatusSearch = true;
                }
                case 2 -> {
                    // Search tasks with status "Done"
                    displayTasks(taskListController.findAllDoneTasks());
                    closeStatusSearch = true;
                }
                default -> closeStatusSearch = true; // Option: Go back
            }
        }
    }

    private void findTasksByDuration() {
        boolean closeDurationSearch = false;
        while (!closeDurationSearch) {
            // Display search options in a dialog
            String[] searchOptions = {"Find Longest Task", "Find Shortest Task", "BACK"};
            int searchChoice = JOptionPane.showOptionDialog(null,
                    "What Would you Like to Find?",
                    "Searching", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, searchOptions, null);

            // Execute different find actions based on the user's choice
            switch (searchChoice) {
                case 0 -> {
                    // Find task with the longest duration
                    if (taskListController.findLongestTask() != null) {
                        displayTask("Found Task with the Longest Duration:",
                                taskListController.findLongestTask());
                    } else {
                        JOptionPane.showMessageDialog(null, "No Tasks found");
                    }
                }
                case 1 -> {
                    // Find task with the shortest duration
                    if (taskListController.findShortestTask() != null) {
                        displayTask("Found Task with the Shortest Duration:",
                                taskListController.findShortestTask());
                    } else {
                        JOptionPane.showMessageDialog(null, "No Tasks found");
                    }
                }
                default -> closeDurationSearch = true; // Option: Go back
            }
        }
    }

    private void showAllTasks() {
        // Retrieve all tasks and display them
        ArrayList<String> results = taskListController.findAllCapturedTasks();
        displayTasks(results);
    }

    private void displayTasks(ArrayList<String> tasks) {
        if (!tasks.isEmpty()) {
            int numTasks = tasks.size();
            String message = (numTasks == 1) ? "1 Task has been found!" : numTasks + " Tasks have been found!";
            JOptionPane.showMessageDialog(null, message);

            // Display each task in a separate dialog
            for (int i = 0; i < tasks.size(); i++) {
                String taskInfo = tasks.get(i);
                JOptionPane.showMessageDialog(null, "Here is Task "
                        + (i + 1)
                        + "\n"
                        + "-------------------------------------------------------------------------"
                        + "\n" + taskInfo);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No Tasks found");
        }
    }

    private void displayTask(String message, String taskInfo) {
        JOptionPane.showMessageDialog(null, message + "\n"
                + "-------------------------------------------------------------------------" + "\n"
                + taskInfo);
    }

    private void deleteTasks() {
        // Prompt for task name to delete
        String inTaskName = JOptionPane.showInputDialog(null,
                "Please Enter the name of the task you would like to delete:");
        // Search for the task and display the result
        String result = taskListController.searchForTask(inTaskName);
        if (result != null) {
            // Ask for confirmation before deleting the task
            int userChoice = JOptionPane.showConfirmDialog(null, "Task \"" + inTaskName
                    + "\" successfully found" + "\n"
                    + "-------------------------------------------------------------------------"
                    + "\n" + result + "\n" + "-------------------------------------------------------------------------"
                    + "\n" + "Are you Sure you want to Delete this Task?");
            if (userChoice == JOptionPane.YES_OPTION) {
                // Delete the task
                taskListController.deleteTask(inTaskName);
                JOptionPane.showMessageDialog(null, "Task Successfully Deleted");
            } else {
                JOptionPane.showMessageDialog(null, "Task Will Not be Deleted");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No Tasks found");
        }
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
            this.homePage.lblTotalHours.setText("Total Number Of Hours Across All Tasks = "
                    + this.taskListController.returnTotalHours(this.taskListController.getListOfTasks()) + " hrs");
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
            this.homePage.tblTasksList.setPreferredScrollableViewportSize(new Dimension(tableWidth
                    , this.homePage.tblTasksList.getPreferredSize().height));

            this.homePage.tblScrollPane.setViewportView(this.homePage.tblTasksList);
        }
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//