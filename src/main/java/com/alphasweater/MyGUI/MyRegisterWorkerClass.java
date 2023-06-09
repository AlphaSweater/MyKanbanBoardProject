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
    public void setInFirstname(String inFirstname) {
        this.inFirstname = inFirstname;
    }
    public String getInFirstname() {
        return inFirstname;
    }

    private String inLastname;
    public void setInLastname(String inLastname) {
        this.inLastname = inLastname;
    }
    public String getInLastname() {
        return inLastname;
    }

    private String inUsername;
    public String getInUsername() {
        return inUsername;
    }
    public void setInUsername(String inUsername) {
        this.inUsername = inUsername;
    }

    private String inPassword;
    public void setInPassword(String inPassword) {
        this.inPassword = inPassword;
    }
    public String getInPassword() {
        return inPassword;
    }

    // RegisterPage object to allow the editing of GUI components
    private RegisterPage registerPage;
    public void setRegisterPage(RegisterPage registerPage) {
        this.registerPage = registerPage;
    }

    private MyLoginWorkerClass loginWorker;
    public void setLoginWorker(MyLoginWorkerClass loginWorker) {
        this.loginWorker = loginWorker;
    }

    //----------------------------------------------------------------------------------------------------------------//
    // Default constructor
    public MyRegisterWorkerClass() {
    }
    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Summons the registration page GUI.
     */
    public void createRegisterPage() {
        this.registerPage = new RegisterPage();
        this.registerPage.setRegisterWorker(this);

        this.registerPage.registerFrame.setContentPane(registerPage.panel);
        this.registerPage.registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.registerPage.registerFrame.pack();
        this.registerPage.registerFrame.setLocationRelativeTo(null);
        this.registerPage.registerFrame.setVisible(true);
    }

    //----------------------------------------------------------------------------------------------------------------//
    protected void beginRegistrationHere() {
        MyUserRegisterClass registrationWorker = new MyUserRegisterClass();
        MyDatabaseClass databaseWorker = new MyDatabaseClass();

        this.setInFirstname(registerPage.edtFirstname.getText());
        this.setInLastname(registerPage.edtLastname.getText());
        this.setInUsername(registerPage.edtUsername.getText());
        this.setInPassword(String.valueOf(registerPage.edtPassword.getPassword()));

        // Call the registerUser method and display the returned message in a dialog
        JOptionPane.showMessageDialog(null, registrationWorker.registerUser(false, this.getInUsername(), this.getInPassword(), this.getInFirstname(), this.getInLastname(), databaseWorker.getDatabase()));

        // Clear the input fields
        this.registerPage.edtUsername.setText("");
        this.registerPage.edtPassword.setText("");

        // If registration is successful, close the register page and display the login page
        if (registrationWorker.getIsRegistered()) {
            swapPageLogin();
        }
    }

    //----------------------------------------------------------------------------------------------------------------//
    protected void swapPageLogin() {
        // Close the register page and display the login page
        this.registerPage.registerFrame.dispose();
        this.loginWorker.createLoginPage();
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//