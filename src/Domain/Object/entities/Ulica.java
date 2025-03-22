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
public class Ulica extends DomainObject {

    private int postanski_br;
    private int id_ulice;
    private String naziv;
    private String naziv_grada;
    private int id_opstine;

    public int getPostanski_br() {
        return postanski_br;
    }

    public void setPostanski_br(int postanski_br) {
        this.postanski_br = postanski_br;
    }

    public int getId_ulice() {
        return id_ulice;
    }

    public void setId_ulice(int id_ulice) {
        this.id_ulice = id_ulice;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getNaziv_grada() {
        return naziv_grada;
    }

    public void setNaziv_grada(String naziv_grada) {
        this.naziv_grada = naziv_grada;
    }

    public int getId_opstine() {
        return id_opstine;
    }

    public void setId_opstine(int id_opstine) {
        this.id_opstine = id_opstine;
    }

    public Ulica(int postanski_br, int id_ulice, String naziv, int id_opstine, String naziv_grada) {
        this.postanski_br = postanski_br;
        this.id_ulice = id_ulice;
        this.naziv = naziv;
        this.naziv_grada = naziv_grada;
        this.id_opstine = id_opstine;
    }

    public Ulica() {
    }

    public Ulica(int postanski_br, int id_ulice, String naziv, int id_opstine) {
        this.postanski_br = postanski_br;
        this.id_ulice = id_ulice;
        this.naziv = naziv;
        this.id_opstine = id_opstine;
    }
    
    
    
    
    @Override
    public String getTableName() {
        return "ULICA";
    }

    @Override
    public String getAllColumnNames() {
        return "POSTANSKI_BR, ID_ULICE, NAZIV, ID_OPSTINE, NAZIV_GRADA"; 
    }

    @Override
    public String getInsertColumnNames() {
                return "POSTANSKI_BR, ID_ULICE, NAZIV, ID_OPSTINE, NAZIV_GRADA"; 
    }

    @Override
    public String getColumnValues() {
        String nazivGradaValue = (naziv_grada != null && !naziv_grada.isEmpty()) ? "'" + naziv_grada + "'" : "NULL";
        return String.format("%d,%d,'%s',%d,%s", postanski_br, id_ulice, naziv, id_opstine, nazivGradaValue);
    }

    @Override
    public String getUpdateClause() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody;
    }

    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateWhereClause() {
        return String.format("POSTANSKI_BR=%d AND ID_ULICE=%d", this.getPostanski_br(), this.getId_ulice());
    }

    @Override
    public String getDeleteWhereClause() {
        return String.format("POSTANSKI_BR=%d AND ID_ULICE=%d", this.getPostanski_br(), this.getId_ulice());

    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> ulice = new ArrayList<>();
        
        while(rs.next()) {
            int postanski_broj = rs.getInt("POSTANSKI_BR"); 
            int id_ulice = rs.getInt("ID_ULICE"); 
            String naziv = rs.getString("NAZIV");
            int id_opstine = rs.getInt("ID_OPSTINE"); 
            String naziv_grada = rs.getString("NAZIV_GRADA");
            
            ulice.add(new Ulica(postanski_broj, id_ulice, naziv,id_opstine,naziv_grada));
        }
        return ulice;
    }
   

    @Override
    public String getOrderByColumn() {
        return "ID_ULICE"; 
    }
    
}
