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
public class Filijala extends DomainObject{
    private int id_filijale;
    private String naziv;

    public Filijala() {
    }

    public Filijala(int id_filijale, String naziv) {
        this.id_filijale = id_filijale;
        this.naziv = naziv;
    }

    public int getId_filijale() {
        return id_filijale;
    }

    public void setId_filijale(int id_filijale) {
        this.id_filijale = id_filijale;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
   
    
    @Override
    public String getTableName() {
        return "FILIJALA";
    }

    @Override
    public String getAllColumnNames() {
        return "ID_FILIJALE, NAZIV"; 
    }

    @Override
    public String getInsertColumnNames() {
        return "ID_FILIJALE, NAZIV"; 
    }

    @Override
    public String getColumnValues() {
        return String.format("%d, %s",id_filijale,naziv);
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
        List<DomainObject> filijale = new ArrayList<>();
        
        while(rs.next()){
            int id_filijale = rs.getInt("ID_FILIJALE");
            String naziv = rs.getString("NAZIV");
            filijale.add(new Filijala(id_filijale, naziv));
        }
        return filijale; 
    }

    @Override
    public String getOrderByColumn() {
        return "ID_FILIJALE"; 
    }
    
}
