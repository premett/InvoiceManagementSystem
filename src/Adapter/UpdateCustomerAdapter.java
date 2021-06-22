/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adapter;
import java.sql.*;
import javax.swing.table.TableModel;

/**
 *
 * @author EREN
 */
public class UpdateCustomerAdapter {
    Connection conn=null;
    public boolean InfoUpdateCustomer;
   
    static String selectedName,selectedSurname,selectedGender,selectedAddress,selectedMail;
    static long selectedId,selectedPhoneNumber;
    static java.sql.Date selectedSqlDate;
    
    public UpdateCustomerAdapter
       (String inputName,String inputSurname,
          String inputGender,String inputAddress, String inputMail,
          long inputId,long inputPhoneNumber, java.sql.Date inputSqlDate)
    
    {
        selectedName = inputName;
        selectedSurname=inputSurname;
        selectedGender=inputGender;
        selectedAddress=inputAddress;
        selectedMail=inputMail;
        selectedId=inputId;
        selectedPhoneNumber=inputPhoneNumber;
        selectedSqlDate=inputSqlDate;
    
    
    }
       
       public void UpdateCustomerSQL() 
       {
          
           conn=DataBaseConnection.DBConnection.connectionDB();
           PreparedStatement pst=null;
           ResultSet rs=null;
           try {
           String sql="UPDATE Customer SET "
                   + "name='"+selectedName+"',"
                   + "surname='"+selectedSurname+"',"
                   + "gender='"+selectedGender+"',"
                   + "dateOfBirth='"+selectedSqlDate+"',"
                   + "phone='"+selectedPhoneNumber+"',"
                   + "address='"+selectedAddress+"',"
                   + "mail_address='"+selectedMail+"'"
                   + "WHERE id='"+selectedId+"' ";
            pst = conn.prepareStatement(sql);
            pst.executeUpdate();
            InfoUpdateCustomer=true;
            
            
           
           }catch(Exception e) 
           {
           e.printStackTrace();
           InfoUpdateCustomer=false;
          
           
           }
           
           
       
       
       
       }
    
}
