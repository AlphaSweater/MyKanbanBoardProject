package com.alphasweater.MyTasks;

import org.junit.Assert;
import org.junit.Test;

/* Author: Chad Fairlie
*  Pseudonym: AlphaSweater
*  Student Number: ST10269509
----------------------------------------------------------------------------------------------------------------------*/
public class MyTaskListControllerTest {
    final MyTaskListController testTaskListController = new MyTaskListController();
    final String[] testTaskName = {"Login Feature", "Add Task Feature", "Registration Feature", "Input Validation"};
    final String[] testTaskDesc = {"Create Login to authenticate users",
            "This is a long task description that exceeds the maximum allowed characters.",
            "Create the registration feature to register a user", "Adding user input validation"};
    final String[] testTaskDevs = {"Robyn Harrison", "Mike Smith", "John Johnson", "Chad Fairlie"};
    final int[] testTaskDuration = {8, 10, 12, 15};
    final String[] testTaskStatus = {"To Do", "Doing", "Done", "Doing"};

    //----------------------------------------------------------------------------------------------------------------//
    @Test
    // Test the accumulation of total hours for a given set of tasks
    public void testCaseTotalHoursAccumulation(){
        //------------------------------------------------------------------------------//
        // Makes use of test case data declared in arrays at the beginning of the class //
        // -----------------------------------------------------------------------------//
        testTaskListController.setNumOfTasks(2);

        for (int i = 0; i < testTaskListController.getNumOfTasks(); i++) {
            testTaskListController.getListOfTasks().add(new MyTasksClass(i, testTaskName[i], testTaskDesc[i],
                    testTaskDuration[i], testTaskStatus[i], testTaskDevs[i]));
        }

        // Test if the total accumulated hours match the expected total hours
        int expectedTotalHours = 18;
        int actualTotalHours = testTaskListController.returnTotalHours(testTaskListController.getListOfTasks());
        Assert.assertEquals("Total hours accumulated is incorrect", expectedTotalHours, actualTotalHours);

        testTaskListController.getListOfTasks().clear();
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    // Test the accumulation of total hours for a given set of hardcoded tasks
    public void testTotalHoursAccumulation() {
        //-----------------------------------------------------------//
        // Makes use of hard coded data provided in Project document //
        // ----------------------------------------------------------//
        testTaskListController.getListOfTasks().add(new MyTasksClass(0, "Task 1",
                "Description 1", 10, "To Do", "Developer 1"));
        testTaskListController.getListOfTasks().add(new MyTasksClass(1, "Task 2",
                "Description 2", 12, "To Do", "Developer 2"));
        testTaskListController.getListOfTasks().add(new MyTasksClass(2, "Task 3",
                "Description 3", 55, "To Do", "Developer 3"));
        testTaskListController.getListOfTasks().add(new MyTasksClass(3, "Task 4",
                "Description 4", 11, "To Do", "Developer 4"));
        testTaskListController.getListOfTasks().add(new MyTasksClass(4, "Task 5",
                "Description 5", 1, "To Do", "Developer 5"));

        // Test if the total accumulated hours match the expected total hours
        int expectedTotalHours = 89;
        int actualTotalHours = testTaskListController.returnTotalHours(testTaskListController.getListOfTasks());
        Assert.assertEquals("Total hours accumulated is incorrect", expectedTotalHours, actualTotalHours);

        testTaskListController.getListOfTasks().clear();
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    public void test
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//