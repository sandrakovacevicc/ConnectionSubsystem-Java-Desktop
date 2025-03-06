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
public class KatastarskaOpstina extends DomainObject{
    private int id_opstine;
    private String naziv;

    public KatastarskaOpstina() {
    }

    public KatastarskaOpstina(int id_opstine, String naziv) {
        this.id_opstine = id_opstine;
        this.naziv = naziv;
    }

    public int getId_opstine() {
        return id_opstine;
    }

    public void setId_opstine(int id_opstine) {
        this.id_opstine = id_opstine;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    
    @Override
    public String getTableName() {
        return "KATASTARSKA_OPSTINA"; 
    }

    @Override
    public String getAllColumnNames() {
    return "ID_OPSTINE,NAZIV";     
    }

    @Override
    public String getInsertColumnNames() {
    return "ID_OPSTINE,NAZIV"; 
    }

    @Override
    public String getColumnValues() {
        return String.format("%d, %s",id_opstine,naziv);
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
    List<DomainObject> opstine = new ArrayList<>();
        
        while(rs.next()) {
            int id_opstine = rs.getInt("ID_OPSTINE");     
            String naziv = rs.getString("NAZIV");
            
            opstine.add(new KatastarskaOpstina(id_opstine, naziv));
        }
        return opstine;    }

    @Override
    public String getOrderByColumn() {
        return "NAZIV";
    }
    
}
