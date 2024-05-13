/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Bilet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

/**
 *
 * @author yaren
 */
public class BiletDAO extends DBConnection {

    private Connection db;

    public List<Bilet> getBiletList() {
        List<Bilet> biletlist = new ArrayList();
        try {
            Statement st = this.getDb().createStatement();
            String query = "select * from bilet";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                biletlist.add(new Bilet(
                        rs.getInt("bilet_id"),
                        rs.getInt("etkinlik_id"),
                        rs.getInt("kullanıcı_id")
                ));

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return biletlist;
    }

    public void createBilet(Bilet bilet) {
        try {
            String query = "INSERT INTO bilet (etkinlik_id, kullanıcı_id) VALUES (?, ?)";
            PreparedStatement preparedStatement = this.connect().prepareStatement(query);
            preparedStatement.setInt(1, bilet.getEtkinlik_id());
            preparedStatement.setInt(2, bilet.getKullanıcı_id());
            
             preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // Hata durumunda işlemleri geri al
            e.printStackTrace();
        }
    }

    public void deleteBilet(int id) {
        try {
            Statement st = this.getDb().createStatement();
            String query = "delete from bilet where bilet_id=" + id;
            st.executeUpdate(query);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateBilet(Bilet b) {
        try {
            Statement st = this.getDb().createStatement();
            String query = "update bilet set kullanıcı_id='" + b.getKullanıcı_id() + "', kullanıcı_id='" + b.getKullanıcı_id() + "' where bilet_id=" + b.getBilet_id();
            int r = st.executeUpdate(query);
            if (r > 0) {
                System.out.println("Bilet güncellendi");
            } else {
                System.out.println("Güncelleme başarısız");
            }
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
