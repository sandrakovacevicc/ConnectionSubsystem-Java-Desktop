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
public class ZaposleniOsnovno extends DomainObject{

    private int id_zaposlenog;
    private String ime;
    private String prezime;

    public ZaposleniOsnovno(int id_zaposlenog, String ime, String prezime) {
        this.id_zaposlenog = id_zaposlenog;
        this.ime = ime;
        this.prezime = prezime;
    }

    public ZaposleniOsnovno() {
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
    
    
    @Override
    public String getTableName() {
    return "ZAPOSLENI_OSNOVNO";     }

    @Override
    public String getAllColumnNames() {
        return "ID_ZAPOSLENOG, IME, PREZIME";
    }

    @Override
    public String getInsertColumnNames() {
        return "ID_ZAPOSLENOG, IME, PREZIME";
    }

    @Override
    public String getColumnValues() {
    return String.format("%d, %s, %s",
                id_zaposlenog,
                ime,
                prezime);    }

    @Override
    public String getUpdateClause() {
        return String.format("ID_ZAPOSLENOG = %d, IME = %s, PREZIME = %s",
                id_zaposlenog,
                ime,
                prezime);
    }

    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateWhereClause() {
    return String.format("ID_ZAPOSLENOG = %d", this.getId_zaposlenog());    }

    @Override
    public String getDeleteWhereClause() {
    return String.format("ID_ZAPOSLENOG = %d", this.getId_zaposlenog());    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
    List<DomainObject> zaposleni = new ArrayList<>();

        while (rs.next()) {
            int id_zaposlenog = rs.getInt("ID_ZAPOSLENOG");
            String ime = rs.getString("IME");
            String prezime = rs.getString("PREZIME");

            zaposleni.add(new ZaposleniOsnovno(id_zaposlenog, ime, prezime));
        }
        return zaposleni;    }

    @Override
    public String getOrderByColumn() {
    return "ID_ZAPOSLENOG";    }
    
}
