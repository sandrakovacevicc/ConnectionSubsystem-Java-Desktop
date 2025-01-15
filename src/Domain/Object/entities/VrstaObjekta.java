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
public class VrstaObjekta extends DomainObject {
    private int id_vrste_objekta;
    private String naziv;

    public VrstaObjekta() {
    }

    public VrstaObjekta(int id_vrste_objekta, String naziv) {
        this.id_vrste_objekta = id_vrste_objekta;
        this.naziv = naziv;
    }

    public int getId_vrste_objekta() {
        return id_vrste_objekta;
    }

    public void setId_vrste_objekta(int id_vrste_objekta) {
        this.id_vrste_objekta = id_vrste_objekta;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    
    @Override
    public String getTableName() {
        return "VRSTA_OBJEKTA"; 
    }

    @Override
    public String getAllColumnNames() {
        return "ID_VRSTE_OBJEKTA,NAZIV"; 
    }

    @Override
    public String getInsertColumnNames() {
        return "ID_VRSTE_OBJEKTA,NAZIV";  
    }

    @Override
    public String getColumnValues() {
        return String.format("%d, %s",id_vrste_objekta,naziv); 
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
