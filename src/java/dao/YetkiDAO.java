/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.List;
import entity.Yetki;
import java.util.ArrayList;
import util.DBConnection;

/**
 *
 * @author yaren
 */
public class YetkiDAO extends DBConnection {

    private Connection db;

    public Yetki findByID(int id){
        Yetki c=null;
         try{
           Statement st=this.connect().createStatement();
           String query="select * from yetki where yetki_id="+id;
           ResultSet rs=st.executeQuery(query);
           
           while(rs.next()){
               c=new Yetki(rs.getInt("yetki_id"),rs.getString("yetki_adı"));
           }
          
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return c;
    }

    public List<Yetki> getYetkiList() {
        List<Yetki> yetkilist = new ArrayList();
        try {
            Statement st = this.getDb().createStatement();
            String query = "select * from yetki";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                yetkilist.add(new Yetki(
                        rs.getInt("yetki_id"),
                        rs.getString("yetki_adı")
                ));

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return yetkilist;
    }

    public void create(Yetki y) {
        try {
            Statement st = this.getDb().createStatement();
            String query = "insert into yetki(yetki_adı) values('" + y.getYetki_adi() + "')";
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Yetki y) {
        try {
            Statement st = this.getDb().createStatement();
            String query = "delete from yetki where yetki_id=" + y.getYetki_id();
            st.executeUpdate(query);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Yetki b) {
        try {
            Statement st = this.getDb().createStatement();
            String query = "update yetki set yetki_adı='" + b.getYetki_adi() + "' where yetki_id=" + b.getYetki_id();
            st.executeUpdate(query);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private Connection getDb() {
        if (this.db == null) {
            this.db = this.connect();
        }
        return db;
    }
}
