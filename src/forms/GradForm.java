/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;

import Domain.Object.entities.Grad;
import Domain.Object.entities.KatastarskaOpstina;
import Domain.Object.entities.Ulica;
import controller.Controller;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author korisnik
 */
public class GradForm extends javax.swing.JFrame {

    /**
     * Creates new form GradForm
     */
    
    List<Grad> gradovi = new LinkedList<Grad>();
    List<Grad> pronadjenGrad = new LinkedList<>();
    List<Ulica> ulice = new LinkedList<>();
    List<Ulica> pronadjeneUlice = new LinkedList<>();
    private HashMap<Integer, String[]> originalneVrednostiUlica = new HashMap<>();
    List<KatastarskaOpstina> katastarskeOpstine = new LinkedList<>();
    List<KatastarskaOpstina> pronadjeneKatastarskeOpstine= new LinkedList<>();
    
    public GradForm() throws Exception {
        initComponents();
        setTitle("Grad");
        setLocationRelativeTo(null);
        setUpTableListenerGrad();
        ucitajPodatkeUFormu();
        
    }

    private void ucitajPodatkeUFormu() throws Exception {
        ucitajGradove();
        cmbGradovi.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        try {
            ucitajGradoveCmb(evt);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(GradForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
});
        
    }
    private void ucitajGradove() throws Exception {
    DefaultTableModel model = (DefaultTableModel) tblGrad.getModel();
    gradovi = controller.Controller.getInstance().loadSveGradove();

    for (Grad zu : gradovi) {
        model.addRow(new Object[]{zu.getPostanski_br(), zu.getNaziv()});
     }
    
    cmbGradovi.removeAllItems(); 
    for (Grad g : gradovi) {
        cmbGradovi.addItem(g.getNaziv()); 
    }
    
    }
    
    private void ucitajGradoveCmb(java.awt.event.ActionEvent evt) throws Exception {
    
     String izabraniGrad = (String) cmbGradovi.getSelectedItem();
    if (izabraniGrad != null) {
        Grad grad= gradovi.stream()
                .filter(g -> g.getNaziv().equals(izabraniGrad))
                .findFirst()
                .orElse(null);
        
        if (izabraniGrad != null) {
            try {
                ucitajKatastarskeOpstine(grad.getPostanski_br());
            } catch (Exception ex) {
                // Handle exceptions (e.g., log)
                ex.printStackTrace();
            }
        }
    }}
    
    
 private void ucitajKatastarskeOpstine(int postanskiBr) throws Exception {
    List<KatastarskaOpstina> katastarskeOpstine = Controller.getInstance().searchOpstineByPostanskiBr(String.valueOf(postanskiBr));

    if (cmbOpstina != null) {
        cmbOpstina.removeAllItems();  // Ukloni sve prethodne stavke u ComboBox-u
        for (KatastarskaOpstina o : katastarskeOpstine) {
            cmbOpstina.addItem(o.getNaziv());  
        }
    } else {
        System.err.println("ComboBox (cmbOpstina) je null.");
    }
}



    
    
    private void sacuvajOriginalneVrednosti(JTable table) {
        DefaultTableModel model = (DefaultTableModel) tblUlica.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            int postanski_broj = (Integer) model.getValueAt(i, 0);
            int id_ulice = (Integer) model.getValueAt(i, 1);
            String naziv = (String) model.getValueAt(i, 2);
            int id_opstine = (Integer) model.getValueAt(i, 3);
            String naziv_grada = (String) model.getValueAt(i, 4);

            originalneVrednostiUlica.put(i, new String[]{String.valueOf(postanski_broj), String.valueOf(id_ulice), naziv, String.valueOf(id_opstine),naziv_grada});
        }
    }
 
     private void ucitajUlice() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblUlica.getModel();

        ulice = controller.Controller.getInstance().loadSveUlice();

        for (Ulica u : ulice) {
            model.addRow(new Object[]{u.getPostanski_br(), u.getId_ulice(), u.getNaziv(), u.getNaziv_grada(), u.getId_opstine()});
        }
    }
     
      private Ulica jeIzabranaUlica() {
        int postanski_br = 0;
        int id_ulice = 0;
        String naziv = null;
        int id_opstine = 0;
        String naziv_grada = null;

        int izabranaUlicaIndex = tblUlica.getSelectedRow();
        if (izabranaUlicaIndex >= 0) {
            DefaultTableModel model = (DefaultTableModel) tblUlica.getModel();
            postanski_br = (Integer) model.getValueAt(izabranaUlicaIndex, 0);
            id_ulice = (Integer) model.getValueAt(izabranaUlicaIndex, 1);
            naziv = (String) model.getValueAt(izabranaUlicaIndex, 2);
            id_opstine = (Integer) model.getValueAt(izabranaUlicaIndex, 3);
            naziv_grada = (String) model.getValueAt(izabranaUlicaIndex, 4);
        }

        Ulica u = new Ulica();
        u.setPostanski_br(postanski_br);
        u.setId_ulice(id_ulice);
        u.setNaziv(naziv);
        u.setId_opstine(id_opstine);
        u.setNaziv_grada(naziv_grada);

        return u;
    }

    private void popuniTabeluUlicama(int postanski_br) throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblUlica.getModel();
        model.setRowCount(0);

        ulice = controller.Controller.getInstance().searchUlice("POSTANSKI_BR='" + String.valueOf(postanski_br) + "'");

        for (Ulica u : ulice) {
            model.addRow(new Object[]{u.getPostanski_br(), u.getId_ulice(), u.getNaziv(), u.getId_opstine(),u.getNaziv_grada()});
        }
    }
    
    private void setUpTableListenerGrad() {
    tblGrad.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        public void valueChanged(ListSelectionEvent event) {
            if (!event.getValueIsAdjusting()) {
                try {
                    Grad izabraniGrad = jeIzabranGrad();
                    pronadjenGrad = controller.Controller.getInstance().searchGradovi("POSTANSKI_BR='" + izabraniGrad.getPostanski_br()+ "'");

                    if (pronadjenGrad != null && !pronadjenGrad.isEmpty()) {
                        izabraniGrad = pronadjenGrad.get(0);
                    }

                    popuniTabeluUlicama(izabraniGrad.getPostanski_br());
                    originalneVrednostiUlica.clear();
                    sacuvajOriginalneVrednosti(tblUlica);
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(GradForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        }
    });
    
}
    
     private Grad jeIzabranGrad() {
        int postanski_broj = 0;
        String naziv = null;

        int izabranGradIndex = tblGrad.getSelectedRow();
        if (izabranGradIndex >= 0) {
            DefaultTableModel model = (DefaultTableModel) tblGrad.getModel();

            postanski_broj = (Integer) model.getValueAt(izabranGradIndex, 0);
            naziv = (String) model.getValueAt(izabranGradIndex, 1);
        }

        Grad g = new Grad();
        g.setPostanski_br(postanski_broj);
        g.setNaziv(naziv);

        return g;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        btnIzmeniGrad = new javax.swing.JButton();
        btnSacuvaj = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnIzmeni = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGrad = new javax.swing.JTable();
        btnObrisi = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUlica = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtOdeljenjeID = new javax.swing.JTextField();
        txtNaziv = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmbGradovi = new javax.swing.JComboBox<>();
        cmbOpstina = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("frmGrad"); // NOI18N

        jLabel4.setText("Grad");

        btnIzmeniGrad.setText("Izmeni");
        btnIzmeniGrad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniGradActionPerformed(evt);
            }
        });

        btnSacuvaj.setText("Sacuvaj");
        btnSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacuvajActionPerformed(evt);
            }
        });

        jLabel1.setText("Grad");

        btnIzmeni.setText("Izmeni");
        btnIzmeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniActionPerformed(evt);
            }
        });

        tblGrad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Postanski broj", "Naziv"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblGrad);

        btnObrisi.setText("Obrisi");
        btnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiActionPerformed(evt);
            }
        });

        tblUlica.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                " Postanski broj", "ID Ulice", "Naziv", "Opstina", "Grad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblUlica);

        jLabel2.setText("ID Ulice");

        jLabel3.setText("Naziv");

        jLabel5.setText("Opstina");

        cmbGradovi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbOpstina.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbOpstina.setSelectedIndex(-1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(327, 327, 327))
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnIzmeniGrad)
                            .addComponent(btnIzmeni)
                            .addComponent(btnObrisi)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGap(29, 29, 29))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtOdeljenjeID, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cmbGradovi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(140, 140, 140)
                                .addComponent(btnSacuvaj))
                            .addComponent(cmbOpstina, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(btnIzmeniGrad)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(btnSacuvaj))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtOdeljenjeID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(cmbGradovi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbOpstina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnObrisi)
                        .addGap(36, 36, 36)
                        .addComponent(btnIzmeni)
                        .addGap(101, 101, 101))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIzmeniGradActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniGradActionPerformed
        try {
            Grad g = jeIzabranGrad();

            controller.Controller.getInstance().updateGrad(g);

            popuniTabeluUlicama(g.getPostanski_br());
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(GradForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Došlo je do greške: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnIzmeniGradActionPerformed

    private void btnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacuvajActionPerformed
       
    }//GEN-LAST:event_btnSacuvajActionPerformed

    private void btnIzmeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniActionPerformed
       
    }//GEN-LAST:event_btnIzmeniActionPerformed

    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiActionPerformed
        
    }//GEN-LAST:event_btnObrisiActionPerformed

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
            java.util.logging.Logger.getLogger(GradForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GradForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GradForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GradForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new GradForm().setVisible(true);
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(GradForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIzmeni;
    private javax.swing.JButton btnIzmeniGrad;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnSacuvaj;
    private javax.swing.JComboBox<String> cmbGradovi;
    private javax.swing.JComboBox<String> cmbOpstina;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblGrad;
    private javax.swing.JTable tblUlica;
    private javax.swing.JTextField txtNaziv;
    private javax.swing.JTextField txtOdeljenjeID;
    // End of variables declaration//GEN-END:variables
}
