/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagementapp.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import timemanagementapp.model.TaskLog;
import timemanagementapp.model.TaskType;

/**
 *
 * @author Ricardoc
 */
public class DAO {
    private Connection connect() throws Exception {
        String url = "jdbc:sqlite:times.db";
        Connection conn = null;        
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection(url);
        return conn;
    }
    
    private List<TaskLog> getTaskLogsFromSqlQuery(String sql) throws Exception {
        List<TaskLog> res = new ArrayList<TaskLog>();
        
        Connection conn = this.connect();
        Statement stmt  = conn.createStatement();
        ResultSet rs    = stmt.executeQuery(sql);            
        while (rs.next()) {
            TaskLog t = new TaskLog();
            t.setId(rs.getInt("id"));
            t.setDate(rs.getString("date"));
            t.setType(rs.getInt("type"));
            t.setTask(rs.getString("task"));
            t.setTime(rs.getInt("time"));
            t.setNotes(rs.getString("notes"));
            res.add(t);
        }
        conn.close();
        return res;
    }
    
    private List<TaskLog> getTaskLogsFromPrepSqlQuery(String sql, List values) throws Exception {
        List<TaskLog> res = new ArrayList<TaskLog>();
        
        Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        int index=1;
        for (Iterator it = values.iterator() ; it.hasNext() ; index++) {            
            Object value = it.next();
            if (value instanceof Integer) {
                pstmt.setInt(index, ((Integer) value).intValue());
            }
            else {
                pstmt.setString(index, (String) value);
            }
        }
        ResultSet rs    = pstmt.executeQuery();            
        while (rs.next()) {
            TaskLog t = new TaskLog();
            t.setId(rs.getInt("id"));
            t.setDate(rs.getString("date"));
            t.setType(rs.getInt("type"));
            t.setTask(rs.getString("task"));
            t.setTime(rs.getInt("time"));
            t.setNotes(rs.getString("notes"));
            res.add(t);
        }
        conn.close();
        return res;
    }
    
    
    public List<TaskLog> getAllTaskLogs() throws Exception {
        return this.getTaskLogsFromSqlQuery("SELECT * FROM TaskLog ORDER BY id");        
    }
    
    public List<TaskLog> getTaskLogs(Date di, Date df) throws Exception {
        return this.getTaskLogs(new SimpleDateFormat("yyyy-MM-dd").format(di), new SimpleDateFormat("yyyy-MM-dd").format(df));
    }
    
    public List<TaskLog> getTaskLogs(String di, String df) throws Exception {
        String sql = "SELECT * FROM TaskLog WHERE date BETWEEN ? AND ? ORDER BY id";
        List values = new ArrayList();
        values.add(di);
        values.add(df);
        return this.getTaskLogsFromPrepSqlQuery(sql, values);        
    }
    
    public TaskLog getTaskLog(int id) throws Exception {
        String sql = "SELECT * FROM TaskLog WHERE id=?";
        TaskLog res = new TaskLog();
        
        Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();        
        if (rs.next()) {
            res.setId(rs.getInt("id"));
            res.setDate(rs.getString("date"));
            res.setType(rs.getInt("type"));
            res.setTask(rs.getString("task"));
            res.setTime(rs.getInt("time"));
            res.setNotes(rs.getString("notes"));
        }
        conn.close();
        return res;
    }
    
    public int addTaskLog(TaskLog tLog) throws Exception {
        int id = -1;
        String sql;
        boolean exists = false;
        if (tLog.getId()>0) {
            TaskLog existing = this.getTaskLog(tLog.getId());
            if (existing.getId()>0) {
              exists = true;
              id = existing.getId();
            } 
        }
        if (exists)
            sql = "UPDATE TaskLog SET date=?,type=?,task=?,time=?,notes=? WHERE id=?";
        else
            sql = "INSERT INTO TaskLog (date,type,task,time,notes) VALUES (?,?,?,?,?)";
        Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, tLog.getDate());
        pstmt.setInt(2, tLog.getType().getId());
        pstmt.setString(3, tLog.getTask());
        pstmt.setInt(4, tLog.getTime());
        pstmt.setString(5, tLog.getNotes());
        if (exists) {
            pstmt.setInt(6, tLog.getId());
        }
        pstmt.executeUpdate();
        if (!exists) {
          sql = "SELECT last_insert_rowid()";
          Statement stmt  = conn.createStatement();
          ResultSet rs    = stmt.executeQuery(sql); 
          if (rs.next()) {
              id = rs.getInt(1);
          }
        }
        conn.close();
        return id;
    }
        
    public void removeTaskLog(int tLogId) throws Exception {
        String sql = "DELETE FROM TaskLog WHERE id=?";
        Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, tLogId);
        pstmt.executeUpdate();
        conn.close();
    }
    
    public List<TaskType> getAllTaskTypes() throws Exception {
        String sql = "SELECT * FROM TaskType ORDER BY id";
        List<TaskType> res = new ArrayList<TaskType>();
        
        Connection conn = this.connect();
        Statement stmt  = conn.createStatement();
        ResultSet rs    = stmt.executeQuery(sql);            
        while (rs.next()) {
            TaskType t = new TaskType();
            t.setId(rs.getInt("id"));
            t.setType(rs.getString("type"));
            t.setNotes(rs.getString(("notes")));
            res.add(t);            
        }
        conn.close();
        return res;
    }
    
    public TaskType getTaskType(int id) throws Exception {
        String sql = "SELECT * FROM TaskType WHERE id=?";
        TaskType res = new TaskType();
        
        Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();        
        if (rs.next()) {
            res.setId(rs.getInt("id"));
            res.setType(rs.getString("type"));
            res.setNotes(rs.getString("notes"));
        }              
        conn.close();
        return res;
    }
    
    public TaskType getTaskType(String taskType) throws Exception {
        String sql = "SELECT * FROM TaskType WHERE type=?";
        TaskType res = new TaskType();
        
        Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, taskType);
        ResultSet rs = pstmt.executeQuery();        
        if (rs.next()) {
            res.setId(rs.getInt("id"));
            res.setType(rs.getString("type"));
            res.setNotes(rs.getString("notes"));
        }            
        conn.close();
        return res;
    }
    
    public void addTaskType(TaskType t) throws Exception {
        String sql;
        boolean exists = false;
        if (t.getId()>0) {
            TaskType existing = this.getTaskType(t.getId());
            if (existing.getId()>0) exists = true; 
        }
        if (exists)
            sql = "UPDATE TaskType SET type=?,notes=? WHERE id=?";
        else
            sql = "INSERT INTO TaskType (type,notes) VALUES (?,?)";
        Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, t.getType());
        pstmt.setString(2, t.getNotes());
        if (exists) {
            pstmt.setInt(3, t.getId());
        }
        pstmt.executeUpdate();
        conn.close();
    }
    
    public void removeTaskType(int tTypeId) throws Exception {
        String sql = "DELETE FROM TaskType WHERE id=?";
        Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, tTypeId);
        pstmt.executeUpdate();
        conn.close();
    }
    
    public String showAllTables() throws Exception {
        String sql = "SELECT name FROM sqlite_master WHERE type='table'";
        String res = new String();
        Connection conn = this.connect();
        Statement stmt  = conn.createStatement();
        ResultSet rs    = stmt.executeQuery(sql);            
        while (rs.next()) {
            res += rs.toString() + "\n";
        }
        conn.close();
        return res;
    }
    
    public int getTotalHoursDate(Date date) throws Exception {        
        int total = 0;
        String sql = "SELECT SUM(time) FROM TaskLog WHERE date=?";
        Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, new SimpleDateFormat("yyyy-MM-dd").format(date));
        ResultSet rs    = pstmt.executeQuery(sql);            
        if (rs.next()) total = rs.getInt(1);
        return total;
    }
}
