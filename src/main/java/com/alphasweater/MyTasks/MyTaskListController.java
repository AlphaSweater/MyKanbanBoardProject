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
    private ArrayList<MyTasksClass> listOfTasks = new ArrayList<>();
    public ArrayList<MyTasksClass> getListOfTasks() {
        return listOfTasks;
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
    public ArrayList<String> findAllToDoTasks (){
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
    public ArrayList<String> findAllDoingTasks (){
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
    public ArrayList<String> findAllDoneTasks (){
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
    public String findLongestTask (){
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
    public String findShortestTask (){
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
    public String searchForTask (String taskName){
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
    public ArrayList<String> findAllDevsTasks (String devName){
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
    public String deleteTask (String taskToDeleteName){
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
    public ArrayList<String> findAllCapturedTasks (){
        ArrayList<String> listOfTasksInfo = new ArrayList<>();
        for (MyTasksClass task : this.listOfTasks) {
          listOfTasksInfo.add(task.printTaskDetails());
        }
        return listOfTasksInfo;
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//