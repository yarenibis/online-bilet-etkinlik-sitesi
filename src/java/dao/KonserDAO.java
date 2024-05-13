/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Konser;
import entity.Kullanıcı;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class KonserDAO extends DBConnection implements Etkinlik_islem<Konser>{
    private Connection db;

    public Connection getDb() {
        if(this.db==null){
            db=this.connect();
        }
        return db;
    }

    @Override
    public void create(Konser k) {
        try{
            Statement st=this.getDb().createStatement();
            String query="INSERT INTO konser(konser_adı,mekan,tarih,sanatçı) VALUES('"  + k.getAdı() + "','" + k.getMekan() + "','"  + k.getTarih() + "',,'"  + k.getSanatçı() + "')";
            st.executeUpdate(query);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(Konser k) {
        try{
            Statement st=this.getDb().createStatement();
            String query="DELETE FROM konser WHERE konser_id=" + k.getId();
            st.executeUpdate(query);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Konser k) {
        try{
            Statement st=this.getDb().createStatement();
            String query="UPDATE konser SET konser_adı='" + k.getAdı() + "', mekan='" + k.getMekan() + "',  tarih='" + k.getTarih() + "',sanatçı='" + k.getSanatçı() + "' WHERE konser_id=" + k.getId();
            st.executeUpdate(query);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Konser> list() {
        List<Konser> tiyatroList = new ArrayList();
        try {
            Statement st = this.getDb().createStatement();
            String query = "select * from konser";
            ResultSet rs = st.executeQuery(query);
            System.out.println("*************************"+query);
            while (rs.next()) {
                tiyatroList.add(new Konser(
                        rs.getInt("konser_id"),
                        rs.getString("konser_adı"),
                        rs.getString("mekan"),
                        rs.getString("tarih"),
                        rs.getString("sanatçı")
                ));
                
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return tiyatroList;
    }
  
    

    
}

