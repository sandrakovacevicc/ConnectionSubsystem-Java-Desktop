/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain.Object.entities;

import Domain.Object.DomainObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author korisnik
 */
public class Direktor extends DomainObject {
    private int id_direktora;
    private String ime;
    private String prezime;

    public Direktor(int id_direktora, String ime, String prezime) {
        this.id_direktora = id_direktora;
        this.ime = ime;
        this.prezime = prezime;
    }

    public Direktor() {
    }

    public int getId_direktora() {
        return id_direktora;
    }

    public void setId_direktora(int id_direktora) {
        this.id_direktora = id_direktora;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
    
    
    @Override
    public String getTableName() {
    return "DIREKTOR";     }

    @Override
    public String getAllColumnNames() {
        return "ID_DIREKTORA, IME, PREZIME"; 
    }

    @Override
    public String getInsertColumnNames() {
    return "ID_DIREKTORA, IME, PREZIME";    }

    @Override
    public String getColumnValues() {
        return String.format("%d, %s, %s,",id_direktora,ime, prezime);    }

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
    return String.format("ID_DIREKTORA= %d", this.getId_direktora());    }

    @Override
    public String getDeleteWhereClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
         List<DomainObject> direktori = new ArrayList<>();
        
        while(rs.next()) {
            int id_direktora = rs.getInt("ID_DIREKTORA");     
            String ime = rs.getString("IME");
            String prezime = rs.getString("PREZIME");
            
            direktori.add(new Direktor(id_direktora, ime, prezime));
        }
        return direktori;
    }

    @Override
    public String getOrderByColumn() {
        return "PREZIME";
    }
    
    @Override
    public String toString() {
    return ime + " " + prezime;
}

    
}
