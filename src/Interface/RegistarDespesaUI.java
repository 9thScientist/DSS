/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;
import Despesas.Categoria;
import Moradores.*;
import Main.SplitExpense;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zesilva63
 */
public class RegistarDespesaUI extends javax.swing.JFrame {

    private String[] categorias;
    private String[] moradores;
    private Morador moradorSelec;
    private DefaultTableModel model;
    /**
     * Creates new form RegistarDespesaUI
     */
    public RegistarDespesaUI() {
        fillCategorias();
        fillMoradores();
        tableFiller();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        Title = new java.awt.Label();
        categoriasBox = new javax.swing.JComboBox<>(categorias);
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Nr_PrestacoesSpinner = new javax.swing.JSpinner();
        freqSpinner = new javax.swing.JSpinner();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        descricaoTextField = new javax.swing.JTextField();
        MoradorBox = new javax.swing.JComboBox<>(moradores);
        valorSpinner = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("Registar Despesa");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("<");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Title.setAlignment(java.awt.Label.CENTER);
        Title.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Title.setFont(new java.awt.Font("Gargi-1.2b", 1, 24)); // NOI18N
        Title.setText("SplitExpense");

        categoriasBox.setSelectedItem(null);
        categoriasBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriasBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Valor da Despesa");

        jLabel2.setText("Número de Prestações");

        Nr_PrestacoesSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));
        Nr_PrestacoesSpinner.setToolTipText("");

        freqSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));

        jTable2.setModel(model);
        jScrollPane2.setViewportView(jTable2);

        jButton3.setText("Registar Despesas");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setText("Categoria");

        jLabel3.setText("Descrição");

        descricaoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descricaoTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addGap(79, 79, 79)
                        .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(MoradorBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel4)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(Nr_PrestacoesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(freqSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(categoriasBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(valorSpinner)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(descricaoTextField)))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoriasBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(valorSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Nr_PrestacoesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(freqSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(descricaoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(MoradorBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        new DespesasUI().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void categoriasBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriasBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoriasBoxActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        SplitExpense se = new SplitExpense();
        Map<Morador, Float> racios = readTable();
        Categoria categoria = se.getCategoria(categoriasBox.getSelectedItem().toString());
        Morador morador = se.getApartamento().getMoradorNome(MoradorBox.getSelectedItem().toString());
        java.sql.Date data = new Date(Calendar.getInstance().getTimeInMillis());

        try {
            se.registarDespesa(false, descricaoTextField.getText(), categoria, Float.parseFloat(valorSpinner.getText()), data, racios, morador);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "O valor deve ser válido.\nEx.: 25.9 Corresponde a 25,09€",  "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        this.setVisible(false);
        new DespesasUI().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void descricaoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descricaoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descricaoTextFieldActionPerformed

    private void fillCategorias() {
        SplitExpense se = new SplitExpense();
        Set<String> cats = se.getCategorias();
        categorias = cats.toArray(new String[cats.size()]);
    }
    
    private void tableFiller() {
        SplitExpense se = new SplitExpense();
        String cols[] = {"Morador", "Rácio"};
        model = new DefaultTableModel(cols, 0) {
        
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };
        
        Set<Morador> ms = new HashSet(se.getApartamento().getMoradores().values());
        float racio = 1 / (float) ms.size();
        System.out.println("1 / " + ms.size() + " = " + racio);
        for (Morador m : ms) {
            Object[] ln = {m.getNome(), racio};
            model.addRow(ln);
        }
    }
    
    private Map<Morador, Float> readTable() {
        Map<Morador, Float> ret = new HashMap<>();
        SplitExpense se = new SplitExpense();
        
        try {
           for (int i = 0; i < model.getRowCount(); i++){
               float racio = Float.parseFloat(model.getValueAt(i, 1).toString());
               Morador m = se.getApartamento().getMoradorNome(model.getValueAt(i, 0).toString());
               ret.put(m, racio);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Os rácios não têm valores válidos.",  "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        return ret;
    }
    
    private void fillMoradores() {
        SplitExpense se = new SplitExpense();
        Set<Morador> ms = new HashSet<>(se.getApartamento().getMoradores().values());
        List<String> tmp = new ArrayList<>();
        
        for (Morador m : ms)
            if (m.ativo())
                tmp.add(m.getNome());
        moradores = new String[tmp.size()];
        moradores = tmp.toArray(moradores);
        
        if (moradores.length > 0)
            moradorSelec = se.getApartamento().getMoradorNome(moradores[0]);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> MoradorBox;
    private javax.swing.JSpinner Nr_PrestacoesSpinner;
    private java.awt.Label Title;
    private javax.swing.JComboBox<String> categoriasBox;
    private javax.swing.JTextField descricaoTextField;
    private javax.swing.JSpinner freqSpinner;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField valorSpinner;
    // End of variables declaration//GEN-END:variables
}
