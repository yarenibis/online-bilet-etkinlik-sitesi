/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Tiyatro;
import entity.Kullanıcı;
import entity.Mekan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class TiyatroDAO extends DBConnection implements Etkinlik_islem<Tiyatro> {

     private Connection db;
    private MekanDAO mekandao;
    private YetkiDAO yetkidao;

    public MekanDAO getMekandao() {
        if (mekandao == null) {
            mekandao = new MekanDAO();
        }
        return mekandao;
    }

    public Connection getDb() {
        if (this.db == null) {
            db = this.connect();

        }
        return db;
    }

    public List<Tiyatro> list(int page, int pageSize) {
        List<Tiyatro> etkinlikList = new ArrayList<>();
        int start = (page - 1) * pageSize;
        String query = "SELECT * FROM tiyatro ORDER BY tiyatro_id ASC LIMIT ? OFFSET ?";

        try (PreparedStatement preparedStatement = this.getDb().prepareStatement(query)) {
            preparedStatement.setInt(1, pageSize);
            preparedStatement.setInt(2, start);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Mekan m = this.getMekandao().findByID(rs.getInt("mekan_id"));
                etkinlikList.add(new Tiyatro(rs.getInt("tiyatro_id"),
                        rs.getString("oyun_adı"),
                        m,
                        rs.getString("oyuncu"),
                        rs.getString("tarih")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return etkinlikList;
    }

    public int count() {
        int count = 0;
        String query = "SELECT COUNT(tiyatro_id) AS total FROM tiyatro";

        try (PreparedStatement preparedStatement = this.getDb().prepareStatement(query); ResultSet rs = preparedStatement.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    public void create(Tiyatro e) {
        String query = "INSERT INTO tiyatro (oyun_adı,mekan_id,oyuncu,tarih) VALUES ( ?, ?, ?,?)";

        try (PreparedStatement preparedStatement = this.getDb().prepareStatement(query)) {
            preparedStatement.setString(1, e.getAdı());
            preparedStatement.setInt(2, e.getMekan().getMekan_id());
            preparedStatement.setString(3, e.getOyuncu());
            preparedStatement.setString(4, e.getTarih());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void admincreate(Tiyatro e) {
        String query = "INSERT INTO tiyatro (oyun_adı ,mekan_id,oyuncu,tarih) VALUES ( ?, ?, ?, ?)";
        try (Connection conn = this.getDb(); PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, e.getAdı());
            preparedStatement.setInt(2, e.getMekan().getMekan_id());
            preparedStatement.setString(3, e.getOyuncu());
            preparedStatement.setString(4, e.getTarih());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                int et_id = rs.getInt(1);
                String participantQuery = "INSERT INTO katılımcı_bilgisi (etkinlik_id, kullanıcı_id) VALUES (?, ?)";

                try (PreparedStatement participantStatement = conn.prepareStatement(participantQuery)) {
                    for (Kullanıcı k : e.getKlist()) {
                        participantStatement.setInt(1, et_id);
                        participantStatement.setInt(2, k.getKullanıcı_id());
                        participantStatement.executeUpdate();
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void adminupdate(Tiyatro c) {
        String query = "UPDATE tiyatro SET oyun_adı = ?, mekan_id = ?,oyuncu=?, tarih = ? WHERE tiyatro_id = ?";
        String deleteParticipantsQuery = "DELETE FROM katılımcı_bilgisi WHERE etkinlik_id = ?";
        String insertParticipantsQuery = "INSERT INTO katılımcı_bilgisi (etkinlik_id, kullanıcı_id) VALUES (?, ?)";

        try (Connection conn = this.getDb(); PreparedStatement preparedStatement = conn.prepareStatement(query); PreparedStatement deleteStatement = conn.prepareStatement(deleteParticipantsQuery); PreparedStatement insertStatement = conn.prepareStatement(insertParticipantsQuery)) {

            preparedStatement.setString(1, c.getAdı());
            preparedStatement.setInt(2, c.getMekan().getMekan_id());
             preparedStatement.setString(3,c.getOyuncu());
            preparedStatement.setString(4, c.getTarih());
            
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

    public void admindelete(Tiyatro c) {
        String deleteParticipantsQuery = "DELETE FROM katılımcı_bilgisi WHERE show_id = ?";
        String deleteEventQuery = "DELETE FROM talkshow WHERE show_id = ?";

        try (Connection conn = this.getDb(); PreparedStatement deleteParticipantsStatement = conn.prepareStatement(deleteParticipantsQuery); PreparedStatement deleteEventStatement = conn.prepareStatement(deleteEventQuery)) {

            deleteParticipantsStatement.setInt(1, c.getId());
            deleteParticipantsStatement.executeUpdate();

            deleteEventStatement.setInt(1, c.getId());
            deleteEventStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(Tiyatro c) {
        String query = "DELETE FROM tiyatro WHERE tiyatro_id = ?";

        try (PreparedStatement preparedStatement = this.getDb().prepareStatement(query)) {
            preparedStatement.setInt(1, c.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(Tiyatro c) {
        String query = "UPDATE tiyatro SET oyun_adı = ? , mekan_id = ?,oyuncu= ?, tarih = ? WHERE tiyatro_id = ?";

        try (PreparedStatement preparedStatement = this.getDb().prepareStatement(query)) {
            preparedStatement.setString(1, c.getAdı());
            preparedStatement.setString(2, c.getOyuncu());
            preparedStatement.setInt(3, c.getMekan().getMekan_id());
            preparedStatement.setString(4, c.getTarih());
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Tiyatro findByID(int id) {
        Tiyatro c = null;
        String query = "SELECT * FROM tiyatro WHERE tiyatro_id = ?";

        try (PreparedStatement preparedStatement = this.getDb().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Mekan y = this.getMekandao().findByID(rs.getInt("mekan_id"));
                c = new Tiyatro(rs.getInt("tiyatro_id"),rs.getString("oyun_adı"),y,rs.getString("oyuncu"),rs.getString("tarih"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }



}

