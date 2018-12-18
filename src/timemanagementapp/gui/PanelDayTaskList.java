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
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import timemanagementapp.DAO.DAO;
import timemanagementapp.model.TaskLog;
import timemanagementapp.util.LocalDateComponentFormatter;

/**
 *
 * @author utilizador
 */
public class PanelDayTaskList extends javax.swing.JPanel {

  private final DAO db = new DAO();
  private BaseJFrame mainFrame;
  private ArrayList<Integer> dayTaskIds;
  private JDatePickerImpl fromDatePicker;
  private JDatePickerImpl toDatePicker;
  
  /**
   * Creates new form PanelDayTaskList
   */
  public PanelDayTaskList(BaseJFrame f) {     
    this.mainFrame = f;
    this.initJDatePickers();
    initComponents();
    this.jPanelFrom.add(fromDatePicker, java.awt.BorderLayout.CENTER);
    this.jPanelTo.add(toDatePicker, java.awt.BorderLayout.CENTER);                  
  }
  
  private void initJDatePickers() {
    Properties p = new Properties();
    p.put("text.today", "today");
    p.put("text.month", "month");
    p.put("text.year", "year");

    UtilDateModel model = new UtilDateModel();        
    Calendar cal = Calendar.getInstance();  
    cal.clear();
    cal.setTime(new Date());
    model.setDate(Integer.parseInt((new SimpleDateFormat("yyyy")).format(cal.getTime())),
               Integer.parseInt((new SimpleDateFormat("MM")).format(cal.getTime()))-1,
               Integer.parseInt((new SimpleDateFormat("dd")).format(cal.getTime()))-8);
    model.setSelected(true);
    JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
    // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    fromDatePicker = new JDatePickerImpl(datePanel, new LocalDateComponentFormatter());

    model = new UtilDateModel();    
    model.setDate(Integer.parseInt((new SimpleDateFormat("yyyy")).format(cal.getTime())),
               Integer.parseInt((new SimpleDateFormat("MM")).format(cal.getTime()))-1,
               Integer.parseInt((new SimpleDateFormat("dd")).format(cal.getTime())));
    model.setSelected(true);
    datePanel = new JDatePanelImpl(model, p);
    toDatePicker = new JDatePickerImpl(datePanel, new LocalDateComponentFormatter());
    
  }
   
  private DaysTableModel fillDays() {
    DaysTableModel model = new DaysTableModel();
    try {
        List<String[]> data = new ArrayList<String[]>();
        
        Calendar fromCal = Calendar.getInstance();  
        fromCal.clear();
        fromCal.set(Calendar.YEAR, fromDatePicker.getModel().getYear());
        fromCal.set(Calendar.MONTH, fromDatePicker.getModel().getMonth());
        fromCal.set(Calendar.DAY_OF_MONTH, fromDatePicker.getModel().getDay());
        Date fromDate = fromCal.getTime();        
        
        Calendar toCal = Calendar.getInstance();  
        toCal.clear();
        toCal.set(Calendar.YEAR, toDatePicker.getModel().getYear());
        toCal.set(Calendar.MONTH, toDatePicker.getModel().getMonth());
        toCal.set(Calendar.DAY_OF_MONTH, toDatePicker.getModel().getDay());
        Date toDate = toCal.getTime();        

        while (fromCal.before(toCal)) {            
            Date date = fromCal.getTime();
            int time = db.getTotalTimeDate(date);
            Double hours = new Double(time / 60.0);
            String itemLine[] = new String[2];
            itemLine[0] = new SimpleDateFormat("yyyy-MM-dd").format(date);
            itemLine[1] = String.format("%.2f", hours);
            data.add(itemLine);
            fromCal.set(Calendar.DAY_OF_MONTH, (fromCal.get(Calendar.DAY_OF_MONTH)+1) );
        }
        model = new DaysTableModel(data);          
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "An error has occured:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    return model;
  }
  
  private TasksTableModel fillTasks() {
    TasksTableModel model = new TasksTableModel();
    this.dayTaskIds = new ArrayList<Integer>();
    if (jTableDays.getSelectedRow()>=0) {
        try {
            String dateSel = (String) jTableDays.getModel().getValueAt(jTableDays.getSelectedRow(), 0);
            List<TaskLog> res = db.getTaskLogs(dateSel, dateSel);
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
                this.dayTaskIds.add(idx, item.getId());              
            }
            model = new TasksTableModel(data);          
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "An error has occured:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDays = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanelFrom = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanelTo = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableTasks = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        jLabel1.setText("From:");

        jTableDays.setModel(fillDays());
        jTableDays.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDaysMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableDays);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

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
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelFrom.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanelFrom.setPreferredSize(new java.awt.Dimension(120, 4));
        jPanelFrom.setLayout(new java.awt.BorderLayout());

        jLabel2.setText("To:");

        jPanelTo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanelTo.setPreferredSize(new java.awt.Dimension(120, 4));
        jPanelTo.setLayout(new java.awt.BorderLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/timemanagementapp/gui/images/icons8-search-32.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTableTasks.setModel(fillTasks());
        jScrollPane2.setViewportView(jTableTasks);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Total Days Detail:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Day Tasks Detail:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1130, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelTo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelTo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelFrom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

  private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    // TODO add your handling code here:
    this.removeAll();
    this.mainFrame.refreshMainPanel();
  }//GEN-LAST:event_jButton3ActionPerformed

    private void jTableDaysMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDaysMouseClicked
        // TODO add your handling code here:
        jTableTasks.setModel(this.fillTasks());
    }//GEN-LAST:event_jTableDaysMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jTableDays.setModel(this.fillDays());
        jTableTasks.setModel(this.fillTasks());
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelFrom;
    private javax.swing.JPanel jPanelTo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableDays;
    private javax.swing.JTable jTableTasks;
    // End of variables declaration//GEN-END:variables
}

class DaysTableModel extends AbstractTableModel {    
    private List<String[]> list;
    private String[] colTitles = new String[] { "Date", "Time" };
    
    public DaysTableModel(List<String[]> dayLogs) {
        this.list = dayLogs;
    }
    
    public DaysTableModel() {
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

class TasksTableModel extends AbstractTableModel {    
    private List<String[]> taskList;
    private String[] colTitles = new String[] { "Description", "Type", "Time", "Notes" };
        
    public TasksTableModel(List<String[]> tasks) {
        this.taskList = tasks;
    }
    
    public TasksTableModel() {
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