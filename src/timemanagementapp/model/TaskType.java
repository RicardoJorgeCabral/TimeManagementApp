/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagementapp.model;

import java.util.Objects;

/**
 *
 * @author Ricardoc
 */
public class TaskType {
    
    private int id = 0;
    private String type = new String();
    private String notes = new String();

    public TaskType() {
    }

    public TaskType(int id, String type, String notes)  throws Exception {
        if (id<1)
            throw new Exception("Invalid Id.");
        if ( (type==null) || (type.length()<1))
            throw new Exception("Type description must be filled.");
        this.id = id;
        this.type = type;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws Exception {
        if (id<1)
            throw new Exception("Invalid Id");
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) throws Exception {
        if ( (type==null) || (type.length()<1))
            throw new Exception("Type description must be filled.");
        this.type = type;
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
        final TaskType other = (TaskType) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.notes, other.notes)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TaskType{" + "id=" + id + ", type=" + type + ", notes=" + notes + '}';
    }
        
}
