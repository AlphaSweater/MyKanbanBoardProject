package com.alphasweater;
/* Author: Chad Fairlie
*  Pseudonym: AlphaSweater
*  Student Number: ST10269509
---------------------------------------------------------------------------------------------------------------------*/

import com.alphasweater.MyGUI.MyLoginWorkerClass;

public class MainClass {

    /**
     * The main method that starts the application by creating the login page.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        MyLoginWorkerClass.createLoginPage();
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//
//⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀       ⠀⠀⠀⣀⣴⣾⣟⡛⣷⡄⠀⠀⠀⠀⠀⠀⠀⠀
//⠀⠀⣀⣠⣀⣄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀   ⠀⠀⢀⣤⡾⢟⣽⠾⠛⢱⢸⣷⠀⠀⠀⠀⠀⠀⠀⠀
//⠀⣼⠏⡉⢉⠉⠛⠻⠷⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   ⠀⠀⠀⣀⣴⠟⣋⣴⠟⠁⠀ ⡀⠻⣘⣿⠀⠀⠀⠀⠀⠀⠀⠀
//⢠⣿⠐⣴⡿⠶⣧⣆⣐⠈⡙⠿⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣤⣀⣀⣀⡀⣀⣤⡾⠛⣁⣶⠻⠅⡒⠂⠄⠀ ⣰⠋⣿⡄⠀⠀⠀⠀⠀⠀⠀
//⢸⣿⢠⣿⠆⡀⠀⠉⠻⢷⣤⡂⠌⡙⠿⣦⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⣴⣾⠛⠉⠈⠉⠉⠉⠙⢻⠻⢶⣽⣋⠀⠀⠈⠀⢄⠀⠀ ⠀⠧⣿⡇⠀⠀⠀⠀⠀⠀⠀
//⠀⣿⠀⣿⡆⢀⠁⠄⠀⠀⠉⠻⢶⣄⠒⡈⠙⠻⠿⢶⣦⣤⣤⣤⣤⠶⡶⠟⠟⠛⣉⠕⠁⠀⠀⠀⠀⠀⠀⠀⠈⠁⠒⠤⡉⠛⠾⢤⣤⣈⠀⠀⠀⢧⢹⣇⠀⠀⠀⠀⠀⠀⠀
//⠀⢿⡇⢹⡂⠠⠈⠀⠀⠀⠀⠀⠀⠙⠷⣦⣁⠂⠔⠠⠐⣀⠂⡐⡀⢂⡐⡬⠔⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠪⠔⠤⢌⣙⠳⣦⣄ ⢈⢾⣿⠀⠀⠀⠀⠀⠀⠀
//⠀⢸⣷⢸⠄⠁⠠⠁⢂⠡⠄⠈⠉⠉⠖⠻⠿⣶⡈⠡⠌⡀⠆⠡⡰⠞⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠈⠃⠰⠛⠿⠞⣿⠀⠀⠀⠀⠀⠀⠀
//⠀⠀⣿⡄⢟⠀⠠⠀⠘⠄⡀⠀⠀⡀⠀⠀⢠⡿⠃⠤⢀⠃⡘⡜⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⣿⣿⣧⣄  ⠀⠘⠧⣿⡇⠀⠀⠀⠀⠀⠀
//⠀⠀⠹⣷⠈⠀⠁⠀⠀⠀⠀⠀⡠⠁⠀⢠⠿⠁⠌⡐⢂⠡⣌⠀⠀⠀⠀⣠⣴⣶⣶⣶⣤⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣏⣘⣿⣿⣿⡄⠀ ⠀⠹⣷⡄⠀⠀⠀⠀⠀
//⠀⠀⠀⢻⣷⢬⠀⠀⠀⢀⠂⠌⠀⠀⢀⣾⠃⠌⡐⠰⢀⢲⡄⠀⠀⢠⣿⣿⣿⣿⣄⣨⣿⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣿⣿⣿⠟⠀⠀⠀ ⠈⢹⢿⣦⠀⠀⠀⠀
//⠀⠀⠀⠀⢿⣆⢣⠀⠀⠠⠐⠀⡀⠄⣾⠃⠰⣀⠡⠒⡈⠄⡄⠀⠀⠈⠛⠛⠿⠿⠿⠿⠿⠿⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠀⠀⠀⠀⠀ ⠀⢶⡌⣻⣷⡄⠀⠀
//⠀⠀⠀⠀⠈⢿⣎⣄⠀⠀⠀⠀⠁⢸⡟⢈⠡⠄⣂⠡⡐⢈⠘⡐⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣔⣦⡴⠴⠾⢟⣲⠀⠀⠀⠀⠀⠀  ⠀⠀⠙⢴⣹⣿⡄⠀
//⠀⠀⠀⠀⠀⠈⢿⣎⢲⠀⠀⠀⠀⣾⠇⡌⢒⡈⢄⠒⢠⠃⡰⢀⢆⡆⠠⠀⠒⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠯⢶⡄⠀⢢⣟⠃⠀⠀⠀⠀⠀ ⠀⠃⠐⢀⠑⣞⣿⡄
//⠀⠀⠀⠀⠀⠀⠈⢿⣎⢇⠀⠀⠀⡿⡐⢌⠢⡘⢄⢊⣴⢮⠳⠙⡸⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠇⢀⣘⠇⠀⠀⠀⡀⢀⠀⠀⠀⠀⠀⠂⠄⠈⢿⡇
//⠀⠀⠀⠀⠀⠀⠀⠈⣿⡎⠳⢖⡚⢥⡘⠤⢃⡜⢠⠊⡍⢄⠢⣡⠇⠂⠁⠀⠀⢀⠤⠂⠀⠀⠀⠀⠀⣤⡄⡀⣀⢠⣤⢰⡦⠟⠙⠚⠒⠒⠛⠛⠉⠀ ⠀⠀⠈⢸⡇
//⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡑⢎⡜⡰⢌⢣⠣⡜⢢⢍⣴⡞⠟⡻⠀⠀⢠⠂⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣺⠁
//⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣏⠖⡼⣡⠏⡶⣙⢬⣳⡞⢎⡜⣡⣧⠴⠁⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡟⠀
//⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⣎⠵⣣⢝⡲⣍⠿⣣⠝⡮⣜⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⠇⠀
//⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣧⣛⠴⣋⢶⡩⢞⡥⣛⠴⣣⠗⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⢤⡿⠀⠀
//⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣏⡞⡭⣖⡹⢎⠶⣍⡳⡭⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡪⢢⡿⠁⠀⠀
//⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣿⢴⢣⡝⣎⢻⣔⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠠⠀⠒⠉⢀⣿⠃⠀⠀⠀
//⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡿⠙⠶⠹⠬⠓⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠃⠀⠀⠀⠀
//⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡏⠀⠀⠀⠀⠀
//⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠁⠀⠀⠀⠀⠀
//⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀
//⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀
//⠀⠀⠀⠀⠀⠀⠀⠀⢠⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀
//⠀⠀⠀⠀⠀⠀⠀⢀⣾⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡄⠀⠀⠀⠀⠀
//⠀⠀⠀⠀⠀⠀⢠⡾⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣇⠀⠀⠀⠀⠀
//⠀⠀⠀⠀⠀⢠⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⡄⠀⠀⠀⠀
//⠀⠀⠀⠀⢠⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⡀⠀⠀⠀
//⠀⠀⠀⢀⡾⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣷⡀⠀⠀⠀
//--------------------------------------------------------------------------------------------------------------------//