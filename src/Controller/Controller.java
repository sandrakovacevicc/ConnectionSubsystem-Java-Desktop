package controller;

import DataBase.DatabaseBroker;
import Domain.Object.entities.Direktor;
import Domain.Object.entities.Filijala;
import Domain.Object.entities.Grad;
import Domain.Object.entities.KatastarskaOpstina;
import Domain.Object.entities.MolbaZaUrbanizam;
import Domain.Object.entities.NacinGrejanja;
import Domain.Object.entities.NamenaObjekta;
import Domain.Object.entities.Objekat;
import Domain.Object.entities.Prikljucak;
import Domain.Object.entities.Resenje;
import Domain.Object.entities.Snaga;
import Domain.Object.entities.TipInstalacije;
import Domain.Object.entities.Ulica;
import Domain.Object.entities.UsloviPostavljanja;
import Domain.Object.entities.UsloviZastite;
import Domain.Object.entities.VrstaPrikljucka;
import Domain.Object.entities.Zahtev;
import Domain.Object.entities.ZaposleniOsnovno;
import Domain.Object.entities.ZaposleniPogled;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author korisnik
 */
public class Controller {
    
    DatabaseBroker db = new DatabaseBroker();
    private static Controller instance;

    private Controller() {

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }
    
    public List<Grad> loadSveGradove() throws Exception {
        List<Grad> gradovi = new LinkedList<Grad>();
        try {
            db.connect();
            gradovi = (List<Grad>) (Object) db.getAll(new Grad());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return gradovi;
    }
    
    public List<Ulica> loadSveUlice() throws Exception {
        List<Ulica> ulice = new LinkedList<Ulica>();
        try {
            db.connect();
            ulice = (List<Ulica>) (Object) db.getAll(new Ulica());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ulice;
    }
    
    
     public List<Ulica> searchUlice(String whereClause) throws Exception {
        List<Ulica> ulice = new LinkedList<Ulica>();
        try {
            db.connect();
            ulice = (List<Ulica>) (Object) db.getAllWithWhere(new Ulica(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ulice;
    }
     
     public List<Grad> searchGradovi(String whereClause) throws Exception {
        List<Grad> gradovi = new LinkedList<Grad>();
        try {
            db.connect();
            gradovi = (List<Grad>) (Object) db.getAllWithWhere(new Grad(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return gradovi;
    }
     
      public List<KatastarskaOpstina> loadSveOpstine() throws Exception {
        List<KatastarskaOpstina> opstine = new LinkedList<KatastarskaOpstina>();
        try {
            db.connect();
            opstine = (List<KatastarskaOpstina>) (Object) db.getAll(new KatastarskaOpstina());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return opstine;
    }
    
    public List<KatastarskaOpstina> searchOpstineByPostanskiBr(String postanski_br) throws Exception {
        List<KatastarskaOpstina> opstine = new LinkedList<KatastarskaOpstina>();
        try {
            db.connect();
            opstine = (List<KatastarskaOpstina>) (Object) db.GetByPostanskiBr(postanski_br);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return opstine;
    }
    
    public List<KatastarskaOpstina> searchOpstine(String whereClause) throws Exception {
        List<KatastarskaOpstina> opstine = new LinkedList<KatastarskaOpstina>();
        try {
            db.connect();
            opstine = (List<KatastarskaOpstina>) (Object) db.getAllWithWhere(new KatastarskaOpstina(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return opstine;
    }
    
    public void updateGrad(Grad g) throws Exception {
        try {
            db.connect();
            int answer = JOptionPane.showConfirmDialog(null, "Zelite li da izmenite grad?", "Izmena", JOptionPane.YES_NO_OPTION);
            if (answer == 1) {
                return;
            }
            db.update(g);
            JOptionPane.showMessageDialog(null, "Uspesno ste izmenili grad");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public void insertUlica(Ulica u) throws Exception {
        try {
            db.connect();
            db.insert(u);
            JOptionPane.showMessageDialog(null, "Uspesno ste uneli");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void deleteUlica(Ulica u) throws Exception {
        try {
        db.connect();
        int answer = JOptionPane.showConfirmDialog(null, "Zaista želite da obrišete ulicu?", "Brisanje", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.NO_OPTION) {
            return;
        }

        try {
            db.delete(u);
            JOptionPane.showMessageDialog(null, "Ulica uspešno obrisana.", "Brisanje", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "Ne možete obrisati ovu ulicu jer postoje povezani zapisi.", "Greška", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Došlo je do greške pri brisanju ulice.", "Greška", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            db.disconnect();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    
   public void updateUlica(Ulica u, String setClause) throws Exception {
    try {
        db.connect();
        int answer = JOptionPane.showConfirmDialog(null, "Želite li da izmenite ulicu?", "Izmena", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            db.updatePartial(u, setClause);
            JOptionPane.showMessageDialog(null, "Uspešno ste izmenili ulicu.");
        }
    } catch (SQLIntegrityConstraintViolationException ex) {
        JOptionPane.showMessageDialog(null, "Greška: Opština ne postoji u bazi!", "Greška", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "SQL Greška: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Došlo je do greške: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            db.disconnect();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

    
     public List<Prikljucak> loadSvePrikljucke() throws Exception {
        List<Prikljucak> prikljucak = new LinkedList<>();
        try {
            db.connect();
            prikljucak = (List<Prikljucak>) (Object) db.getAll(new Prikljucak());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return prikljucak;
    }
     
      public Prikljucak searchPrikljucak(String whereClause) throws Exception {
    Prikljucak prikljucak = null;
    try {
        db.connect();
        Object result = db.getWithWhere(new Prikljucak(), whereClause);
        if (result != null) {
            prikljucak = (Prikljucak) result;
        }
    } catch (Exception ex) {
        throw ex;
    } finally {
        try {
            db.disconnect();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return prikljucak;
}

      
      public List<Prikljucak> searchPrikljucci(String whereClause) throws Exception {
        List<Prikljucak> prikljucak = new LinkedList<>();        
        try {
            db.connect();
            prikljucak = (List<Prikljucak>) (Object) db.getAllWithWhere(new Prikljucak(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return prikljucak;
    }
      
       public List<Resenje> searchResenja(String whereClause) throws Exception {
        List<Resenje> resenja = new LinkedList<>();        
        try {
            db.connect();
            resenja = (List<Resenje>) (Object) db.getAllWithWhere(new Resenje(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resenja;
    }
       
       public List<Direktor> loadSveDirektore() throws Exception {
        List<Direktor> direktor = new LinkedList<>();
        try {
            db.connect();
            direktor = (List<Direktor>) (Object) db.getAll(new Direktor());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return direktor;
    }
       
        public List<Zahtev> loadSveZahteve() throws Exception {
        List<Zahtev> zahtev = new LinkedList<>();
        try {
            db.connect();
            zahtev = (List<Zahtev>) (Object) db.getAll(new Zahtev());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return zahtev;
    }

        public List<UsloviPostavljanja> loadSveUsloveP() throws Exception {
        List<UsloviPostavljanja> up = new LinkedList<>();
        try {
            db.connect();
            up = (List<UsloviPostavljanja>) (Object) db.getAll(new UsloviPostavljanja());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return up;
    }
        
         public List<UsloviZastite> loadSveUsloveZ() throws Exception {
        List<UsloviZastite> uz = new LinkedList<>();
        try {
            db.connect();
            uz = (List<UsloviZastite>) (Object) db.getAll(new UsloviZastite());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return uz;
    }
         
         public void insertPrikljucak(Prikljucak p) throws Exception {
        try {
            db.connect();
            db.insert(p);
            JOptionPane.showMessageDialog(null, "Uspesno ste uneli");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
         
          public void updatePrikljucak(Prikljucak p) throws Exception {
        try {
            db.connect();
            int answer = JOptionPane.showConfirmDialog(null, "Zelite li da izmenite prikljucak?", "Izmena", JOptionPane.YES_NO_OPTION);
            if (answer == 1) {
                return;
            }
            db.update(p);
            JOptionPane.showMessageDialog(null, "Uspesno ste izmenili");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

        public List<Direktor> searchDirektori(String whereClause) throws Exception {
        List<Direktor> direktori = new LinkedList<>();        
        try {
            db.connect();
            direktori = (List<Direktor>) (Object) db.getAllWithWhere(new Direktor(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return direktori;
    }
         public List<UsloviPostavljanja> searchUsloviP(String whereClause) throws Exception {
        List<UsloviPostavljanja> uslovp = new LinkedList<>();        
        try {
            db.connect();
            uslovp = (List<UsloviPostavljanja>) (Object) db.getAllWithWhere(new UsloviPostavljanja(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return uslovp;
    }
           public List<UsloviZastite> searchUsloviZ(String whereClause) throws Exception {
        List<UsloviZastite> uslovz = new LinkedList<>();        
        try {
            db.connect();
            uslovz = (List<UsloviZastite>) (Object) db.getAllWithWhere(new UsloviZastite(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return uslovz;
    }
        public List<Zahtev> searchZahteve(String whereClause) throws Exception {
        List<Zahtev> zahtev = new LinkedList<>();        
        try {
            db.connect();
            zahtev = (List<Zahtev>) (Object) db.getAllWithWhere(new Zahtev(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return zahtev;
    }
        public List<Prikljucak> searchPrikljucke(String whereClause) throws Exception {
        List<Prikljucak> prikljucak = new LinkedList<>();        
        try {
            db.connect();
            prikljucak = (List<Prikljucak>) (Object) db.getAllWithWhere(new Prikljucak(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return prikljucak;
    }
      public void deleteResenje(Resenje r) throws Exception {
    try {
        db.connect();
        int answer = JOptionPane.showConfirmDialog(null, "Zaista želite da obrišete rešenje?", "Brisanje", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.NO_OPTION) {
            return;
        }

        try {
            db.delete(r);
            JOptionPane.showMessageDialog(null, "Rešenje uspešno obrisano.", "Brisanje", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "Ne možete obrisati ovo rešenje jer postoje povezani zapisi.", "Greška", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Došlo je do greške pri brisanju rešenja.", "Greška", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            db.disconnect();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
      
       public void insertResenje(Resenje r) throws Exception {
        try {
            db.connect();
            db.insert(r);
            JOptionPane.showMessageDialog(null, "Uspesno ste uneli");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
       
          public void updateResenje(Resenje r) throws Exception {
        try {
            db.connect();
            int answer = JOptionPane.showConfirmDialog(null, "Zelite li da izmenite resenje?", "Izmena", JOptionPane.YES_NO_OPTION);
            if (answer == 1) {
                return;
            }
            db.update(r);
            JOptionPane.showMessageDialog(null, "Uspesno ste izmenili");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
          
        public List<ZaposleniPogled> loadSveZaposlene() throws Exception {
        List<ZaposleniPogled> zaposleni = new LinkedList<ZaposleniPogled>();
        try {
            db.connect();
            zaposleni = (List<ZaposleniPogled>) (Object) db.getAll(new ZaposleniPogled());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return zaposleni;
    }
        
        public List<Objekat> loadSveObjekte() throws Exception {
        List<Objekat> objekti = new LinkedList<Objekat>();
        try {
            db.connect();
            objekti = (List<Objekat>) (Object) db.getAll(new Objekat());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return objekti;
    }
        
        public List<ZaposleniPogled> searchZaposleni(String whereClause) throws Exception {
        List<ZaposleniPogled> zaposleni = new LinkedList<>();        
        try {
            db.connect();
            zaposleni = (List<ZaposleniPogled>) (Object) db.getAllWithWhere(new ZaposleniPogled(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return zaposleni;
    }
        
        public List<Objekat> searchObjekti(String whereClause) throws Exception {
        List<Objekat> objekti = new LinkedList<>();        
        try {
            db.connect();
            objekti = (List<Objekat>) (Object) db.getAllWithWhere(new Objekat(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return objekti;
    }

        public List<Zahtev> getParticijeZahtevi(String string) throws Exception {
        List<Zahtev> z = new LinkedList<Zahtev>();
        try {
            db.connect();
            z = (List<Zahtev>) (Object) db.getPartition(new Zahtev(), string);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return z;
    }
        
         public void insertZahtev(Zahtev z) throws Exception {
        try {
            db.connect();
            db.insert(z);
            JOptionPane.showMessageDialog(null, "Uspesno ste uneli");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
          public void updateZahtev(Zahtev z) throws Exception {
        try {
            db.connect();
            int answer = JOptionPane.showConfirmDialog(null, "Zelite li da izmenite zahtev?", "Izmena", JOptionPane.YES_NO_OPTION);
            if (answer == 1) {
                return;
            }
            db.update(z);
            JOptionPane.showMessageDialog(null, "Uspesno ste izmenili");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
          
        public List<Filijala> loadSveFilijale() throws Exception {
        List<Filijala> filijale = new LinkedList<Filijala>();
        try {
            db.connect();
            filijale = (List<Filijala>) (Object) db.getAll(new Filijala());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return filijale;
    }
        
        
         public List<Filijala> searchFilijale(String whereClause) throws Exception {
        List<Filijala> filijale = new LinkedList<>();        
        try {
            db.connect();
            filijale = (List<Filijala>) (Object) db.getAllWithWhere(new Filijala(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return filijale;
    }
        
         public List<ZaposleniOsnovno> loadSveZaposleneOSnovno() throws Exception {
        List<ZaposleniOsnovno> zaposleni = new LinkedList<ZaposleniOsnovno>();
        try {
            db.connect();
            zaposleni = (List<ZaposleniOsnovno>) (Object) db.getAll(new ZaposleniOsnovno());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return zaposleni;
    }

       public void insertZaposleniPogled(ZaposleniPogled zp) throws Exception {
        try {
            db.connect();
            db.insert(zp);
            JOptionPane.showMessageDialog(null, "Uspesno ste uneli");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
       
       public void deleteZaposleniPogled(ZaposleniPogled zp) throws Exception {
        try {
            db.connect();
            int answer = JOptionPane.showConfirmDialog(null, "Zaista zelite da obrisete zaposlenog?", "Brisanje", JOptionPane.YES_NO_OPTION);
            if (answer == 1) {
                return;
            }
            db.delete(zp);

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
       
        public void updateZaposleni(ZaposleniPogled zp) throws Exception {
        try {
            db.connect();
            int answer = JOptionPane.showConfirmDialog(null, "Zelite li da izmenite zaposlenog?", "Izmena", JOptionPane.YES_NO_OPTION);
            if (answer == 1) {
                return;
            }
            db.update(zp);
            JOptionPane.showMessageDialog(null, "Uspesno ste izmenili");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<NacinGrejanja> loadSvaGrejanja() throws Exception {
        List<NacinGrejanja> grejanje = new LinkedList<>();
        try {
            db.connect();
            grejanje = (List<NacinGrejanja>) (Object) db.getAll(new NacinGrejanja());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return grejanje;
    }
    

    public List<VrstaPrikljucka> loadSveVrstePrikljucaka() throws Exception{
    List<VrstaPrikljucka> vrstaprikljucka = new LinkedList<>();
        try {
            db.connect();
            vrstaprikljucka = (List<VrstaPrikljucka>) (Object) db.getAll(new VrstaPrikljucka());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return vrstaprikljucka;    }

    public List<TipInstalacije> loadSveInstalacije() throws Exception {
        List<TipInstalacije> instalacija = new LinkedList<>();
        try {
            db.connect();
            instalacija = (List<TipInstalacije>) (Object) db.getAll(new TipInstalacije());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instalacija;   
    }

    public List<NamenaObjekta> loadSveNameneObjekta() throws Exception {
        List<NamenaObjekta> namene = new LinkedList<>();
        try {
            db.connect();
            namene = (List<NamenaObjekta>) (Object) db.getAll(new NamenaObjekta());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return namene;   
    }


     public List<MolbaZaUrbanizam> searchMolbe(String whereClause) throws Exception {
        List<MolbaZaUrbanizam> molbe = new LinkedList<>();        
        try {
            db.connect();
            molbe = (List<MolbaZaUrbanizam>) (Object) db.getAllWithWhere(new MolbaZaUrbanizam(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return molbe;
    }
     
         public List<NamenaObjekta> searchNamenaObjekta(String whereClause) throws Exception {
        List<NamenaObjekta> namene = new LinkedList<>();        
        try {
            db.connect();
            namene = (List<NamenaObjekta>) (Object) db.getAllWithWhere(new NamenaObjekta(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return namene;
    }
         
          public List<NacinGrejanja> searchGrejanja(String whereClause) throws Exception {
        List<NacinGrejanja> grejanje = new LinkedList<>();        
        try {
            db.connect();
            grejanje = (List<NacinGrejanja>) (Object) db.getAllWithWhere(new NacinGrejanja(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return grejanje;
    }
          
        public List<TipInstalacije> searchInstalacije(String whereClause) throws Exception {
        List<TipInstalacije> instalacije = new LinkedList<>();        
        try {
            db.connect();
            instalacije = (List<TipInstalacije>) (Object) db.getAllWithWhere(new TipInstalacije(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instalacije;
    }
              
        public List<VrstaPrikljucka> searchVrstaPrikljucka(String whereClause) throws Exception {
        List<VrstaPrikljucka> vrstaprikljucka = new LinkedList<>();        
        try {
            db.connect();
            vrstaprikljucka = (List<VrstaPrikljucka>) (Object) db.getAllWithWhere(new VrstaPrikljucka(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return vrstaprikljucka;
    }
        
         public List<Snaga> loadSveSnage() throws Exception {
        List<Snaga> snage = new LinkedList<Snaga>();
        try {
            db.connect();
            snage = (List<Snaga>) (Object) db.getAll(new Snaga());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return snage;
    }
         
         public List<Snaga> searchSnaga(String whereClause) throws Exception {
        List<Snaga> snaga = new LinkedList<Snaga>();
        try {
            db.connect();
            snaga = (List<Snaga>) (Object) db.getAllWithWhere(new Snaga(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return snaga;
    }
         
             public void deleteSnaga(Snaga s) throws Exception {
    try {
        db.connect();
        int answer = JOptionPane.showConfirmDialog(null, "Zaista želite da obrišete snagu?", "Brisanje", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.NO_OPTION) {
            return;
        }

        try {
            db.delete(s);
            JOptionPane.showMessageDialog(null, "Snaga uspešno obrisana.", "Brisanje", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "Ne možete obrisati ovu snagu jer postoje povezani zapisi.", "Greška", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Došlo je do greške pri brisanju snage.", "Greška", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            db.disconnect();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
             
             public void insertSnaga(Snaga s) throws Exception {
        try {
            db.connect();
            db.insert(s);
            JOptionPane.showMessageDialog(null, "Uspesno ste uneli");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
             
              public void updateSnaga(Snaga s) throws Exception {
        try {
            db.connect();
            int answer = JOptionPane.showConfirmDialog(null, "Zelite li da izmenite snagu?", "Izmena", JOptionPane.YES_NO_OPTION);
            if (answer == 1) {
                return;
            }
            db.update(s);
            JOptionPane.showMessageDialog(null, "Uspesno ste izmenili");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
              
    public void updateObjekat(Objekat o, String setClause) throws Exception {
    try {
        db.connect();
        int answer = JOptionPane.showConfirmDialog(null, "Želite li da izmenite objekat?", "Izmena", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            db.updatePartial(o, setClause);
            JOptionPane.showMessageDialog(null, "Uspešno ste izmenili objekat.");
        }
    } catch (SQLIntegrityConstraintViolationException ex) {
        JOptionPane.showMessageDialog(null, "Greška: Vrsta objekta moze biti - 'Bazna stanica mobilne telefonije', 'Industrijski objekat', 'Poslovni prostor' ili 'Stambeni objekat !", "Greška", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "SQL Greška: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Došlo je do greške: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            db.disconnect();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
}
    
      public List<NacinGrejanja> searchNacinGrejanja(String whereClause) throws Exception {
        List<NacinGrejanja> grejanje = new LinkedList<NacinGrejanja>();
        try {
            db.connect();
            grejanje = (List<NacinGrejanja>) (Object) db.getAllWithWhere(new NacinGrejanja(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return grejanje;
    }
      
       public List<TipInstalacije> searchInstalacija(String whereClause) throws Exception {
        List<TipInstalacije> instalacija = new LinkedList<TipInstalacije>();
        try {
            db.connect();
            instalacija = (List<TipInstalacije>) (Object) db.getAllWithWhere(new TipInstalacije(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instalacija;
    }
       
             public void insertObjekat(Objekat o) throws Exception {
        try {
            db.connect();
            db.insert(o);
            JOptionPane.showMessageDialog(null, "Uspesno ste uneli");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
             
        public void updateObjekat(Objekat o) throws Exception {
        try {
            db.connect();
            int answer = JOptionPane.showConfirmDialog(null, "Zelite li da izmenite objekat?", "Izmena", JOptionPane.YES_NO_OPTION);
            if (answer == 1) {
                return;
            }
            db.update(o);
            JOptionPane.showMessageDialog(null, "Uspesno ste izmenili");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        public void deleteObjekat(Objekat o) throws Exception {
    try {
        db.connect();
        int answer = JOptionPane.showConfirmDialog(null, "Zaista želite da obrišete objekat?", "Brisanje", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.NO_OPTION) {
            return;
        }

        try {
            db.delete(o);
            JOptionPane.showMessageDialog(null, "Objekat uspešno obrisan.", "Brisanje", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "Ne možete obrisati ovaj objekat jer postoje povezani zapisi.", "Greška", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Došlo je do greške pri brisanju objekta.", "Greška", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            db.disconnect();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
}
