package com.alphasweater.MyTasks;

import java.util.ArrayList;

/* Author: Chad Fairlie
*  Pseudonym: AlphaSweater
*  Student Number: ST10269509
---------------------------------------------------------------------------------------------------------------------*/
public class MyTaskListController {
    
    //----------------------------------------------------------------------------------------------------------------//
    private int numOfTasks;
    public int getNumOfTasks() {
        return numOfTasks;
    }
    public void setNumOfTasks(int numOfTasks) {
        this.numOfTasks = numOfTasks;
    }
    //----------------------------------------------------------------------------------------------------------------//
    private final ArrayList<MyTasksClass> listOfTasks = new ArrayList<MyTasksClass>();
    public ArrayList<MyTasksClass> getListOfTasks() {
        return listOfTasks;
    }
    //----------------------------------------------------------------------------------------------------------------//
//    public final ArrayList<MyTasksClass> listOfCurrentWorkingTasks = new ArrayList<MyTasksClass>();
    //----------------------------------------------------------------------------------------------------------------//
    public int returnTotalHours(ArrayList<MyTasksClass> listOfTasks) {
        int totalHours = 0;
        for (MyTasksClass task : listOfTasks) {
            totalHours += task.getTaskDuration();
        }
        return totalHours;
    }
    //----------------------------------------------------------------------------------------------------------------//
    public ArrayList<MyTasksClass> findAllDoneTasks (ArrayList<MyTasksClass> listOfTasks){
        ArrayList<MyTasksClass> listOfTaskStatusDone = new ArrayList<MyTasksClass>();
        for (MyTasksClass task : listOfTasks) {
            if (task.getTaskStatus().equals("Done")) {
                listOfTaskStatusDone.add(task);
            }
        }
        return listOfTaskStatusDone;
    }
    //----------------------------------------------------------------------------------------------------------------//
    public MyTasksClass findLongestTask (ArrayList<MyTasksClass> listOfTasks){
        int longest = listOfTasks.get(0).getTaskDuration();
        MyTasksClass longestTask = listOfTasks.get(0);

        for (MyTasksClass task : listOfTasks) {
            if (task.getTaskDuration() > longest){
                longest = task.getTaskDuration();
                longestTask = task;
            }
        }
        return longestTask;
    }
    //----------------------------------------------------------------------------------------------------------------//
    public MyTasksClass SearchForTask (ArrayList<MyTasksClass> listOfTasks, String taskName){
        for (MyTasksClass task : listOfTasks) {
            if (task.getTaskName().equals(taskName)){
                return task;
            }
        }
        return null;
    }
    //----------------------------------------------------------------------------------------------------------------//
    public ArrayList<MyTasksClass> FindAllDevsTasks (ArrayList<MyTasksClass> listOfTasks, String DevName){
        ArrayList<MyTasksClass> listOfDevsTasks = new ArrayList<MyTasksClass>();
        for (MyTasksClass task : listOfTasks) {
            if (task.getTaskDevInfo().equals(DevName)){
                listOfDevsTasks.add(task);
            }
        }
        return listOfDevsTasks;
    }
    //----------------------------------------------------------------------------------------------------------------//
    public void
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//