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
public class TiyatroDAO extends DBConnection implements Etkinlik_islem<Tiyatro> {

    private Connection db;

    public Connection getDb() {
        if (this.db == null) {
            db = this.connect();

        }
        return db;
    }

    public Tiyatro findByID(int id) {
        Tiyatro c = null;
        String query = "SELECT * FROM tiyatro WHERE tiyatro_id = ?";
        try (PreparedStatement st = this.connect().prepareStatement(query)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    c = new Tiyatro(
                            rs.getInt("tiyatro_id"),
                            rs.getString("oyun_adı"),
                            rs.getString("mekan"),
                            rs.getString("oyuncu"),
                            rs.getString("tarih")
                    );
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }

    // Delete method
    @Override
    public void delete(Tiyatro c) {
        String query = "DELETE FROM tiyatro WHERE tiyatro_id = ?";
        try (PreparedStatement st = this.connect().prepareStatement(query)) {
            st.setInt(1, c.getId());
            st.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Update method
    @Override
    public void update(Tiyatro c) {
        String query = "UPDATE tiyatro SET oyun_adı = ?, mekan = ?, oyuncu = ?, tarih = ? WHERE tiyatro_id = ?";
        try (PreparedStatement st = this.connect().prepareStatement(query)) {
            st.setString(1, c.getAdı());
            st.setString(2, c.getMekan());
            st.setString(3, c.getOyuncu());
            st.setString(4, c.getTarih());
            st.setInt(5, c.getId());
            st.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Create method
    @Override
    public void create(Tiyatro e) {
        String query = "INSERT INTO tiyatro (oyun_adı, mekan, oyuncu, tarih) VALUES (?, ?, ?, ?)";
        try (PreparedStatement st = this.getDb().prepareStatement(query)) {
            st.setString(1, e.getAdı());
            st.setString(2, e.getMekan());
            st.setString(3, e.getOyuncu());
            st.setString(4, e.getTarih());
            st.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // List method
    @Override
    public List<Tiyatro> list() {
        List<Tiyatro> tiyatroList = new ArrayList<>();
        String query = "SELECT * FROM tiyatro";
        try (PreparedStatement st = this.getDb().prepareStatement(query); ResultSet rs = st.executeQuery()) {

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
