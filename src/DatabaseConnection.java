/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
public class DatabaseConnection {
    private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    private String dbConnectionURL = "jdbc:mysql://localhost:3306/MABS";
    private String dbUsername = "phpMyAdmin";
    private String dbPassword = "admin";
    
    // getters

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public String getDbConnectionURL() {
        return dbConnectionURL;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }
    
    
}
