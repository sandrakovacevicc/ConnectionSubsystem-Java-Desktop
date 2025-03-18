/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain.Object.entities;

import Domain.Object.DomainObject;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Struct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author korisnik
 */
public class Resenje extends DomainObject{
    private int id_resenja;
    private Date datum;
    private String broj;
    private int id_direktora;
    private int br_zahteva;
    private int id_uslovP;
    private int id_uslovZ;
    private int id_prikljucka;
    private String naziv_prikljucka;

    public Resenje(int id_resenja, Date datum, String broj, int id_direktora, int br_zahteva, int id_uslovP, int id_uslovZ,int id_prikljucka, String naziv_prikljucka) {
        this.id_resenja = id_resenja;
        this.datum = datum;
        this.broj = broj;
        this.id_direktora = id_direktora;
        this.br_zahteva = br_zahteva;
        this.id_uslovP = id_uslovP;
        this.id_uslovZ = id_uslovZ;
        this.id_prikljucka = id_prikljucka;
        this.naziv_prikljucka = naziv_prikljucka;
    }

   

    public int getId_resenja() {
        return id_resenja;
    }

    public void setId_resenja(int id_resenja) {
        this.id_resenja = id_resenja;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public int getId_direktora() {
        return id_direktora;
    }

    public void setId_direktora(int id_direktora) {
        this.id_direktora = id_direktora;
    }

    public int getBr_zahteva() {
        return br_zahteva;
    }

    public void setBr_zahteva(int br_zahteva) {
        this.br_zahteva = br_zahteva;
    }

    public int getId_uslovP() {
        return id_uslovP;
    }

    public void setId_uslovP(int id_uslovP) {
        this.id_uslovP = id_uslovP;
    }

    public int getId_uslovZ() {
        return id_uslovZ;
    }

    public void setId_uslovZ(int id_uslovZ) {
        this.id_uslovZ = id_uslovZ;
    }

    public int getId_prikljucka() {
        return id_prikljucka;
    }

    public void setId_prikljucka(int id_prikljucka) {
        this.id_prikljucka = id_prikljucka;
    }

    public String getNaziv_prikljucka() {
        return naziv_prikljucka;
    }

    public void setNaziv_prikljucka(String naziv_prikljucka) {
        this.naziv_prikljucka = naziv_prikljucka;
    }

    public Resenje() {
    }
    
    

    @Override
    public String getTableName() {
        return "RESENJE";
    }

    @Override
    public String getAllColumnNames() {
        return "ID_RESENJA,DATUM,BROJ,ID_DIREKTORA,BR_ZAHTEVA, ID_USLOVP, ID_USLOVZ,ID_PRIKLJUCAK,NAZIV_PRIKLJUCKA";
    }

    @Override
    public String getInsertColumnNames() {
        return "ID_RESENJA,DATUM,BROJ,ID_DIREKTORA,BR_ZAHTEVA,ID_USLOVP, ID_USLOVZ,ID_PRIKLJUCAK,NAZIV_PRIKLJUCKA";
    }

    @Override
    public String getColumnValues() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        String datumd = datum != null ? "TO_DATE('" + sdf.format(datum) + "', 'dd-MM-yyyy')" : "NULL";

        return String.format("%d, %s, %s, %d, %d, %d, %d, %d, '%s'",
                id_resenja,
                datumd,
                broj,
                id_direktora,
                br_zahteva,
                id_uslovP,
                id_uslovZ,
                id_prikljucka,
                naziv_prikljucka);    }

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
    return String.format("ID_RESENJA = %d", this.getId_resenja());    }

    @Override
    public String getDeleteWhereClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
    List<DomainObject> resenja =new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        
        while (rs.next()){
              int id_resenja =rs.getInt("ID_RESENJA");
              Date datum = rs.getDate("DATUM");
              String broj =rs.getString("BROJ");
              int id_direktora =rs.getInt("ID_DIREKTORA");
              int br_zahteva =rs.getInt("BR_ZAHTEVA");
              int id_uslovP =rs.getInt("ID_USLOVP");
              int id_uslovZ =rs.getInt("ID_USLOVZ");
              int id_prikljucka =rs.getInt("ID_PRIKLJUCAK");
              String naziv_prikljucka = rs.getString("NAZIV_PRIKLJUCKA");

            
            resenja.add(new Resenje(id_resenja, datum, broj, id_direktora, br_zahteva, id_uslovP, id_uslovZ, id_prikljucka, naziv_prikljucka));
        }
        return resenja;    }

    @Override
    public String getOrderByColumn() {
        return "DATUM";
    }
    
}
