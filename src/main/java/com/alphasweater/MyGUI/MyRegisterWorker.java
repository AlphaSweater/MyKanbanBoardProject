package com.alphasweater.MyGUI;

import com.alphasweater.MyUser.MyUserDAOClass;
import com.alphasweater.MyUser.MyUserRegisterClass;

import javax.swing.*;

/* Author: Chad Fairlie
*  Pseudonym: AlphaSweater
*  Student Number: ST********
---------------------------------------------------------------------------------------------------------------------*/
public class MyRegisterWorker {
    private String inFirstname;
    public String getInFirstname() {
        return inFirstname;
    }
    private String inLastname;
    public String getInLastname() {
        return inLastname;
    }
    private String inUsername;
    public String getInPassword() {
        return inPassword;
    }
    private String inPassword;
    public String getInUsername() {
        return inUsername;
    }
    // Default constructor
    public MyRegisterWorker() {
    }

    public MyRegisterWorker(String inFirstname, String inLastname, String inUsername, String inPassword) {
        this.inFirstname = inFirstname;
        this.inLastname = inLastname;
        this.inUsername = inUsername;
        this.inPassword = inPassword;
    }

    public void beginHere(){
        // Call the registerUser method and display the returned message in a dialog
        JOptionPane.showMessageDialog(null, MyUserRegisterClass.registerUser(false, this.getInUsername(), this.getInPassword(), this.getInFirstname(), this.getInLastname(), MyUserDAOClass.getUserDatabase()));

    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//