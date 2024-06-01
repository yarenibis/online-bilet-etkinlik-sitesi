/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Festival;
import entity.Kullanıcı;
import entity.Mekan;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;
import java.sql.*;

public class FestivalDAO extends DBConnection implements Etkinlik_islem<Festival> {

   private Connection db;
    private MekanDAO mekandao;
    private YetkiDAO yetkidao;

    public MekanDAO getMekandao() {
        if (mekandao == null) {
            mekandao = new MekanDAO();
        }
        return mekandao;
    }



    public List<Festival> list(int page, int pageSize) {
        List<Festival> etkinlikList = new ArrayList<>();
        int start = (page - 1) * pageSize;
        String query = "SELECT * FROM festival ORDER BY festival_id ASC LIMIT ? OFFSET ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, pageSize);
            preparedStatement.setInt(2, start);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Mekan m = this.getMekandao().findByID(rs.getInt("mekan_id"));
                etkinlikList.add(new Festival(rs.getInt("festival_id"),
                        rs.getString("festival_adı"),
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
        String query = "SELECT COUNT(festival_id) AS total FROM festival";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query); ResultSet rs = preparedStatement.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    public void create(Festival e) {
        String query = "INSERT INTO festival (festival_adı, mekan_id, tarih) VALUES ( ?, ?, ?)";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, e.getFestival_adi());
            preparedStatement.setInt(2, e.getMekan().getMekan_id());
            preparedStatement.setString(3, e.getTarih());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void admincreate(Festival e) {
        String query = "INSERT INTO festival (festival_adı, mekan_id, tarih) VALUES ( ?, ?, ?)";

        try (Connection conn = this.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, e.getFestival_adi());
            preparedStatement.setInt(2, e.getMekan().getMekan_id());
            preparedStatement.setString(3, e.getTarih());
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

    public void adminupdate(Festival c) {
        String query = "UPDATE festival SET festival_adı = ?, mekan_id = ?, tarih = ? WHERE festival_id = ?";
        String deleteParticipantsQuery = "DELETE FROM katılımcı_bilgisi WHERE etkinlik_id = ?";
        String insertParticipantsQuery = "INSERT INTO katılımcı_bilgisi (etkinlik_id, kullanıcı_id) VALUES (?, ?)";

        try (Connection conn = this.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(query); PreparedStatement deleteStatement = conn.prepareStatement(deleteParticipantsQuery); PreparedStatement insertStatement = conn.prepareStatement(insertParticipantsQuery)) {

            preparedStatement.setString(1, c.getFestival_adi());
            preparedStatement.setInt(2, c.getMekan().getMekan_id());
            preparedStatement.setString(3, c.getTarih());
            preparedStatement.setInt(4, c.getFestival_id());
            preparedStatement.executeUpdate();

            deleteStatement.setInt(1, c.getFestival_id());
            deleteStatement.executeUpdate();

            for (Kullanıcı k : c.getKlist()) {
                insertStatement.setInt(1, c.getFestival_id());
                insertStatement.setInt(2, k.getKullanıcı_id());
                insertStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void admindelete(Festival c) {
        String deleteParticipantsQuery = "DELETE FROM katılımcı_bilgisi WHERE festival_id = ?";
        String deleteEventQuery = "DELETE FROM festival WHERE festival_id = ?";

        try (Connection conn = this.getConnection(); PreparedStatement deleteParticipantsStatement = conn.prepareStatement(deleteParticipantsQuery); PreparedStatement deleteEventStatement = conn.prepareStatement(deleteEventQuery)) {

            deleteParticipantsStatement.setInt(1, c.getFestival_id());
            deleteParticipantsStatement.executeUpdate();

            deleteEventStatement.setInt(1, c.getFestival_id());
            deleteEventStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(Festival c) {
        String query = "DELETE FROM festival WHERE festival_id = ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, c.getFestival_id());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(Festival c) {
        String query = "UPDATE festival SET festival_adı = ?, mekan_id = ?, tarih = ? WHERE festival_id = ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, c.getFestival_adi());
            preparedStatement.setInt(2, c.getMekan().getMekan_id());
            preparedStatement.setString(3, c.getTarih());
            preparedStatement.setInt(4, c.getFestival_id());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Festival findByID(int id) {
        Festival c = null;
        String query = "SELECT * FROM festival WHERE festival_id = ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Mekan y = this.getMekandao().findByID(rs.getInt("mekan_id"));
                c = new Festival(rs.getInt("festival_id"), rs.getString("festival_adı"), y, rs.getString("tarih"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }

 

}
