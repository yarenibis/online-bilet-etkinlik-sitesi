/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Sinema;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class SinemaDAO extends DBConnection implements Etkinlik_islem<Sinema> {

    private Connection db;

    private Connection getDb() {
        if (this.db == null) {
            this.db = this.connect();
        }
        return db;
    }

    @Override
    public void create(Sinema sinema) {
        String query = "INSERT INTO sinema (film_adı, salon_no) VALUES (?, ?)";
        try (PreparedStatement st = this.getDb().prepareStatement(query)) {
            st.setString(1, sinema.getFilm_adi());
            st.setInt(2, sinema.getSalon_no());
            st.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(Sinema sinema) {
        String query = "DELETE FROM sinema WHERE sinema_id = ?";
        try (PreparedStatement st = this.getDb().prepareStatement(query)) {
            st.setInt(1, sinema.getSinema_id());
            int r = st.executeUpdate();
            if (r > 0) {
                System.out.println("Sinema silindi");
            } else {
                System.out.println("Silme başarısız");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Sinema sinema) {
        String query = "UPDATE sinema SET film_adı = ?, salon_no = ? WHERE sinema_id = ?";
        try (PreparedStatement st = this.getDb().prepareStatement(query)) {
            st.setString(1, sinema.getFilm_adi());
            st.setInt(2, sinema.getSalon_no());
            st.setInt(3, sinema.getSinema_id());
            int r = st.executeUpdate();
            if (r > 0) {
                System.out.println("Sinema güncellendi");
            } else {
                System.out.println("Güncelleme başarısız");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Sinema> list() {
        List<Sinema> sinemaList = new ArrayList<>();
        String query = "SELECT * FROM sinema";
        try (PreparedStatement st = this.getDb().prepareStatement(query); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                sinemaList.add(new Sinema(
                        rs.getInt("sinema_id"),
                        rs.getString("film_adı"),
                        rs.getInt("salon_no")
                ));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return sinemaList;
    }

    public Sinema findByName(String film_adi) {
        Sinema c = null;
        String query = "SELECT * FROM sinema WHERE film_adı = ?";
        try (PreparedStatement st = this.connect().prepareStatement(query)) {
            st.setString(1, film_adi);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    c = new Sinema(rs.getInt("sinema_id"), rs.getString("film_adı"), rs.getInt("salon_no"));
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }

    public Sinema findByID(int id) {
        Sinema c = null;
        String query = "SELECT * FROM sinema WHERE sinema_id = ?";
        try (PreparedStatement st = this.connect().prepareStatement(query)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    c = new Sinema(rs.getInt("sinema_id"), rs.getString("film_adı"), rs.getInt("salon_no"));
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return c;
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
