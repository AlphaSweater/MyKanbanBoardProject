package com.alphasweater.MyGUI;

import com.alphasweater.MyTasks.MyTasksClass;
import com.alphasweater.MyUser.MyUserClass;

/* Author: Chad Fairlie
*  Pseudonym: AlphaSweater
*  Student Number: ST********
---------------------------------------------------------------------------------------------------------------------*/
public class MyHomeWorkerClass {
    public String getTitleHeading() {
        return "Welcome to EasyKanban.";
    }
    public String getWelcomeMessage() {
        return "Hi " + MyUserClass.currentUser.getUserFirstName() + " "
                + MyUserClass.currentUser.getUserLastName() + ", it is great to see you.";
    }
    public MyHomeWorkerClass() {
    }
    public void beginHere() {

        MyTasksClass.setNumOfTasks(10);
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//