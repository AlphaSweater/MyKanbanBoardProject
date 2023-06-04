package com.alphasweater.MyUser;
/* Author: Chad Fairlie
*  Pseudonym: AlphaSweater
*  Student Number: ST********
---------------------------------------------------------------------------------------------------------------------*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The Database class provides methods for reading and accessing the user database file.
 */
public class MyUserDAOClass {
    // The name of the file containing the user database
    private static final String FILE_NAME = "src/main/resources/usersDatabase.txt";
    // A 2D array to store the contents of the user database
    private static String[][] userDatabase;

    /**
     * Returns the filename of the user database.
     *
     * @return The filename of the user database.
     */
    public static String getFileName() {
        return FILE_NAME;
    }

    /**
     * Reads the contents of a file and returns them as a 2D array of strings.
     *
     * @param fileName The name of the file to read.
     * @return A 2D array of strings containing the file contents.
     * @throws IOException If an error occurs while reading the file.
     */
    public static String[][] readDataFromFile(String fileName) throws IOException {
        // Create a BufferedReader to read the file
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        try {
            // Use the lines() method to read the file into an array of strings
            String[] lines = reader.lines().toArray(String[]::new);

            // Create a 2D array to store the split lines
            String[][] data = new String[lines.length][];
            for (int i = 0; i < lines.length; i++) {
                // Split each line by "||" and store the resulting array in the data array
                data[i] = lines[i].split("\\|\\|");
            }
            return data;
        } finally {
            // Ensure that the file is closed, even if an exception occurs
            reader.close();
        }
    }

    /**
     * Returns the contents of the user database.
     * This method reloads the database from the file to ensure it is up-to-date.
     *
     * @return The contents of the user database as a 2D array of strings.
     */
    public static String[][] getUserDatabase() {
        // Reload the user database here to ensure it is up-to-date
        reLoadUserDatabase();
        return userDatabase;
    }

    /**
     * Reloads the user database from the file.
     * This method should be called whenever the database needs to be updated.
     */
    public static void reLoadUserDatabase() {
        try {
            // Read the contents of the user database from the file and store them in the userDatabase array
            userDatabase = readDataFromFile(getFileName());
        } catch (IOException e) {
            // If an exception occurs, wrap it in a RuntimeException and re-throw it
            throw new RuntimeException(e);
        }
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//