/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagementapp.gui;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import timemanagementapp.DAO.DAO;
import timemanagementapp.model.TaskLog;
import timemanagementapp.model.TaskType;
import timemanagementapp.util.LocalDateComponentFormatter;

/**
 *
 * @author Ricardoc
 */
public class PanelTaskLogEdit extends javax.swing.JPanel {

    private JDatePickerImpl datePicker;
    private BaseJFrame mainFrame;
    private final DAO db = new DAO();
    private TaskLog currentTaskLog = new TaskLog();
    
    /**
     * Creates new form PanelTaskLogEdit
     */
    public PanelTaskLogEdit(BaseJFrame f) {
        this.mainFrame = f;
        initComponents();       
        this.createCalendarPicker();        
        this.fillTaskTypesCBox();
        this.fillTaskLogsCBox();
        this.jComboBox1.setVisible(true);
        this.jLabelTaskId.setVisible(false);
    }
    
    public PanelTaskLogEdit(BaseJFrame f, int taskId) {
        try {
            this.setCurrentTaskLog(taskId);   
            this.mainFrame = f;
            initComponents();       
            this.createCalendarPicker();        
            this.fillTaskTypesCBox();
            this.jComboBox1.setVisible(false);
            this.jLabelTaskId.setText(new Integer(taskId).toString());
            this.jLabelTaskId.setVisible(true);
            this.showData();
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "An error has occured:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void fillTaskLogsCBox() {
        try {
            this.currentTaskLog = new TaskLog();
            this.jComboBox1.removeAllItems();
            this.jComboBox1.addItem("<add new ...>");            
            List<TaskLog> litsTaskLogs = db.getAllTaskLogs();            
            for (Iterator it = litsTaskLogs.iterator(); it.hasNext(); ) {
                TaskLog t = (TaskLog) it.next();
                this.jComboBox1.addItem(new Integer(t.getId()).toString());
            }
            this.jComboBox1.setSelectedIndex(0);   
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "An error has occured:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void fillTaskTypesCBox() {
        try {
            this.jComboBox2.removeAllItems();            
            List<TaskType> litsTaskTypes = db.getAllTaskTypes();            
            for (Iterator it = litsTaskTypes.iterator(); it.hasNext(); ) {
                TaskType t = (TaskType) it.next();
                this.jComboBox2.addItem(t.getType());
            }
            if (this.jComboBox2.getItemCount()<1) 
                this.jComboBox2.setEnabled(false);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "An error has occured:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void createCalendarPicker() {
        Properties p = new Properties();
	p.put("text.today", "today");
	p.put("text.month", "month");
	p.put("text.year", "year");
        
        UtilDateModel model = new UtilDateModel();        
        Date now = new Date();
        
        //model.setDate(2018, 10, 30);        
        model.setDate(Integer.parseInt((new SimpleDateFormat("yyyy")).format(now)),
                   Integer.parseInt((new SimpleDateFormat("MM")).format(now))-1,
                   Integer.parseInt((new SimpleDateFormat("dd")).format(now)));
        model.setSelected(true);
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        datePicker = new JDatePickerImpl(datePanel, new LocalDateComponentFormatter());
        this.jPanelcalendar.add(datePicker, java.awt.BorderLayout.CENTER);
    }

    private void cleanFields() {
        Date now = new Date();
        this.datePicker.getModel().setDate(Integer.parseInt((new SimpleDateFormat("yyyy")).format(now)),
                                           Integer.parseInt((new SimpleDateFormat("MM")).format(now))-1,
                                           Integer.parseInt((new SimpleDateFormat("dd")).format(now)));
        //jComboBox2.setSelectedIndex(0);
        jTextField1.setText("");
        jTextFieldTime.setText("0:00");        
        jTextArea1.setText("");
    }
    
    private void setCurrentTaskLog(int id) {
        try {
            this.currentTaskLog = db.getTaskLog(id);            
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "An error has occured:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private int getMinutesTime(String time) {
      String[] res = time.split(":");
      Integer hours = Integer.parseInt(res[0]);
      Integer minutes = Integer.parseInt(res[1]);
      return (hours * 60) + minutes;
    }
    
    private void showData() {
        try {
            Date data = new SimpleDateFormat("yyyy-MM-dd").parse(this.currentTaskLog.getDate());  
            Integer day = new Integer(new SimpleDateFormat("dd").format(data));
            Integer month = new Integer(new SimpleDateFormat("MM").format(data));
            Integer year = new Integer(new SimpleDateFormat("yyyy").format(data));           
            datePicker.getModel().setDay(day);
            datePicker.getModel().setMonth(month-1);
            datePicker.getModel().setYear(year);
            jComboBox2.setSelectedItem(this.currentTaskLog.getType().getType());
            jTextField1.setText(this.currentTaskLog.getTask());
            jTextFieldTime.setText(this.currentTaskLog.getHoursTime());
            jTextArea1.setText(this.currentTaskLog.getNotes());
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "An error has occured:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    buttonGroup1 = new javax.swing.ButtonGroup();
    jPanel2 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    jComboBox1 = new javax.swing.JComboBox<>();
    jLabel2 = new javax.swing.JLabel();
    jPanelcalendar = new javax.swing.JPanel();
    jLabel3 = new javax.swing.JLabel();
    jComboBox2 = new javax.swing.JComboBox<>();
    jLabel4 = new javax.swing.JLabel();
    jTextField1 = new javax.swing.JTextField();
    jLabel5 = new javax.swing.JLabel();
    jLabel6 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    jTextArea1 = new javax.swing.JTextArea();
    jLabelTaskId = new javax.swing.JLabel();
    jTextFieldTime = new javax.swing.JFormattedTextField();
    jLabel7 = new javax.swing.JLabel();
    jPanel1 = new javax.swing.JPanel();
    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();
    jButton3 = new javax.swing.JButton();

    jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jLabel1.setText("ID:");

    jComboBox1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jComboBox1ActionPerformed(evt);
      }
    });

    jLabel2.setText("Date:");

    jPanelcalendar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    jPanelcalendar.setLayout(new java.awt.BorderLayout());

    jLabel3.setText("Type:");

    jLabel4.setText("Task:");

    jLabel5.setText("Time:");

    jLabel6.setText("Notes:");

    jTextArea1.setColumns(20);
    jTextArea1.setRows(5);
    jScrollPane1.setViewportView(jTextArea1);

    jLabelTaskId.setText("0");

    jTextFieldTime.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));
    jTextFieldTime.setText("0:00");

    jLabel7.setText("Hours");

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel2Layout.createSequentialGroup()
            .addComponent(jLabel6)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane1))
          .addGroup(jPanel2Layout.createSequentialGroup()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel3)
                  .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabelTaskId))
                  .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)))
              .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelcalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldTime, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7))
              .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 1094, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabelTaskId))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jPanelcalendar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel3)
          .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel4)
          .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel5)
          .addComponent(jTextFieldTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel7))
        .addGap(12, 12, 12)
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel2Layout.createSequentialGroup()
            .addComponent(jLabel6)
            .addGap(0, 439, Short.MAX_VALUE))
          .addGroup(jPanel2Layout.createSequentialGroup()
            .addComponent(jScrollPane1)
            .addContainerGap())))
    );

    jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

    jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/timemanagementapp/gui/images/icons8-save-50.png"))); // NOI18N
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });

    jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/timemanagementapp/gui/images/icons8-trash-50.png"))); // NOI18N
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton2ActionPerformed(evt);
      }
    });

    jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/timemanagementapp/gui/images/icons8-exit-50.png"))); // NOI18N
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton3ActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jButton3)
          .addComponent(jButton2)
          .addComponent(jButton1))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
    );
  }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        this.cleanFields();
        if (jComboBox1.getSelectedIndex()>0) {
            int id = Integer.parseInt((String) jComboBox1.getSelectedItem());
            this.setCurrentTaskLog(id);
            this.showData();
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {            
            Calendar myCal = Calendar.getInstance();
            myCal.set(Calendar.YEAR, datePicker.getModel().getYear());
            myCal.set(Calendar.MONTH, datePicker.getModel().getMonth());
            myCal.set(Calendar.DAY_OF_MONTH, datePicker.getModel().getDay());
            Date theDate = myCal.getTime();
            this.currentTaskLog.setDate(new SimpleDateFormat("yyyy-MM-dd").format(theDate));
            TaskType taskType = db.getTaskType((String) jComboBox2.getSelectedItem());;
            this.currentTaskLog.setType(taskType);
            this.currentTaskLog.setTask(jTextField1.getText());
            this.currentTaskLog.setTime(this.getMinutesTime(jTextFieldTime.getText()));
            this.currentTaskLog.setNotes(jTextArea1.getText());
            int id = db.addTaskLog(currentTaskLog);            
            JOptionPane.showMessageDialog(null, "Task Log added / modified.", "Info", JOptionPane.INFORMATION_MESSAGE);
            this.fillTaskLogsCBox();            
            if (id>0) {
              jComboBox1.setSelectedItem(new Integer(id).toString());
              this.jComboBox1ActionPerformed(null);            
            }            
            /*
            String newType = jTextField1.getText();
            type.setType(newType);
            type.setNotes(jTextArea1.getText());
            db.addTaskType(type);
            this.fillTaskTypesCBox();
            jComboBox1.setSelectedItem(newType);
            //this.showData(jTextField1.getText());
            this.jComboBox1ActionPerformed(null);
            JOptionPane.showMessageDialog(null, "Task type added / modified.", "Info", JOptionPane.INFORMATION_MESSAGE);
            */
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "An error has occured:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        if (jComboBox1.getSelectedIndex()>0) {
            int confirm = JOptionPane.showConfirmDialog (null, "Are you sure you want to remove the record?","Config",JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    TaskLog aTask = db.getTaskLog(Integer.parseInt((String) jComboBox1.getSelectedItem()));
                    db.removeTaskLog(aTask.getId());
                    this.cleanFields();
                    this.fillTaskLogsCBox();
                    this.fillTaskTypesCBox();
                    JOptionPane.showMessageDialog(null, "Task log removed.", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "An error has occured:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.removeAll();
        this.mainFrame.refreshMainPanel();
    }//GEN-LAST:event_jButton3ActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.ButtonGroup buttonGroup1;
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private javax.swing.JButton jButton3;
  private javax.swing.JComboBox<String> jComboBox1;
  private javax.swing.JComboBox<String> jComboBox2;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabelTaskId;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel jPanelcalendar;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTextArea jTextArea1;
  private javax.swing.JTextField jTextField1;
  private javax.swing.JFormattedTextField jTextFieldTime;
  // End of variables declaration//GEN-END:variables
}
