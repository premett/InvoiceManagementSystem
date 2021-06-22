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
public class ProductAdapter {
    Connection conn=null;
    
   
    
    public  boolean AddProduct(String inputProductName ,int inputCategoryID , double inputPrice ,double inputProductTax,int inputProductStock,String image)  
    {
        try {
            conn=DataBaseConnection.DBConnection.connectionDB();
            String sql = "INSERT INTO Product (name,category_id,price,tax,stock,image) VALUES("
                    + "'"+inputProductName+"',"
                    + "'"+inputCategoryID+"',"
                    + "'"+inputPrice+"',"
                    +"'"+inputProductTax+"',"
                    + "'"+inputProductStock+"',"
                    + "'"+image+"')";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
          return  true;
                
               }
        catch(Exception e) 
        {
            e.printStackTrace();
            return  false;
    
        }
    }
    
    public boolean DeleteProduct(int selectedId) 
    {
    
        try {
            conn = DataBaseConnection.DBConnection.connectionDB();
            String sql = "DELETE FROM Product WHERE id ='"+selectedId+"'";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.executeUpdate();
            return  true;
        }
        
        catch(Exception e) 
        {
            e.printStackTrace();
            return false;  
        }
    
    
    }
    
}
