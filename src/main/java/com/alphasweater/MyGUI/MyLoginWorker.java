package com.alphasweater.MyGUI;

import com.alphasweater.MyUser.MyUserDAOClass;
import com.alphasweater.MyUser.MyUserLoginClass;

/* Author: Chad Fairlie
*  Pseudonym: AlphaSweater
*  Student Number: ST********
---------------------------------------------------------------------------------------------------------------------*/
public class MyLoginWorker {
    private LoginPage loginPage;
    public MyLoginWorker() {
    }
    public MyLoginWorker(LoginPage loginPage){
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
            LoginPage.login.dispose();
            // TODO: Check this out again afterwards
            HomePage home = new HomePage();
            home.createHomePage();
        }

        // Clear the input fields
        loginPage.edtUsername.setText("");
        loginPage.edtPassword.setText("");

        // Display the login status message
        loginPage.lblStatus.setText(MyUserLoginClass.getStatus());
        loginPage.lblStatus.setVisible(true);
    }
    protected void swapPageRegister(){
        // Close the login page and open the registration page
        LoginPage.login.dispose();
        RegisterPage.createRegisterPage();
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//