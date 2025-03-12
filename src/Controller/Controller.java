package controller;

import DataBase.DatabaseBroker;
import Domain.Object.entities.Direktor;
import Domain.Object.entities.Grad;
import Domain.Object.entities.KatastarskaOpstina;
import Domain.Object.entities.Prikljucak;
import Domain.Object.entities.Resenje;
import Domain.Object.entities.Ulica;
import Domain.Object.entities.UsloviPostavljanja;
import Domain.Object.entities.UsloviZastite;
import Domain.Object.entities.Zahtev;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
            int answer = JOptionPane.showConfirmDialog(null, "Zaista zelite da obrisete ulicu?", "Brisanje", JOptionPane.YES_NO_OPTION);
            if (answer == 1) {
                return;
            }
            db.delete(u);

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
    
    public void updateUlica(Ulica u, String setClause) throws Exception {
          try {
            db.connect();
            int answer = JOptionPane.showConfirmDialog(null, "Zelite li da izmenite nalog?", "Izmena", JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION) {
                db.updatePartial(u, setClause);
                JOptionPane.showMessageDialog(null, "Uspesno ste izmenili");
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
        Prikljucak prikljucak = new Prikljucak();
        try {
            db.connect();
            prikljucak = (Prikljucak) (Object) db.getWithWhere(new Prikljucak(), whereClause);
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


}
