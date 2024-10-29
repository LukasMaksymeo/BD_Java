/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package extranathanjava;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.AbstractCellEditor;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author lukas
 */
public class TelaDeVisualização extends javax.swing.JFrame {
    
    private TableRowSorter<DefaultTableModel> rowSorter;
    /**
     * Creates new form Visualizador
     */
    public TelaDeVisualização() {
        initCustomComponents();
        initComponents();
        addRadioButtonListeners();{
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
    private void BtnDeslogarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        TelaDeMenu TelaPrincipal = new TelaDeMenu();
        TelaPrincipal.setVisible(true);
        dispose();        // TODO add your handling code here:
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
            String url = "jdbc:mysql://localhost:3306/teste2";
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
            
            // Configuração do filtro de pesquisa
            rowSorter = new TableRowSorter<>(tableModel);
            ListaDePessoas.setRowSorter(rowSorter);
            
            

            // Adiciona o DocumentListener ao campo de pesquisa
            Pesquisa.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    filterTable();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    filterTable();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    filterTable();
                }

                private void filterTable() {
                    String text = Pesquisa.getText();
                    if (text.trim().length() == 0) {
                        rowSorter.setRowFilter(null);
                    } else {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    }
                }
            });
            addRadioButtonListeners();
        }
        catch(Exception ex){
            System.out.printf("Nao foi possivel conectar\n");
        }
    }
    }

    private void initCustomComponents() {
        // Inicialização dos RadioButtons
        SexoFeminino = new JRadioButton("Feminino");
        SexoMasculino = new JRadioButton("Masculino");
        AtivoSim = new JRadioButton("Sim");
        AtivoNao = new JRadioButton("Não");
        
        ButtonGroup sexoGroup = new ButtonGroup();
        sexoGroup.add(SexoFeminino);
        sexoGroup.add(SexoMasculino);
        
        ButtonGroup ativoGroup = new ButtonGroup();
        ativoGroup.add(AtivoSim);
        ativoGroup.add(AtivoNao); }
        
    
    private void addRadioButtonListeners() {
    // Listener para seleção de sexo
    SexoMasculino.addActionListener(e -> {
        if (SexoMasculino.isSelected()) {
            SexoFeminino.setSelected(false); // Desmarca o botão oposto
        }
        filterTable(); // Atualiza a tabela com o filtro
    });

    SexoFeminino.addActionListener(e -> {
        if (SexoFeminino.isSelected()) {
            SexoMasculino.setSelected(false); // Desmarca o botão oposto
        }
        filterTable(); // Atualiza a tabela com o filtro
    });

    // Listener para seleção de ativo
    AtivoSim.addActionListener(e -> {
        if (AtivoSim.isSelected()) {
            AtivoNao.setSelected(false); // Desmarca o botão oposto
        }
        filterTable(); // Atualiza a tabela com o filtro
    });

    AtivoNao.addActionListener(e -> {
        if (AtivoNao.isSelected()) {
            AtivoSim.setSelected(false); // Desmarca o botão oposto
        }
        filterTable(); // Atualiza a tabela com o filtro
    });
}


    private void filterTable() {
        String text = Pesquisa.getText();
        ArrayList<RowFilter<Object, Object>> filters = new ArrayList<>();

        // Filtro de texto
        if (text.trim().length() > 0) {
            filters.add(RowFilter.regexFilter("(?i)" + text));
        }

        // Filtro de sexo
        if (SexoFeminino.isSelected()) {
            filters.add(RowFilter.regexFilter("^Feminino$", 2)); // Coluna 2 é "Sexo"
        } else if (SexoMasculino.isSelected()) {
            filters.add(RowFilter.regexFilter("^Masculino$", 2));
        }

        // Filtro de ativo
        if (AtivoSim.isSelected()) {
            filters.add(RowFilter.regexFilter("^Sim$", 3)); // Coluna 3 é "Ativo"
        } else if (AtivoNao.isSelected()) {
            filters.add(RowFilter.regexFilter("^Não$", 3));
        }

        RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filters);
        rowSorter.setRowFilter(combinedFilter);
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
        jLabel1 = new javax.swing.JLabel();
        Pesquisa = new javax.swing.JTextField();
        SexoMasculino = new javax.swing.JRadioButton();
        SexoFeminino = new javax.swing.JRadioButton();
        AtivoSim = new javax.swing.JRadioButton();
        AtivoNao = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
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

        jLabel1.setText("Pesquisa: ");

        Pesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PesquisaActionPerformed(evt);
            }
        });

        SexoMasculino.setText("Masculino");

        SexoFeminino.setText("Feminino");

        AtivoSim.setText("Sim");

        AtivoNao.setText("Não");

        jLabel2.setText("Sexo:");

        jLabel3.setText("Ativo:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnDeslogar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(AtivoSim, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(AtivoNao, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(SexoMasculino, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(SexoFeminino, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnDeslogar)
                    .addComponent(jLabel1)
                    .addComponent(Pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SexoMasculino)
                    .addComponent(SexoFeminino)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AtivoSim)
                    .addComponent(AtivoNao)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void PesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PesquisaActionPerformed
        // TODO add your handling code here:
        JTextField searchField = new JTextField(20); // Seu campo de pesquisa
JTable table = new JTable(); // Sua tabela de usuários

TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
table.setRowSorter(rowSorter);

searchField.getDocument().addDocumentListener(new DocumentListener() {
    @Override
    public void insertUpdate(DocumentEvent e) {
        filterTable();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        filterTable();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        filterTable();
    }

    private void filterTable() {
        String text = searchField.getText();
        if (text.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }
});

    }//GEN-LAST:event_PesquisaActionPerformed
    
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
    private javax.swing.JRadioButton AtivoNao;
    private javax.swing.JRadioButton AtivoSim;
    private javax.swing.JButton BtnDeslogar;
    public javax.swing.JTable ListaDePessoas;
    private javax.swing.JTextField Pesquisa;
    private javax.swing.JRadioButton SexoFeminino;
    private javax.swing.JRadioButton SexoMasculino;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    private void configurarTabela() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}