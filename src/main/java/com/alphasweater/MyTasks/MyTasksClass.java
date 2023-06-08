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
    public String getTaskDescription() {
        return taskDescription;
    }

    private String taskDevInfo;
    public String getTaskDevInfo() {
        return taskDevInfo;
    }

    private int taskDuration;
    public int getTaskDuration() {
        return taskDuration;
    }

    private String taskID;
    public String getTaskID() {
        return taskID;
    }

    private String taskStatus;
    public String getTaskStatus() {
        return taskStatus;
    }

    private static int totalHours = 0;
    public static int getTotalHours() {
        return totalHours;
    }

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
    public static MyTasksClass[] getListOfTasks() {
        return listOfTasks;
    }
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
        totalHours += taskDuration;
    }

    public boolean checkTaskDescription(String taskDescription) {
        if (taskDescription.length() > 50){
            return false;
        }
        return true;
    }

    private String createTaskID(){
        String taskNameFirstTwoLetters = this.getTaskName().substring(0,2);
        String taskNum = String.valueOf(this.getTaskNumber());
        String taskDevLastThreeLetters = this.getTaskDevInfo().substring(this.getTaskDevInfo().length() - 3);
        return (taskNameFirstTwoLetters + ":" + taskNum + ":" + taskDevLastThreeLetters).toUpperCase();
    }

    public String printTaskDetails(){
        return "Hi";
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//