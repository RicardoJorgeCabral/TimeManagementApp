/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagementapp.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 *
 * @author Ricardoc
 */
public class DBLogs {
    
    /*
    XML DOC STRUCT
    
    <backups>
        <backup>
            <filename>...</filename>
            <datetime>yyyy-MM-dd hh:mm:ss</datetime>
        </backup>
    
    </backups>
    
    */
    
    public static final String LOGFILE = "logs/dblog.xml";
    private Document data;
    
    public List<String[]> getLogs() throws Exception {
        List<String[]> res = new ArrayList<String[]>();
        NodeList nList = this.data.getElementsByTagName("backup");
        for (int i=0; i<nList.getLength(); i++) {            
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               String[] bckDetail = new String[2];
               Element eElement = (Element) nNode;
               bckDetail[0] = eElement.getElementsByTagName("filename").item(0).getTextContent();
               bckDetail[1] = eElement.getElementsByTagName("datetime").item(0).getTextContent();
               res.add(bckDetail);
            }
        }
        return res;
    }
    
    public void addToLog(String fileName) throws Exception {
        Element rootElement = this.data.getDocumentElement();
        Element backupElem = this.data.createElement("backup");        
        Element fileNameElem = this.data.createElement("filename");
        fileNameElem.appendChild(this.data.createTextNode(fileName));
        Element datetimeElem = this.data.createElement("datetime");
        datetimeElem.appendChild(this.data.createTextNode(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));        
        backupElem.appendChild(fileNameElem);
        backupElem.appendChild(datetimeElem);
        rootElement.appendChild(backupElem); 
        this.saveToFile();
    }
    
    private void saveToFile() throws Exception {                
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(this.data);
        StreamResult result = new StreamResult(new File(DBLogs.LOGFILE));
        transformer.transform(source, result);
    }
       
    
    private static void createXMLLogFile() throws Exception {
        File f = new File(DBLogs.LOGFILE);
        if (f.exists()) f.delete();        
        f.getParentFile().mkdirs(); 
        f.createNewFile();
        
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();
        
        Element rootElement = doc.createElement("backups");
        doc.appendChild(rootElement);
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(f);
        transformer.transform(source, result);
    }
    
    private static boolean logFileExists() throws Exception {
        File f = new File(DBLogs.LOGFILE);
        return f.exists();
    }    
    
    private void parseXMLData() throws Exception {
        File inputFile = new File(DBLogs.LOGFILE);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();        
        this.data = doc;
    }
    
    public DBLogs() throws Exception {
        if (!DBLogs.logFileExists()) DBLogs.createXMLLogFile();
        this.parseXMLData();        
    }
    
}
