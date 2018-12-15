/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagementapp.gui;

/**
 *
 * @author Ricardoc
 */
public class BaseJFrame extends javax.swing.JFrame {

    /**
     * Creates new form BaseJFrame
     */
    public BaseJFrame() {
        initComponents();
        showMainDashboard();
    }
    
    private void showMainDashboard() {
      this.mainPanel.removeAll();
      PanelMainDashboard p = new PanelMainDashboard(this);
      this.mainPanel.add(p);
      this.revalidate();
    }         
    
    public void refreshMainPanel() {
      cleanMainPanel();
      showMainDashboard();
    }
    
    private void cleanMainPanel() {
        this.mainPanel.removeAll();        
        this.repaint();
        this.revalidate();    
    }
    
    public void showTaskLog(int taskId) {
        this.cleanMainPanel();    
        PanelTaskLogEdit p = new PanelTaskLogEdit(this, taskId);
        this.mainPanel.add(p);
        this.revalidate();   
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
    mainPanel = new javax.swing.JPanel();
    jMenuBar1 = new javax.swing.JMenuBar();
    jMenu1 = new javax.swing.JMenu();
    jMenuItem1 = new javax.swing.JMenuItem();
    jMenu2 = new javax.swing.JMenu();
    jMenuItem2 = new javax.swing.JMenuItem();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jScrollPane1.setViewportView(mainPanel);

    jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N

    jMenu1.setText("Configurations");
    jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

    jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    jMenuItem1.setText("Task Types");
    jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItem1ActionPerformed(evt);
      }
    });
    jMenu1.add(jMenuItem1);

    jMenuBar1.add(jMenu1);

    jMenu2.setText("Log Times");
    jMenu2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

    jMenuItem2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    jMenuItem2.setText("Log Time");
    jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItem2ActionPerformed(evt);
      }
    });
    jMenu2.add(jMenuItem2);

    jMenuBar1.add(jMenu2);

    setJMenuBar(jMenuBar1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)
    );

    setSize(new java.awt.Dimension(1222, 856));
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        this.cleanMainPanel();    
        PanelTaskTypeEdit p = new PanelTaskTypeEdit(this);
        this.mainPanel.add(p);
        this.revalidate();        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        this.cleanMainPanel();    
        PanelTaskLogEdit p = new PanelTaskLogEdit(this);
        this.mainPanel.add(p);
        this.revalidate();  
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
            java.util.logging.Logger.getLogger(BaseJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BaseJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BaseJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BaseJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BaseJFrame().setVisible(true);
            }
        });
    }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JMenu jMenu1;
  private javax.swing.JMenu jMenu2;
  private javax.swing.JMenuBar jMenuBar1;
  private javax.swing.JMenuItem jMenuItem1;
  private javax.swing.JMenuItem jMenuItem2;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JPanel mainPanel;
  // End of variables declaration//GEN-END:variables
}
