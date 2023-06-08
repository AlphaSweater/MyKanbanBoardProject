package com.alphasweater.MyGUI;

import com.alphasweater.MyUser.MyUserDAOClass;
import com.alphasweater.MyUser.MyUserRegisterClass;

import javax.swing.*;

/* Author: Chad Fairlie
*  Pseudonym: AlphaSweater
*  Student Number: ST10269509
---------------------------------------------------------------------------------------------------------------------*/
public class MyRegisterWorkerClass {
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
    // RegisterPage object to allow the editing of GUI components
    private RegisterPage registerPage;
    //----------------------------------------------------------------------------------------------------------------//
    // Default constructor
    public MyRegisterWorkerClass() {
    }
    public MyRegisterWorkerClass(RegisterPage registerPage) {
        this.registerPage = registerPage;
    }
    //----------------------------------------------------------------------------------------------------------------//
    /**
     * Summons the registration page GUI.
     */
    public static void createRegisterPage() {
        RegisterPage.registerFrame.setContentPane(new RegisterPage().panel);
        RegisterPage.registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RegisterPage.registerFrame.pack();
        RegisterPage.registerFrame.setLocationRelativeTo(null);
        RegisterPage.registerFrame.setVisible(true);
    }
    //----------------------------------------------------------------------------------------------------------------//
    protected void beginRegistrationHere(){
        // Call the registerUser method and display the returned message in a dialog
        JOptionPane.showMessageDialog(null, MyUserRegisterClass.registerUser(false, this.getInUsername(), this.getInPassword(), this.getInFirstname(), this.getInLastname(), MyUserDAOClass.getUserDatabase()));

        // Clear the input fields
        registerPage.edtUsername.setText("");
        registerPage.edtPassword.setText("");

        // If registration is successful, close the register page and display the login page
        if (MyUserRegisterClass.getIsRegistered()) {
            swapPageLogin();
        }
    }
    //----------------------------------------------------------------------------------------------------------------//
    protected void swapPageLogin(){
        // Close the register page and display the login page
        RegisterPage.registerFrame.dispose();
        MyLoginWorkerClass.createLoginPage();
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//