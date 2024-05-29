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
        List<Bilet> biletlist = new ArrayList<>();
        String query = "SELECT * FROM bilet";
        try (PreparedStatement preparedStatement = this.getDb().prepareStatement(query); ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                biletlist.add(new Bilet(
                        rs.getInt("bilet_id"),
                        rs.getInt("etkinlik_id"),
                        rs.getInt("kullanıcı_id")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return biletlist;
    }

    public void createBilet(Bilet bilet) {
        String query = "INSERT INTO bilet (etkinlik_id, kullanıcı_id) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = this.getDb().prepareStatement(query)) {
            preparedStatement.setInt(1, bilet.getEtkinlik_id());
            preparedStatement.setInt(2, bilet.getKullanıcı_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBilet(int id) {
        String query = "DELETE FROM bilet WHERE bilet_id = ?";
        try (PreparedStatement preparedStatement = this.getDb().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateBilet(Bilet b) {
        String query = "UPDATE bilet SET etkinlik_id = ?, kullanıcı_id = ? WHERE bilet_id = ?";
        try (PreparedStatement preparedStatement = this.getDb().prepareStatement(query)) {
            preparedStatement.setInt(1, b.getEtkinlik_id());
            preparedStatement.setInt(2, b.getKullanıcı_id());
            preparedStatement.setInt(3, b.getBilet_id());
            int r = preparedStatement.executeUpdate();
            if (r > 0) {
                System.out.println("Bilet güncellendi");
            } else {
                System.out.println("Güncelleme başarısız");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
     public Bilet findByID(int id) {
        Bilet c = null;
        String query = "SELECT * FROM bilet WHERE kullanıcı_id = ?";

        try (PreparedStatement preparedStatement = this.getDb().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            
                c = new Bilet(rs.getInt("bilet_id"), rs.getInt("etkinlik_id"), rs.getInt("kullanıcı_id"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }
    
    private Connection getDb() {
        if (this.db == null) {
            this.db = this.connect();
        }
        return db;
    }
}
