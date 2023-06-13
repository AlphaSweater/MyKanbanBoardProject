package com.alphasweater.MyTasks;

/*
 * Author: Chad Fairlie
 * Pseudonym: AlphaSweater
 * Student Number: ST10269509
 */

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for the MyTasksClass class.
 */
public class MyTasksClassTest {
    final MyTasksClass testTaskWorker = new MyTasksClass();
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
    // Test the length of task descriptions
    public void testTaskDescriptionLength() {
        //-------------------------------------------------------------------------//
        // Makes use of test data declared in arrays at the beginning of the class //
        //-------------------------------------------------------------------------//
        testTaskListController.setNumOfTasks(2);

        for (int i = 0; i < testTaskListController.getNumOfTasks(); i++) {
            testTaskListController.getListOfTasks().add(new MyTasksClass(i, testTaskName[i], testTaskDesc[i],
                    testTaskDuration[i], testTaskStatus[i], testTaskDevs[i]));
        }

        // Test if the task description is less than or equal to 50 characters
        boolean result1 = testTaskWorker.checkTaskDescription(testTaskListController.getListOfTasks().get(0).getTaskDescription());
        Assert.assertTrue("Task description is expected to be less than or equal to 50 characters", result1);

        // Test if the task description is greater than 50 characters
        boolean result2 = testTaskWorker.checkTaskDescription(testTaskListController.getListOfTasks().get(1).getTaskDescription());
        Assert.assertFalse("Task description is expected to be greater than 50 characters", result2);

        testTaskListController.getListOfTasks().clear();
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    // Test the generation of task IDs
    public void testTaskIDGeneration() {
        //-------------------------------------------------------------------------//
        // Makes use of test data declared in arrays at the beginning of the class //
        //-------------------------------------------------------------------------//
        testTaskListController.setNumOfTasks(4);

        String[] expectedTaskIDs = {"LO:0:SON", "AD:1:ITH", "RE:2:SON", "IN:3:LIE"};
        for (int i = 0; i < testTaskListController.getNumOfTasks(); i++) {
            testTaskListController.getListOfTasks().add(new MyTasksClass(i, testTaskName[i], testTaskDesc[i],
                    testTaskDuration[i], testTaskStatus[i], testTaskDevs[i]));

            // Test if the generated task ID matches the expected task ID
            String actualTaskID = testTaskListController.getListOfTasks().get(i).getTaskID();
            Assert.assertEquals("Task ID is incorrect", expectedTaskIDs[i], actualTaskID);
        }
        testTaskListController.getListOfTasks().clear();
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//