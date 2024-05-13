/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Mekan;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author yaren
 */
public class MekanDAO extends DBConnection{
    private Connection db;
 

    public Connection getDb() {
        if(this.db==null){
            db=this.connect();
            
        }
        return db;
    }
     
    public List<Mekan> getMekanList() {
        List<Mekan> etkinlikList = new ArrayList();
        try {
            Statement st = this.getDb().createStatement();
            String query = "select * from mekan";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                etkinlikList.add(new Mekan(
                        rs.getInt("mekan_id"),
                        rs.getString("adı")    ,
                        rs.getString("adres"),
                        rs.getInt("kapasite")
                ));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return etkinlikList;
    }
    
    
    //CREATE İŞLEMİ
    public void MekanOluştur(Mekan e){
        try{
           Statement st=this.getDb().createStatement();
           String query="insert into mekan(adı,adres,kapasite) values('"+e.getMekan_adi()+"','"+e.getAdres()+"','"+e.getKapasite()+"')";
           st.executeUpdate(query);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
    public Mekan findByID(int id){
        Mekan c=null;
         try{
           Statement st=this.connect().createStatement();
           String query="select * from mekan where mekan_id="+id;
           ResultSet rs=st.executeQuery(query);
           
           while(rs.next()){
               c=new Mekan(rs.getInt("mekan_id"),rs.getString("adı"),rs.getString("adres"),rs.getInt("kapasite"));
           }
          
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return c;
    }
    
 
    public void delete(Mekan c){
         try{
           Statement st=this.connect().createStatement();
           String query="delete from mekan where mekan_id="+c.getMekan_id();
           
           st.executeUpdate(query);
           
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public void update(Mekan c){
         try{
           Statement st=this.connect().createStatement();
           String query="update mekan set adı='"+c.getMekan_adi()+"',adres='"+c.getAdres()+"',kapasite='"+c.getKapasite()+"' where mekan_id="+c.getMekan_id();
           
           st.executeUpdate(query);
           
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
