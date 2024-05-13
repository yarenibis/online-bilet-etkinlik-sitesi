/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Tiyatro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

/**
 *
 * @author yaren
 */
public class TiyatroDAO extends DBConnection implements Etkinlik_islem<Tiyatro>{
    private Connection db;
 

    public Connection getDb() {
        if(this.db==null){
            db=this.connect();
            
        }
        return db;
    }
     
    
   public Tiyatro findByID(int id){
        Tiyatro c=null;
         try{
           Statement st=this.connect().createStatement();
           String query="select * from tiyatro where tiyatro_id="+id;
           
           ResultSet rs=st.executeQuery(query);
           while(rs.next()){
               c=new Tiyatro(rs.getInt("id"),rs.getString("oyun_adı"),rs.getString("mekan"),rs.getString("oyuncu"),rs.getString("tarih"));
           }
           
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return c;
    }
    
    @Override
    public void delete(Tiyatro c){
         try{
           Statement st=this.connect().createStatement();
           String query="delete from tiyatro where tiyatro_id="+c.getId();
           
           st.executeUpdate(query);
           
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void update(Tiyatro c){
         try{
           Statement st=this.connect().createStatement();
           String query="update tiyatro set oyun_adı='"+c.getAdı()+"',mekan='"+c.getMekan()+"',oyuncu='"+c.getOyuncu()+"',tarih='"+c.getTarih()+"' where tiyatro_id="+c.getId();
           
           st.executeUpdate(query);
           
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void create(Tiyatro e) {
        try{
           Statement st=this.getDb().createStatement();
           String query="insert into tiyatro(oyun_adı,mekan,oyuncu,tarih) values('"+e.getAdı()+"',,'"+e.getMekan()+"','"+e.getOyuncu()+"','"+e.getTarih()+"')";
           st.executeUpdate(query);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    
    }
    @Override
    public List<Tiyatro> list() {
        List<Tiyatro> tiyatroList = new ArrayList();
        try {
            Statement st = this.getDb().createStatement();
            String query = "select * from tiyatro";
            ResultSet rs = st.executeQuery(query);
            System.out.println("*************************"+query);
            while (rs.next()) {
                tiyatroList.add(new Tiyatro(
                        rs.getInt("tiyatro_id"),
                        rs.getString("oyun_adı"),
                        rs.getString("mekan"),
                        rs.getString("oyuncu"),
                        rs.getString("tarih")
                ));
                
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return tiyatroList;
    }
  
    
}


