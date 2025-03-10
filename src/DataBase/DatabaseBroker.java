/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package DataBase;

import Domain.Object.DomainObject;
import Domain.Object.entities.KatastarskaOpstina;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.sql.PreparedStatement;


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
    
     public int insert(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO " + object.getTableName() + "(" + object.getInsertColumnNames() + ")" + " VALUES (" + object.getColumnValues() + ")";
            System.out.println(query);
            return statement.executeUpdate(query);

        } catch (SQLException ex) {
            throw ex;
        }
    }

    public List<DomainObject> getAll(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT " + object.getAllColumnNames() + " FROM "
                    + object.getTableName() + " ORDER BY " + object.getOrderByColumn();
            ResultSet rs = statement.executeQuery(query);
            System.out.println(query);
            return object.getObjectsFromResultSet(rs);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public List<DomainObject> getAllWithWhere(DomainObject object, String whereClause) throws SQLException {
    try {
        Statement statement = connection.createStatement();
        String query = "SELECT " + object.getAllColumnNames() + " FROM "
                + object.getTableName() + " WHERE " + whereClause + " ORDER BY " + object.getOrderByColumn();
        ResultSet rs = statement.executeQuery(query);

        return object.getObjectsFromResultSet(rs);
    } catch (SQLException ex) {
        throw ex;
    }
}

    public int updatePartial(DomainObject odo, String setClause) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE " + odo.getTableName() + " SET " + setClause + " WHERE " + odo.getUpdateWhereClause();
            System.out.println(query);
            int rowsUpdated = statement.executeUpdate(query);
            return rowsUpdated;
        } catch (SQLException ex) {
            throw ex;
        }
    }
    

    public int delete(DomainObject odo) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM " + odo.getTableName() + " WHERE " + odo.getDeleteWhereClause();
            int rowsUpdated = statement.executeUpdate(query);
            return rowsUpdated;
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public int update(DomainObject odo) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE " + odo.getTableName() + " SET " + odo.getUpdateClause() + " WHERE " + odo.getUpdateWhereClause();
            System.out.println(query);
            int rowsUpdated = statement.executeUpdate(query);
            return rowsUpdated;
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
     public List<KatastarskaOpstina> GetByPostanskiBr(String postanski_br) throws SQLException {
     String query = "SELECT ID_OPSTINE, NAZIV FROM KATASTARSKA_OPSTINA " +
                   "WHERE ID_OPSTINE IN (" +
                   "  SELECT K.ID_OPSTINE " +
                   "  FROM KATASTARSKA_OPSTINA K " +
                   "  JOIN Ulica U ON K.ID_OPSTINE = U.ID_OPSTINE " +
                   "  JOIN Grad G ON U.POSTANSKI_BR = G.POSTANSKI_BR " +
                   "  WHERE G.POSTANSKI_BR = ?)" + 
                   " ORDER BY NAZIV";

    PreparedStatement stmt = connection.prepareStatement(query);
    stmt.setString(1, postanski_br);  

    ResultSet rs = stmt.executeQuery();
    List<KatastarskaOpstina> opstine = new ArrayList<>();

    while (rs.next()) {
        KatastarskaOpstina opstina = new KatastarskaOpstina();
        opstina.setId_opstine(rs.getInt("ID_OPSTINE"));
        opstina.setNaziv(rs.getString("NAZIV"));
        opstine.add(opstina);
    }

    return opstine;
    }
    
    
    
}
