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
    public void setTaskName(String taskName) {
        this.taskName = taskName;
        regenerateTaskID();
    }
    //----------------------------------------------------------------------------------------------------------------//
    private int taskNumber;
    public int getTaskNumber() {
        return taskNumber;
    }
    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
        regenerateTaskID();
    }
    //----------------------------------------------------------------------------------------------------------------//
    private String taskDescription;
    public String getTaskDescription() {
        return taskDescription;
    }
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    //----------------------------------------------------------------------------------------------------------------//
    private String taskDevInfo;
    public String getTaskDevInfo() {
        return taskDevInfo;
    }
    public void setTaskDevInfo(String taskDevInfo) {
        this.taskDevInfo = taskDevInfo;
        regenerateTaskID();
    }
    //----------------------------------------------------------------------------------------------------------------//
    private int taskDuration;
    public int getTaskDuration() {
        return taskDuration;
    }
    public void setTaskDuration(int taskDuration) {
        this.taskDuration = taskDuration;
    }
    //----------------------------------------------------------------------------------------------------------------//
    private String taskID;
    public String getTaskID() {
        return taskID;
    }
    public void regenerateTaskID() {
        this.taskID = this.createTaskID();
    }
    //----------------------------------------------------------------------------------------------------------------//
    private String taskStatus;
    public String getTaskStatus() {
        return taskStatus;
    }
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
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
        return "-> Task Status : " + this.getTaskStatus() + "\n"
                + "-> Developer Details : " + this.getTaskDevInfo() + "\n"
                + "-> Task Number : " + this.getTaskNumber() + "\n"
                + "-> Task Name : " + this.getTaskName() + "\n"
                + "-> Task Description : " + this.getTaskDescription() + "\n"
                + "-> Task ID : " + this.getTaskID() + "\n"
                + "-> Task Duration : " + this.getTaskDuration();
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//