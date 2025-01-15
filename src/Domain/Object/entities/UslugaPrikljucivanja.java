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
public class UslugaPrikljucivanja extends DomainObject {
    private int id_usluge;
    private String naziv;
    private float cena;

    public UslugaPrikljucivanja() {
    }

    public UslugaPrikljucivanja(int id_usluge, String naziv, float cena) {
        this.id_usluge = id_usluge;
        this.naziv = naziv;
        this.cena = cena;
    }

    public int getId_usluge() {
        return id_usluge;
    }

    public void setId_usluge(int id_usluge) {
        this.id_usluge = id_usluge;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }
    
    
    @Override
    public String getTableName() {
        return "USLUGA_PRIKLJUCIVANJA"; 
    }

    @Override
    public String getAllColumnNames() {
        return "ID_USLUGE,NAZIV,CENA"; 
    }

    @Override
    public String getInsertColumnNames() {
        return "ID_USLUGE,NAZIV,CENA"; 
    }

    @Override
    public String getColumnValues() {
          return String.format("%d, %s, %.2f",
                id_usluge,
                naziv,
                cena);
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
