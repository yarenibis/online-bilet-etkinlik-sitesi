/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Kullanıcı;
import entity.Yetki;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        String query = "SELECT * FROM kullanıcı WHERE email = ? AND şifre = ?";

        try (PreparedStatement pst = this.getConnection().prepareStatement(query)) {
            pst.setString(1, mail);
            pst.setString(2, şifre); // Hash the password before comparing

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    System.out.println("**************************************bulundu");
                    return true; // Kullanıcı bulundu, giriş başarılı
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         System.out.println("******************************bulunamadı");
        return false; // Kullanıcı bulunamadı veya şifre yanlış
    }

//    private String hashPassword(String password) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            byte[] hash = md.digest(password.getBytes());
//            StringBuilder hexString = new StringBuilder(2 * hash.length);
//            for (byte b : hash) {
//                String hex = Integer.toHexString(0xff & b);
//                if (hex.length() == 1) {
//                    hexString.append('0');
//                }
//                hexString.append(hex);
//            }
//            return hexString.toString();
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    
    

//    public boolean kullanıcıGirişi(String mail, String şifre) {
//        String query = "SELECT * FROM kullanıcı WHERE email = ? AND şifre = ?";
//
//    try (PreparedStatement pst = this.getDb().prepareStatement(query)) {
//        pst.setString(1, mail);
//        pst.setString(2, şifre);
//
//        try (ResultSet rs = pst.executeQuery()) {
//            if (rs.next()) {
//                return true; // Kullanıcı bulundu, giriş başarılı
//            }
//        }
//
//    } catch (SQLException ex) {
//        System.out.println(ex.getMessage());
//    }
//    return false; // Kullanıcı bulunamadı veya şifre yanlış
//    }
//    
    
//    private String hashPassword(String password) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            byte[] hash = md.digest(password.getBytes());
//            StringBuilder hexString = new StringBuilder(2 * hash.length);
//            for (byte b : hash) {
//                String hex = Integer.toHexString(0xff & b);
//                if (hex.length() == 1) {
//                    hexString.append('0');
//                }
//                hexString.append(hex);
//            }
//            return hexString.toString();
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//    }

    
    
    
//CREATE işlemi
    public void kullanıcıkayıt(Kullanıcı n) {
         String query = "INSERT INTO kullanıcı(adı, soyadı, email, şifre) VALUES (?, ?, ?, ?)";

    try (PreparedStatement pst = this.getConnection().prepareStatement(query)) {
        pst.setString(1, n.getAdı());
        pst.setString(2, n.getSoyadı());
        pst.setString(3, n.getEmail());
        pst.setString(4, n.getŞifre());
        pst.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    }

    public Kullanıcı findByMail(String mail) {
        Kullanıcı c = null;
        try {
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM kullanıcı WHERE email=?");
            ps.setString(1, mail);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c = new Kullanıcı(rs.getInt("kullanıcı_id"), rs.getString("adı"), rs.getString("soyadı"), rs.getString("email"), rs.getString("şifre"));
            }

         
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return c;
    }

    
    public Kullanıcı findByID(int id) {
        Kullanıcı c = null;
        try {
            String query = "SELECT * FROM kullanıcı WHERE kullanıcı_id=?";
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(query);
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
    
    
  
    
    
    
        
    public List<Kullanıcı> getList() {
    List<Kullanıcı> list = new ArrayList<>();
    String query = "SELECT * FROM kullanıcı";
    try (PreparedStatement st = this.getConnection().prepareStatement(query);
         ResultSet rs = st.executeQuery()) {

        while (rs.next()) {
            list.add(new Kullanıcı(
                    rs.getInt("kullanıcı_id"),
                    rs.getString("adı"),
                    rs.getString("soyadı"),
                    rs.getString("email"),
                    rs.getString("şifre"),
                    this.admingetyetkiler(rs.getInt("kullanıcı_id"))
            ));
        }
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
    return list;
}

    
    
    
    
    
     
    public List<Yetki> admingetyetkiler(int kullanıcı_id) {
    List<Yetki> yetkilist = new ArrayList<>();
    String query = "SELECT * FROM yetki WHERE yetki_id IN (SELECT yetki_id FROM kullanıcı_yetki WHERE kullanıcı_id = ?)";
    try (PreparedStatement st = this.getConnection().prepareStatement(query)) {
        st.setInt(1, kullanıcı_id);
        try (ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                yetkilist.add(new Yetki(
                        rs.getInt("yetki_id"),
                        rs.getString("yetki_adı")
                ));
            }
        }
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
    return yetkilist;
}

    
    

    
   
    
    public void create(Kullanıcı e){
        try{
           Statement st=this.getConnection().createStatement();
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
    
    
 
     public void adminupdate(Kullanıcı n){
         try{
           Statement st=this.getConnection().createStatement();
           String query="update kullanıcı set adı='"+n.getAdı()+"',soyadı='"+n.getSoyadı()+"',email='"+n.getEmail()+"',şifre='"+n.getŞifre()+"' where kullanıcı_id="+n.getKullanıcı_id();
           
           st.executeUpdate(query);
           st.executeUpdate("delete from kullanıcı_yetki where kullanıcı_id= "+n.getKullanıcı_id());
           
         
           
           for(Yetki k:n.getYetkiler()){
               query="insert into kullanıcı_yetki(kullanıcı_id,yetki_id) values("+n.getKullanıcı_id()+","+k.getYetki_id()+")";
               st.executeUpdate(query);
           }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
     
     public void admindelete(Kullanıcı c){
         try{
           Statement st=this.getConnection().createStatement();
           String query="delete from kullanıcı where kullanıcı_id="+c.getKullanıcı_id();
            st.executeUpdate(query);
           st.executeUpdate("delete from kullanıcı_yetki where kullanıcı_id= "+c.getKullanıcı_id());
           
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
    

}
