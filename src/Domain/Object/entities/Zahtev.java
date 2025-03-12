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

    public Zahtev(int br_zahteva, Date datum) {
        this.br_zahteva = br_zahteva;
        this.datum = datum;
    }

    public Zahtev() {
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
        return "BR_ZAHTEVA, DATUM";
    }

    @Override
    public String getInsertColumnNames() {
        return "BR_ZAHTEVA, DATUM";    }

    @Override
    public String getColumnValues() {
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");

        String datumd = datum != null ? "TO_DATE('" + sdf.format(datum) + "', 'DD-MM-YY')" : "NULL";

        return String.format("%d, %s",
                br_zahteva,
                datumd);    }

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
    List<DomainObject> zahtevi =new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy");
        
        while (rs.next()){
              int br_zahteva =rs.getInt("BR_ZAHTEVA");
              Date datum = rs.getDate("DATUM");


            
            zahtevi.add(new Zahtev(br_zahteva, datum));
        }
        return zahtevi;        }

    @Override
    public String getOrderByColumn() {
        return "DATUM";
    }
    
    @Override
    public String toString() {
        return datum.toString();
    }
    
}
