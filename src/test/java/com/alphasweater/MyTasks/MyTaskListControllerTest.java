package com.alphasweater.MyTasks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        testTaskName = new String[]{"Create Login", "Create Add Features", "Create Reports", "Add Arrays"};
        testTaskDesc = new String[]{"Create Login to authenticate users",
                "This is a long task description that exceeds the maximum allowed characters.",
                "Create the registration feature to register a user", "Adding user input validation"};
        testTaskDevs = new String[]{"Mike Smith", "Edward Harrison", "Samantha Paulson", "Glenda Oberholzer"};
        testTaskDuration = new int[]{5, 8, 2, 11};
        testTaskStatus = new String[]{"To Do", "Doing", "Done", "To Do"};

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

        for (int i = 0; i < testTaskListController.getListOfTasks().size(); i++) {
            testTaskListController.getListOfTasks().add(new MyTasksClass(i, testTaskName[i], testTaskDesc[i],
                    testTaskDuration[i], testTaskStatus[i], testTaskDevs[i]));
        }

        // Test if the total accumulated hours match the expected total hours
        int expectedTotalHours = 0;
        for (int i = 0; i < testTaskListController.getListOfTasks().size(); i++) {
            int hours = testTaskDuration[i];
            expectedTotalHours += hours;
        }

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
        ArrayList<String> expectedTasks = new ArrayList<>();
        for (MyTasksClass task : testTaskListController.getListOfTasks()) {
            if (task.getTaskStatus().equals("Done")) {
                expectedTasks.add("-> Developer Details : " + task.getTaskDevInfo() + "\n"
                        + "-> Task Name : " + task.getTaskName() + "\n"
                        + "-> Task Duration : " + task.getTaskDuration());
            }
        }

        ArrayList<String> result = testTaskListController.findAllDoneTasks();
        Assert.assertEquals("The correct Tasks were not found!!!",expectedTasks, result);
    }
    @Test
    public void testFindAllDoneTasksWithNoTasks() {
        for (MyTasksClass task : testTaskListController.getListOfTasks()) {
            task.setTaskStatus("Doing");
        }

        ArrayList<String> result = testTaskListController.findAllDoneTasks();
        Assert.assertNotNull("They were Null",result);
        Assert.assertTrue("It was not in fact empty",result.isEmpty());
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    public void testFindLongestTask() {
        MyTasksClass expectedTask = testTaskListController.getListOfTasks().get(3); // Get the task with the longest duration from the @Before data
        String expectedResult = "-> Task Name : " + expectedTask.getTaskName() + "\n"
                + "-> Developer Details : " + expectedTask.getTaskDevInfo() + "\n"
                + "-> Task Duration : " + expectedTask.getTaskDuration();
        String result = testTaskListController.findLongestTask();
        Assert.assertEquals("Task Incorrect",expectedResult, result);
    }
    @Test
    public void testFindLongestTaskWithEmptyList() {
        testTaskListController.getListOfTasks().clear(); // Clear the list to simulate an empty list
        String result = testTaskListController.findLongestTask();
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
        String expectedResult = "-> Task Name : " + task5.getTaskName() + "\n"
                + "-> Developer Details : " + task5.getTaskDevInfo() + "\n"
                + "-> Task Duration : " + task5.getTaskDuration();

        String result = testTaskListController.findLongestTask();
        Assert.assertEquals(expectedResult, result);
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    public void testSearchForExistingTask() {
        // Testing for an existing task
        MyTasksClass expectedTask = testTaskListController.getListOfTasks().get(0); // Get the first task from the list
        String expectedResult = "-> Task Name : " + expectedTask.getTaskName() + "\n"
                + "-> Task Description : " + "-> Developer Details : "
                + expectedTask.getTaskDevInfo() + "\n" + "-> Task Status : "
                + expectedTask.getTaskStatus() + "\n";

        String result = testTaskListController.searchForTask(expectedTask.getTaskName());
        Assert.assertEquals("The task was not found", expectedResult, result);
    }
    @Test
    public void testSearchForNonExistingTask() {
        // Testing for a non existing task
        String nonExistingTaskName = "Non-existing Task";
        String result = testTaskListController.searchForTask(nonExistingTaskName);
        Assert.assertNull("The task was not meant to be found!!!", result);
    }
    @Test
    public void testSearchForTaskWithNullName() {
        // Testing a search with a Null name
        String result = testTaskListController.searchForTask(null);
        Assert.assertNull("That should return Null!!!", result);
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    public void testFindAllDevsTasksWithExistingDevName() {
        String devName = testTaskDevs[0]; // Using the first developer name from @Before data

        ArrayList<String> expectedTasks = new ArrayList<String>();
        for (MyTasksClass task : testTaskListController.getListOfTasks()) {
            if (task.getTaskDevInfo().equals(devName)){
                expectedTasks.add("-> Task Name : " + task.getTaskName() + "\n"
                        + "-> Task Status : " + task.getTaskStatus() + "\n");
            }
        }

        ArrayList<String> result = testTaskListController.findAllDevsTasks(devName);
        Assert.assertEquals("",expectedTasks, result);

    }
    @Test
    public void testFindAllDevsTasksWithNoAssignedTasks() {
        String devName = "Non-existing Developer";

        ArrayList<String > result = testTaskListController.findAllDevsTasks(devName);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());
    }
    @Test
    public void testFindAllDevsTasksWithNullDevName() {
        ArrayList<String> result = testTaskListController.findAllDevsTasks(null);
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
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//