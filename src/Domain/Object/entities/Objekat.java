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
public class Objekat extends DomainObject {
    private int id_objekta;
    private String katastarska_pacrela;
    private int postanski_br;
    private int id_ulice;
    private int id_nacin_grejanja;
    private int id_namene_objekta;
    private int id_vrste_prikljucka;
    private int id_instalacije;
    private String vrsta_objekta;
    private float ukupna_snaga;

    public Objekat(int id_objekta, String katastarska_pacrela, int postanski_br, int id_ulice, int id_nacin_grejanja, int id_namene_objekta, int id_vrste_prikljucka, int id_instalacije, String vrsta_objekta, float ukupna_snaga) {
        this.id_objekta = id_objekta;
        this.katastarska_pacrela = katastarska_pacrela;
        this.postanski_br = postanski_br;
        this.id_ulice = id_ulice;
        this.id_nacin_grejanja = id_nacin_grejanja;
        this.id_namene_objekta = id_namene_objekta;
        this.id_vrste_prikljucka = id_vrste_prikljucka;
        this.id_instalacije = id_instalacije;
        this.vrsta_objekta = vrsta_objekta;
        this.ukupna_snaga = ukupna_snaga;
    }

    public Objekat() {
    }

    public int getId_objekta() {
        return id_objekta;
    }

    public void setId_objekta(int id_objekta) {
        this.id_objekta = id_objekta;
    }

    public String getKatastarska_pacrela() {
        return katastarska_pacrela;
    }

    public void setKatastarska_pacrela(String katastarska_pacrela) {
        this.katastarska_pacrela = katastarska_pacrela;
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

    public int getId_nacin_grejanja() {
        return id_nacin_grejanja;
    }

    public void setId_nacin_grejanja(int id_nacin_grejanja) {
        this.id_nacin_grejanja = id_nacin_grejanja;
    }

    public int getId_namene_objekta() {
        return id_namene_objekta;
    }

    public void setId_namene_objekta(int id_namene_objekta) {
        this.id_namene_objekta = id_namene_objekta;
    }

    public int getId_vrste_prikljucka() {
        return id_vrste_prikljucka;
    }

    public void setId_vrste_prikljucka(int id_vrste_prikljucka) {
        this.id_vrste_prikljucka = id_vrste_prikljucka;
    }

    public int getId_instalacije() {
        return id_instalacije;
    }

    public void setId_instalacije(int id_instalacije) {
        this.id_instalacije = id_instalacije;
    }

    public String getVrsta_objekta() {
        return vrsta_objekta;
    }

    public void setVrsta_objekta(String vrsta_objekta) {
        this.vrsta_objekta = vrsta_objekta;
    }

    public float getUkupna_snaga() {
        return ukupna_snaga;
    }

    public void setUkupna_snaga(float ukupna_snaga) {
        this.ukupna_snaga = ukupna_snaga;
    }
    

    @Override
    public String getTableName() {
        return "OBJEKAT";
    }

    @Override
    public String getAllColumnNames() {
        return "ID_OBJEKTA, KATASTARSKA_PARCELA, POSTANSKI_BR, ID_ULICE, ID_NACIN_GREJANJA, ID_NAMENE_OBJEKTA, ID_VRSTE_PRIKLJUCKA, ID_INSTALACIJE, VRSTA_OBJEKTA, UKUPNA_SNAGA";
    }

    @Override
    public String getInsertColumnNames() {
        return "ID_OBJEKTA, KATASTARSKA_PARCELA, POSTANSKI_BR, ID_ULICE, ID_NACIN_GREJANJA, ID_NAMENE_OBJEKTA, ID_VRSTE_PRIKLJUCKA, ID_INSTALACIJE, VRSTA_OBJEKTA, UKUPNA_SNAGA";
    }

    @Override
    public String getColumnValues() {
        return String.format("%d, %s, %d, %d, %d, %d, %d, %d,'%s', ukupna_snaga(%.2f,)",
                id_objekta,
                katastarska_pacrela,
                postanski_br,
                id_ulice,
                id_nacin_grejanja,
                id_namene_objekta,
                id_vrste_prikljucka,
                id_instalacije,
                vrsta_objekta,
                ukupna_snaga);    }

    @Override
    public String getUpdateClause() {
    return String.format("ID_OBJEKTA = %d, UKUPNA_SNAGA = %.2f",
                id_objekta,
                ukupna_snaga);    }

    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateWhereClause() {
    return String.format("ID_OBJEKTA = %d", this.getId_objekta());    }

    @Override
    public String getDeleteWhereClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   @Override
public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
    List<DomainObject> objekti = new ArrayList<>();
    
    while (rs.next()) {
        int id_objekta = rs.getInt("ID_OBJEKTA");
        String katastarska_parcela = rs.getString("KATASTARSKA_PARCELA");
        int postanski_br = rs.getInt("POSTANSKI_BR");
        int id_ulice = rs.getInt("ID_ULICE");
        int id_nacin_grejanja = rs.getInt("ID_NACIN_GREJANJA");
        int id_namene_objekta = rs.getInt("ID_NAMENE_OBJEKTA");
        int id_vrste_prikljucka = rs.getInt("ID_VRSTE_PRIKLJUCKA");
        int id_instalacije = rs.getInt("ID_INSTALACIJE");
        String vrsta_objekta = rs.getString("VRSTA_OBJEKTA");
        float ukupna_snaga = rs.getFloat("UKUPNA_SNAGA");
       

        objekti.add(new Objekat(id_objekta, katastarska_parcela, postanski_br, id_ulice, 
                                id_nacin_grejanja, id_namene_objekta, id_vrste_prikljucka, 
                                id_instalacije, vrsta_objekta, ukupna_snaga));
    }
    return objekti;
}

    @Override
    public String getOrderByColumn() {
        return "ID_OBJEKTA";
    }
    
     @Override
    public String toString() {
        return vrsta_objekta;
    }
    
}
