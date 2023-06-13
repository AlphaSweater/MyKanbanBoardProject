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
    public int returnTotalHours(ArrayList<MyTasksClass> listOfTasks) {
        int totalHours = 0;
        for (MyTasksClass task : listOfTasks) {
            totalHours += task.getTaskDuration();
        }
        return totalHours;
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//