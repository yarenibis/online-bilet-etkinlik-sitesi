/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Sinema;
import entity.Kullanıcı;
import entity.Mekan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class SinemaDAO extends DBConnection implements Etkinlik_islem<Sinema> {

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

    public List<Sinema> list(int page, int pageSize) {
        List<Sinema> etkinlikList = new ArrayList<>();
        int start = (page - 1) * pageSize;
        String query = "SELECT * FROM sinema ORDER BY sinema_id ASC LIMIT ? OFFSET ?";

        try (PreparedStatement preparedStatement = this.getDb().prepareStatement(query)) {
            preparedStatement.setInt(1, pageSize);
            preparedStatement.setInt(2, start);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Mekan m = this.getMekandao().findByID(rs.getInt("mekan_id"));
                etkinlikList.add(new Sinema(rs.getInt("sinema_id"),
                        rs.getString("film_adı"),
                        rs.getInt("salon_no"),
                        m,
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
        String query = "SELECT COUNT(sinema_id) AS total FROM sinema";

        try (PreparedStatement preparedStatement = this.getDb().prepareStatement(query); ResultSet rs = preparedStatement.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    public void create(Sinema e) {
        String query = "INSERT INTO sinema (film_adı, salon_no,mekan_id,tarih) VALUES ( ?, ?, ?,?)";

        try (PreparedStatement preparedStatement = this.getDb().prepareStatement(query)) {
            preparedStatement.setString(1, e.getFilm_adi());
            preparedStatement.setInt(2, e.getSalon_no());
            preparedStatement.setInt(3, e.getMekan().getMekan_id());
            preparedStatement.setString(4, e.getTarih());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void admincreate(Sinema e) {
        String query = "INSERT INTO sinema (film_adı,salon_no ,mekan_id,tarih) VALUES ( ?, ?, ?, ?)";
        try (Connection conn = this.getDb(); PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, e.getFilm_adi());
             preparedStatement.setInt(2, e.getSalon_no());
            preparedStatement.setInt(3, e.getMekan().getMekan_id());
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

    public void adminupdate(Sinema c) {
        String query = "UPDATE sinema SET film_adı = ?,  salon_no = ?, mekan_id = ?, tarih = ? WHERE sinema_id = ?";
        String deleteParticipantsQuery = "DELETE FROM katılımcı_bilgisi WHERE etkinlik_id = ?";
        String insertParticipantsQuery = "INSERT INTO katılımcı_bilgisi (etkinlik_id, kullanıcı_id) VALUES (?, ?)";

        try (Connection conn = this.getDb(); PreparedStatement preparedStatement = conn.prepareStatement(query); PreparedStatement deleteStatement = conn.prepareStatement(deleteParticipantsQuery); PreparedStatement insertStatement = conn.prepareStatement(insertParticipantsQuery)) {

            preparedStatement.setString(1, c.getFilm_adi());
            preparedStatement.setInt(2, c.getSalon_no());
            preparedStatement.setInt(3, c.getMekan().getMekan_id());
             preparedStatement.setString(4, c.getTarih());
            
            preparedStatement.executeUpdate();

            deleteStatement.setInt(1, c.getSinema_id());
            deleteStatement.executeUpdate();

            for (Kullanıcı k : c.getKlist()) {
                insertStatement.setInt(1, c.getSinema_id());
                insertStatement.setInt(2, k.getKullanıcı_id());
                insertStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void admindelete(Sinema c) {
        String deleteParticipantsQuery = "DELETE FROM katılımcı_bilgisi WHERE sinema_id = ?";
        String deleteEventQuery = "DELETE FROM sinema WHERE sinema_id = ?";

        try (Connection conn = this.getDb(); PreparedStatement deleteParticipantsStatement = conn.prepareStatement(deleteParticipantsQuery); PreparedStatement deleteEventStatement = conn.prepareStatement(deleteEventQuery)) {

            deleteParticipantsStatement.setInt(1, c.getSinema_id());
            deleteParticipantsStatement.executeUpdate();

            deleteEventStatement.setInt(1, c.getSinema_id());
            deleteEventStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(Sinema c) {
        String query = "DELETE FROM sinema WHERE sinema_id = ?";

        try (PreparedStatement preparedStatement = this.getDb().prepareStatement(query)) {
            preparedStatement.setInt(1, c.getSinema_id());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(Sinema c) {
        String query = "UPDATE sinema SET film_adı = ?, salon_no = ? , mekan_id = ?, tarih = ? WHERE sinema_id = ?";

        try (PreparedStatement preparedStatement = this.getDb().prepareStatement(query)) {
            preparedStatement.setString(1, c.getFilm_adi());
            preparedStatement.setInt(2, c.getSalon_no());
            preparedStatement.setInt(3, c.getMekan().getMekan_id());
             preparedStatement.setString(4, c.getTarih());
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Sinema findByID(int id) {
        Sinema c = null;
        String query = "SELECT * FROM sinema WHERE sinema_id = ?";

        try (PreparedStatement preparedStatement = this.getDb().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Mekan y = this.getMekandao().findByID(rs.getInt("mekan_id"));
                c = new Sinema(rs.getInt("sinema_id"), rs.getString("film_adı"),rs.getInt("salon_no") ,y, rs.getString("tarih"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }



}
