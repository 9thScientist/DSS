/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Despesas.Despesa;
import Despesas.Movimento;
import Main.SplitExpense;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.table.DefaultTableModel;


public class DespesasUI extends javax.swing.JFrame {

    private String[] categorias;
    private List<Movimento> ms;
    private Movimento movSelec;
    private DefaultTableModel model;
    /**
     * Creates new form DespesasUI
     */
    public DespesasUI() {
        fillCategorias();
        tableFiller();
        initComponents();
        setSaldo();
        categoriasBox.setSelectedItem("Todas as categorias");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        BackButton = new javax.swing.JButton();
        Title = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        EditarCategoriasButton = new javax.swing.JButton();
        RegistarDespesaButton = new javax.swing.JButton();
        EditarDespesaButton = new javax.swing.JButton();
        FiltrarDespesasButton = new javax.swing.JButton();
        RemoverDespesaButton = new javax.swing.JButton();
        categoriasBox = new javax.swing.JComboBox<>(categorias);
        fromSpinner = new javax.swing.JSpinner();
        toSpinner = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BackButton.setText("<");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        Title.setAlignment(java.awt.Label.CENTER);
        Title.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Title.setFont(new java.awt.Font("Gargi-1.2b", 1, 24)); // NOI18N
        Title.setText("SplitExpense");

        jTable1.setModel(model);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        EditarCategoriasButton.setText("Categorias");
        EditarCategoriasButton.setMaximumSize(new java.awt.Dimension(150, 23));
        EditarCategoriasButton.setMinimumSize(new java.awt.Dimension(150, 23));
        EditarCategoriasButton.setPreferredSize(new java.awt.Dimension(150, 23));
        EditarCategoriasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarCategoriasButtonActionPerformed(evt);
            }
        });

        RegistarDespesaButton.setText("Registar Despesa");
        RegistarDespesaButton.setMaximumSize(new java.awt.Dimension(150, 23));
        RegistarDespesaButton.setMinimumSize(new java.awt.Dimension(150, 23));
        RegistarDespesaButton.setPreferredSize(new java.awt.Dimension(150, 23));
        RegistarDespesaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistarDespesaButtonActionPerformed(evt);
            }
        });

        EditarDespesaButton.setText("Editar Despesa");
        EditarDespesaButton.setEnabled(false);
        EditarDespesaButton.setMaximumSize(new java.awt.Dimension(150, 23));
        EditarDespesaButton.setMinimumSize(new java.awt.Dimension(150, 23));
        EditarDespesaButton.setPreferredSize(new java.awt.Dimension(150, 23));
        EditarDespesaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarDespesaButtonActionPerformed(evt);
            }
        });

        FiltrarDespesasButton.setText("Filtrar Despesas");
        FiltrarDespesasButton.setMaximumSize(new java.awt.Dimension(150, 23));
        FiltrarDespesasButton.setMinimumSize(new java.awt.Dimension(150, 23));
        FiltrarDespesasButton.setPreferredSize(new java.awt.Dimension(150, 23));
        FiltrarDespesasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiltrarDespesasButtonActionPerformed(evt);
            }
        });

        RemoverDespesaButton.setText("Remover Despesa");
        RemoverDespesaButton.setEnabled(false);
        RemoverDespesaButton.setMaximumSize(new java.awt.Dimension(150, 23));
        RemoverDespesaButton.setMinimumSize(new java.awt.Dimension(150, 23));
        RemoverDespesaButton.setPreferredSize(new java.awt.Dimension(150, 23));
        RemoverDespesaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoverDespesaButtonActionPerformed(evt);
            }
        });

        categoriasBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriasBoxActionPerformed(evt);
            }
        });

        fromSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1417098600000L), null, null, java.util.Calendar.MONTH));

        toSpinner.setModel(new javax.swing.SpinnerDateModel());

        jLabel1.setText("Inicio:");

        jLabel2.setText("Fim:");

        label1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        label1.setPreferredSize(new java.awt.Dimension(155, 36));
        label1.setText("Saldo:");

        label2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        label2.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(toSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(fromSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(categoriasBox, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(115, 115, 115))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(BackButton)
                                .addGap(172, 172, 172)
                                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(EditarCategoriasButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(RegistarDespesaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(EditarDespesaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(RemoverDespesaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(FiltrarDespesasButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BackButton))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fromSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(toSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(categoriasBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(EditarCategoriasButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RegistarDespesaButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(EditarDespesaButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RemoverDespesaButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(FiltrarDespesasButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        label1.getAccessibleContext().setAccessibleName("Saldo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void categoriasBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriasBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoriasBoxActionPerformed

    private void RemoverDespesaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoverDespesaButtonActionPerformed
        SplitExpense se = new SplitExpense();

        if (movSelec.isTransacao())
            se.removerMovimento(movSelec);
        else
            se.removerDespesa((Despesa) movSelec);

        EditarDespesaButton.setEnabled(false);
        RemoverDespesaButton.setEnabled(false);
        this.setVisible(false);
        new DespesasUI().setVisible(true);
    }//GEN-LAST:event_RemoverDespesaButtonActionPerformed

    private void EditarDespesaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarDespesaButtonActionPerformed
        this.setVisible(false);
        new EditarDespesaUI(movSelec).setVisible(true);
    }//GEN-LAST:event_EditarDespesaButtonActionPerformed

    private void RegistarDespesaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistarDespesaButtonActionPerformed
        this.setVisible(false);
        new RegistarDespesaUI().setVisible(true);
    }//GEN-LAST:event_RegistarDespesaButtonActionPerformed

    private void EditarCategoriasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarCategoriasButtonActionPerformed
        this.setVisible(false);
        new CategoriasUI().setVisible(true);
    }//GEN-LAST:event_EditarCategoriasButtonActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        movSelec = ms.get(jTable1.getSelectedRow());

        if (!movSelec.isTransacao()) EditarDespesaButton.setEnabled(true);
        else EditarDespesaButton.setEnabled(false);
        RemoverDespesaButton.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        this.setVisible(false);
        new SplitExpenseUI().setVisible(true);
    }//GEN-LAST:event_BackButtonActionPerformed

    private void FiltrarDespesasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiltrarDespesasButtonActionPerformed
        SplitExpense se = new SplitExpense();
        model.setRowCount(0);
        
        if (categoriasBox.getSelectedItem().equals("Todas as categorias")) {
            java.sql.Date from = new java.sql.Date(((java.util.Date) fromSpinner.getValue()).getTime());
            java.sql.Date to = new java.sql.Date(((java.util.Date) toSpinner.getValue()).getTime());
         
            ms = new ArrayList(se.getHistorico(from, to));
        } else {
            java.sql.Date from = new java.sql.Date(((java.util.Date) fromSpinner.getValue()).getTime());
            java.sql.Date to = new java.sql.Date(((java.util.Date) toSpinner.getValue()).getTime());
                        
            ms = new ArrayList(se.getHistorico(from, to, se.getCategoria(categoriasBox.getSelectedItem().toString())));
        }
        
        for (Movimento m : ms) {
           Date data = m.getData();
           String morador = m.getMorador().getNome();
           float valor = m.getValor();
           String descricao = m.isTransacao() ? "Transação" : getDescricao(m);
           String categoria = m.isTransacao() ? getTipoTransacao(m) : getCategoria(m);
            
            Object[] ln = {data, morador, valor, descricao, categoria};
            model.addRow(ln);
        }
    }//GEN-LAST:event_FiltrarDespesasButtonActionPerformed

     private void tableFiller() {
        SplitExpense se = new SplitExpense();
        String cols[] = {"Data", "Morador", "Valor", "Descricao", "Categoria"};
        model = new DefaultTableModel(cols, 0) {
                
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        
        ms = new ArrayList(se.getHistoricoList());
        for (Movimento m : ms) {
           Date data = m.getData();
           String morador = m.getMorador().getNome();
           float valor = m.getValor();
           String descricao = m.isTransacao() ? "Transação" : getDescricao(m);
           String categoria = m.isTransacao() ? getTipoTransacao(m) : getCategoria(m);
            
            Object[] ln = {data, morador, valor, descricao, categoria};
            model.addRow(ln);
        }
    }
     
     private String getCategoria(Movimento m) {
         Despesa d = (Despesa) m;
         
         return d.getCategoria().getDescricao();
     }
     
     private String getTipoTransacao(Movimento m) {
         return (m.getValor() < 0) ? "Levantamento" : "Depósito";
     }
     
     private String getDescricao(Movimento m) {
         if (m.getClass().getSimpleName().equals("Despesa")) {
            Despesa d = (Despesa) m;
            return d.getDescricao();
         } else 
             return "";
     } 
     
     
     private void setSaldo(){
         SplitExpense se = new SplitExpense();
         label2.setText(""+se.getApartamento().getSaldo());
     }
     
     private void fillCategorias() {
        SplitExpense se = new SplitExpense();
        Set<String> cats = se.getCategorias();
        categorias = cats.toArray(new String[cats.size() + 1]);
        categorias[cats.size()] = "Todas as categorias";
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JButton EditarCategoriasButton;
    private javax.swing.JButton EditarDespesaButton;
    private javax.swing.JButton FiltrarDespesasButton;
    private javax.swing.JButton RegistarDespesaButton;
    private javax.swing.JButton RemoverDespesaButton;
    private java.awt.Label Title;
    private javax.swing.JComboBox<String> categoriasBox;
    private javax.swing.JSpinner fromSpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private javax.swing.JSpinner toSpinner;
    // End of variables declaration//GEN-END:variables
}
