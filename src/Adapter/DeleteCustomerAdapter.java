/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adapter;
import java.sql.*;

/**
 *
 * @author EREN
 */
public class DeleteCustomerAdapter {
    static long deleteId;
    public static boolean InfoDeleteCustomer;
    Connection conn;
    
    public DeleteCustomerAdapter(long inputId) {
        deleteId=inputId;
    
    }
    
    
    public void DeleteCustomerSQL() {
        
        conn = DataBaseConnection.DBConnection.connectionDB();
        
        try {
            String sql ="DELETE FROM Customer where id='"+deleteId+"'";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.executeUpdate();
            InfoDeleteCustomer=true;
            
        }
        catch(Exception e) 
        {
        
            e.printStackTrace();
            InfoDeleteCustomer=false;
        }
    
    
    }
    
}
