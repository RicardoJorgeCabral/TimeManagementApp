/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagementapp.model;

import java.text.DecimalFormat;
import java.util.Objects;
import timemanagementapp.DAO.DAO;

/**
 *
 * @author Ricardoc
 */
public class TaskLog {
    private int id;
    private String date;
    private TaskType type;
    private String task;
    private int time; //  in minutes
    private String notes;

    public TaskLog(int id, String date, TaskType type, String task, int time, String notes) throws Exception {
        if (id<1)
            throw new Exception("Invalid Id.");
        if ( (task==null) || (task.length()<1)) 
            throw new Exception("Task description must be filled.");
        this.id = id;
        this.date = date;
        this.type = type;
        this.task = task;
        this.time = time;
        this.notes = notes;
    }

    public TaskLog() {
        this.id = 0;
        this.date = null;
        this.type = null;
        this.task = null;
        this.time = 0;
        this.notes = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws Exception {
        if (id<1)
            throw new Exception("Invalid Id");
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }
    
    public void setType(int typeId) throws Exception {
        DAO dao = new DAO();
        this.type = dao.getTaskType(typeId);
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) throws Exception {
        if ( (task==null) || (task.length()<1)) 
            throw new Exception("Task description must be filled.");
        this.task = task;
    }

    public int getTime() {
        return time;
    }

    public String getHoursTime() {
        int minutes = this.time;
        double hours = new Double(minutes) / 60.0;
        return new DecimalFormat("#0.00").format(hours);
    }
    
    public void setTime(int time) {
        this.time = time;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TaskLog other = (TaskLog) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.time != other.time) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.task, other.task)) {
            return false;
        }
        if (!Objects.equals(this.notes, other.notes)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TaskLog{" + "id=" + id + ", date=" + date + ", type=" + type + ", task=" + task + ", time=" + time + ", notes=" + notes + '}';
    }
        
}
