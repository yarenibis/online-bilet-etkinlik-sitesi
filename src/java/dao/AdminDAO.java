/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Admin;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

/**
 *
 * @author yaren
 */
public class AdminDAO extends DBConnection {

    private Connection db;
  

    
     public boolean adminGirişi(String mail, String şifre) {
        String query = "SELECT * FROM padmin WHERE email = ? AND şifre = ?";

        try (PreparedStatement pst = this.getConnection().prepareStatement(query)) {
            pst.setString(1, mail);
            pst.setString(2, şifre); // Hash the password before comparing

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    System.out.println("**************************************bulundu");
                    return true; // admin bulundu, giriş başarılı
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         System.out.println("******************************bulunamadı");
        return false; // Admin bulunamadı veya şifre yanlış
    }


    
    public List<Admin> getList() {
    List<Admin> list = new ArrayList<>();
    String query = "SELECT * FROM padmin";
    try (PreparedStatement st = this.getConnection().prepareStatement(query);
         ResultSet rs = st.executeQuery()) {

        while (rs.next()) {
            list.add(new Admin(
                    rs.getInt("id"),
                    rs.getString("adı"),
                    rs.getString("soyadı"),
                    rs.getString("email"),
                    rs.getString("şifre")
            ));
        }
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
    return list;
}
    
    
     public Admin findByMail(String mail) {
        Admin c = null;
        try {
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM padmin WHERE email=?");
            ps.setString(1, mail);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c = new Admin(rs.getInt("id"), rs.getString("adı"), rs.getString("soyadı"), rs.getString("email"), rs.getString("şifre"));
            }

         
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return c;
    }

}
