/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;


import Domain.Object.entities.Direktor;
import Domain.Object.entities.Prikljucak;
import Domain.Object.entities.Resenje;
import Domain.Object.entities.UsloviPostavljanja;
import Domain.Object.entities.UsloviZastite;
import Domain.Object.entities.Zahtev;
import controller.Controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author korisnik
 */
public class PrikljucakForm extends javax.swing.JFrame {

    List<Prikljucak> prikljucci = new ArrayList<>();
    List<Prikljucak> pronadjenPrikljucak = new ArrayList<>();
    List<Resenje> resenja = new ArrayList<>();
    List<Resenje> pronadjenaResenja = new ArrayList<>();
    private final HashMap<Integer, String[]> originalneVrednostiResenje = new HashMap<>();
    List<Direktor> direktori = new ArrayList<>();
    List<Zahtev> zahtevi = new ArrayList<>();
    List<UsloviPostavljanja> usloviPostavljanja = new ArrayList<>();
    List<UsloviZastite> usloviZastite = new ArrayList<>();
    List<UsloviZastite> pronadjeniUslovZastite= new ArrayList<>();

    /**
     * Creates new form PrikljucakForm
     * @throws java.lang.Exception
     */
    public PrikljucakForm() throws Exception {
        initComponents();
        setTitle("Prikljucak");
        setLocationRelativeTo(null);
        ucitajPodatkeUFormu();
        setUpTableListenerPrikljucak();
        setUpTableListenerResenje();
        
    }

        private void ucitajPodatkeUFormu() throws Exception {
        ucitajPrikljucke();
        ucitajDirektore();
        ucitajUslovePostavljanja();
        ucitajUsloveZastite();
        ucitajZahteve();
            
    }
    private void ucitajPrikljucke() throws Exception {
    DefaultTableModel model = (DefaultTableModel) tblPrikljucak.getModel();
    model.setRowCount(0);  

    prikljucci = controller.Controller.getInstance().loadSvePrikljucke();

    for (Prikljucak p : prikljucci) {
        model.addRow(new Object[]{p.getId_prikljucak(), p.getNaziv(), p.getOpis()});
    }

    cmbPrikljucak.removeAllItems(); 
    for (Prikljucak p : prikljucci) {
        cmbPrikljucak.addItem(p.toString());  
    }
}

    
    private void ucitajDirektore() throws Exception {
    direktori = controller.Controller.getInstance().loadSveDirektore();

    cmbDirektor.removeAllItems(); 
    for (Direktor d : direktori) {
        cmbDirektor.addItem(d.toString()); 
    }
}
    
    private void ucitajZahteve() throws Exception {
    zahtevi = controller.Controller.getInstance().loadSveZahteve();

    cmbZahtev.removeAllItems(); 
    for (Zahtev z : zahtevi) {
        cmbZahtev.addItem(z.toString()); 
    }
}
     private void ucitajUslovePostavljanja() throws Exception {
    usloviPostavljanja = controller.Controller.getInstance().loadSveUsloveP();

    cmbUslovPostavljanja.removeAllItems(); 
    for (UsloviPostavljanja up : usloviPostavljanja) {
        cmbUslovPostavljanja.addItem(up.toString()); 
    }
}
    private void ucitajUsloveZastite() throws Exception {
    usloviZastite = controller.Controller.getInstance().loadSveUsloveZ();

    cmbUslovZastite.removeAllItems(); 
    for (UsloviZastite uz : usloviZastite) {
        cmbUslovZastite.addItem(uz.toString()); 
    }
}

    
      private void popuniTabeluResenjima(int id_prikljucka) throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblResenje.getModel();
        model.setRowCount(0);

        resenja = controller.Controller.getInstance().searchResenja("ID_PRIKLJUCAK  ='" + String.valueOf(id_prikljucka) + "'");

         for (Resenje r : resenja) {
        model.addRow(new Object[]{
            r.getId_resenja(), r.getDatum(), r.getBroj(),
            r.getId_direktora(), r.getBr_zahteva(),
            r.getId_uslovP(), r.getId_uslovZ(),
            r.getId_prikljucka(), r.getNaziv_prikljucka()
        });
    }}
    private void popuniFormuIzabranimPrikljuckom(Prikljucak p) throws Exception {
        if (p != null) {
            txtPrikljucakID.setText(String.valueOf(p.getId_prikljucak()));
            txtNaziv.setText(String.valueOf(p.getNaziv()));
            txtOpis.setText(String.valueOf(p.getOpis()));
            txtMerniUredjaj.setText(p.getMerni_uredjaj());
            txtMestoPrikljucenja.setText(p.getMesto_prikljucenja());
            txtMestoVezivanja.setText(p.getMesto_vezivanja());
            txtZastitniUredjaj.setText(String.valueOf(p.getZastitni_uredjaj()));
            }
}
        

    private Prikljucak jeIzabranPrikljucak() throws Exception {
    int id_prikljucka = 0;

    int izabranPrikljucakIndex = tblPrikljucak.getSelectedRow();
    if (izabranPrikljucakIndex >= 0) {
        DefaultTableModel model = (DefaultTableModel) tblPrikljucak.getModel();
        id_prikljucka = (Integer) model.getValueAt(izabranPrikljucakIndex, 0);
        System.out.println("Selected Prikljucak ID: " + id_prikljucka);
    }

    Prikljucak p = controller.Controller.getInstance().searchPrikljucak("ID_PRIKLJUCAK='" + id_prikljucka + "'");
    if (p != null) {
        System.out.println("Naziv Prikljucka: " + p.getId_prikljucak());
    }
    popuniFormuIzabranimPrikljuckom(p);
    return p;
}



      
     private Resenje jeIzabranoResenje() {
    int id_resenja = 0;
    Date datum = null;
    String broj = null;
    int id_direktora = 0;
    int br_zahteva = 0;
    int id_uslovP = 0;
    int id_uslovZ = 0;
    int id_prikljucka = 0;
    String naziv_prikljucka = null;

    int izabranoResenjeIndex = tblResenje.getSelectedRow();
    if (izabranoResenjeIndex >= 0) {
        DefaultTableModel model = (DefaultTableModel) tblResenje.getModel();
        id_resenja = (Integer) model.getValueAt(izabranoResenjeIndex, 0);
        

        java.sql.Date sqlDate = (java.sql.Date) model.getValueAt(izabranoResenjeIndex, 1);
        datum = sqlDate != null ? new Date(sqlDate.getTime()) : null; 

        broj = (String) model.getValueAt(izabranoResenjeIndex, 2);
        id_direktora = (Integer) model.getValueAt(izabranoResenjeIndex, 3);
        br_zahteva = (Integer) model.getValueAt(izabranoResenjeIndex, 4);
        id_uslovP = (Integer) model.getValueAt(izabranoResenjeIndex, 5);
        id_uslovZ = (Integer) model.getValueAt(izabranoResenjeIndex, 6);
        id_prikljucka = (Integer) model.getValueAt(izabranoResenjeIndex, 7);
        naziv_prikljucka = (String) model.getValueAt(izabranoResenjeIndex, 8);
    }

    return new Resenje(id_resenja, datum, broj, id_direktora, br_zahteva, id_uslovP, id_uslovZ, id_prikljucka, naziv_prikljucka);
}
     
   private void popuniFormuResenjem(Resenje r) throws Exception {
    txtIDResenja.setText(String.valueOf(r.getId_resenja()));
    txtBrResenja.setText(r.getBroj());

    if (r.getDatum() != null) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        txtDatum.setText(dateFormat.format(r.getDatum()));
    } else {

        txtDatum.setText("");
    }


    cmbDirektor.setSelectedItem(String.valueOf(r.getId_direktora()));
    try {
        ucitajDirektore();
    } catch (Exception ex) {
        java.util.logging.Logger.getLogger(GradForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }

    List<Direktor> direktor = Controller.getInstance().searchDirektori("ID_DIREKTORA='" + r.getId_direktora() + "'");
    if (!direktor.isEmpty()) {
        cmbDirektor.setSelectedItem(direktor.get(0).toString());
    }

    cmbUslovPostavljanja.setSelectedItem(String.valueOf(r.getId_uslovP()));
    try {
        ucitajUslovePostavljanja();
    } catch (Exception ex) {
        java.util.logging.Logger.getLogger(GradForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }

    List<UsloviPostavljanja> usloviP = Controller.getInstance().searchUsloviP("ID_USLOVP='" + r.getId_uslovP() + "'");
    if (!usloviP.isEmpty()) {
        cmbUslovPostavljanja.setSelectedItem(usloviP.get(0).toString());
    }

    cmbUslovZastite.setSelectedItem(String.valueOf(r.getId_uslovZ()));
    try {
        ucitajUsloveZastite();
    } catch (Exception ex) {
        java.util.logging.Logger.getLogger(GradForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }

    List<UsloviZastite> usloviZ = Controller.getInstance().searchUsloviZ("ID_USLOVZ='" + r.getId_uslovZ() + "'");
    if (!usloviZ.isEmpty()) {
        cmbUslovZastite.setSelectedItem(usloviZ.get(0).toString());
    }

    cmbZahtev.setSelectedItem(String.valueOf(r.getBr_zahteva()));
    try {
        ucitajZahteve();
    } catch (Exception ex) {
        java.util.logging.Logger.getLogger(GradForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }

    List<Zahtev> zahtev = Controller.getInstance().searchZahteve("BR_ZAHTEVA='" + r.getBr_zahteva() + "'");
    if (!zahtev.isEmpty()) {
        cmbZahtev.setSelectedItem(zahtev.get(0).toString());
    }

    try {
        ucitajPrikljucke();
    } catch (Exception ex) {
        java.util.logging.Logger.getLogger(GradForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }

    List<Prikljucak> prikljucak = Controller.getInstance().searchPrikljucke("ID_PRIKLJUCAK='" + r.getId_prikljucka() + "'");
    if (!prikljucak.isEmpty()) {
        cmbPrikljucak.setSelectedItem(prikljucak.get(0).toString());
    }
}




    private void setUpTableListenerPrikljucak() {
    tblPrikljucak.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
        if (!event.getValueIsAdjusting()) {
            try {
                Prikljucak izabraniPrikljucak = jeIzabranPrikljucak();
                
                if (izabraniPrikljucak == null) {
                    System.out.println("Izabrani priključak je null!");
                    return;
                }
                
                pronadjenPrikljucak = controller.Controller.getInstance()
                        .searchPrikljucci("ID_PRIKLJUCAK='" + izabraniPrikljucak.getId_prikljucak() + "'");
                
                if (pronadjenPrikljucak != null && !pronadjenPrikljucak.isEmpty()) {
                    izabraniPrikljucak = pronadjenPrikljucak.get(0);
                }
                
                popuniTabeluResenjima(izabraniPrikljucak.getId_prikljucak());
                originalneVrednostiResenje.clear();
                sacuvajOriginalneVrednosti(tblResenje);
                
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(GradForm.class.getName())
                        .log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    });
}

    

      
        private void setUpTableListenerResenje() {
        tblResenje.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            if (!event.getValueIsAdjusting()) {
                try {
                    Resenje izabranoResenje= jeIzabranoResenje();
                    pronadjenaResenja = Controller.getInstance().searchResenja("ID_RESENJA='" + String.valueOf(izabranoResenje.getId_resenja()) + "'");
                    
                    if (pronadjenaResenja != null && !pronadjenaResenja.isEmpty()) {
                        izabranoResenje = pronadjenaResenja.get(0);
                    }
                    popuniFormuResenjem(izabranoResenje);
                    
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(GradForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        });
    }
      
       private void sacuvajOriginalneVrednosti(JTable table) {
        DefaultTableModel model = (DefaultTableModel) tblResenje.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            int id_resenja = (Integer) model.getValueAt(i, 0);
            String datum = (String) model.getValueAt(i, 1).toString();
            String broj = (String) model.getValueAt(i, 2);
            int id_direktora = (Integer) model.getValueAt(i, 3);
            int br_zahteva = (Integer) model.getValueAt(i, 4);
            int id_uslovp = (Integer) model.getValueAt(i, 5);
            int id_uslovz = (Integer) model.getValueAt(i, 6);
            int id_prikljucka = (Integer) model.getValueAt(i, 7);
            String naziv_prikljucka = (String) model.getValueAt(i, 8);

            originalneVrednostiResenje.put(i, new String[]{String.valueOf(id_resenja), String.valueOf(datum), broj, String.valueOf(id_direktora),String.valueOf(br_zahteva),String.valueOf(id_uslovp),String.valueOf(id_uslovz),String.valueOf(id_prikljucka),naziv_prikljucka});
        }
    }
       
       private Prikljucak preuzmiPodatkeZaPrikljucak() throws Exception {
        int prikljucakID = Integer.parseInt(txtPrikljucakID.getText());
        String naziv = txtNaziv.getText();
        String opis = txtOpis.getText();
        String mestoPrikljucenja = txtMestoPrikljucenja.getText();
        String mestoVezivanja = txtMestoVezivanja.getText();
        String merniUredjaj = txtMerniUredjaj.getText();
        String zastitniUredjaj = txtZastitniUredjaj.getText();

        Prikljucak p = new Prikljucak(prikljucakID, naziv, opis, mestoPrikljucenja, mestoVezivanja, merniUredjaj, zastitniUredjaj);

        return p;
    }
   public Resenje preuzmiPodatkeZaResenje() throws Exception {
        int resenjeID;
        try {
            resenjeID = Integer.parseInt(txtIDResenja.getText().trim());
        } catch (NumberFormatException e) {
            throw new Exception("Neispravan ID rešenja. Unesite broj.");
        }

        String broj = txtBrResenja.getText().trim();
        String rawDatum = txtDatum.getText().trim();
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date datum = null;

        if (rawDatum == null || rawDatum.isEmpty()) {
            throw new Exception("Datum ne može biti prazan.");
        }

        try {
            datum = inputFormat.parse(rawDatum);
        } catch (ParseException e) {
            throw new Exception("Neispravan format datuma. Očekivani format je dd-MM-yyyy.");
        }

        if (cmbDirektor.getSelectedItem() == null || cmbZahtev.getSelectedItem() == null ||
            cmbUslovPostavljanja.getSelectedItem() == null || cmbUslovZastite.getSelectedItem() == null ||
            cmbPrikljucak.getSelectedItem() == null) {
            throw new Exception("Molimo vas da odaberete sve potrebne vrednosti iz padajućih menija.");
        }

 
        String imePrezime = (String) cmbDirektor.getSelectedItem();
        String[] delovi = imePrezime.trim().split("\\s+"); 

        if (delovi.length < 2) {
            throw new Exception("Neispravan format imena i prezimena direktora.");
        }

        String ime = delovi[0];  
        String prezime = delovi[delovi.length - 1];


        List<Direktor> pronadjeniDirektori = Controller.getInstance().searchDirektori("ime='" + ime + "' AND prezime='" + prezime + "'");

        if (pronadjeniDirektori.isEmpty()) {
            throw new Exception("Direktor sa imenom " + ime + " i prezimenom " + prezime + " nije pronađen.");
        }

        int direktorID = pronadjeniDirektori.get(0).getId_direktora();

 
        Object selectedItem = cmbZahtev.getSelectedItem();
        Date selectedDate = null;

        if (selectedItem instanceof String rawDate) {
            SimpleDateFormat inputFormatt = new SimpleDateFormat("yyyy-MM-dd");
            try {
                selectedDate = inputFormatt.parse(rawDate);
            } catch (ParseException e) {
                throw new Exception("Neispravan format datuma. Očekivani format je yyyy-MM-dd.");
            }
        } else if (selectedItem instanceof Date date) {
            selectedDate = date;
        } else {
            throw new Exception("Neispravan tip podataka u ComboBox-u za datum.");
        }

        if (selectedDate == null) {
            throw new Exception("Datum zahteva ne može biti null.");
        }

        SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dbFormat.format(selectedDate);
        List<Zahtev> pronadjeniZahtevi = Controller.getInstance().searchZahteve("DATUM=TO_DATE('" + formattedDate + "', 'YYYY-MM-DD')");

        if (pronadjeniZahtevi.isEmpty()) {
            throw new Exception("Zahtev nije pronađen.");
        }

        int br_zahteva = pronadjeniZahtevi.get(0).getBr_zahteva();

        String idUslovPStr = (String) cmbUslovPostavljanja.getSelectedItem();
        List<UsloviPostavljanja> pronadjeniUslovPostavljanja = Controller.getInstance().searchUsloviP("NAZIV='" + idUslovPStr + "'");

        if (pronadjeniUslovPostavljanja.isEmpty()) {
            throw new Exception("Uslov postavljanja nije pronađen.");
        }
        int id_uslovp = pronadjeniUslovPostavljanja.get(0).getId_uslovp();

        String idUslovZStr = (String) cmbUslovZastite.getSelectedItem();
        List<UsloviZastite> pronadjeniUslovZastite = Controller.getInstance().searchUsloviZ("NAZIV='" + idUslovZStr + "'");

        if (pronadjeniUslovZastite.isEmpty()) {
            throw new Exception("Uslov zaštite nije pronađen.");
        }
        int id_uslovz = pronadjeniUslovZastite.get(0).getId_uslovz();

   
        String idPrikljucakStr = (String) cmbPrikljucak.getSelectedItem();
        List<Prikljucak> pronadjenPrikljucak = Controller.getInstance().searchPrikljucci("NAZIV='" + idPrikljucakStr + "'");

        if (pronadjenPrikljucak.isEmpty()) {
            throw new Exception("Priključak nije pronađen.");
        }
        int id_prikljucak = pronadjenPrikljucak.get(0).getId_prikljucak();
        String nazivprikljucka = ""; 

        return new Resenje(resenjeID, datum, broj, direktorID, br_zahteva, id_uslovp, id_uslovz, id_prikljucak, nazivprikljucka);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPrikljucakID = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtBrResenja = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cmbZahtev = new javax.swing.JComboBox<>();
        btnSacuvajPrikljucak = new javax.swing.JButton();
        btnIzmeni = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPrikljucak = new javax.swing.JTable();
        txtIDResenja = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNaziv = new javax.swing.JTextField();
        txtOpis = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResenje = new javax.swing.JTable();
        btnIzmeniPrikljucak = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtMestoVezivanja = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtMerniUredjaj = new javax.swing.JTextField();
        txtMestoPrikljucenja = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtZastitniUredjaj = new javax.swing.JTextField();
        btnSacuvaj3 = new javax.swing.JButton();
        txtDatum = new javax.swing.JTextField();
        cmbDirektor = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cmbUslovPostavljanja = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        cmbUslovZastite = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        cmbPrikljucak = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Prikljucak");

        jLabel2.setText("ID prikljucka");

        jLabel3.setText("ID Resenja");

        jLabel4.setText("Datum izdavanja");

        jLabel10.setText("Broj resenja");

        jLabel12.setText("Broj zahteva");

        cmbZahtev.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSacuvajPrikljucak.setText("Sacuvaj");
        btnSacuvajPrikljucak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacuvajPrikljucakActionPerformed(evt);
            }
        });

        btnIzmeni.setText("Izmeni");
        btnIzmeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniActionPerformed(evt);
            }
        });

        btnObrisi.setText("Obrisi");
        btnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiActionPerformed(evt);
            }
        });

        tblPrikljucak.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Prikljucka", "Naziv", "Opis"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblPrikljucak);

        jLabel5.setText("Naziv");

        jLabel6.setText("Opis");

        txtOpis.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel8.setText("Resenje");

        tblResenje.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Resenja", "Datum", "Broj", "ID Direktora", "BR Zahteva", "ID Uslova Porstavljanja", "ID Uslova Zastite", "ID Prikljucak", "Naziv prikljucka"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblResenje.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblResenje);

        btnIzmeniPrikljucak.setText("Izmeni");
        btnIzmeniPrikljucak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniPrikljucakActionPerformed(evt);
            }
        });

        jLabel13.setText("Mesto vezivanja");

        jLabel14.setText("Zastitni uredjaj");

        txtMestoVezivanja.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel15.setText("Mesto prikljucenja");

        txtMerniUredjaj.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        txtMestoPrikljucenja.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel16.setText("Merni uredjaj");

        txtZastitniUredjaj.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        btnSacuvaj3.setText("Sacuvaj");
        btnSacuvaj3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacuvaj3ActionPerformed(evt);
            }
        });

        txtDatum.setText("dd-MM-yyyy");

        cmbDirektor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbDirektor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDirektorActionPerformed(evt);
            }
        });

        jLabel17.setText("Direktor");

        jLabel18.setText("Uslov postavljanja");

        cmbUslovPostavljanja.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel19.setText("Uslov zastite");

        cmbUslovZastite.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel20.setText("Prikljucak");

        cmbPrikljucak.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNaziv)
                                    .addComponent(txtPrikljucakID)
                                    .addComponent(txtOpis, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(49, 49, 49)
                                        .addComponent(jLabel8))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel13))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtMestoPrikljucenja, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                            .addComponent(txtMestoVezivanja))
                                        .addGap(33, 33, 33)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel14))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtMerniUredjaj, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                                            .addComponent(txtZastitniUredjaj))))))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnIzmeniPrikljucak)
                            .addComponent(btnSacuvajPrikljucak))
                        .addGap(41, 41, 41))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel10))
                                        .addGap(75, 75, 75)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cmbDirektor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtBrResenja, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                            .addComponent(txtIDResenja)
                                            .addComponent(txtDatum)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(308, 308, 308)
                                        .addComponent(jLabel1))
                                    .addComponent(jLabel17))
                                .addGap(90, 90, 90)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmbPrikljucak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmbZahtev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addGap(36, 36, 36)
                                        .addComponent(cmbUslovPostavljanja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmbUslovZastite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnIzmeni)
                                    .addComponent(btnObrisi)
                                    .addComponent(btnSacuvaj3))))
                        .addGap(0, 70, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtPrikljucakID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtOpis, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel15)
                                            .addComponent(txtMestoPrikljucenja, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel16))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(txtMerniUredjaj)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(15, 15, 15))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtMestoVezivanja, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtZastitniUredjaj, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14)))))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(btnIzmeniPrikljucak)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSacuvajPrikljucak)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(txtIDResenja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(cmbZahtev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtBrResenja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19))
                                .addGap(15, 15, 15))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel18)
                                        .addComponent(cmbUslovPostavljanja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(cmbUslovZastite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSacuvaj3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnIzmeni)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnObrisi)
                        .addGap(1, 1, 1)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(cmbDirektor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(cmbPrikljucak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSacuvajPrikljucakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacuvajPrikljucakActionPerformed
        try {
            Prikljucak prikljucak = preuzmiPodatkeZaPrikljucak();

            controller.Controller.getInstance().insertPrikljucak(prikljucak);
            ucitajPrikljucke();

        } catch (Exception ex) {
            Logger.getLogger(PrikljucakForm.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Došlo je do greške: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSacuvajPrikljucakActionPerformed

    private void btnIzmeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniActionPerformed
          try {

        Resenje r = preuzmiPodatkeZaResenje(); 

        Controller.getInstance().updateResenje(r);
        popuniTabeluResenjima(r.getId_prikljucka());

    } catch (Exception ex) {
        java.util.logging.Logger.getLogger(GradForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Došlo je do greške: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnIzmeniActionPerformed

    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiActionPerformed
       try {
            Resenje r = jeIzabranoResenje();

            Controller.getInstance().deleteResenje(r);
            popuniTabeluResenjima(r.getId_prikljucka());

        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(GradForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Došlo je do greške: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnObrisiActionPerformed

    private void btnIzmeniPrikljucakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniPrikljucakActionPerformed
        try {
            Prikljucak p = preuzmiPodatkeZaPrikljucak();

            controller.Controller.getInstance().updatePrikljucak(p);
            ucitajPrikljucke();
            popuniTabeluResenjima(p.getId_prikljucak());
        } catch (Exception ex) {
            Logger.getLogger(PrikljucakForm.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Došlo je do greške: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnIzmeniPrikljucakActionPerformed

    private void btnSacuvaj3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacuvaj3ActionPerformed
         try {
            Resenje resenje = preuzmiPodatkeZaResenje();

            controller.Controller.getInstance().insertResenje(resenje);
            popuniTabeluResenjima(resenje.getId_prikljucka());
            
        } catch (Exception ex) {
            Logger.getLogger(PrikljucakForm.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Došlo je do greške: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSacuvaj3ActionPerformed

    private void cmbDirektorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDirektorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDirektorActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrikljucakForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrikljucakForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrikljucakForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrikljucakForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new PrikljucakForm().setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(PrikljucakForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIzmeni;
    private javax.swing.JButton btnIzmeniPrikljucak;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnSacuvaj3;
    private javax.swing.JButton btnSacuvajPrikljucak;
    private javax.swing.JComboBox<String> cmbDirektor;
    private javax.swing.JComboBox<String> cmbPrikljucak;
    private javax.swing.JComboBox<String> cmbUslovPostavljanja;
    private javax.swing.JComboBox<String> cmbUslovZastite;
    private javax.swing.JComboBox<String> cmbZahtev;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblPrikljucak;
    private javax.swing.JTable tblResenje;
    private javax.swing.JTextField txtBrResenja;
    private javax.swing.JTextField txtDatum;
    private javax.swing.JTextField txtIDResenja;
    private javax.swing.JTextField txtMerniUredjaj;
    private javax.swing.JTextField txtMestoPrikljucenja;
    private javax.swing.JTextField txtMestoVezivanja;
    private javax.swing.JTextField txtNaziv;
    private javax.swing.JTextField txtOpis;
    private javax.swing.JTextField txtPrikljucakID;
    private javax.swing.JTextField txtZastitniUredjaj;
    // End of variables declaration//GEN-END:variables
}
