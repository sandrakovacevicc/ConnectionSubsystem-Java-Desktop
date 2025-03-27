/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain.Object.entities;

import Domain.Object.DomainObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author korisnik
 */
public class Snaga extends DomainObject{

    private int id_snage;
    private int id_objekta;
    private String naziv;
    private float jacina;

    public Snaga(int id_snage, int id_objekta, String naziv, float jacina) {
        this.id_snage = id_snage;
        this.id_objekta = id_objekta;
        this.naziv = naziv;
        this.jacina = jacina;
    }

    public Snaga() {
    }

    public int getId_snage() {
        return id_snage;
    }

    public void setId_snage(int id_snage) {
        this.id_snage = id_snage;
    }

    public int getId_objekta() {
        return id_objekta;
    }

    public void setId_objekta(int id_objekta) {
        this.id_objekta = id_objekta;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public float getJacina() {
        return jacina;
    }

    public void setJacina(float jacina) {
        this.jacina = jacina;
    }
    
    
    
    @Override
    public String getTableName() {
        return "SNAGA";
    }

    @Override
    public String getAllColumnNames() {
        return "ID_SNAGE, ID_OBJEKTA, NAZIV,JACINA";    }

    @Override
    public String getInsertColumnNames() {
        return "ID_SNAGE, ID_OBJEKTA, NAZIV,JACINA"; 
    }

    @Override
    public String getColumnValues() {
    return String.format("%d, %d, '%s', %.2f",
                id_snage,
                id_objekta,
                naziv,
                jacina);     }

    @Override
    public String getUpdateClause() {
    return String.format("ID_SNAGE = %d, ID_OBJEKTA = %d, NAZIV = '%s', JACINA = %.2f",
                id_snage,
                id_objekta,
                naziv,
                jacina
        );    }

    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateWhereClause() {
    return String.format("ID_SNAGE=%d", this.getId_snage());    }

    @Override
    public String getDeleteWhereClause() {
        return String.format("ID_SNAGE=%d", this.getId_snage());  
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
    List<DomainObject> snage = new ArrayList<>();
    
    while (rs.next()) {
        int id_snage = rs.getInt("ID_SNAGE");
        int id_objekta = rs.getInt("ID_OBJEKTA");
        String naziv = rs.getString("NAZIV");
        float jacina = rs.getFloat("JACINA");
       

        snage.add(new Snaga(id_snage, id_objekta, naziv, jacina));
    }
    return snage;    }

    @Override
    public String getOrderByColumn() {
        return "ID_SNAGE";
    }
    
}
