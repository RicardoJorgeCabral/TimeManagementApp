/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagementapp.util;

import javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Ricardoc
 */
public class LocalDateComponentFormatter extends JFormattedTextField.AbstractFormatter  {
    private static final long serialVersionUID = 5997312768041129127L;
    
    @Override
    public String valueToString(Object value) throws ParseException {
        Calendar cal = (Calendar) value;
        if (cal == null) {
            return "";
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(cal.getTime());
    }

    @Override
    public Object stringToValue(String text) throws ParseException {
        if (text == null || text.equals("")) {
            return null;
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(text);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
}
