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
public class Grad extends DomainObject{
    private int postanski_br;
    private String naziv;

    public Grad() {
    }

    public Grad(int postanski_br, String naziv) {
        this.postanski_br = postanski_br;
        this.naziv = naziv;
    }

    public int getPostanski_br() {
        return postanski_br;
    }

    public void setPostanski_br(int postanski_br) {
        this.postanski_br = postanski_br;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    
    @Override
    public String getTableName() {
        return "GRAD"; 
    }

    @Override
    public String getAllColumnNames() {
        return "POSTANSKI_BR, NAZIV"; 
    }

    @Override
    public String getInsertColumnNames() {
        return "POSTANSKI_BR, NAZIV";     
    }

    @Override
    public String getColumnValues() {
        return String.format("%d, %s",postanski_br,naziv);
    }

    @Override
    public String getUpdateClause() {
         return String.format("POSTANSKI_BR = %d, NAZIV = '%s'",
                this.getPostanski_br(),
                this.getNaziv());      
    }

    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateWhereClause() {
        return String.format("POSTANSKI_BR = %d", this.getPostanski_br());
    }

    @Override
    public String getDeleteWhereClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> gradovi = new ArrayList<>();
        
        while(rs.next()) {
            int postanski_broj = rs.getInt("POSTANSKI_BR");     
            String naziv = rs.getString("NAZIV");
            
            gradovi.add(new Grad(postanski_broj, naziv));
        }
        return gradovi;
    }

    @Override
    public String getOrderByColumn() {
        return "NAZIV"; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
