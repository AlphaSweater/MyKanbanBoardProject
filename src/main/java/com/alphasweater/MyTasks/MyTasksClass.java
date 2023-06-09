package com.alphasweater.MyTasks;

/* Author: Chad Fairlie
*  Pseudonym: AlphaSweater
*  Student Number: ST10269509
---------------------------------------------------------------------------------------------------------------------*/
public class MyTasksClass {
    private String taskName;

    public String getTaskName() {
        return taskName;
    }

    //----------------------------------------------------------------------------------------------------------------//
    private int taskNumber;

    public int getTaskNumber() {
        return taskNumber;
    }

    //----------------------------------------------------------------------------------------------------------------//
    private String taskDescription;

    public String getTaskDescription() {
        return taskDescription;
    }

    //----------------------------------------------------------------------------------------------------------------//
    private String taskDevInfo;

    public String getTaskDevInfo() {
        return taskDevInfo;
    }

    //----------------------------------------------------------------------------------------------------------------//
    private int taskDuration;

    public int getTaskDuration() {
        return taskDuration;
    }

    //----------------------------------------------------------------------------------------------------------------//
    private String taskID;

    public String getTaskID() {
        return taskID;
    }

    //----------------------------------------------------------------------------------------------------------------//
    private String taskStatus;

    public String getTaskStatus() {
        return taskStatus;
    }

    //----------------------------------------------------------------------------------------------------------------//
    private int totalHours = 0;

    public int returnTotalHours(MyTasksClass[] listOfTasks) {
        for (MyTasksClass task : listOfTasks) {
            this.totalHours += task.getTaskDuration();
        }
       return this.totalHours;
    }

    //----------------------------------------------------------------------------------------------------------------//
    private int numOfTasks = 0;

    public int getNumOfTasks() {
        return numOfTasks;
    }

    public void setNumOfTasks(int numOfTasks) {
        this.numOfTasks = numOfTasks;
    }

    //----------------------------------------------------------------------------------------------------------------//
    private MyTasksClass currentTask;

    public MyTasksClass getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(MyTasksClass currentTask) {
        this.currentTask = currentTask;
    }

    //----------------------------------------------------------------------------------------------------------------//
    private MyTasksClass[] listOfTasks;

    public MyTasksClass[] getListOfTasks() {
        return listOfTasks;
    }

    public void setListOfTasks(MyTasksClass[] listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    //----------------------------------------------------------------------------------------------------------------//
    // Default constructor
    public MyTasksClass() {
    }

    //----------------------------------------------------------------------------------------------------------------//
    public MyTasksClass(int taskNumber, String taskName, String taskDescription, int taskDuration, String taskStatus, String taskDevInfo) {
        this.taskName = taskName;
        this.taskNumber = taskNumber;
        this.taskDescription = taskDescription;
        this.taskDevInfo = taskDevInfo;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.taskID = this.createTaskID();
        totalHours += taskDuration;
    }

    //----------------------------------------------------------------------------------------------------------------//
    public boolean checkTaskDescription(String taskDescription) {
        return taskDescription.length() <= 50;
    }

    //----------------------------------------------------------------------------------------------------------------//
    private String createTaskID() {
        String taskNameFirstTwoLetters = this.getTaskName().substring(0, 2);
        String taskNum = String.valueOf(this.getTaskNumber());
        String taskDevLastThreeLetters = this.getTaskDevInfo().substring(this.getTaskDevInfo().length() - 3);
        return (taskNameFirstTwoLetters + ":" + taskNum + ":" + taskDevLastThreeLetters).toUpperCase();
    }

    //----------------------------------------------------------------------------------------------------------------//
    public String printTaskDetails() {
        //TODO: Come Back here
        return "Hi";
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//