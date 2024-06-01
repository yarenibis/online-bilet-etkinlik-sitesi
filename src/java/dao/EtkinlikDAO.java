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
        String query = "SELECT * FROM etkinlik ORDER BY etkinlik_id ASC LIMIT ? OFFSET ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, pageSize);
            preparedStatement.setInt(2, start);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Mekan m = this.getMekandao().findByID(rs.getInt("mekan_id"));
                etkinlikList.add(new Etkinlik(rs.getInt("etkinlik_id"),
                        rs.getString("etkinlik_adı"),
                        rs.getString("açıklama"),
                        m,
                        rs.getString("tarih_saat")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return etkinlikList;
    }

    public int count() {
        int count = 0;
        String query = "SELECT COUNT(etkinlik_id) AS total FROM etkinlik";

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
        String query = "INSERT INTO etkinlik (etkinlik_adı, açıklama, mekan_id, tarih_saat) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, e.getAdı());
            preparedStatement.setString(2, e.getAçıklama());
            preparedStatement.setInt(3, e.getMekan().getMekan_id());
            preparedStatement.setString(4, e.getTarih_saat());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void adminupdate(Etkinlik c) {
        String query = "UPDATE etkinlik SET etkinlik_adı = ?, açıklama = ?, mekan_id = ?, tarih_saat = ? WHERE etkinlik_id = ?";
        String deleteParticipantsQuery = "DELETE FROM katılımcı_bilgisi WHERE etkinlik_id = ?";
        String insertParticipantsQuery = "INSERT INTO katılımcı_bilgisi (etkinlik_id, kullanıcı_id) VALUES (?, ?)";

        try (Connection conn = this.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(query); PreparedStatement deleteStatement = conn.prepareStatement(deleteParticipantsQuery); PreparedStatement insertStatement = conn.prepareStatement(insertParticipantsQuery)) {

            preparedStatement.setString(1, c.getAdı());
            preparedStatement.setString(2, c.getAçıklama());
            preparedStatement.setInt(3, c.getMekan().getMekan_id());
            preparedStatement.setString(4, c.getTarih_saat());
            preparedStatement.setInt(5, c.getId());
            preparedStatement.executeUpdate();

            deleteStatement.setInt(1, c.getId());
            deleteStatement.executeUpdate();

            for (Kullanıcı k : c.getKlist()) {
                insertStatement.setInt(1, c.getId());
                insertStatement.setInt(2, k.getKullanıcı_id());
                insertStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void admindelete(Etkinlik c) {
        String deleteParticipantsQuery = "DELETE FROM katılımcı_bilgisi WHERE etkinlik_id = ?";
        String deleteEventQuery = "DELETE FROM etkinlik WHERE etkinlik_id = ?";

        try (Connection conn = this.getConnection(); PreparedStatement deleteParticipantsStatement = conn.prepareStatement(deleteParticipantsQuery); PreparedStatement deleteEventStatement = conn.prepareStatement(deleteEventQuery)) {

            deleteParticipantsStatement.setInt(1, c.getId());
            deleteParticipantsStatement.executeUpdate();

            deleteEventStatement.setInt(1, c.getId());
            deleteEventStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(Etkinlik c) {
        String query = "DELETE FROM etkinlik WHERE etkinlik_id = ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, c.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(Etkinlik c) {
        String query = "UPDATE etkinlik SET etkinlik_adı = ?, açıklama = ?, mekan_id = ?, tarih_saat = ? WHERE etkinlik_id = ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, c.getAdı());
            preparedStatement.setString(2, c.getAçıklama());
            preparedStatement.setInt(3, c.getMekan().getMekan_id());
            preparedStatement.setString(4, c.getTarih_saat());
            preparedStatement.setInt(5, c.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Etkinlik findByID(int id) {
        Etkinlik c = null;
        String query = "SELECT * FROM etkinlik WHERE etkinlik_id = ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Mekan y = this.getMekandao().findByID(rs.getInt("mekan_id"));
                c = new Etkinlik(rs.getInt("etkinlik_id"), rs.getString("etkinlik_adı"), rs.getString("açıklama"), y, rs.getString("tarih_saat"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }

}
