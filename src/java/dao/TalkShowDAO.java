/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Talkshow;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class TalkShowDAO extends DBConnection implements Etkinlik_islem<Talkshow> {

    private Connection db;

    private Connection getDb() {
        if (this.db == null) {
            this.db = this.connect();
        }
        return db;
    }

    @Override
    public void create(Talkshow talkShow) {
        String query = "INSERT INTO talkshow (show_adı, showman_adı, mekan, tarih) VALUES (?, ?, ?, ?)";
        try (PreparedStatement st = this.getDb().prepareStatement(query)) {
            st.setString(1, talkShow.getShow_adi());
            st.setString(2, talkShow.getShowman_adi());
            st.setString(3, talkShow.getMekan());
            st.setString(4, talkShow.getTarih());
            st.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Delete method
    @Override
    public void delete(Talkshow talkShow) {
        String query = "DELETE FROM talkshow WHERE show_id = ?";
        try (PreparedStatement st = this.getDb().prepareStatement(query)) {
            st.setInt(1, talkShow.getShow_id());
            int r = st.executeUpdate();
            if (r > 0) {
                System.out.println("Talkshow silindi");
            } else {
                System.out.println("Silme başarısız");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Update method
    @Override
    public void update(Talkshow talkShow) {
        String query = "UPDATE talkshow SET show_adı = ?, showman_adı = ?, mekan = ?, tarih = ? WHERE show_id = ?";
        try (PreparedStatement st = this.getDb().prepareStatement(query)) {
            st.setString(1, talkShow.getShow_adi());
            st.setString(2, talkShow.getShowman_adi());
            st.setString(3, talkShow.getMekan());
            st.setString(4, talkShow.getTarih());
            st.setInt(5, talkShow.getShow_id());
            int r = st.executeUpdate();
            if (r > 0) {
                System.out.println("Talkshow güncellendi");
            } else {
                System.out.println("Güncelleme başarısız");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // List method
    @Override
    public List<Talkshow> list() {
        List<Talkshow> talkShowList = new ArrayList<>();
        String query = "SELECT * FROM talkshow";
        try (PreparedStatement st = this.getDb().prepareStatement(query); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                talkShowList.add(new Talkshow(
                        rs.getInt("show_id"),
                        rs.getString("show_adı"),
                        rs.getString("showman_adı"),
                        rs.getString("mekan"),
                        rs.getString("tarih")
                ));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return talkShowList;
    }

    // Find by ID method
    public Talkshow findByID(int id) {
        Talkshow c = null;
        String query = "SELECT * FROM talkshow WHERE show_id = ?";
        try (PreparedStatement st = this.connect().prepareStatement(query)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    c = new Talkshow(
                            rs.getInt("show_id"),
                            rs.getString("show_adı"),
                            rs.getString("showman_adı"),
                            rs.getString("mekan"),
                            rs.getString("tarih")
                    );
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
