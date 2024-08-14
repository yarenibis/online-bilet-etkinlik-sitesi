/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Etkinlik;
import entity.Kullanıcı;
import entity.Mekan;
import entity.Yetki;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

/**
 *
 * @author yaren
 */
public class EtkinlikDAO extends DBConnection {

    private Connection db;
    private MekanDAO mekandao;
    private YetkiDAO yetkidao;

    public MekanDAO getMekandao() {
        if (mekandao == null) {
            mekandao = new MekanDAO();
        }
        return mekandao;
    }



    public List<Etkinlik> getEtkinlikList(int page, int pageSize) {
        List<Etkinlik> etkinlikList = new ArrayList<>();
        int start = (page - 1) * pageSize;
        String query = "SELECT * FROM etkinlik ORDER BY id ASC LIMIT ? OFFSET ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, pageSize);
            preparedStatement.setInt(2, start);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Mekan m = this.getMekandao().findByID(rs.getInt("mekan_id"));
                etkinlikList.add(new Etkinlik(rs.getInt("id"),
                        rs.getString("adı"),
                        rs.getString("açıklama"),
                        m,
                        rs.getString("tarih_saat"),
                        rs.getString("type")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return etkinlikList;
    }

    public int count() {
        int count = 0;
        String query = "SELECT COUNT(id) AS total FROM etkinlik";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query); ResultSet rs = preparedStatement.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    public void EtkinlikOluştur(Etkinlik e) {
        String query = "INSERT INTO etkinlik (adı, açıklama, mekan_id, tarih_saat,type) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, e.getAdı());
            preparedStatement.setString(2, e.getAçıklama());
            preparedStatement.setInt(3, e.getMekan().getMekan_id());
            preparedStatement.setString(4, e.getTarih_saat());
            preparedStatement.setString(5, e.getType());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    public void delete(Etkinlik c) {
        String query = "DELETE FROM etkinlik WHERE id = ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, c.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(Etkinlik c) {
        String query = "UPDATE etkinlik SET adı = ?, açıklama = ?, mekan_id = ?, tarih_saat = ?, type= ? WHERE id = ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, c.getAdı());
            preparedStatement.setString(2, c.getAçıklama());
            preparedStatement.setInt(3, c.getMekan().getMekan_id());
            preparedStatement.setString(4, c.getTarih_saat());
             preparedStatement.setString(5, c.getType());
            preparedStatement.setInt(6, c.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Etkinlik findByID(int id) {
        Etkinlik c = null;
        String query = "SELECT * FROM etkinlik WHERE id = ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Mekan y = this.getMekandao().findByID(rs.getInt("mekan_id"));
                c = new Etkinlik(rs.getInt("id"), rs.getString("adı"), rs.getString("açıklama"), y, rs.getString("tarih_saat"), rs.getString("type"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }

}
