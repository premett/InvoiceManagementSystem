/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBaseConnection;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author EREN
 */
public class DBConnection {
    
    public static Connection connectionDB() {
        
        try {
        
              Class.forName("org.sqlite.JDBC");
              Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\EREN\\Documents\\NetBeansProjects\\FaturaYonetimSistemi\\InvoiceManagementDB.sqlite");
              
              
              return conn;
             
            
            
        }
        catch(Exception ex) {
        
         JOptionPane.showConfirmDialog(null, ex.toString());
         return null;
        
        }
    
    
    }
    
}
