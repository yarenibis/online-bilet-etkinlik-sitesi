/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Kullanıcı;
import entity.Yetki;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class KullanıcıDAO extends DBConnection {

    private Connection db;
    private YetkiDAO yetkidao;

    public YetkiDAO getYetkidao() {
        if (this.yetkidao == null) {
            yetkidao = new YetkiDAO();
        }
        return yetkidao;
    }

    public void setYetkidao(YetkiDAO yetkidao) {
        this.yetkidao = yetkidao;
    }


    public boolean kullanıcıGirişi(String mail, String şifre) {
        try {
            Statement st = this.getDb().createStatement();
            String query = "SELECT * FROM kullanıcı WHERE email = '" + mail + "'AND şifre = '" + şifre + "'";

            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                return true; // Kullanıcı bulundu, giriş başarılı
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false; // Kullanıcı bulunamadı veya şifre yanlış
    }

//CREATE işlemi
    public void kullanıcıkayıt(Kullanıcı n) {
        try {
            Statement st = this.getDb().createStatement();
            String query = "insert into kullanıcı(adı,soyadı,email,şifre) values('" + n.getAdı() + "','" + n.getSoyadı() + "','" + n.getEmail() + "','" + n.getŞifre() + "')";
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Kullanıcı findByMail(String mail) {
        Kullanıcı c = null;
        try {
            Connection conn = this.connect();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM kullanıcı WHERE email=?");
            ps.setString(1, mail);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c = new Kullanıcı(rs.getInt("kullanıcı_id"), rs.getString("adı"), rs.getString("soyadı"), rs.getString("email"), rs.getString("şifre"));
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return c;
    }

    
    public Kullanıcı findByID(int id) {
        Kullanıcı c = null;
        try {
            String query = "SELECT * FROM kullanıcı WHERE kullanıcı_id=?";
            PreparedStatement preparedStatement = this.connect().prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Yetki y=this.getYetkidao().findByID(rs.getInt("yetki_id"));
                c = new Kullanıcı(rs.getInt("kullanıcı_id"), rs.getString("adı"), rs.getString("soyadı"),rs.getString("email") , rs.getString("şifre"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }
    
    
    public Connection getDb() {
        if (this.db == null) {
            this.db = this.connect();
        }
        return db;
    }
    
    
    
        
    
    public List<Kullanıcı> getList() {
        List<Kullanıcı> list = new ArrayList<>();
        try {
            Statement st = this.connect().createStatement();
            String query = "select * from kullanıcı";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                list.add(new Kullanıcı(rs.getInt("kullanıcı_id"),
                rs.getString("adı"),
                rs.getString("soyadı"),
                rs.getString("email"),
                rs.getString("şifre"),
                this.admingetyetkiler(rs.getInt("kullanıcı_id"))));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    
    
    
    
     
    public List<Yetki> admingetyetkiler(int kullanıcı_id) {
        List<Yetki> yetkilist = new ArrayList();
        try {
            Statement st = this.getDb().createStatement();
            String query = "select * from yetki where yetki_id in(select yetki_id from kullanıcı_yetki where kullanıcı_id="+kullanıcı_id+")";
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
    
    

    
   
    
    public void create(Kullanıcı e){
        try{
           Statement st=this.getDb().createStatement();
           String query="insert into kullanıcı(adı,soyadı,email,şifre) values('"+e.getAdı()+"','"+e.getSoyadı()+"','"+e.getEmail()+"','"+e.getŞifre()+"')";
           st.executeUpdate(query);
           ResultSet rs=st.executeQuery("select max(kullanıcı_id) as mid from kullanıcı");
           rs.next();
           
           int kullanıcı_id=rs.getInt("mid");
           
           for(Yetki k:e.getYetkiler()){
               query="insert into kullanıcı_yetki(kullanıcı_id,yetki_id) values("+kullanıcı_id+","+k.getYetki_id()+")";
               st.executeUpdate(query);
           }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
 
     public void adminupdate(Kullanıcı c){
         try{
           Statement st=this.connect().createStatement();
           String query="update kullanıcı set adı='"+c.getAdı()+"',soyadı='"+c.getSoyadı()+"',email='"+c.getEmail()+"',şifre='"+c.getŞifre()+"' where kullanıcı_id="+c.getKullanıcı_id();
           
           st.executeUpdate(query);
           st.executeUpdate("delete from kullanıcı_yetki where kullanıcı_id= "+c.getKullanıcı_id());
           
         
           
           for(Yetki k:c.getYetkiler()){
               query="insert into kullanıcı_yetki(kullanıcı_id,yetki_id) values("+c.getKullanıcı_id()+","+k.getYetki_id()+")";
               st.executeUpdate(query);
           }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
     
     public void admindelete(Kullanıcı c){
         try{
           Statement st=this.connect().createStatement();
           String query="delete from kullanıcı where kullanıcı_id="+c.getKullanıcı_id();
            st.executeUpdate(query);
           st.executeUpdate("delete from kullanıcı_yetki where kullanıcı_id= "+c.getKullanıcı_id());
           
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
    

}
