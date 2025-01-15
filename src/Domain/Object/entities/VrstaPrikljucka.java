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
public class VrstaPrikljucka extends DomainObject{
    private int id_vrste_prikljucka;
    private String naziv;

    public VrstaPrikljucka() {
    }

    public VrstaPrikljucka(int id_vrste_prikljucka, String naziv) {
        this.id_vrste_prikljucka = id_vrste_prikljucka;
        this.naziv = naziv;
    }

    public int getId_vrste_prikljucka() {
        return id_vrste_prikljucka;
    }

    public void setId_vrste_prikljucka(int id_vrste_prikljucka) {
        this.id_vrste_prikljucka = id_vrste_prikljucka;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    
    @Override
    public String getTableName() {
        return "VRSTA_PRIKLJUCKA"; 
    }

    @Override
    public String getAllColumnNames() {
        return "ID_VRSTE_PRIKLJUCKA,NAZIV"; 
    }

    @Override
    public String getInsertColumnNames() {
        return "ID_VRSTE_PRIKLJUCKA,NAZIV"; 
    }

    @Override
    public String getColumnValues() {
        return String.format("%d, %s",id_vrste_prikljucka,naziv); 
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
