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
public class NacinGrejanja extends DomainObject{
    private int id_nacin_grejanja;
    private String naziv;

    public NacinGrejanja() {
    }

    public NacinGrejanja(int id_nacin_grejanja, String naziv) {
        this.id_nacin_grejanja = id_nacin_grejanja;
        this.naziv = naziv;
    }

    public int getId_nacin_grejanja() {
        return id_nacin_grejanja;
    }

    public void setId_nacin_grejanja(int id_nacin_grejanja) {
        this.id_nacin_grejanja = id_nacin_grejanja;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    
    @Override
    public String getTableName() {
        return "NACIN_GREJANJA";
    }

    @Override
    public String getAllColumnNames() {
        return "ID_NACIN_GREJANJA, NAZIV"; 
    }

    @Override
    public String getInsertColumnNames() {
       return "ID_NACIN_GREJANJA, NAZIV";  
    }

    @Override
    public String getColumnValues() {
        return String.format("%d, %s",id_nacin_grejanja,naziv);
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
