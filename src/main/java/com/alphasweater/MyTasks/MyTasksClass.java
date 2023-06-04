package com.alphasweater.MyTasks;

/* Author: Chad Fairlie
*  Pseudonym: AlphaSweater
*  Student Number: ST********
---------------------------------------------------------------------------------------------------------------------*/
public class MyTasksClass {
    private String taskName;

    public String getTaskName() {
        return taskName;
    }

    private int taskNumber;

    public int getTaskNumber() {
        return taskNumber;
    }

    private String taskDescription;
    private String taskDevInfo;

    public String getTaskDevInfo() {
        return taskDevInfo;
    }

    private int taskDuration;
    private String taskID;
    private String taskStatus;

    private static int numOfTasks = 0;

    public static int getNumOfTasks() {
        return numOfTasks;
    }

    public static void setNumOfTasks(int numOfTasks) {
        MyTasksClass.numOfTasks = numOfTasks;
    }

    private static MyTasksClass currentTask;

    public static MyTasksClass getCurrentTask() {
        return currentTask;
    }

    public static void setCurrentTask(MyTasksClass currentTask) {
        MyTasksClass.currentTask = currentTask;
    }

    private static MyTasksClass[] listOfTasks = new MyTasksClass[numOfTasks];

    public static void addTaskToList(MyTasksClass addTask){
        listOfTasks[addTask.taskNumber] = addTask;
    }

    // Default constructor
    public MyTasksClass() {
    }

    public MyTasksClass(int taskNumber, String taskName, String taskDescription, int taskDuration, String taskStatus, String taskDevInfo) {
        this.taskName = taskName;
        this.taskNumber = taskNumber;
        this.taskDescription = taskDescription;
        this.taskDevInfo = taskDevInfo;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.taskID = this.createTaskID();
    }

    public boolean checkTaskDescription(String taskDescription) {
        if (taskDescription.length() > 50){
            return false;
        }
        return true;
    }

    private String createTaskID(){
        String taskNameLastTwoLetters = this.getTaskName().substring(this.getTaskName().length() - 2);
        String taskNum = String.valueOf(this.getTaskNumber());
        String taskDevLastThreeLetters = this.getTaskDevInfo().substring(this.getTaskDevInfo().length() - 3);
        return (taskNameLastTwoLetters + ":" + taskNum + ":" + taskDevLastThreeLetters).toUpperCase();
    }

    public String printTaskDetails(){
        return "Hi";
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//