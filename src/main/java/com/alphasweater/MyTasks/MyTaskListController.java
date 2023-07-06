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
    private ArrayList<MyTasksClass> listOfTasks = new ArrayList<MyTasksClass>();
    public ArrayList<MyTasksClass> getListOfTasks() {
        return listOfTasks;
    }
    private void setListOfTasks(ArrayList<MyTasksClass> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }
    //----------------------------------------------------------------------------------------------------------------//
    public int returnTotalHours(ArrayList<MyTasksClass> listOfTasks) {
        int totalHours = 0;
        for (MyTasksClass task : listOfTasks) {
            totalHours += task.getTaskDuration();
        }
        return totalHours;
    }
    //----------------------------------------------------------------------------------------------------------------//
    public ArrayList<MyTasksClass> findAllDoneTasks (){
        ArrayList<MyTasksClass> listOfTaskStatusDone = new ArrayList<MyTasksClass>();
        for (MyTasksClass task : this.listOfTasks) {
            if (task.getTaskStatus().equals("Done")) {
                listOfTaskStatusDone.add(task);
            }
        }
        return listOfTaskStatusDone;
    }
    //----------------------------------------------------------------------------------------------------------------//
    public MyTasksClass findLongestTask (){
        if (this.listOfTasks.size() != 0) {
            int longest = this.listOfTasks.get(0).getTaskDuration();
            MyTasksClass longestTask = this.listOfTasks.get(0);

            for (MyTasksClass task : this.listOfTasks) {
                if (task.getTaskDuration() > longest) {
                    longest = task.getTaskDuration();
                    longestTask = task;
                }
            }
            return longestTask;
        }else return null;
    }
    //----------------------------------------------------------------------------------------------------------------//
    public MyTasksClass searchForTask (String taskName){
        for (MyTasksClass task : this.listOfTasks) {
            if (task.getTaskName().equals(taskName)){
                return task;
            }
        }
        return null;
    }
    //----------------------------------------------------------------------------------------------------------------//
    public ArrayList<MyTasksClass> findAllDevsTasks (String DevName){
        ArrayList<MyTasksClass> listOfDevsTasks = new ArrayList<MyTasksClass>();
        for (MyTasksClass task : this.listOfTasks) {
            if (task.getTaskDevInfo().equals(DevName)){
                listOfDevsTasks.add(task);
            }
        }
        return listOfDevsTasks;
    }
    //----------------------------------------------------------------------------------------------------------------//
    public String deleteTask (String taskToDeleteName){
        MyTasksClass taskToDelete = null;
        for (MyTasksClass task : this.listOfTasks) {
            if (task.getTaskName().equals(taskToDeleteName)){
                taskToDelete = task;
                this.listOfTasks.remove(taskToDelete);
                break;
            }
        }

        if (taskToDelete == null) {
            return "No entry with that name was found";
        } else if (!this.listOfTasks.contains(taskToDelete)) {
            return "Entry " + taskToDeleteName + " successfully deleted";
        } else {
            return "Task deletion failed";
        }
    }
    //----------------------------------------------------------------------------------------------------------------//
    public ArrayList<String> findAllCapturedTasks (){
        ArrayList<String> listOfTasksInfo = new ArrayList<String>();
        for (MyTasksClass task : this.listOfTasks) {
          listOfTasksInfo.add(task.printTaskDetails());
        }
        return listOfTasksInfo;
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//