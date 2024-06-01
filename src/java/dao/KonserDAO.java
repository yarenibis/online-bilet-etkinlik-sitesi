/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Konser;
import entity.Kullanıcı;
import entity.Mekan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class KonserDAO extends DBConnection implements Etkinlik_islem<Konser> {

    private Connection db;
    private MekanDAO mekandao;
    private YetkiDAO yetkidao;

    public MekanDAO getMekandao() {
        if (mekandao == null) {
            mekandao = new MekanDAO();
        }
        return mekandao;
    }

 

    public List<Konser> list(int page, int pageSize) {
        List<Konser> etkinlikList = new ArrayList<>();
        int start = (page - 1) * pageSize;
        String query = "SELECT * FROM konser ORDER BY konser_id ASC LIMIT ? OFFSET ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, pageSize);
            preparedStatement.setInt(2, start);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Mekan m = this.getMekandao().findByID(rs.getInt("mekan_id"));
                etkinlikList.add(new Konser(rs.getInt("konser_id"),
                        rs.getString("konser_adı"),
                        m,
                        rs.getString("tarih"),
                         rs.getString("sanatçı")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return etkinlikList;
    }

    public int count() {
        int count = 0;
        String query = "SELECT COUNT(konser_id) AS total FROM konser";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query); ResultSet rs = preparedStatement.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    public void create(Konser e) {
        String query = "INSERT INTO konser (konser_adı, mekan_id, tarih ,sanatçı) VALUES ( ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, e.getAdı());
            preparedStatement.setInt(2, e.getMekan().getMekan_id());
            preparedStatement.setString(3, e.getTarih());
            preparedStatement.setString(4, e.getSanatçı());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    public void delete(Konser c) {
        String query = "DELETE FROM konser WHERE konser_id = ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, c.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(Konser c) {
        String query = "UPDATE konser SET konser_adı = ?, mekan_id = ?, tarih = ?, sanatçı = ? WHERE konser_id = ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, c.getAdı());
            preparedStatement.setInt(2, c.getMekan().getMekan_id());
            preparedStatement.setString(3, c.getTarih());
            preparedStatement.setString(4, c.getSanatçı());
            preparedStatement.setInt(5, c.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Konser findByID(int id) {
        Konser c = null;
        String query = "SELECT * FROM konser WHERE konser_id = ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Mekan y = this.getMekandao().findByID(rs.getInt("mekan_id"));
                c = new Konser(rs.getInt("konser_id"), rs.getString("konser_adı"), y, rs.getString("tarih"),rs.getString("sanatçı"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }


}
