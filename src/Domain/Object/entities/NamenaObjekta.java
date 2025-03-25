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
public class NamenaObjekta extends DomainObject{
    private int id_namena_objekta;
    private String naziv;

    public NamenaObjekta() {
    }

    public NamenaObjekta(int id_namena_objekta, String naziv) {
        this.id_namena_objekta = id_namena_objekta;
        this.naziv = naziv;
    }

    public int getId_namena_objekta() {
        return id_namena_objekta;
    }

    public void setId_namena_objekta(int id_namena_objekta) {
        this.id_namena_objekta = id_namena_objekta;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    
    @Override
    public String getTableName() {
        return "NAMENA_OBJEKTA";
    }

    @Override
    public String getAllColumnNames() {
        return "ID_NAMENA_OBJEKTA,NAZIV";
    }

    @Override
    public String getInsertColumnNames() {
        return "ID_NAMENA_OBJEKTA,NAZIV";
    }

    @Override
    public String getColumnValues() {
        return String.format("%d, %s",id_namena_objekta,naziv);
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
    List<DomainObject> namena = new ArrayList<>();
        
        while(rs.next()) {
            int id_namena_objekta = rs.getInt("ID_NAMENA_OBJEKTA"); 
            String naziv = rs.getString("NAZIV");
            
            namena.add(new NamenaObjekta(id_namena_objekta, naziv));
        }
        return namena;      }

    @Override
    public String getOrderByColumn() {
        return "ID_NAMENA_OBJEKTA";
    }
    
}
