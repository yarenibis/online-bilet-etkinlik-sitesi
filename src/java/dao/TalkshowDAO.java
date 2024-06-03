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
        String query = "SELECT * FROM talkshow ORDER BY show_id ASC LIMIT ? OFFSET ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, pageSize);
            preparedStatement.setInt(2, start);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Mekan m = this.getMekandao().findByID(rs.getInt("mekan_id"));
                etkinlikList.add(new Talkshow(rs.getInt("show_id"),
                        rs.getString("show_adı"),
                        rs.getString("showman_adı"),
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
        String query = "SELECT COUNT(show_id) AS total FROM talkshow";

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
        String query = "INSERT INTO talkshow (show_adı, showman_adı,mekan_id,tarih) VALUES ( ?, ?, ?,?)";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, e.getShow_adi());
            preparedStatement.setString(2, e.getShowman_adi());
            preparedStatement.setInt(3, e.getMekan().getMekan_id());
            preparedStatement.setString(4, e.getTarih());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

   


    
    
    
     public void update(Talkshow c){
         try{
           Statement st=this.getConnection().createStatement();
           String query="update talkshow set show_adı='"+c.getShow_adi()+"',showman_adı='"+c.getShowman_adi()+"',mekan_id='"+c.getMekan().getMekan_id()+"',tarih_saat='"+c.getTarih()+"' where show_id="+c.getShow_id();
           
           st.executeUpdate(query);
           
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    

    public void delete(Talkshow c) {
        String query = "DELETE FROM talkshow WHERE show_id = ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, c.getShow_id());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public Talkshow findByID(int id) {
        Talkshow c = null;
        String query = "SELECT * FROM talkshow WHERE show_id = ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Mekan y = this.getMekandao().findByID(rs.getInt("mekan_id"));
                c = new Talkshow(rs.getInt("show_id"),rs.getString("show_adı"), rs.getString("showman_adı") ,y,rs.getString("tarih"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }


}
