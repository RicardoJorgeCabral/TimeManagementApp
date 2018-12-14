/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagementapp.gui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import timemanagementapp.DAO.DAO;
import timemanagementapp.model.TaskLog;

/**
 *
 * @author utilizador
 */
public class PanelMainDashboard extends javax.swing.JPanel {

  private final DAO db = new DAO();
  private BaseJFrame mainFrame;
  private List<Integer> todayTaskIds;
  /**
   * Creates new form PanelMainDashboard
   */
  public PanelMainDashboard(BaseJFrame f) {
    this.mainFrame = f;
    initComponents();
    Date now = new Date();
    jLabel2.setText(new SimpleDateFormat("yyyy-MM-dd").format(now));
    try {
      int totalToday = db.getTotalTimeDate(now);
      double hoursToday = totalToday / 60.0;
      String timeText = String.format("%.2f", hoursToday);
      jLabel4.setText(timeText);
    }
    catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "An error has occured:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
  
  private TimesLast7DaysTableModel fillLast7Days() {
      TimesLast7DaysTableModel model = new TimesLast7DaysTableModel();
      try {
          List<String[]> data = new ArrayList<String[]>();
          Calendar cal = Calendar.getInstance();          
          cal.setTime(new Date());
          cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-8);          
          for (int i=0; i<7; i++) {
              cal.set(Calendar.DAY_OF_MONTH, (cal.get(Calendar.DAY_OF_MONTH)+1) );
              Date date = cal.getTime();
              int time = db.getTotalTimeDate(date);
              Double hours = new Double(time / 60.0);
              String itemLine[] = new String[2];
              itemLine[0] = new SimpleDateFormat("yyyy-MM-dd").format(date);
              itemLine[1] = String.format("%.2f", hours);
              data.add(itemLine);
          }
          model = new TimesLast7DaysTableModel(data);          
      } catch (Exception ex) {
          JOptionPane.showMessageDialog(null, "An error has occured:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      }
      return model;
  }
  
  private TodayTasksTableModel fillTodayTasks() {
      TodayTasksTableModel model = new TodayTasksTableModel();
      this.todayTaskIds = new ArrayList<Integer>();
      try {
          Date now = new Date();
          List<TaskLog> res = db.getTaskLogs(now, now);
          List<String[]> data = new ArrayList<String[]>();
          int idx=0;
          for (Iterator it=res.iterator(); it.hasNext(); idx++) {
              TaskLog item = (TaskLog) it.next();
              String[] itemLine = new String[4];
              itemLine[0] = item.getTask();
              itemLine[1] = item.getType().getType();
              itemLine[2] = item.getHoursTime();
              itemLine[3] = item.getNotes();                           
              data.add(itemLine);
              this.todayTaskIds.add(idx, item.getId());              
          }
          model = new TodayTasksTableModel(data);          
      } catch (Exception ex) {
          JOptionPane.showMessageDialog(null, "An error has occured:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      }
      return model;
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
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTodayTasks = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableTimesLast7Days = new javax.swing.JTable();

        jLabel1.setText("Date:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("jLabel2");

        jLabel3.setText("Total time today:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 102));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("jLabel4");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 204, 0)));

        jLabel5.setText("Tasks today:");

        jTableTodayTasks.setModel(fillTodayTasks());
        jScrollPane1.setViewportView(jTableTodayTasks);

        jLabel6.setText("hours");

        jButton1.setText("Edit selected task");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setText("Times last 7 days");

        jTableTimesLast7Days.setModel(fillLast7Days());
        jScrollPane2.setViewportView(jTableTimesLast7Days);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel6)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE)
                        .addGap(162, 162, 162)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jButton1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        /*
        String msg = "Selected Today Tasks Table index: " + this.jTableTodayTasks.getSelectedRow() + "\n" + "Selected Last 7 Days Table index: " + this.jTableTimesLast7Days.getSelectedRow();;
        JOptionPane.showMessageDialog(null, msg, "DBG", JOptionPane.INFORMATION_MESSAGE);
        */
        int idx = this.jTableTodayTasks.getSelectedRow();
        if (idx>=0) {
            Integer taskId = this.todayTaskIds.get(idx);
            this.mainFrame.showTaskLog(taskId.intValue());
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableTimesLast7Days;
    private javax.swing.JTable jTableTodayTasks;
    // End of variables declaration//GEN-END:variables
}

class TimesLast7DaysTableModel extends AbstractTableModel {    
    private List<String[]> list;
    private String[] colTitles = new String[] { "Date", "Time" };
    
    public TimesLast7DaysTableModel(List<String[]> dayLogs) {
        this.list = dayLogs;
    }
    
    public TimesLast7DaysTableModel() {
        this.list = new ArrayList<String[]>();
    }
    
    public int getRowCount() {
        return list.size();
    }
  
    public int getColumnCount() {
        return colTitles.length;
    }
    
    @Override
    public String getColumnName(int columnIndex){
      return colTitles[columnIndex];
    }    
    
    @Override  
    public Class<?> getColumnClass(int columnIndex) {  
        return String.class;  
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      String[] value = this.list.get(rowIndex);
      return value[columnIndex];
    }
    
    public void setValueAt(String[] aValue, int rowIndex) {  
        String[] value = this.list.get(rowIndex);
        
        try {            
            value[0] = aValue[0];
            value[1] = aValue[1];
            
            this.list.set(rowIndex, value);
            fireTableCellUpdated(rowIndex, 0);  
            fireTableCellUpdated(rowIndex, 1);  
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "An error has occured:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }   
    }
    
    @Override  
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {  
        String[] value = this.list.get(rowIndex);
        value[columnIndex] = (String) aValue;
        this.list.set(rowIndex, value);
        fireTableCellUpdated(rowIndex, columnIndex);  
    }     
    
    public boolean isEditable(int rowIndex, int columnIndex) {
        return false;
    }
}

class TodayTasksTableModel extends AbstractTableModel {    
    private List<String[]> taskList;
    private String[] colTitles = new String[] { "Description", "Type", "Time", "Notes" };
        
    public TodayTasksTableModel(List<String[]> tasks) {
        this.taskList = tasks;
    }
    
    public TodayTasksTableModel() {
        this.taskList = new ArrayList<String[]>();
    }
    
    public int getRowCount() {
        return taskList.size();
    }
  
    public int getColumnCount() {
        return colTitles.length;
    }
    
    @Override
    public String getColumnName(int columnIndex){
      return colTitles[columnIndex];
    }    
     
    @Override  
    public Class<?> getColumnClass(int columnIndex) {  
        return String.class;  
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      String[] task = this.taskList.get(rowIndex);
      return task[columnIndex];
    }
    
    public void setValueAt(String[] aValue, int rowIndex) {  
        String[] task = this.taskList.get(rowIndex);
        
        try {            
            task[0] = aValue[0];
            task[1] = aValue[1];
            task[2] = aValue[2];
            task[3] = aValue[3];

            this.taskList.set(rowIndex, task);
            fireTableCellUpdated(rowIndex, 0);  
            fireTableCellUpdated(rowIndex, 1);  
            fireTableCellUpdated(rowIndex, 2);  
            fireTableCellUpdated(rowIndex, 3);  
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "An error has occured:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }   
    }
    
    @Override  
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {  
        String[] task = this.taskList.get(rowIndex);
        task[columnIndex] = (String) aValue;
        this.taskList.set(rowIndex, task);
        fireTableCellUpdated(rowIndex, columnIndex);  
    }     
    
    public boolean isEditable(int rowIndex, int columnIndex) {
        return false;
    }
}