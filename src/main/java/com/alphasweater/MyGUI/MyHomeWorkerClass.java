package com.alphasweater.MyGUI;

import com.alphasweater.MyTasks.MyTasksClass;
import com.alphasweater.MyUser.MyUserClass;
import com.alphasweater.MyUtil.WordWrapRenderer;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.Component;

/* Author: Chad Fairlie
*  Pseudonym: AlphaSweater
*  Student Number: ST********
---------------------------------------------------------------------------------------------------------------------*/
public class MyHomeWorkerClass {
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
    public MyHomeWorkerClass() {
    }
    public MyHomeWorkerClass(HomePage homePage) {
        this.homePage = homePage;
    }
    public void beginHere() {
        homePage.lblTitle.setText(getTitleHeading());
        // Set the welcome label text to display the user's first and last name
        homePage.lblWelcome.setText(getWelcomeMessage());

        MyTasksClass.setNumOfTasks(10);
    }
    public String getTitleHeading() {
        return "Welcome to EasyKanban.";
    }
    public String getWelcomeMessage() {
        return "Hi " + MyUserClass.getCurrentUser().getUserFirstName() + " "
                + MyUserClass.getCurrentUser().getUserLastName() + ", it is great to see you.";
    }

    protected void logOut(){
        // Dispose the home JFrame
        HomePage.homeFrame.dispose();
        // Create and display the login page
        LoginPage.createLoginPage();

        MyUserClass.setCurrentUser(null);
    }

    // Modifying Custom UI components
    public void editComponents(){
        homePage.tblTasksList = new JTable(data, columnNames) {
            @Override
            public boolean getScrollableTracksViewportWidth() {
                return getPreferredSize().width < getParent().getWidth();
            }
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);

                if (component instanceof JComponent) {
                    ((JComponent) component).setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
                }
                return component;
            }
        };

        homePage.tblTasksList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Set auto resize mode to OFF

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
        columnModel.getColumn(6).setPreferredWidth(40); // Set width for the seventh column (Task Duration)


        homePage.tblScrollPane = new JScrollPane(homePage.tblTasksList);
        homePage.tblScrollPane.setViewportView(homePage.tblTasksList);
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//