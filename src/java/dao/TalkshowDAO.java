/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Talkshow;
import entity.Kullanıcı;
import entity.Mekan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class TalkshowDAO extends DBConnection implements Etkinlik_islem<Talkshow> {

    private Connection db;
    private MekanDAO mekandao;
    private YetkiDAO yetkidao;

    public MekanDAO getMekandao() {
        if (mekandao == null) {
            mekandao = new MekanDAO();
        }
        return mekandao;
    }


    public List<Talkshow> list(int page, int pageSize) {
        List<Talkshow> etkinlikList = new ArrayList<>();
        int start = (page - 1) * pageSize;
        String query = "SELECT * FROM talkshow ORDER BY id ASC LIMIT ? OFFSET ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, pageSize);
            preparedStatement.setInt(2, start);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Mekan m = this.getMekandao().findByID(rs.getInt("mekan_id"));
                etkinlikList.add(new Talkshow(rs.getInt("id"),
                        rs.getString("adı"),
                    rs.getString("açıklama"),
                    m,
                    rs.getString("tarih_saat"),
                    rs.getString("type"),
                    rs.getString("showman_adı"),
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
        String query = "SELECT COUNT(id) AS total FROM talkshow";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query); ResultSet rs = preparedStatement.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    public void create(Talkshow e) {
        String query = "INSERT INTO talkshow (adı,açıklama,mekan_id,tarih_saat,type,showman_adı) VALUES ( ?, ?, ?,?,?)";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, e.getAdı());
            preparedStatement.setString(2, e.getAçıklama());
            preparedStatement.setInt(3, e.getMekan().getMekan_id());
            preparedStatement.setString(4, e.getTarih_saat());
            preparedStatement.setString(5, e.getType());
            preparedStatement.setString(6, e.getShowman_adi());
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

   


    
    
    
      public void update(Talkshow c) {
        String query = "UPDATE tiyatro SET adı = ? ,açıklama = ? , mekan_id = ?,tarih_saat = ?, type = ? ,showman_adı= ?, WHERE id = ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, c.getAdı());
            preparedStatement.setString(2, c.getAçıklama());
            preparedStatement.setInt(3, c.getMekan().getMekan_id());
            preparedStatement.setString(4, c.getTarih_saat());
            preparedStatement.setString(5, c.getType());
            preparedStatement.setString(6, c.getShowman_adi());
             preparedStatement.setInt(7, c.getId());
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    

    public void delete(Talkshow c) {
        String query = "DELETE FROM talkshow WHERE id = ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, c.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public Talkshow findByID(int id) {
        Talkshow c = null;
        String query = "SELECT * FROM talkshow WHERE etkinlik_id = ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            Mekan y = this.getMekandao().findByID(rs.getInt("mekan_id"));
                c = new Talkshow(rs.getInt("id"),rs.getString("adı"),rs.getString("açıklama"),y,rs.getString("tarih_saat"),rs.getString("type"),rs.getString("showman_adı"),rs.getInt("etkinlik_id"));
               
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }


}
