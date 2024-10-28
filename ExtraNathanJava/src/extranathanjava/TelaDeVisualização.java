/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package extranathanjava;

import java.awt.Button;
import java.awt.Component;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author lukas
 */
public class TelaDeVisualização extends javax.swing.JFrame {

    /**
     * Creates new form Visualizador
     */
    public TelaDeVisualização() {
        initComponents();{
    private void aplicarFiltros() {
    String nomeFiltro = filtroNome.getText().trim();
    String sexoFiltro = sexoM.isSelected() ? "M" : sexoF.isSelected() ? "F" : "";
    String ativoFiltro = ativoS.isSelected() ? "S" : ativoN.isSelected() ? "N" : "";

    StringBuilder query = new StringBuilder("SELECT Id, Nome, Sexo, Ativo FROM usuarios WHERE 1=1");

    if (!nomeFiltro.isEmpty()) {
        query.append(" AND Nome LIKE '%").append(nomeFiltro).append("%'");
    }
    if (!sexoFiltro.isEmpty()) {
        query.append(" AND Sexo = '").append(sexoFiltro).append("'");
    }
    if (!ativoFiltro.isEmpty()) {
        query.append(" AND Ativo = '").append(ativoFiltro).append("'");
    }

    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/extranathan", "root", null);
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(query.toString())) {

        DefaultTableModel tableModel = (DefaultTableModel) ListaDePessoas.getModel();
        tableModel.setRowCount(0); // Limpa a tabela

        while (rs.next()) {
            Object[] row = {
                rs.getInt("Id"),
                rs.getString("Nome"),
                rs.getString("Sexo"),
                rs.getString("Ativo")
            };
            tableModel.addRow(row);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
        class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setText("Editar"); // Define o texto do botão
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}
    class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private JButton button;
    private int row; // Variável para armazenar o índice da linha
    private JTable table;

    public ButtonEditor(JButton button) {
        this.button = button;
        button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fireEditingStopped(); // Finaliza a edição
                        
                        // Obtém o ID da linha selecionada
                        int id = (int) table.getValueAt(row, 0);
                        TelaDeEdicao TelaEditar = new TelaDeEdicao(id);
                        TelaEditar.setVisible(true);
                        dispose();
                    }
                });
    }
 
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row; // Armazena o índice da linha
        this.table = table; // Armazena a referência da tabela
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return "Editar";
    }
}


        try{
            String url = "jdbc:mysql://localhost:3306/extranathan";
            Connection con = DriverManager.getConnection(url, "root", null);
            Statement stmt = con.createStatement();
            System.out.printf("Conectado com Sucesso!\n");
            ResultSet rs = stmt.executeQuery("select Id, Nome, Sexo, Ativo from usuarios");                
            
            ArrayList<Pessoa> pessoas = new ArrayList<>();
            String[] colunas = {"ID", "Nome", "Sexo", "Ativo", "Ação"};
            
            while(rs.next())
            {
                int id =  rs.getInt("Id");
                String nome = rs.getString("Nome");                
                String sexo = rs.getString("Sexo");
                String ativo = rs.getString("Ativo");
                
                                
                Pessoa pessoa = new Pessoa(id, nome, sexo, ativo); 
                pessoas.add(pessoa);
            }      
            
            DefaultTableModel tableModel = new DefaultTableModel(colunas, 0);
            
            for(Pessoa pessoa: pessoas)
            {
                Object[] row = {pessoa.Id, pessoa.Nome, pessoa.Sexo, pessoa.Ativo};
                tableModel.addRow(row);
            }
            ListaDePessoas.setModel(tableModel);
            TableColumn colunaAcao = ListaDePessoas.getColumn("Ação");
            colunaAcao.setCellRenderer(new ButtonRenderer());
            colunaAcao.setCellEditor(new ButtonEditor(new JButton("Editar")));
            
            
           
        }
        catch(Exception ex){
            System.out.printf("Nao foi possivel conectar\n");
        }
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

        BtnDeslogar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        ListaDePessoas = new javax.swing.JTable();
        filtroNome = new javax.swing.JTextField();
        nomeFiltro = new javax.swing.JLabel();
        sexoFiltro = new javax.swing.JLabel();
        ativoLabel = new javax.swing.JLabel();
        btnFiltrar = new javax.swing.JButton();
        sexoM = new javax.swing.JRadioButton();
        sexoF = new javax.swing.JRadioButton();
        ativoS = new javax.swing.JRadioButton();
        ativoN = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(600, 300, 0, 0));

        BtnDeslogar.setText("Retorna");
        BtnDeslogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDeslogarActionPerformed(evt);
            }
        });

        ListaDePessoas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1), "teste", "M", "s", "desenvolvedor", ""},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Sexo", "Ativo", "Cargo", "teste"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ListaDePessoas.setFocusable(false);
        ListaDePessoas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListaDePessoasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(ListaDePessoas);

        filtroNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtroNomeActionPerformed(evt);
            }
        });

        nomeFiltro.setText("Nome");

        sexoFiltro.setText("Sexo");

        ativoLabel.setText("Ativo");

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        sexoM.setText("M");

        sexoF.setText("F");

        ativoS.setText("S");

        ativoN.setText("N");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nomeFiltro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sexoFiltro)
                        .addGap(76, 76, 76)
                        .addComponent(ativoLabel)
                        .addGap(111, 111, 111)
                        .addComponent(BtnDeslogar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(filtroNome, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(sexoM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sexoF)
                        .addGap(40, 40, 40)
                        .addComponent(ativoS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ativoN)
                        .addGap(18, 18, 18)
                        .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(BtnDeslogar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nomeFiltro)
                            .addComponent(sexoFiltro)
                            .addComponent(ativoLabel))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(filtroNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(sexoM)
                                .addComponent(sexoF)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFiltrar)
                            .addComponent(ativoN)
                            .addComponent(ativoS))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnDeslogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDeslogarActionPerformed
        TelaDeMenu TelaPrincipal = new TelaDeMenu();
        TelaPrincipal.setVisible(true);
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDeslogarActionPerformed

    private void ListaDePessoasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaDePessoasMouseClicked
     // TODO add your handling code here:
    }//GEN-LAST:event_ListaDePessoasMouseClicked

    private void filtroNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtroNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filtroNomeActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
       aplicarFiltros();
    }//GEN-LAST:event_btnFiltrarActionPerformed
    
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
            java.util.logging.Logger.getLogger(TelaDeVisualização.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaDeVisualização.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaDeVisualização.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaDeVisualização.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaDeVisualização().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnDeslogar;
    public javax.swing.JTable ListaDePessoas;
    private javax.swing.JLabel ativoLabel;
    private javax.swing.JRadioButton ativoN;
    private javax.swing.JRadioButton ativoS;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JTextField filtroNome;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel nomeFiltro;
    private javax.swing.JRadioButton sexoF;
    private javax.swing.JLabel sexoFiltro;
    private javax.swing.JRadioButton sexoM;
    // End of variables declaration//GEN-END:variables
}
