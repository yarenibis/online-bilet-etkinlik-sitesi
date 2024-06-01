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

    public Yetki findByID(int id) {
        Yetki c = null;
        String query = "SELECT * FROM yetki WHERE yetki_id = ?";
        try (PreparedStatement st = this.getConnection().prepareStatement(query)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    c = new Yetki(
                            rs.getInt("yetki_id"),
                            rs.getString("yetki_adı")
                    );
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }

    // List method
    public List<Yetki> getYetkiList() {
        List<Yetki> yetkiList = new ArrayList<>();
        String query = "SELECT * FROM yetki";
        try (PreparedStatement st = this.getConnection().prepareStatement(query); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                yetkiList.add(new Yetki(
                        rs.getInt("yetki_id"),
                        rs.getString("yetki_adı")
                ));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return yetkiList;
    }

    // Create method
    public void create(Yetki y) {
        String query = "INSERT INTO yetki (yetki_adı) VALUES (?)";
        try (PreparedStatement st = this.getConnection().prepareStatement(query)) {
            st.setString(1, y.getYetki_adi());
            st.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Delete method
    public void delete(Yetki y) {
        String query = "DELETE FROM yetki WHERE yetki_id = ?";
        try (PreparedStatement st = this.getConnection().prepareStatement(query)) {
            st.setInt(1, y.getYetki_id());
            st.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Update method
    public void update(Yetki b) {
        String query = "UPDATE yetki SET yetki_adı = ? WHERE yetki_id = ?";
        try (PreparedStatement st = this.getConnection().prepareStatement(query)) {
            st.setString(1, b.getYetki_adi());
            st.setInt(2, b.getYetki_id());
            st.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
