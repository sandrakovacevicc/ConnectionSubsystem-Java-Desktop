/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain.Object.entities;

import Domain.Object.DomainObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author korisnik
 */
public class ZaposleniPogled extends DomainObject {
    
    private int id_zaposlenog;
    private String ime;
    private String prezime;
    private String kontakt;
    private int postanski_br;
    private int id_ulice;
    private int id_filijale;

    public ZaposleniPogled(int id_zaposlenog, String ime, String prezime, String kontakt, int postanski_br, int id_ulice, int id_filijale) {
        this.id_zaposlenog = id_zaposlenog;
        this.ime = ime;
        this.prezime = prezime;
        this.kontakt = kontakt;
        this.postanski_br = postanski_br;
        this.id_ulice = id_ulice;
        this.id_filijale = id_filijale;
    }

    public ZaposleniPogled() {
    }

    public int getId_zaposlenog() {
        return id_zaposlenog;
    }

    public void setId_zaposlenog(int id_zaposlenog) {
        this.id_zaposlenog = id_zaposlenog;
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

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

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

    public int getId_filijale() {
        return id_filijale;
    }

    public void setId_filijale(int id_filijale) {
        this.id_filijale = id_filijale;
    }


    
    @Override
    public String getTableName() {
        return "ZAPOSLENI_POGLED"; 
    }

    @Override
    public String getAllColumnNames() {
        return "ID_ZAPOSLENOG, IME, PREZIME, KONTAKT, POSTANSKI_BR, ID_ULICE, ID_FILIJALE";
    }

    @Override
    public String getInsertColumnNames() {
        return "ID_ZAPOSLENOG, IME, PREZIME, KONTAKT, POSTANSKI_BR, ID_ULICE, ID_FILIJALE";
    }

    @Override
    public String getColumnValues() {
        return String.format("%d, %s, %s, %s, %d, %d, %d",
                id_zaposlenog,
                ime,
                prezime,
                kontakt,
                postanski_br,
                id_ulice,
                id_filijale);
    }

    @Override
    public String getUpdateClause() {
         return String.format("ID_ZAPOSLENOG = %d, IME = %s, PREZIME = %s, KONTAKT = '%s', POSTANSKI_BR = %d, ID_ULICE = %d, ID_FILIJALE = %d",
                id_zaposlenog,
                ime,
                prezime,
                kontakt,
                postanski_br,
                id_ulice,
                id_filijale);
    }

    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateWhereClause() {
        return String.format("ID_ZAPOSLENOG = %d", this.getId_zaposlenog());
    }

    @Override
    public String getDeleteWhereClause() {
        return String.format("ID_ZAPOSLENOG = %d", this.getId_zaposlenog());

    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> zaposleni = new ArrayList<>();

        while (rs.next()) {
            int id_zaposlenog = rs.getInt("ID_ZAPOSLENOG");
            String ime = rs.getString("IME");
            String prezime = rs.getString("PREZIME");
            String kontakt = rs.getString("KONTAKT");
            int postanski_br = rs.getInt("POSTANSKI_BR");
            int id_ulice = rs.getInt("ID_ULICE");
            int id_filijale = rs.getInt("ID_FILIJALE");

            zaposleni.add(new ZaposleniPogled(id_zaposlenog, ime, prezime, kontakt, postanski_br, id_ulice, id_filijale));
        }
        return zaposleni;
    }

    @Override
    public String getOrderByColumn() {
        return "ID_ZAPOSLENOG";
    }
    
     @Override
     public String toString(){
         
        return ime + " " + prezime;
         
     }
    
}
