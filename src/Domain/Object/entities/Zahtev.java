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
public class Zahtev extends DomainObject{
    private int br_zahteva;
    private Date datum;
    private int zaposleni_podnosioc;
    private int zaposleni_zastupnik;
    private int id_objekta;

    public Zahtev(int br_zahteva, Date datum, int zaposleni_podnosioc, int zaposleni_zastupnik, int id_objekta) {
        this.br_zahteva = br_zahteva;
        this.datum = datum;
        this.zaposleni_podnosioc = zaposleni_podnosioc;
        this.zaposleni_zastupnik = zaposleni_zastupnik;
        this.id_objekta = id_objekta;
    }
    


    public Zahtev() {
    }

    public int getZaposleni_podnosioc() {
        return zaposleni_podnosioc;
    }

    public void setZaposleni_podnosioc(int zaposleni_podnosioc) {
        this.zaposleni_podnosioc = zaposleni_podnosioc;
    }

    public int getZaposleni_zastupnik() {
        return zaposleni_zastupnik;
    }

    public void setZaposleni_zastupnik(int zaposleni_zastupnik) {
        this.zaposleni_zastupnik = zaposleni_zastupnik;
    }

    public int getId_objekta() {
        return id_objekta;
    }

    public void setId_objekta(int id_objekta) {
        this.id_objekta = id_objekta;
    }
    

    public int getBr_zahteva() {
        return br_zahteva;
    }

    public void setBr_zahteva(int br_zahteva) {
        this.br_zahteva = br_zahteva;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
   

    @Override
    public String getTableName() {
        return "ZAHTEV";
    }

    @Override
    public String getAllColumnNames() {
        return "BR_ZAHTEVA, DATUM, ZAPOSLENI_PODNOSIOC,ZAPOSLENI_ZASTUPNIK,ID_OBJEKTA";
    }

    @Override
    public String getInsertColumnNames() {
        return "BR_ZAHTEVA, DATUM, ZAPOSLENI_PODNOSIOC,ZAPOSLENI_ZASTUPNIK,ID_OBJEKTA";    }

    @Override
    public String getColumnValues() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");

        String datumd = datum != null ? "TO_DATE('" + sdf.format(datum) + "', 'DD-MM-YY')" : "NULL";

        return String.format("%d, %s,%d,%d,%d",
                br_zahteva,
                datumd,
                zaposleni_podnosioc,
                zaposleni_zastupnik,
                id_objekta);    }

    @Override
    public String getUpdateClause() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");

        String datumd = datum != null ? "TO_DATE('" + sdf.format(datum) + "', 'DD-MM-YY')" : "NULL";
    return String.format("BR_ZAHTEVA = %d, DATUM = %s, ZAPOSLENI_PODNOSIOC = %d, ZAPOSLENI_ZASTUPNIK = %d, ID_OBJEKTA = %d",
                br_zahteva,
                datumd,
                zaposleni_podnosioc,
                zaposleni_zastupnik,
                id_objekta);   
    }

    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateWhereClause() {
    return String.format("BR_ZAHTEVA = '%d'", this.getBr_zahteva());     }

    @Override
    public String getDeleteWhereClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
    List<DomainObject> zahtevi =new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy");
        
        while (rs.next()){
              int br_zahteva =rs.getInt("BR_ZAHTEVA");
              Date datum = rs.getDate("DATUM");
              int zaposleni_podnosioc =rs.getInt("ZAPOSLENI_PODNOSIOC");
              int zaposleni_zastupnik =rs.getInt("ZAPOSLENI_ZASTUPNIK");
              int id_objekta =rs.getInt("ID_OBJEKTA");


            
            zahtevi.add(new Zahtev(br_zahteva, datum,zaposleni_podnosioc, zaposleni_zastupnik, id_objekta));
        }
        return zahtevi;        }

    @Override
    public String getOrderByColumn() {
        return "BR_ZAHTEVA";
    }
    
    @Override
    public String toString() {
        return datum.toString();
    }
    
}
