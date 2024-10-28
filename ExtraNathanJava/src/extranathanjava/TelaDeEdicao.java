/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package extranathanjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author lukas
 */
public class TelaDeEdicao extends javax.swing.JFrame {
    public int ID;
    /**
     * Creates new form TelaDeCadastro
     */
    public TelaDeEdicao(int ID) {
    this.ID = ID;
    initComponents();
        MasculinoOpt.setActionCommand("Masculino");
        FemininoOpt.setActionCommand("Feminino");
        OptSim.setActionCommand("SIM");
        OptNão.setActionCommand("NÃO");
    try {
        String url = "jdbc:mysql://localhost:3306/extranathan";
        Connection con = DriverManager.getConnection(url, "root", null);
        Statement stmt = con.createStatement();
        // Executa a consulta
        ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios WHERE ID=" + ID);

        // Verifica se encontrou algum resultado
        if (rs.next()) {
            System.out.printf("ID: %d\nNome: %s\nSexo: %s\nAtivo: %s\n", 
                              rs.getInt("ID"), 
                              rs.getString("Nome"), 
                              rs.getString("Sexo"), 
                              rs.getString("Ativo"));
            String sexo = rs.getString("Sexo");

            if (sexo.equalsIgnoreCase("M")) {
                MasculinoOpt.setSelected(true);
            }
            else if (sexo.equalsIgnoreCase("F")) {
                FemininoOpt.setSelected(true);
            }
            String ativo = rs.getString("Ativo");

            if (ativo.equalsIgnoreCase("S")) {
               OptSim.setSelected(true);
            }            
            else if (ativo.equalsIgnoreCase("N")) {
                OptNão.setSelected(true);
            }
            CampoNome.setText(rs.getString("Nome"));

        } else {
            System.out.println("Nenhum registro encontrado para o ID:" + ID);
        }

    } catch (Exception e) {
        System.out.printf("\nErro ao conectar"); // Exibe a exceção completa para depuração
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

        SexoEscolha = new javax.swing.ButtonGroup();
        AtivoEscolha = new javax.swing.ButtonGroup();
        BtnDeslogar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        CampoNome = new javax.swing.JTextField();
        MasculinoOpt = new javax.swing.JRadioButton();
        FemininoOpt = new javax.swing.JRadioButton();
        OptSim = new javax.swing.JRadioButton();
        OptNão = new javax.swing.JRadioButton();
        Editar = new javax.swing.JButton();
        Excluir = new javax.swing.JButton();
        Executado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(600, 300, 0, 0));

        BtnDeslogar.setText("Retorna");
        BtnDeslogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDeslogarActionPerformed(evt);
            }
        });

        jLabel1.setText("Ativo:");

        jLabel2.setText("Nome:");

        jLabel3.setText("Sexo:");

        SexoEscolha.add(MasculinoOpt);
        MasculinoOpt.setText("Masculino");

        SexoEscolha.add(FemininoOpt);
        FemininoOpt.setText("Feminino");

        AtivoEscolha.add(OptSim);
        OptSim.setText("SIM");

        AtivoEscolha.add(OptNão);
        OptNão.setText("NÃO");

        Editar.setText("Editar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });

        Excluir.setText("Excluir");
        Excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(MasculinoOpt, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(FemininoOpt, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(OptSim, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(OptNão, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(CampoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 4, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(Executado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnDeslogar)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(Editar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Excluir)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnDeslogar)
                    .addComponent(Executado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CampoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MasculinoOpt)
                    .addComponent(FemininoOpt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OptSim)
                    .addComponent(OptNão))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Editar)
                    .addComponent(Excluir))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnDeslogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDeslogarActionPerformed
        TelaDeVisualização TelaVisualizar = new TelaDeVisualização();
        TelaVisualizar.setVisible(true);
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDeslogarActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        try{
            String url = "jdbc:mysql://localhost:3306/extranathan";
            Connection con = DriverManager.getConnection(url, "root", null);
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE usuarios SET Nome ='"+CampoNome.getText()+"', Sexo ='"+
                    SexoEscolha.getSelection().getActionCommand().charAt(0)+"', Ativo ='"+
                    AtivoEscolha.getSelection().getActionCommand().charAt(0)+"' WHERE id ="+ID);
            Executado.setText("Editado com Sucesso!");
        }
        catch(Exception ex){
            System.out.printf("Nao foi possivel conectar\n");
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExcluirActionPerformed
        ConfirmarAção TelaConfirmar = new ConfirmarAção(ID);
        TelaConfirmar.setVisible(true);
        dispose(); 
    }//GEN-LAST:event_ExcluirActionPerformed

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
            java.util.logging.Logger.getLogger(TelaDeEdicao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaDeEdicao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaDeEdicao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaDeEdicao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.ButtonGroup AtivoEscolha;
    private javax.swing.JButton BtnDeslogar;
    public javax.swing.JTextField CampoNome;
    public javax.swing.JButton Editar;
    public javax.swing.JButton Excluir;
    private javax.swing.JLabel Executado;
    public javax.swing.JRadioButton FemininoOpt;
    public javax.swing.JRadioButton MasculinoOpt;
    public javax.swing.JRadioButton OptNão;
    public javax.swing.JRadioButton OptSim;
    public javax.swing.ButtonGroup SexoEscolha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables

}
