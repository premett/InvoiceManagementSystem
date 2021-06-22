/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adapter;
import static Adapter.DeleteCustomerAdapter.deleteId;
import DataBaseConnection.DBConnection;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


 

import java.io.FileOutputStream;


import java.sql.*;


 

/**
 *
 * @author VEDAT
 */
public class InvoiceAdapter {
     Connection conn=null;
     boolean AddInvoiceInfo;
    
     
     public boolean AddInvoice(
             long inputCustomerId,String inputCustomerName,String inputCustomerSurname,
             long inputCustomerPhone, String inputCustomerMail,String inputCustomerAddress,String inputProductName1,
             double inputInvoiceProduct1,double inputInvoiceAmount1,double inputInvoiceSum1,String inputProductName2,
             double inputInvoiceProduct2,double inputInvoiceAmount2,double inputInvoiceSum2,String inputProductName3,
             double inputInvoiceProduct3,double inputInvoiceAmount3,double inputInvoiceSum3,
             double inputInvoiceSumTax,double inputInvoiceSumPrice,Date inputDate
     ) {
     
     
      try {
                Connection conn = DBConnection.connectionDB();
                String sql = "INSERT INTO Invoice (userid,username,usersurname,userphone,usermail,useraddress,productName1,productPrice1,productAmount1,totalProduct1,productName2,productPrice2,productAmount2,totalProduct2,productName3,productPrice3,productAmount3,totalProduct3,totalTax,totalPayment,createdDate)"
                        + "VALUES ("
                        + "'"+inputCustomerId+"',"
                        + "'"+inputCustomerName+"',"
                        + "'"+inputCustomerSurname+"',"
                        + "'"+inputCustomerPhone+"',"
                        + "'"+inputCustomerMail+"',"
                        + "'"+inputCustomerAddress+"',"
                        + "'"+inputProductName1+"',"
                        + "'"+inputInvoiceProduct1+"',"
                        + "'"+inputInvoiceAmount1+"',"
                        + "'"+inputInvoiceSum1+"',"
                        + "'"+inputProductName2+"',"
                        + "'"+inputInvoiceProduct2+"',"
                        + "'"+inputInvoiceAmount2+"',"
                        + "'"+inputInvoiceSum2+"',"
                        + "'"+inputProductName3+"',"
                        + "'"+inputInvoiceProduct3+"',"
                        + "'"+inputInvoiceAmount3+"',"
                        + "'"+inputInvoiceSum3+"',"
                        + "'"+inputInvoiceSumTax+"',"
                        + "'"+inputInvoiceSumPrice+"',"
                        + "'"+inputDate+"')";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
               
               
               
                conn.close();
                 return true;
                
                
            }
            catch(Exception e) { 
                e.printStackTrace(); 
                System.out.println("Error"); 
                return false;}
        }
     
     
     public boolean DeleteInvoice(int inputInvoiceId) {
         
          conn = DataBaseConnection.DBConnection.connectionDB();
        
        try {
            String sql ="DELETE FROM Invoice where id='"+inputInvoiceId+"'";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.executeUpdate();
            return true;
            
        }
        catch(Exception e) 
        {
        
            e.printStackTrace();
            return false;
            
        }
     
     }
     
     public boolean CreateInvoicePDF(int inputInvoiceId,String pdfPath)  {
       
        
        
        
        
         try {
             conn = DataBaseConnection.DBConnection.connectionDB();
             String sql = "SELECT * FROM Invoice WHERE id='"+inputInvoiceId+"'";
             PreparedStatement pst= conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery();
             if(rs.next()) {
              int invoiceId = rs.getInt("id");
              long customerId = rs.getLong("userid");
              String customerName = rs.getString("username");
              String customerSurname = rs.getString("usersurname");
              String customerAddress = rs.getString("useraddress");
              long customerPhone=rs.getLong("userphone");
              String invoiceDate=rs.getString("createdDate");
              
              String productName1=rs.getString("productName1");
              System.out.println(productName1);
              double productPrice1=rs.getDouble("productPrice1");
              int productAmount1=rs.getInt("productAmount1");
              double totalProduct1=rs.getDouble("totalProduct1");
              
              String productName2=rs.getString("productName2");
              double productPrice2=rs.getDouble("productPrice2");
              int productAmount2=rs.getInt("productAmount2");
              double totalProduct2=rs.getDouble("totalProduct2");
              
              String productName3=rs.getString("productName3");
              double productPrice3=rs.getDouble("productPrice3");
              int productAmount3=rs.getInt("productAmount3");
              double totalProduct3=rs.getDouble("totalProduct3");
              
              double totalTax=rs.getDouble("totalTax");
              double totalPayment=rs.getDouble("totalPayment");
              rs.close();
               
           
            
           
              String selectedPath=pdfPath+"\\"+customerId+"__"+invoiceId+"_Fatura.pdf"; 
              
              System.out.println(selectedPath);
              Document document = new Document(PageSize.A4,0,0,0,0);
              PdfWriter.getInstance(document, new FileOutputStream(selectedPath));
              
             
              document.open();
              
              document.add(new Paragraph(" "));
             
//////////////Table 1
              PdfPTable table1 = new PdfPTable(10);
              
             
              Font f = new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);

//Row 1              
              PdfPCell cel1= new PdfPCell(new Phrase("Fatura Yönetim Sistemi",f));
              cel1.setColspan(10);
              cel1.setHorizontalAlignment(Element.ALIGN_CENTER);
              cel1.setPadding(50f);
              cel1.setBackgroundColor(new BaseColor(28,30,41));
              
               
              table1.addCell(cel1);
              
              
////////
              
//////////////Row2
      
              String space="                                                                                         ";
              PdfPCell cell2= new PdfPCell(new Phrase("Müsteri Bilgileri"+space+"Fatura Bilgileri"));
              cell2.setColspan(10);
              //customerCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
              cell2.setPadding(10f);
              table1.addCell(cell2);
             
              
////////
              
//////////////Row3
      
              
              PdfPCell cell3= new PdfPCell(new Phrase(
                      "\n\nAd Soyad : "+customerName +" "+customerSurname+"\n\nAdres : "+customerAddress+"\n\nTelefon :+90 0"+customerPhone));
              cell3.setColspan(5);
              cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
              cell3.setPadding(8f);
              table1.addCell(cell3);
              
              PdfPCell cell4= new PdfPCell(new Phrase("\n\nFatura No : "+invoiceId+"\n\nFatura Tarihi : "+invoiceDate));
              cell4.setColspan(5);
              cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
              cell4.setPadding(8f);
              table1.addCell(cell4);
             
              
////////


//////////////Row4

 
      
              
              PdfPCell infocell1= new PdfPCell(new Phrase(
                      " Ürün Adı"));
              infocell1.setColspan(4);
              infocell1.setHorizontalAlignment(Element.ALIGN_CENTER);
              infocell1.setPadding(10f);
              table1.addCell(infocell1);
              
               PdfPCell infocell2= new PdfPCell(new Phrase(
                      " Fiyat"));
              infocell2.setColspan(2);
              infocell2.setHorizontalAlignment(Element.ALIGN_CENTER);
              infocell2.setPadding(10f);
              table1.addCell(infocell2);
              
               PdfPCell infocell3= new PdfPCell(new Phrase(
                      "Adet"));
              infocell3.setColspan(2);
              infocell3.setHorizontalAlignment(Element.ALIGN_CENTER);
              infocell3.setPadding(10f);
              table1.addCell(infocell3);
              
               PdfPCell infocell4= new PdfPCell(new Phrase(
                      "Tutar"));
              infocell4.setColspan(2);
              infocell4.setHorizontalAlignment(Element.ALIGN_CENTER);
              infocell4.setPadding(10f);
              table1.addCell(infocell4);
              
              
             
              
////////








//////////////Row5
      
              
              PdfPCell cell5= new PdfPCell(new Phrase(
                      "\n\n"+productName1));
              cell5.setColspan(4);
              cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
              cell5.setPadding(2f);
              table1.addCell(cell5);
              
               PdfPCell cell6= new PdfPCell(new Phrase(
                      "\n\n"+String.format("%,.2f", productPrice1)));
              cell6.setColspan(2);
              cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
              cell6.setPadding(2f);
              table1.addCell(cell6);
              
               PdfPCell cell7= new PdfPCell(new Phrase(
                      "\n\n"+productAmount1));
              cell7.setColspan(2);
              cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
              cell7.setPadding(2f);
              table1.addCell(cell7);
              
               PdfPCell cell8= new PdfPCell(new Phrase(
                      "\n\n"+String.format("%,.2f", totalProduct1)));
              cell8.setColspan(2);
              cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
              cell8.setPadding(2f);
              table1.addCell(cell8);
              
              
             
              
////////

//////////////Row6
      
              if(!productName2.equals("-")) {
              PdfPCell cell9= new PdfPCell(new Phrase(
                      "\n\n"+productName2));
              cell9.setColspan(4);
              cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
              cell9.setPadding(2f);
              table1.addCell(cell9);
              
               PdfPCell cell10= new PdfPCell(new Phrase(
                      "\n\n"+String.format("%,.2f", productPrice2)));
              cell10.setColspan(2);
              cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
              cell10.setPadding(2f);
              table1.addCell(cell10);
              
               PdfPCell cell11= new PdfPCell(new Phrase(
                      "\n\n"+productAmount2));
              cell11.setColspan(2);
              cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
              cell11.setPadding(2f);
              table1.addCell(cell11);
              
               PdfPCell cell12= new PdfPCell(new Phrase(
                        "\n\n"+String.format("%,.2f", totalProduct2)));
              cell12.setColspan(2);
              cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
              cell12.setPadding(2f);
              table1.addCell(cell12);
              
              
             
              }
////////
//////////////Row7
      
              if(!productName3.equals("-")) {
              PdfPCell cell13= new PdfPCell(new Phrase(
                      "\n\n"+productName3));
              cell13.setColspan(4);
              cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
              cell13.setPadding(2f);
              table1.addCell(cell13);
              
               PdfPCell cell14= new PdfPCell(new Phrase(
                      "\n\n"+String.format("%,.2f", productPrice3)));
              cell14.setColspan(2);
              cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
              cell14.setPadding(2f);
              table1.addCell(cell14);
              
               PdfPCell cell15= new PdfPCell(new Phrase(
                      "\n\n"+productAmount3));
              cell15.setColspan(2);
              cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
              cell15.setPadding(2f);
              table1.addCell(cell15);
              
               PdfPCell cell16= new PdfPCell(new Phrase(
                        "\n\n"+String.format("%,.2f", totalProduct3)));
              cell16.setColspan(2);
              cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
              cell16.setPadding(2f);
              table1.addCell(cell16);
              
              
             
              }
////////
//////////////Row8
      
               
              PdfPCell cell17= new PdfPCell(new Phrase(
                      "\nKDV  "));
              cell17.setColspan(8);
              cell17.setHorizontalAlignment(Element.ALIGN_RIGHT);
              cell17.setPadding(1f);
              table1.addCell(cell17);
              
               PdfPCell cell18= new PdfPCell(new Phrase(
                      "\n\n"+String.format("%,.2f", totalTax)));
              cell18.setColspan(2);
              cell18.setHorizontalAlignment(Element.ALIGN_CENTER);
              cell18.setPadding(1f);
              table1.addCell(cell18);
              
                 PdfPCell cell19= new PdfPCell(new Phrase(
                      "\nTOPLAM  "));
              cell19.setColspan(8);
              cell19.setHorizontalAlignment(Element.ALIGN_RIGHT);
              cell19.setPadding(1f);
              table1.addCell(cell19);
              
               PdfPCell cell20= new PdfPCell(new Phrase(
                        "\n\n"+String.format("%,.2f", totalPayment)));
              cell20.setColspan(2);
              cell20.setHorizontalAlignment(Element.ALIGN_CENTER);
              cell20.setPadding(1f);
              table1.addCell(cell20);
                
             
             
////////
                
              
              document.add(table1);
              
              document.close();
            
              
              
             }  
               return true;
          
         }
         catch(Exception e) 
         {e.printStackTrace(); 
         
         return false;}
         
         
         
        
     }
        
     
  }
  
    

