package com.alphasweater.MyGUI;

import com.alphasweater.MyUser.MyUserDAOClass;
import com.alphasweater.MyUser.MyUserLoginClass;

/* Author: Chad Fairlie
*  Pseudonym: AlphaSweater
*  Student Number: ST********
---------------------------------------------------------------------------------------------------------------------*/
public class MyLoginWorkerClass {
    // LoginPage object to allow the editing of GUI components
    private LoginPage loginPage;

    public MyLoginWorkerClass() {
    }
    public MyLoginWorkerClass(LoginPage loginPage){
        this.loginPage = loginPage;
    }
    protected void beginLoginHere(){

        // Retrieve the entered username and password
        String inUsername = loginPage.edtUsername.getText();
        String inPassword = String.valueOf(loginPage.edtPassword.getPassword());

        // Get the user database from the Database class
        String[][] userDatabase = MyUserDAOClass.getUserDatabase();

        // Attempt to log in the user using the entered credentials
        if (MyUserLoginClass.logInUser(inUsername, inPassword, userDatabase)) {
            // If login is successful, close the login page and open the home page
            swapPageHome();
        }

        // Clear the input fields
        loginPage.edtUsername.setText("");
        loginPage.edtPassword.setText("");

        // Display the login status message
        loginPage.lblStatus.setText(MyUserLoginClass.getStatus());
        loginPage.lblStatus.setVisible(true);
    }
    protected void swapPageHome(){
        LoginPage.loginFrame.dispose();
        HomePage.createHomePage();
    }
    protected void swapPageRegister(){
        // Close the login page and open the registration page
        LoginPage.loginFrame.dispose();
        RegisterPage.createRegisterPage();
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//