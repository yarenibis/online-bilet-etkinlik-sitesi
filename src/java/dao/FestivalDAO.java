/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Festival;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;
import java.sql.*;

public class FestivalDAO extends DBConnection implements Etkinlik_islem<Festival> {

    private Connection db;

    @Override
    public void delete(Festival f) {
        String query = "DELETE FROM festival WHERE festival_id = ?";
        try (PreparedStatement preparedStatement = this.connect().prepareStatement(query)) {
            preparedStatement.setInt(1, f.getFestival_id());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(Festival f) {
        String query = "UPDATE festival SET festival_adı = ?, mekan = ?, tarih = ? WHERE festival_id = ?";
        try (PreparedStatement preparedStatement = this.connect().prepareStatement(query)) {
            preparedStatement.setString(1, f.getFestival_adi());
            preparedStatement.setString(2, f.getMekan());
            preparedStatement.setString(3, f.getTarih());
            preparedStatement.setInt(4, f.getFestival_id());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void create(Festival f) {
        String query = "INSERT INTO festival (festival_adı, mekan, tarih) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = this.getDb().prepareStatement(query)) {
            preparedStatement.setString(1, f.getFestival_adi());
            preparedStatement.setString(2, f.getMekan());
            preparedStatement.setString(3, f.getTarih());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Festival> list() {
        List<Festival> festivalList = new ArrayList<>();
        String query = "SELECT * FROM festival";
        try (PreparedStatement preparedStatement = this.getDb().prepareStatement(query); ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                festivalList.add(new Festival(
                        rs.getInt("festival_id"),
                        rs.getString("festival_adı"),
                        rs.getString("mekan"),
                        rs.getString("tarih")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return festivalList;
    }

    public Festival findByID(int id) {
        Festival festival = null;
        String query = "SELECT * FROM festival WHERE festival_id = ?";
        try (PreparedStatement preparedStatement = this.connect().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                festival = new Festival(
                        rs.getInt("festival_id"),
                        rs.getString("festival_adı"),
                        rs.getString("mekan"),
                        rs.getString("tarih")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return festival;
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

    public Connection getDb() {
        if (this.db == null) {
            db = this.connect();

        }
        return db;
    }

}
