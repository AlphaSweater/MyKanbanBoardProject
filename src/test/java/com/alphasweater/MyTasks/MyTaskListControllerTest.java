package com.alphasweater.MyTasks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.util.ArrayList;

/* Author: Chad Fairlie
*  Pseudonym: AlphaSweater
*  Student Number: ST10269509
----------------------------------------------------------------------------------------------------------------------*/
public class MyTaskListControllerTest {
    MyTaskListController testTaskListController = new MyTaskListController();
    String[] testTaskName;
    String[] testTaskDesc;
    String[] testTaskDevs;
    int[] testTaskDuration;
    String[] testTaskStatus;
    //----------------------------------------------------------------------------------------------------------------//
    @Before
    public void createTestObjects() {
        testTaskListController = new MyTaskListController();
        testTaskName = new String[]{"Login Feature", "Add Task Feature", "Registration Feature", "Input Validation"};
        testTaskDesc = new String[]{"Create Login to authenticate users",
                "This is a long task description that exceeds the maximum allowed characters.",
                "Create the registration feature to register a user", "Adding user input validation"};
        testTaskDevs = new String[]{"Robyn Harrison", "Mike Smith", "John Johnson", "Chad Fairlie"};
        testTaskDuration = new int[]{8, 10, 12, 15};
        testTaskStatus = new String[]{"To Do", "Doing", "Done", "Doing"};

        testTaskListController.setNumOfTasks(4);
        for (int i = 0; i < testTaskListController.getNumOfTasks(); i++) {
            testTaskListController.getListOfTasks().add(new MyTasksClass(i, testTaskName[i], testTaskDesc[i],
                    testTaskDuration[i], testTaskStatus[i], testTaskDevs[i]));
        }
    }
    @After
    public void clearTaskList(){
        testTaskListController.getListOfTasks().clear();
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    // Test the accumulation of total hours for a given set of tasks
    public void testCaseTotalHoursAccumulation(){
        testTaskListController.getListOfTasks().clear();
        //------------------------------------------------------------------------------//
        // Makes use of test case data from the @Before data                            //
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
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    // Test the accumulation of total hours for a given set of hardcoded tasks
    public void testTotalHoursAccumulation() {
        testTaskListController.getListOfTasks().clear();
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
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    public void testFindAllDoneTasksWithMultipleTasks() {
        ArrayList<MyTasksClass> expectedTasks = new ArrayList<>();
        for (MyTasksClass task : testTaskListController.getListOfTasks()) {
            if (task.getTaskStatus().equals("Done")) {
                expectedTasks.add(task);
            }
        }
        ArrayList<MyTasksClass> result = testTaskListController.findAllDoneTasks();
        Assert.assertEquals("The correct Tasks were not found!!!",expectedTasks, result);
    }
    @Test
    public void testFindAllDoneTasksWithNoTasks() {
        for (MyTasksClass task : testTaskListController.getListOfTasks()) {
            task.setTaskStatus("Doing");
        }

        ArrayList<MyTasksClass> result = testTaskListController.findAllDoneTasks();
        Assert.assertNotNull("They were Null",result);
        Assert.assertTrue("It was not in fact empty",result.isEmpty());
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    public void testFindLongestTask() {
        MyTasksClass expectedTask = testTaskListController.getListOfTasks().get(3); // Get the task with the longest duration from the @Before data
        MyTasksClass result = testTaskListController.findLongestTask();
        Assert.assertEquals(expectedTask, result);
    }
    @Test
    public void testFindLongestTaskWithEmptyList() {
        testTaskListController.getListOfTasks().clear(); // Clear the list to simulate an empty list
        MyTasksClass result = testTaskListController.findLongestTask();
        Assert.assertNull(result);
    }
    @Test
    public void testFindLongestTaskWithEqualDurations() {
        MyTasksClass task5 = new MyTasksClass(5, "Task 5", "Description 1",
                20, "To Do", "Developer 5");
        MyTasksClass task6 = new MyTasksClass(6, "Task 6", "Description 2",
                20, "To Do", "Developer 6");
        testTaskListController.getListOfTasks().add(task5);
        testTaskListController.getListOfTasks().add(task6);

        MyTasksClass result = testTaskListController.findLongestTask();
        Assert.assertEquals(task5, result);
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    public void testSearchForExistingTask() {
        // Testing for an existing task
        MyTasksClass expectedTask = testTaskListController.getListOfTasks().get(0); // Get the first task from the list
        MyTasksClass result = testTaskListController.searchForTask(expectedTask.getTaskName());
        Assert.assertEquals("The task was not found", expectedTask, result);
    }
    @Test
    public void testSearchForNonExistingTask() {
        // Testing for a non existing task
        String nonExistingTaskName = "Non-existing Task";
        MyTasksClass result = testTaskListController.searchForTask(nonExistingTaskName);
        Assert.assertNull("The task was not meant to be found!!!", result);
    }
    @Test
    public void testSearchForTaskWithNullName() {
        // Testing a search with a Null name
        MyTasksClass result = testTaskListController.searchForTask(null);
        Assert.assertNull("That should return Null!!!", result);
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    public void testFindAllDevsTasksWithExistingDevName() {
        String devName = testTaskDevs[0]; // Using the first developer name from @Before data

        ArrayList<MyTasksClass> expectedTasks = new ArrayList<>();
        for (MyTasksClass task : testTaskListController.getListOfTasks()) {
            if (task.getTaskDevInfo().equals(devName)) {
                expectedTasks.add(task);
            }
        }

        ArrayList<MyTasksClass> result = testTaskListController.findAllDevsTasks(devName);
        Assert.assertEquals("",expectedTasks, result);
    }
    @Test
    public void testFindAllDevsTasksWithNoAssignedTasks() {
        String devName = "Non-existing Developer";

        ArrayList<MyTasksClass> result = testTaskListController.findAllDevsTasks(devName);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());
    }
    @Test
    public void testFindAllDevsTasksWithNullDevName() {
        ArrayList<MyTasksClass> result = testTaskListController.findAllDevsTasks(null);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    public void testDeleteExistingTask() {
        String taskToDeleteName = testTaskName[0]; // Get the name of the first task
        MyTasksClass taskToDelete = testTaskListController.getListOfTasks().get(0);
        String expectedResult = "Entry " + taskToDeleteName + " successfully deleted";

        String result = testTaskListController.deleteTask(taskToDeleteName);
        Assert.assertEquals(expectedResult, result);
        Assert.assertFalse(testTaskListController.getListOfTasks().contains(taskToDelete));
    }
    @Test
    public void testDeleteNonExistingTask() {
        String nonExistingTaskName = "Non-existing Task";
        String expectedResult = "No entry with that name was found";

        String result = testTaskListController.deleteTask(nonExistingTaskName);
        Assert.assertEquals(expectedResult, result);
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    public void testFindAllCapturedTasks() {
        ArrayList<String> expectedTasksInfo = new ArrayList<>();
        for (MyTasksClass task : testTaskListController.getListOfTasks()) {
            expectedTasksInfo.add(task.printTaskDetails());
        }

        ArrayList<String> result = testTaskListController.findAllCapturedTasks();
        Assert.assertEquals(expectedTasksInfo, result);
    }
    //----------------------------------------------------------------------------------------------------------------//
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//