/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regrec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.math.BigDecimal; 
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.*;
import java.math.RoundingMode;


/**
 *
 * @author user
 */
public class createTex {
    private String appno = "";
  
    String vname = "";
    String email = "";
    
    String query1 = "SELECT\n" +
"applicationmaster.`Vendor name                        `,\n" +
"applicationmaster.`E-Mail Address                         `\n" +
"FROM\n" +
"applicationmaster\n" +
"where applicationmaster.`Application No.`= ";
  
    
    
    String newApp = "NA";
    String renewApp = "NA";
    String upGradation = "NA";
    String inClusion = "NA";    
String query2 ="SELECT\n" +
"applicationmaster.`New/Renewal`,\n" +
"applicationmaster.Inclusion,\n" +
"applicationmaster.Upgradatio2,\n" +
"applicationmaster.Version\n" +
"FROM\n" +
"applicationmaster\n" +
"where applicationmaster.`Application No.`= " ;
 
String boilc ="NA";
String comid ="NA";
String elecc ="NA";
String epfcd ="NA";
String esicd ="NA";
String gstcd ="NA";
String pancd ="NA";
String tlcd ="NA";



//Establish connection
    String dbURL = "jdbc:mysql://localhost:3306/registration";
    String username ="root";
    String password = "orko";
    Connection dbCon = null;
    Statement stmt = null;
    ResultSet rs = null;
    
   
    
    public void createTexFile(String appno)
    {
        System.out.println("from createTex.java appno :"+appno);
        query1 +=appno+"";
        query2 +=appno+"";
        //System.out.println(query2);
        try {         
            //getting database connection to MySQL server
            dbCon = DriverManager.getConnection(dbURL, username, password);
        }catch(SQLException q)
        {
            System.out.println("Exception while loading driver"+q);
        }
        
       
        
       //Start Name and email 
        
        try{
            stmt = dbCon.prepareStatement(query1);
            rs = stmt.executeQuery(query1);
          //get Name and email address 
          while(rs.next()){
          vname = rs.getString("Vendor name                        ");
          email = rs.getString("E-Mail Address                         ");
          
          }
          
        }catch(SQLException sq){
            System.out.println("Error while fetching data from minutes "+ sq);
        }
        
     vname = vname.trim();
     email = email.trim();
         //    End name *& email 
  
     //Start Renewal, Upgradion,Inclusion, New 
       try{
            
           String n = null;
           String u = null;
           String i = null;
           
           try
           {
                 stmt = dbCon.prepareStatement(query2);
                 rs = stmt.executeQuery(query2);
               int count = 1;
                while(rs.next()){
                    n = rs.getString("New/Renewal");
                    i = rs.getString("Inclusion");
                    u = rs.getString("Upgradatio2");
                  // System.out.println(""+count+n+" "+i+" "+u);
                   if (n.startsWith("N"))
                    {
                        newApp = "New Registration";
                   
                    }
                     if (n.startsWith("R"))
                    {
                        renewApp = "Renewal Application";
                    }
                    if (u.startsWith("X"))
                    {
                        upGradation = "Applied for Upgradation";
                    }

                    if (i.startsWith("X"))
                    {
                        inClusion = "Applied for Inclusion";
                    }
                 
                     i = "";
                     u = "";
                     n = "";
                     count++;
                }
                    
          } catch(NullPointerException nulldata)
                {
                    System.out.println("Caught Null POinter Exception ");
                }
          
          
          
          
        
        }catch(SQLException sq){
            System.out.println("Error while fetching data from query2 "+ sq);
        } 
     System.out.println("name:" + vname+ " email : "+email);
     System.out.println(newApp+renewApp+inClusion+upGradation);  
  
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
       //Create the Tex file
       /*try {

        File statText = new File("d:/Rfxnotes/"+prno+"_minutes.tex");
        
        FileOutputStream is = new FileOutputStream(statText);
        System.out.println("creating  TeX file");
        OutputStreamWriter osw = new OutputStreamWriter(is);    
        Writer w = new BufferedWriter(osw);
        w.write(output);
        w.close();
    } catch (IOException e) {
        System.err.println("Problem writing to the Tex file "+e);
    }
        
        
       try {

        File statText = new File("d:/Rfxnotes/POReleaseComments/"+prno+"_POReleaseComments.rtf");
        
        FileOutputStream is = new FileOutputStream(statText);
        System.out.println("creating  text file");
        OutputStreamWriter osw = new OutputStreamWriter(is);    
        Writer w = new BufferedWriter(osw);
        w.write(textOutput);
        w.close();
    } catch (IOException e) {
        System.err.println("Problem writing to the rtf file "+e);
    }
        
     */  
       
        
    }

    /**
     * @return the appno
     */
        
public String getAppno() {
        return appno;
    }

    /**
     * @param appno the appno to set
     */
    public void setAppno(String appno) {
        this.appno = appno;
    }
    
}
