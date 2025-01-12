/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package DataBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author korisnik
 */
public class DatabaseBroker {
    private Connection connection;
    private String url;
    private String username;
    private String password;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       DatabaseBroker databaseBroker = new DatabaseBroker();
        
        try {
            databaseBroker.connect();
            System.out.println("Connection successful!");
            // Disconnect after testing
            databaseBroker.disconnect();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public DatabaseBroker() {
        this.setDatabaseAccessParams();
    }

    private void setDatabaseAccessParams() {
        try {
            Properties properties = new Properties();
            String propertiesFileName = "config/db.properties"; //String propertiesFileName = "config/db.properties";
            FileInputStream fileInputStream = new FileInputStream(propertiesFileName);

            properties.load(fileInputStream);

            this.url = properties.getProperty("url");
            this.username = properties.getProperty("username");
            this.password = properties.getProperty("password");

            fileInputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void connect() throws Exception {
        try {
            connection = DriverManager.getConnection(url, username, password);
            //connection.setAutoCommit(false);
        } catch (SQLException ex) {
            throw new Exception("Error establishing a database connection");
        }
    }

    public void disconnect() throws Exception {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("DB disconnected");
            } catch (SQLException ex) {
                throw new Exception("Didconnection error!");
            }
        }
    }
    
}
