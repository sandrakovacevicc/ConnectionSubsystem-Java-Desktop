/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;


import Domain.Object.entities.Direktor;
import Domain.Object.entities.Grad;
import Domain.Object.entities.Prikljucak;
import Domain.Object.entities.Resenje;
import Domain.Object.entities.Ulica;
import Domain.Object.entities.UsloviPostavljanja;
import Domain.Object.entities.UsloviZastite;
import Domain.Object.entities.Zahtev;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author korisnik
 */
public class PrikljucakForm extends javax.swing.JFrame {

    List<Prikljucak> prikljucci = new LinkedList<Prikljucak>();
    List<Prikljucak> pronadjenPrikljucak = new LinkedList<>();
    List<Resenje> resenja = new LinkedList<>();
    List<Resenje> pronadjenaResenja = new LinkedList<>();
    private HashMap<Integer, String[]> originalneVrednostiResenje = new HashMap<>();
    List<Direktor> direktori = new LinkedList<>();
    List<Direktor> pronadjeniDirektori= new LinkedList<>();
    List<Zahtev> zahtevi = new LinkedList<>();
    List<Zahtev> pronadjeniZahtevi= new LinkedList<>();
    List<UsloviPostavljanja> usloviPostavljanja = new LinkedList<>();
    List<UsloviPostavljanja> pronadjeniUslovPostavljanja= new LinkedList<>();
    List<UsloviZastite> usloviZastite = new LinkedList<>();
    List<UsloviZastite> pronadjeniUslovZastite= new LinkedList<>();

    /**
     * Creates new form PrikljucakForm
     */
    public PrikljucakForm() throws Exception {
        initComponents();
        setTitle("Prikljucak");
        setLocationRelativeTo(null);
        ucitajPodatkeUFormu();
        setUpTableListenerPrikljucak();
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
    
      private Prikljucak jeIzabranPrikljucak() throws Exception {
        int id_prikljucka = 0;
        String naziv = null;
        String opis = null;

        int izabranPrikljucakIndex = tblPrikljucak.getSelectedRow();
        if (izabranPrikljucakIndex >= 0) {
            DefaultTableModel model = (DefaultTableModel) tblPrikljucak.getModel();

            id_prikljucka = (Integer) model.getValueAt(izabranPrikljucakIndex, 0);
            naziv = (String) model.getValueAt(izabranPrikljucakIndex, 1);
            opis = (String) model.getValueAt(izabranPrikljucakIndex, 2);
        }

        Prikljucak p = (Prikljucak) controller.Controller.getInstance().searchPrikljucak("ID_PRIKLJUCAK='" + id_prikljucka+ "'");
        return p;
    }
      private void setUpTableListenerPrikljucak() {
    tblPrikljucak.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        public void valueChanged(ListSelectionEvent event) {
            if (!event.getValueIsAdjusting()) {
                try {
                    Prikljucak izabraniPrikljucak = jeIzabranPrikljucak();
                    pronadjenPrikljucak = controller.Controller.getInstance().searchPrikljucci("ID_PRIKLJUCAK='" + izabraniPrikljucak.getId_prikljucak()+ "'");

                    if (pronadjenPrikljucak != null && !pronadjenPrikljucak.isEmpty()) {
                        izabraniPrikljucak = pronadjenPrikljucak.get(0);
                    }

                    popuniTabeluResenjima(izabraniPrikljucak.getId_prikljucak());
                    originalneVrednostiResenje.clear();
                    sacuvajOriginalneVrednosti(tblResenje);
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(GradForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
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
        txtDatumIzdavanja = new javax.swing.JTextField();
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

        jLabel15.setText("Mesto prikljucenja");

        jLabel16.setText("Merni uredjaj");

        btnSacuvaj3.setText("Sacuvaj");
        btnSacuvaj3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacuvaj3ActionPerformed(evt);
            }
        });

        txtDatumIzdavanja.setText("dd-MM-yyyy");

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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 7, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
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
                                            .addComponent(txtOpis, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(94, 94, 94)
                                                .addComponent(jLabel8))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(74, 74, 74)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel15)
                                                    .addComponent(jLabel13))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtMestoPrikljucenja, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                                    .addComponent(txtMestoVezivanja))
                                                .addGap(74, 74, 74)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel16)
                                                    .addComponent(jLabel14))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtZastitniUredjaj, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtMerniUredjaj, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(0, 193, Short.MAX_VALUE)))
                                .addGap(77, 77, 77))
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
                                            .addComponent(txtDatumIzdavanja)))
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
                                    .addComponent(btnSacuvaj3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnIzmeniPrikljucak)
                            .addComponent(btnSacuvajPrikljucak)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
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
                                    .addComponent(txtOpis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(txtMestoPrikljucenja, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16)
                                    .addComponent(txtMerniUredjaj, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(15, 15, 15))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtMestoVezivanja, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtZastitniUredjaj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14)))))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(btnIzmeniPrikljucak)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSacuvajPrikljucak)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
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
                                    .addComponent(txtDatumIzdavanja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
       
    }//GEN-LAST:event_btnSacuvajPrikljucakActionPerformed

    private void btnIzmeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniActionPerformed
        
    }//GEN-LAST:event_btnIzmeniActionPerformed

    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiActionPerformed
       
    }//GEN-LAST:event_btnObrisiActionPerformed

    private void btnIzmeniPrikljucakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniPrikljucakActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIzmeniPrikljucakActionPerformed

    private void btnSacuvaj3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacuvaj3ActionPerformed
        // TODO add your handling code here:
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PrikljucakForm().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(PrikljucakForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIzmeni;
    private javax.swing.JButton btnIzmeniPrikljucak;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnSacuvaj;
    private javax.swing.JButton btnSacuvaj1;
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
    private javax.swing.JTextField txtDatumIzdavanja;
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
