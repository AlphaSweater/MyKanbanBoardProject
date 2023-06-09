package com.alphasweater.MyGUI;

import com.alphasweater.MyDatabases.MyDatabaseClass;
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
    public void setRegisterPage(RegisterPage registerPage) {
        this.registerPage = registerPage;
    }

    private MyLoginWorkerClass loginWorker;

    //----------------------------------------------------------------------------------------------------------------//
    // Default constructor
    public MyRegisterWorkerClass() {
    }

    public MyRegisterWorkerClass(RegisterPage registerPage,MyLoginWorkerClass myLoginWorkerClass) {
        this.registerPage = registerPage;
        this.loginWorker = myLoginWorkerClass;
    }
    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Summons the registration page GUI.
     */
    public void createRegisterPage() {
        registerPage.registerFrame.setContentPane(new RegisterPage(this.loginWorker).panel);
        registerPage.registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerPage.registerFrame.pack();
        registerPage.registerFrame.setLocationRelativeTo(null);
        registerPage.registerFrame.setVisible(true);
    }

    //----------------------------------------------------------------------------------------------------------------//
    protected void beginRegistrationHere() {
        MyUserRegisterClass registrationWorker = new MyUserRegisterClass();
        MyDatabaseClass databaseWorker = new MyDatabaseClass();
        // Call the registerUser method and display the returned message in a dialog
        JOptionPane.showMessageDialog(null, registrationWorker.registerUser(false, this.getInUsername(), this.getInPassword(), this.getInFirstname(), this.getInLastname(), databaseWorker.getDatabase()));

        // Clear the input fields
        registerPage.edtUsername.setText("");
        registerPage.edtPassword.setText("");

        // If registration is successful, close the register page and display the login page
        if (registrationWorker.getIsRegistered()) {
            swapPageLogin();
        }
    }

    //----------------------------------------------------------------------------------------------------------------//
    protected void swapPageLogin() {
        // Close the register page and display the login page
        registerPage.registerFrame.dispose();
        loginWorker.createLoginPage();
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//