/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Konser;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class KonserDAO extends DBConnection implements Etkinlik_islem<Konser> {

    private Connection db;

    public Connection getDb() {
        if (this.db == null) {
            db = this.connect();
        }
        return db;
    }

    @Override
    public void create(Konser k) {
        String query = "INSERT INTO konser (konser_adı, mekan, tarih, sanatçı) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = this.getDb().prepareStatement(query)) {
            preparedStatement.setString(1, k.getAdı());
            preparedStatement.setString(2, k.getMekan());
            preparedStatement.setString(3, k.getTarih());
            preparedStatement.setString(4, k.getSanatçı());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Konser k) {
        String query = "DELETE FROM konser WHERE konser_id = ?";
        try (PreparedStatement preparedStatement = this.getDb().prepareStatement(query)) {
            preparedStatement.setInt(1, k.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Konser k) {
        String query = "UPDATE konser SET konser_adı = ?, mekan = ?, tarih = ?, sanatçı = ? WHERE konser_id = ?";
        try (PreparedStatement preparedStatement = this.getDb().prepareStatement(query)) {
            preparedStatement.setString(1, k.getAdı());
            preparedStatement.setString(2, k.getMekan());
            preparedStatement.setString(3, k.getTarih());
            preparedStatement.setString(4, k.getSanatçı());
            preparedStatement.setInt(5, k.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Konser> list() {
        List<Konser> konserList = new ArrayList<>();
        String query = "SELECT * FROM konser";
        try (PreparedStatement preparedStatement = this.getDb().prepareStatement(query); ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                konserList.add(new Konser(
                        rs.getInt("konser_id"),
                        rs.getString("konser_adı"),
                        rs.getString("mekan"),
                        rs.getString("tarih"),
                        rs.getString("sanatçı")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return konserList;
    }

    public Konser findByID(int id) {
        Konser konser = null;
        String query = "SELECT * FROM konser WHERE konser_id = ?";
        try (PreparedStatement preparedStatement = this.connect().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                konser = new Konser(
                        rs.getInt("konser_id"),
                        rs.getString("konser_adı"),
                        rs.getString("mekan"),
                        rs.getString("tarih"),
                        rs.getString("sanatçı")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return konser;
    }
    
    
     public int count() {
        int count = 0;
        String query = "SELECT COUNT(etkinlik_id) AS total FROM etkinlik";

        try (PreparedStatement preparedStatement = this.getDb().prepareStatement(query); ResultSet rs = preparedStatement.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

}
