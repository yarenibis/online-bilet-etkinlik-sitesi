/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Mekan;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yaren
 */
public class MekanDAO extends DBConnection {

    private Connection db;

  
    public List<Mekan> getMekanList() {
        List<Mekan> etkinlikList = new ArrayList<>();
        String query = "SELECT * FROM mekan";
        try (PreparedStatement st = this.getConnection().prepareStatement(query); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                etkinlikList.add(new Mekan(
                        rs.getInt("mekan_id"),
                        rs.getString("adı"),
                        rs.getString("adres"),
                        rs.getInt("kapasite")
                ));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return etkinlikList;
    }

    public void MekanOluştur(Mekan e) {
        String query = "INSERT INTO mekan (adı, adres, kapasite) VALUES (?, ?, ?)";
        try (PreparedStatement st = this.getConnection().prepareStatement(query)) {
            st.setString(1, e.getMekan_adi());
            st.setString(2, e.getAdres());
            st.setInt(3, e.getKapasite());
            st.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Mekan findByID(int id) {
        Mekan c = null;
        String query = "SELECT * FROM mekan WHERE mekan_id = ?";
        try (PreparedStatement st = this.getConnection().prepareStatement(query)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    c = new Mekan(rs.getInt("mekan_id"), rs.getString("adı"), rs.getString("adres"), rs.getInt("kapasite"));
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }

    public void delete(Mekan c) {
        String query = "DELETE FROM mekan WHERE mekan_id = ?";
        try (PreparedStatement st = this.getConnection().prepareStatement(query)) {
            st.setInt(1, c.getMekan_id());
            st.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Mekan c) {
        String query = "UPDATE mekan SET adı = ?, adres = ?, kapasite = ? WHERE mekan_id = ?";
        try (PreparedStatement st = this.getConnection().prepareStatement(query)) {
            st.setString(1, c.getMekan_adi());
            st.setString(2, c.getAdres());
            st.setInt(3, c.getKapasite());
            st.setInt(4, c.getMekan_id());
            st.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
