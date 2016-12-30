/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;
import Main.SplitExpense;
import Moradores.*;
import java.util.HashSet;
import java.util.Set;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author zesilva63
 */
public class MoradoresUI extends javax.swing.JFrame {

    private DefaultTableModel tb;
    /**
     * Creates new form MoradoresUI
     */
    public MoradoresUI() {
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

        label1 = new java.awt.Label();
        jPanel1 = new javax.swing.JPanel();
        Deposito = new javax.swing.JButton();
        Levantamento = new javax.swing.JButton();
        AddMorador = new javax.swing.JButton();
        EditMorador = new javax.swing.JButton();
        RemoveMorador = new javax.swing.JButton();
        Title = new java.awt.Label();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableMoradores = new javax.swing.JTable();
        BackButton = new javax.swing.JButton();

        label1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label1.setFont(new java.awt.Font("Gargi-1.2b", 1, 24)); // NOI18N
        label1.setText("SplitExpense");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Deposito.setText("Efetuar Depósito");
        Deposito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DepositoActionPerformed(evt);
            }
        });

        Levantamento.setText("Efetuar Levantamento");
        Levantamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LevantamentoActionPerformed(evt);
            }
        });

        AddMorador.setText("Adicionar Morador");
        AddMorador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddMoradorActionPerformed(evt);
            }
        });

        EditMorador.setText("Editar Morador");
        EditMorador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditMoradorActionPerformed(evt);
            }
        });

        RemoveMorador.setText("Remover Morador");
        RemoveMorador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveMoradorActionPerformed(evt);
            }
        });

        Title.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Title.setFont(new java.awt.Font("Gargi-1.2b", 1, 24)); // NOI18N
        Title.setText("SplitExpense");

        TableMoradores.setModel(tb);
        jScrollPane2.setViewportView(TableMoradores);

        BackButton.setText("<");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BackButton)
                        .addGap(190, 190, 190)
                        .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(219, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Levantamento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EditMorador, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RemoveMorador, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddMorador, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Deposito, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BackButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Deposito)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Levantamento)
                        .addGap(43, 43, 43)
                        .addComponent(AddMorador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EditMorador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RemoveMorador))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddMoradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddMoradorActionPerformed
        this.setVisible(false);
        new AdicionarMoradorUI().setVisible(true);
    }//GEN-LAST:event_AddMoradorActionPerformed

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        this.setVisible(false);
        new SplitExpenseUI().setVisible(true);
    }//GEN-LAST:event_BackButtonActionPerformed

    private void LevantamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LevantamentoActionPerformed
        this.setVisible(false);
        new LevantamentoUI().setVisible(true);
    }//GEN-LAST:event_LevantamentoActionPerformed

    private void DepositoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DepositoActionPerformed
        this.setVisible(false);
        new DepositoUI().setVisible(true);
    }//GEN-LAST:event_DepositoActionPerformed

    private void RemoveMoradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveMoradorActionPerformed
        this.setVisible(false);
        new RemoverMoradorUI().setVisible(true);
    }//GEN-LAST:event_RemoveMoradorActionPerformed

    private void EditMoradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditMoradorActionPerformed
        this.setVisible(false);
        new EditarMoradorUI().setVisible(true);
    }//GEN-LAST:event_EditMoradorActionPerformed

    private void tableFiller() {
        SplitExpense se = new SplitExpense();
        String cols[] = {"Nome", "Contacto", "Saldo"};
        tb = new DefaultTableModel(cols, 0) {
        
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
            return false;
            }
        };
        
        Set<Morador> ms = new HashSet(se.getApartamento().getMoradores().values());
        
        for (Morador m : ms) {
            String nome = m.getNome();
            String contacto = m.getContacto();
            float saldo = m.getSaldo();
            Object[] ln = {nome, contacto, saldo};
            tb.addRow(ln);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddMorador;
    private javax.swing.JButton BackButton;
    private javax.swing.JButton Deposito;
    private javax.swing.JButton EditMorador;
    private javax.swing.JButton Levantamento;
    private javax.swing.JButton RemoveMorador;
    private javax.swing.JTable TableMoradores;
    private java.awt.Label Title;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.Label label1;
    // End of variables declaration//GEN-END:variables
}
