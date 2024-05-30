/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import java.sql.Connection;
import java.sql.DriverManager;

public abstract class DBConnection {
     public Connection connect(){
         Connection c=null;
         try{
             Class.forName("org.postgresql.Driver");
             c=DriverManager.getConnection("jdbc:postgresql://localhost:5432/onlineticket","postgres","123456");
         }
         catch(Exception ex){
             System.out.println(ex.getMessage());
             
         }
          return c;
     }
}
