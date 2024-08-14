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


    
    
     @Override
    public List<Sinema> list(int page, int pageSize) {
        List<Sinema> etkinlikList = new ArrayList<>();
        int start = (page - 1) * pageSize;
        String query = "SELECT * FROM sinema ORDER BY id ASC LIMIT ? OFFSET ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, pageSize);
            preparedStatement.setInt(2, start);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Mekan m = this.getMekandao().findByID(rs.getInt("mekan_id"));
                etkinlikList.add(new Sinema(
                    rs.getInt("id"),
                    rs.getString("adı"),
                    rs.getString("açıklama"),
                    m,
                    rs.getString("tarih_saat"),
                    rs.getString("type"),
                    rs.getInt("salon_no"),
                        rs.getInt("etkinlik_id")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return etkinlikList;
    }

    
    public int count() {
        int count = 0;
        String query = "SELECT COUNT(id) AS total FROM sinema";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query); ResultSet rs = preparedStatement.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    
    @Override
    public void create(Sinema e) {
        String query = "INSERT INTO cinema (adı, açıklama, mekan_id, tarih_saat, type, salon_no) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, e.getAdı());
            preparedStatement.setString(2, e.getAçıklama());
            preparedStatement.setInt(3, e.getMekan().getMekan_id());
            preparedStatement.setString(4, e.getTarih_saat());
            preparedStatement.setString(5, e.getType());
            preparedStatement.setInt(6, e.getSalon_no());

             preparedStatement.executeUpdate();

           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void delete(Sinema c) {
        String query = "DELETE FROM sinema WHERE id = ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, c.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Sinema c) {
        String query = "UPDATE sinema SET adı = ?, açıklama = ?, mekan_id = ?, tarih_saat = ?, type = ?, salon_no = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, c.getAdı());
            preparedStatement.setString(2, c.getAçıklama());
            preparedStatement.setInt(3, c.getMekan().getMekan_id());
            preparedStatement.setString(4, c.getTarih_saat());
            preparedStatement.setString(5, c.getType());
            preparedStatement.setInt(6, c.getSalon_no());
            preparedStatement.setInt(7, c.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

   public Sinema findByID(int id) {
        Sinema c = null;
        String query = "SELECT * FROM sinema WHERE etkinlik_id = ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Mekan m = this.getMekandao().findByID(rs.getInt("mekan_id"));
                c = new Sinema(
                    rs.getInt("id"),
                    rs.getString("adı"),
                    rs.getString("açıklama"),
                    m,
                    rs.getString("tarih_saat"),
                    rs.getString("type"),
                    rs.getInt("salon_no"),
                        rs.getInt("etkinlik_id")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }

}
