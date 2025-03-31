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
public class GradskaUprava extends DomainObject{
    private int id_uprave;
    private String naziv;
    private int postanski_br;
    private int id_ulice;

    public GradskaUprava(int id_uprave, String naziv, int postanski_br, int id_ulice) {
        this.id_uprave = id_uprave;
        this.naziv = naziv;
        this.postanski_br = postanski_br;
        this.id_ulice = id_ulice;
    }

    public GradskaUprava() {
    }

    public int getId_uprave() {
        return id_uprave;
    }

    public void setId_uprave(int id_uprave) {
        this.id_uprave = id_uprave;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
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
        return "GRADSKA_UPRAVA";
    }

    @Override
    public String getAllColumnNames() {
        return "ID_UPRAVE, NAZIV, POSTANSKI_BR, ID_ULICE";
    }

    @Override
    public String getInsertColumnNames() {
        return "ID_UPRAVE, NAZIV, POSTANSKI_BR, ID_ULICE";
    }

    @Override
    public String getColumnValues() {
    return String.format("%d, '%s',%d, %d",
                id_uprave,
                naziv,
                postanski_br,
                id_ulice);    }

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
    List<DomainObject> uprave = new ArrayList<>();

        while (rs.next()) {
            int id_uprave = rs.getInt("ID_UPRAVE");
            String naziv = rs.getString("NAZIV");
            int postanski_br = rs.getInt("POSTANSKI_BR");
            int id_ulice = rs.getInt("ID_ULICE");

            uprave.add(new GradskaUprava(id_uprave, naziv, postanski_br, id_ulice));
        }
        return uprave;    }

    @Override
    public String getOrderByColumn() {
        return "ID_UPRAVE";
    }
    
}
