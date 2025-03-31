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
public class MolbaZaUrbanizam extends DomainObject{

    private int id_molbe;
    private Date datum;
    private String delovodni_br;
    private int br_iz_LKRM;
    private int id_objekta;
    private int id_kontakt_osobe;
    private int id_koordinatora_tehnike;
    private int id_uprave;
    private int postanski_br;
    private int id_ulice;

    public MolbaZaUrbanizam(int id_molbe, Date datum, String delovodni_br, int br_iz_LKRM, int id_objekta, int id_kontakt_osobe, int id_koordinatora_tehnike, int id_uprave, int postanski_br, int id_ulice) {
        this.id_molbe = id_molbe;
        this.datum = datum;
        this.delovodni_br = delovodni_br;
        this.br_iz_LKRM = br_iz_LKRM;
        this.id_objekta = id_objekta;
        this.id_kontakt_osobe = id_kontakt_osobe;
        this.id_koordinatora_tehnike = id_koordinatora_tehnike;
        this.id_uprave = id_uprave;
        this.postanski_br = postanski_br;
        this.id_ulice = id_ulice;
    }

    public MolbaZaUrbanizam() {
    }

    public int getId_molbe() {
        return id_molbe;
    }

    public void setId_molbe(int id_molbe) {
        this.id_molbe = id_molbe;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getDelovodni_br() {
        return delovodni_br;
    }

    public void setDelovodni_br(String delovodni_br) {
        this.delovodni_br = delovodni_br;
    }

    public int getBr_iz_LKRM() {
        return br_iz_LKRM;
    }

    public void setBr_iz_LKRM(int br_iz_LKRM) {
        this.br_iz_LKRM = br_iz_LKRM;
    }

    public int getId_objekta() {
        return id_objekta;
    }

    public void setId_objekta(int id_objekta) {
        this.id_objekta = id_objekta;
    }

    public int getId_kontakt_osobe() {
        return id_kontakt_osobe;
    }

    public void setId_kontakt_osobe(int id_kontakt_osobe) {
        this.id_kontakt_osobe = id_kontakt_osobe;
    }

    public int getId_koordinatora_tehnike() {
        return id_koordinatora_tehnike;
    }

    public void setId_koordinatora_tehnike(int id_koordinatora_tehnike) {
        this.id_koordinatora_tehnike = id_koordinatora_tehnike;
    }

    public int getId_uprave() {
        return id_uprave;
    }

    public void setId_uprave(int id_uprave) {
        this.id_uprave = id_uprave;
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
    
    
    @Override
    public String getTableName() {
    return "MOLBA_ZA_URBANIZAM"; }

    @Override
    public String getAllColumnNames() {
        return "ID_MOLBE, DATUM, DELOVODNI_BR, BR_IZ_LKRM, ID_OBJEKTA, ID_KONTAKT_OSOBE, ID_KOORDINATORA_TEHNIKE, ID_UPRAVE, POSTANSKI_BR, ID_ULICE";
    }

    @Override
    public String getInsertColumnNames() {
        return "ID_MOLBE, DATUM, DELOVODNI_BR, BR_IZ_LKRM, ID_OBJEKTA, ID_KONTAKT_OSOBE, ID_KOORDINATORA_TEHNIKE, ID_UPRAVE,POSTANSKI_BR, ID_ULICE";
    }

 @Override
public String getColumnValues() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    String datumIzdavanja = (datum != null) ? 
        String.format("TO_DATE('%s', 'DD-MM-YYYY')", sdf.format(datum)) : "NULL";

    String postanskiBrValue = (postanski_br == 0) ? "NULL" : String.valueOf(postanski_br);
    String idUliceValue = (id_ulice == 0) ? "NULL" : String.valueOf(id_ulice);


    return String.format("%d, %s, '%s', br_iz_LKRM(%d), %d, %d, %d, %d, %s, %s",
            id_molbe,
            datumIzdavanja,
            delovodni_br,
            br_iz_LKRM,
            id_objekta,
            id_kontakt_osobe,
            id_koordinatora_tehnike,
            id_uprave,
            postanskiBrValue,
            idUliceValue);
}



    @Override
    public String getUpdateClause() {
         SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");

        String datumd = datum != null ? "TO_DATE('" + sdf.format(datum) + "', 'DD-MM-YY')" : "NULL";
       return String.format("ID_MOLBE = %d, DATUM = %s, DELOVODNI_BR = '%s' ,BR_IZ_LKRM = br_iz_LKRM(%d),ID_OBJEKTA = %d, ID_KONTAKT_OSOBE= %d, ID_KOORDINATORA_TEHNIKE = %d, ID_UPRAVE = %d",
                id_molbe,
                datumd,
                delovodni_br,
                br_iz_LKRM,
                id_objekta,
                id_kontakt_osobe,
                id_koordinatora_tehnike,
                id_uprave);}
    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateWhereClause() {
    return String.format("ID_MOLBE = %d", this.getId_molbe());    }

    @Override
    public String getDeleteWhereClause() {
    return String.format("ID_MOLBE = %d", this.getId_molbe());    
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
    List<DomainObject> molbe =new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy");
        
        while (rs.next()){
              int molbaID =rs.getInt("ID_MOLBE");
              Date datum = rs.getDate("DATUM");
              String delovodni_br =rs.getString("DELOVODNI_BR");
              Struct struct = (Struct) rs.getObject("BR_IZ_LKRM");
              Object[] attributes = struct.getAttributes();
              int urd = attributes[0] != null ? ((BigDecimal) attributes[0]).intValue() : 0;
              int id_objekta =rs.getInt("ID_OBJEKTA");
              int id_kontakt_osobe =rs.getInt("ID_KONTAKT_OSOBE");
              int id_koordinatora_tehnike =rs.getInt("ID_KOORDINATORA_TEHNIKE");
              int id_uprave =rs.getInt("ID_UPRAVE");
              int postanski_br =rs.getInt("POSTANSKI_BR");
              int id_ulice =rs.getInt("ID_ULICE");
            
            molbe.add(new MolbaZaUrbanizam(molbaID, datum, delovodni_br, urd, id_objekta, id_kontakt_osobe, id_koordinatora_tehnike, id_uprave, postanski_br, id_ulice));
        }
        return molbe;    }

  @Override
    public String getOrderByColumn() {
        return "ID_MOLBE";
    }
    
}
