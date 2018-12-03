/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagementapp;

import java.util.Iterator;
import java.util.List;
import timemanagementapp.DAO.DAO;
import timemanagementapp.model.TaskLog;
import timemanagementapp.model.TaskType;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ricardoc
 */
public class TimeManagementApp {

    /**
     * @param args the command line arguments
     */
    
    private static void test1() {
        try {
            DAO dao = new DAO();           
            /*
            TaskType t = new TaskType();
            t.setType("Type 3");
            t.setNotes("Notes for Type 3.\nThis is for testing purposes only.");
            dao.addTaskType(t);
            List<TaskType> res = dao.getAllTaskTypes();
            for (Iterator it = res.iterator(); it.hasNext(); ) {
                TaskType aTaskType = (TaskType) it.next();
                System.out.println(aTaskType.toString());
            }
*/
            TaskType t = dao.getTaskType(2);
            t = dao.getTaskType("Type 10");
            System.out.println(t.toString());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    private static void test2() {
        try {
            DAO db = new DAO();
/*
            TaskLog t = new TaskLog();
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2018-12-10");         
            t.setDate(new SimpleDateFormat("yyyy-MM-dd").format(date));
            t.setType(2);
            t.setTask("After Birthday task");
            t.setTime(120);
            db.addTaskLog(t);
*/

            db.removeTaskLog(5);

            //List<TaskLog> res = db.getAllTaskLogs();
            List<TaskLog> res = db.getTaskLogs("2018-12-01", "2018-12-30");
            for (Iterator it = res.iterator(); it.hasNext(); ) {
                TaskLog aTaskLog = (TaskLog) it.next();
                System.out.println(aTaskLog.toString());
            }
            
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        test2();
    }
    
}
