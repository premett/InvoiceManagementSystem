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
public class AddCustomerAdapter {
    Connection conn=null;
    public  boolean InfoInsertCustomer;
   static String name,surname,gender,address,mail;
   static long id,phoneNumber;
   static java.sql.Date sqlDate;
    
    public AddCustomerAdapter
        (String inputName,String inputSurname,
          String inputGender,String inputAddress, String inputMail,
          long inputId,long inputPhoneNumber, java.sql.Date inputSqlDate) 
    { 
        
        name=inputName;
        surname=inputSurname;
        gender=inputGender;
        address=inputAddress;
        mail=inputMail;
        id=inputId;
        phoneNumber=inputPhoneNumber;
        sqlDate=inputSqlDate;
    }
    
   
   
    
        public void AddCustomerSQL() {
         
            conn=DataBaseConnection.DBConnection.connectionDB();
         
           try {
            
              String sql ="INSERT INTO Customer (id,name,surname,gender,dateOfBirth,phone,address,mail_address)"
                   + " VALUES ("
                      + "'"+id+"',"
                      + "'"+name+"',"
                      + "'"+surname+"',"
                      + "'"+gender+"',"
                      + "'"+sqlDate+"',"
                      + "'"+phoneNumber+"',"
                      + "'"+address+"',"
                      + "'"+mail+"')";
           PreparedStatement pst = conn.prepareStatement(sql);
           pst.execute();
           InfoInsertCustomer = true;
           
           
           }catch(Exception e) {
                   e.printStackTrace();
                   System.out.println(e.getMessage());
                   InfoInsertCustomer = false;
           
           }
        
          
           
            
   
    }

  
    
}

    

