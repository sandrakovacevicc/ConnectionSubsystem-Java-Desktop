/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain.Object.entities;

import Domain.Object.DomainObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author korisnik
 */
public class Prikljucak extends DomainObject{
    private int id_prikljucak;
    private String naziv;
    private String opis;
    private String mesto_prikljucenja;
    private String mesto_vezivanja;
    private String merni_uredjaj;
    private String zastitni_uredjaj;

    public Prikljucak() {
    }

    public Prikljucak(int id_prikljucak, String naziv, String opis, String mesto_prikljucenja, String mesto_vezivanja, String merni_uredjaj, String zastitni_uredjaj) {
        this.id_prikljucak = id_prikljucak;
        this.naziv = naziv;
        this.opis = opis;
        this.mesto_prikljucenja = mesto_prikljucenja;
        this.mesto_vezivanja = mesto_vezivanja;
        this.merni_uredjaj = merni_uredjaj;
        this.zastitni_uredjaj = zastitni_uredjaj;
    }

    public int getId_prikljucak() {
        return id_prikljucak;
    }

    public void setId_prikljucak(int id_prikljucak) {
        this.id_prikljucak = id_prikljucak;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getMesto_prikljucenja() {
        return mesto_prikljucenja;
    }

    public void setMesto_prikljucenja(String mesto_prikljucenja) {
        this.mesto_prikljucenja = mesto_prikljucenja;
    }

    public String getMesto_vezivanja() {
        return mesto_vezivanja;
    }

    public void setMesto_vezivanja(String mesto_vezivanja) {
        this.mesto_vezivanja = mesto_vezivanja;
    }

    public String getMerni_uredjaj() {
        return merni_uredjaj;
    }

    public void setMerni_uredjaj(String merni_uredjaj) {
        this.merni_uredjaj = merni_uredjaj;
    }

    public String getZastitni_uredjaj() {
        return zastitni_uredjaj;
    }

    public void setZastitni_uredjaj(String zastitni_uredjaj) {
        this.zastitni_uredjaj = zastitni_uredjaj;
    }
    
    
    @Override
    public String getTableName() {
        return "PRIKLJUCAK";
    }

    @Override
    public String getAllColumnNames() {
        return "ID_PRIKLJUCAK,NAZIV,OPIS,PRIKLJUCAK_PODACI";
    }

    @Override
    public String getInsertColumnNames() {
        return "ID_PRIKLJUCAK,NAZIV,OPIS,PRIKLJUCAK_PODACI";
    }

    @Override
    public String getColumnValues() {
    return String.format(
        "'%d', '%s', '%s', NEW PRIKLJUCAK_PODACI(%s, '%s', '%s', '%s')",
        id_prikljucak,
        naziv,
        opis,
        mesto_prikljucenja,
        mesto_vezivanja,
        merni_uredjaj,
        zastitni_uredjaj
    );
}


    @Override
    public String getUpdateClause() {
        return String.format("ID_PRIKLJUCAK = '%d', NAZIV = '%s', OPIS = '%s', PRIKLJUCAK_PODACI = PRIKLJUCAK_PODACI(%s, '%s', '%s', '%s')",
                id_prikljucak,
                naziv,
                opis,
                mesto_prikljucenja,
                mesto_vezivanja,
                merni_uredjaj,
                zastitni_uredjaj);
    }

    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateWhereClause() {
        return String.format("ID_PRIKLJUCAK = '%d'", this.getId_prikljucak()); 
    }

    @Override
    public String getDeleteWhereClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> priključci = new ArrayList<>();

        while (rs.next()) {
            int id_prikljucak = rs.getInt("ID_PRIKLJUCAK");
            String naziv = rs.getString("NAZIV");
            String opis = rs.getString("OPIS");
            Struct struct = (Struct) rs.getObject("PRIKLJUCAK_PODACI");
            Object[] attributes = struct.getAttributes();
            String mesto_prikljucenja = (String) attributes[0];
            String mesto_vezivanja = (String) attributes[1];
            String merni_uredjaj = (String) attributes[2];
            String zastitni_uredjaj = (String) attributes[3];

            priključci.add(new Prikljucak(id_prikljucak, naziv, opis, mesto_prikljucenja, mesto_vezivanja, merni_uredjaj, zastitni_uredjaj));
        }
        return priključci;
    }

    @Override
    public String getOrderByColumn() {
        return "ID_PRIKLJUCAK"; 
    }
    
    @Override
    public String toString() {
    return naziv;}
    
}
