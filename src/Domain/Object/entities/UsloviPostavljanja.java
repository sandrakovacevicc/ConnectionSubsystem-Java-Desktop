/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain.Object.entities;

import Domain.Object.DomainObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author korisnik
 */
public class UsloviPostavljanja extends DomainObject {
    private int id_uslovp;
    private String naziv;

    public UsloviPostavljanja() {
    }

    public UsloviPostavljanja(int id_uslovp, String naziv) {
        this.id_uslovp = id_uslovp;
        this.naziv = naziv;
    }

    public int getId_uslovp() {
        return id_uslovp;
    }

    public void setId_uslovp(int id_uslovp) {
        this.id_uslovp = id_uslovp;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    
    @Override
    public String getTableName() {
        return "USLOVI_POSTAVLJANJA"; 
    }

    @Override
    public String getAllColumnNames() {
        return "ID_USLOVP,NAZIV";
    }

    @Override
    public String getInsertColumnNames() {
        return "ID_USLOVP,NAZIV"; 
    }

    @Override
    public String getColumnValues() {
         return String.format("%d, %s",id_uslovp,naziv);
    }

    @Override
    public String getUpdateClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateWhereClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getDeleteWhereClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getOrderByColumn() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
