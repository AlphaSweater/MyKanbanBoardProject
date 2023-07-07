package com.alphasweater.MyTasks;

import java.util.ArrayList;

/* Author: Chad Fairlie
 *  Pseudonym: AlphaSweater
 *  Student Number: ST10269509
 */
public class MyTaskListController {

    //----------------------------------------------------------------------------------------------------------------//
    // Variable to store the number of tasks
    private int numOfTasks;

    // Getter method for the number of tasks
    public int getNumOfTasks() {
        return numOfTasks;
    }

    // Setter method for the number of tasks
    public void setNumOfTasks(int numOfTasks) {
        this.numOfTasks = numOfTasks;
    }
    //----------------------------------------------------------------------------------------------------------------//

    // ArrayList to store the tasks
    private final ArrayList<MyTasksClass> listOfTasks = new ArrayList<>();

    // Getter method for the list of tasks
    public ArrayList<MyTasksClass> getListOfTasks() {
        return listOfTasks;
    }
    //----------------------------------------------------------------------------------------------------------------//

    // Method to calculate the total duration of all tasks
    public int returnTotalHours(ArrayList<MyTasksClass> listOfTasks) {
        int totalHours = 0;
        for (MyTasksClass task : listOfTasks) {
            totalHours += task.getTaskDuration();
        }
        return totalHours;
    }
    //----------------------------------------------------------------------------------------------------------------//

    // Method to find all tasks with status "To Do"
    public ArrayList<String> findAllToDoTasks() {
        ArrayList<String> listOfTaskStatusDone = new ArrayList<>();
        for (MyTasksClass task : this.listOfTasks) {
            if (task.getTaskStatus().equalsIgnoreCase("To Do")) {
                listOfTaskStatusDone.add("-> Developer Details : " + task.getTaskDevInfo() + "\n"
                        + "-> Task Name : " + task.getTaskName() + "\n"
                        + "-> Task Duration : " + task.getTaskDuration());
            }
        }
        return listOfTaskStatusDone;
    }
    //----------------------------------------------------------------------------------------------------------------//

    // Method to find all tasks with status "Doing"
    public ArrayList<String> findAllDoingTasks() {
        ArrayList<String> listOfTaskStatusDone = new ArrayList<>();
        for (MyTasksClass task : this.listOfTasks) {
            if (task.getTaskStatus().equalsIgnoreCase("Doing")) {
                listOfTaskStatusDone.add("-> Developer Details : " + task.getTaskDevInfo() + "\n"
                        + "-> Task Name : " + task.getTaskName() + "\n"
                        + "-> Task Duration : " + task.getTaskDuration());
            }
        }
        return listOfTaskStatusDone;
    }
    //----------------------------------------------------------------------------------------------------------------//

    // Method to find all tasks with status "Done"
    public ArrayList<String> findAllDoneTasks() {
        ArrayList<String> listOfTaskStatusDone = new ArrayList<>();
        for (MyTasksClass task : this.listOfTasks) {
            if (task.getTaskStatus().equalsIgnoreCase("Done")) {
                listOfTaskStatusDone.add("-> Developer Details : " + task.getTaskDevInfo() + "\n"
                        + "-> Task Name : " + task.getTaskName() + "\n"
                        + "-> Task Duration : " + task.getTaskDuration());
            }
        }
        return listOfTaskStatusDone;
    }
    //----------------------------------------------------------------------------------------------------------------//

    // Method to find the task with the longest duration
    public String findLongestTask() {
        if (this.listOfTasks.size() != 0) {
            int longest = this.listOfTasks.get(0).getTaskDuration();
            MyTasksClass longestTask = this.listOfTasks.get(0);

            for (MyTasksClass task : this.listOfTasks) {
                if (task.getTaskDuration() > longest) {
                    longest = task.getTaskDuration();
                    longestTask = task;
                }
            }
            return "-> Task Name : " + longestTask.getTaskName() + "\n"
                    + "-> Developer Details : " + longestTask.getTaskDevInfo() + "\n"
                    + "-> Task Duration : " + longestTask.getTaskDuration();
        }
        return null;
    }
    //----------------------------------------------------------------------------------------------------------------//

    // Method to find the task with the shortest duration
    public String findShortestTask() {
        if (this.listOfTasks.size() != 0) {
            int shortest = this.listOfTasks.get(0).getTaskDuration();
            MyTasksClass shortestTask = this.listOfTasks.get(0);

            for (MyTasksClass task : this.listOfTasks) {
                if (task.getTaskDuration() < shortest) {
                    shortest = task.getTaskDuration();
                    shortestTask = task;
                }
            }
            return "-> Task Name : " + shortestTask.getTaskName() + "\n"
                    + "-> Developer Details : " + shortestTask.getTaskDevInfo() + "\n"
                    + "-> Task Duration : " + shortestTask.getTaskDuration();
        }
        return null;
    }
    //----------------------------------------------------------------------------------------------------------------//

    // Method to search for a task by its name
    public String searchForTask(String taskName) {
        for (MyTasksClass task : this.listOfTasks) {
            if (task.getTaskName().equalsIgnoreCase(taskName)){
                return "-> Task Name : " + task.getTaskName() + "\n"
                        + "-> Developer Details : " + task.getTaskDevInfo() + "\n"
                        + "-> Task Status : " + task.getTaskStatus();
            }
        }
        return null;
    }
    //----------------------------------------------------------------------------------------------------------------//

    // Method to find all tasks assigned to a specific developer
    public ArrayList<String> findAllDevsTasks(String devName) {
        ArrayList<String> listOfDevsTasks = new ArrayList<>();
        for (MyTasksClass task : this.listOfTasks) {
            if (task.getTaskDevInfo().equalsIgnoreCase(devName)){
                listOfDevsTasks.add("-> Task Name : " + task.getTaskName() + "\n"
                        + "-> Task Status : " + task.getTaskStatus());
            }
        }
        return listOfDevsTasks;
    }
    //----------------------------------------------------------------------------------------------------------------//

    // Method to delete a task by its name
    public String deleteTask(String taskToDeleteName) {
        MyTasksClass taskToDelete = null;
        for (MyTasksClass task : this.listOfTasks) {
            if (task.getTaskName().equalsIgnoreCase(taskToDeleteName)){
                taskToDelete = task;
                this.listOfTasks.remove(taskToDelete);
                break;
            }
        }

        if (taskToDelete == null) {
            return "No entry with that name was found";
        } else if (!this.listOfTasks.contains(taskToDelete)) {
            return "Entry " + taskToDeleteName + " successfully deleted";
        }

        return "Task deletion failed";
    }
    //----------------------------------------------------------------------------------------------------------------//

    // Method to find details of all captured tasks
    public ArrayList<String> findAllCapturedTasks() {
        ArrayList<String> listOfTasksInfo = new ArrayList<>();
        for (MyTasksClass task : this.listOfTasks) {
            listOfTasksInfo.add(task.printTaskDetails());
        }
        return listOfTasksInfo;
    }
}

//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//