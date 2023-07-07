package com.alphasweater.MyTasks;

/*
 * Author: Chad Fairlie
 * Pseudonym: AlphaSweater
 * Student Number: ST10269509
 */

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the MyTasksClass class.
 */
public class MyTasksClassTest {
    //----------------------------------------------------------------------------------------------------------------//
    private MyTasksClass testTaskWorker;
    private MyTaskListController testTaskListController;
    private final String[] testTaskName = {"Login Feature", "Add Task Feature", "Registration Feature", "Input Validation"};
    private final String[] testTaskDesc = {"Create Login to authenticate users",
            "This is a long task description that exceeds the maximum allowed characters.",
            "Create the registration feature to register a user", "Adding user input validation"};
    private final String[] testTaskDevs = {"Robyn Harrison", "Mike Smith", "John Johnson", "Chad Fairlie"};
    private final int[] testTaskDuration = {8, 10, 12, 15};
    private final String[] testTaskStatus = {"To Do", "Doing", "Done", "Doing"};
    //----------------------------------------------------------------------------------------------------------------//
    @Before
    public void setUp() {
        // Create the necessary objects before each test
        testTaskWorker = new MyTasksClass();
        testTaskListController = new MyTaskListController();
    }
    //----------------------------------------------------------------------------------------------------------------//
    @After
    public void tearDown() {
        // Clear the list of tasks after each test
        testTaskListController.getListOfTasks().clear();
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    public void testTaskDescriptionLength_validDescription_ReturnsTrue() {
        String validDescription = "Valid description";

        boolean result = testTaskWorker.checkTaskDescription(validDescription);

        Assert.assertTrue("Task description is expected to be less than or equal to 50 characters", result);
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    public void testTaskDescriptionLength_invalidDescription_ReturnsFalse() {
        String invalidDescription = "This is a long task description that exceeds the maximum allowed characters.";

        boolean result = testTaskWorker.checkTaskDescription(invalidDescription);

        Assert.assertFalse("Task description is expected to be greater than 50 characters", result);
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    public void testTaskIDGeneration() {
        testTaskListController.setNumOfTasks(4);
        String[] expectedTaskIDs = {"LO:0:SON", "AD:1:ITH", "RE:2:SON", "IN:3:LIE"};

        for (int i = 0; i < testTaskListController.getNumOfTasks(); i++) {
            testTaskListController.getListOfTasks().add(new MyTasksClass(i, testTaskName[i], testTaskDesc[i],
                    testTaskDuration[i], testTaskStatus[i], testTaskDevs[i]));

            String actualTaskID = testTaskListController.getListOfTasks().get(i).getTaskID();
            Assert.assertEquals("Task ID is incorrect", expectedTaskIDs[i], actualTaskID);
        }
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//