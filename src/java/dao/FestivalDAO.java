/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Festival;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class FestivalDAO extends DBConnection implements Etkinlik_islem<Festival>{
     private Connection db;
 

     @Override
    public void delete(Festival f) {
        try {
            Statement st = this.connect().createStatement();
            String query = "delete from festival where festival_id=" + f.getFestival_id();
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Festival f) {
        try {
            Statement st = this.connect().createStatement();
            String query = "update festival set festival_adı='" + f.getFestival_adi() + "',mekan='"+f.getMekan()+"', tarih='" + f.getTarih() + "' where festival_id=" + f.getFestival_id();
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void create(Festival f) {
        try {
            Statement st = this.getDb().createStatement();
            String query = "insert into festival( festival_adı,mekan, tarih) values('" + f.getFestival_adi() + "','" + f.getMekan() + "','" + f.getTarih() + "')";
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Festival> list() {
        List<Festival> festivalList = new ArrayList<>();
        try {
            Statement st = this.getDb().createStatement();
            String query = "select * from festival";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                festivalList.add(new Festival(
                        rs.getInt("festival_id"),
                        rs.getString("festival_adı"),
                        rs.getString("mekan"),
                        rs.getString("tarih")
                ));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return festivalList;
        
    }
    
        public Connection getDb() {
        if(this.db==null){
            db=this.connect();
            
        }
        return db;
    }
        
        public Festival findByID(int id){
        Festival c=null;
         try{
           Statement st=this.connect().createStatement();
           String query="select * from festival where festival_id="+id;
           
           ResultSet rs=st.executeQuery(query);
           while(rs.next()){
               c=new Festival(rs.getInt("festival_id"),rs.getString("festival_adı"),rs.getString("mekan"),rs.getString("tarih"));
           
           }
           
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return c;
    }
   
}

