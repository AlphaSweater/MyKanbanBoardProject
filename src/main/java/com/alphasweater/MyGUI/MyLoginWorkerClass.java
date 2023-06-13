package com.alphasweater.MyGUI;

import com.alphasweater.MyDatabases.MyDatabaseClass;
import com.alphasweater.MyUser.MyUserClass;
import com.alphasweater.MyUser.MyUserLoginClass;

import javax.swing.JFrame;

/* Author: Chad Fairlie
*  Pseudonym: AlphaSweater
*  Student Number: ST10269509
---------------------------------------------------------------------------------------------------------------------*/
public class MyLoginWorkerClass {
    // LoginPage object to allow the editing of GUI components
    private LoginPage loginPage;
    public LoginPage getLoginPage() {
        return loginPage;
    }

    private MyHomeWorkerClass homeWorker;
    private MyRegisterWorkerClass registerWorker;
    private MyUserClass currentUser;

    public MyUserClass getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(MyUserClass loggedInUser) {
        this.currentUser = loggedInUser;
    }

    //----------------------------------------------------------------------------------------------------------------//
    // Default Constructor
    public MyLoginWorkerClass() {
    }
    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Summons the login page GUI.
     */
    public void createLoginPage() {
        this.loginPage = new LoginPage();
        this.loginPage.setLoginWorker(this);
        this.homeWorker = new MyHomeWorkerClass();
        this.homeWorker.setLoginWorker(this);
        this.registerWorker = new MyRegisterWorkerClass();
        this.registerWorker.setLoginWorker(this);

        // Create and display the login page window
        this.loginPage.loginFrame.setContentPane(loginPage.panel);
        this.loginPage.loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.loginPage.loginFrame.pack();
        this.loginPage.loginFrame.setLocationRelativeTo(null);
        this.loginPage.loginFrame.setVisible(true);

    }

    //----------------------------------------------------------------------------------------------------------------//
    protected void beginLoginHere() {
        MyDatabaseClass databaseWorker = new MyDatabaseClass();
        MyUserLoginClass loginUserWorker = new MyUserLoginClass();

        loginUserWorker.setLoginWorker(this);
        // Retrieve the entered username and password
        String inUsername = loginPage.edtUsername.getText();
        String inPassword = String.valueOf(loginPage.edtPassword.getPassword());

        // Get the user database from the Database class
        String[][] userDatabase = databaseWorker.getDatabase();

        // Attempt to log in the user using the entered credentials
        if (loginUserWorker.logInUser(inUsername, inPassword, userDatabase)) {
            // If login is successful, close the login page and open the home page
            homeWorker.setCurrentUser(this.getCurrentUser());
            swapPageHome();
        }

        // Clear the input fields
        this.loginPage.edtUsername.setText("");
        this.loginPage.edtPassword.setText("");

        // Display the login status message
        this.loginPage.lblStatus.setText(loginUserWorker.getStatus());
        this.loginPage.lblStatus.setVisible(true);
    }

    //----------------------------------------------------------------------------------------------------------------//
    protected void swapPageHome() {
        homeWorker.createHomePage();
        loginPage.loginFrame.dispose();
    }

    //----------------------------------------------------------------------------------------------------------------//
    protected void swapPageRegister() {
        // Close the login page and open the registration page
        loginPage.loginFrame.dispose();
        registerWorker.createRegisterPage();
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//