/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Talkshow;
import entity.Talkshow;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class TalkShowDAO extends DBConnection implements Etkinlik_islem<Talkshow>{
    private Connection db;
    
    private Connection getDb() {
        if (this.db == null) {
            this.db = this.connect();
        }
        return db;
    }

    @Override
    public void create(Talkshow talkShow) {
         try {
            Statement st = this.getDb().createStatement();
            String query = "INSERT INTO talkshow(show_adı, showman_adı,mekan, tarih) VALUES('" +
                            talkShow.getShow_adi() + "','" +
                            talkShow.getShowman_adi() + "','" +
                            talkShow.getMekan() + "','" +
                            talkShow.getTarih() + "')";
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(Talkshow id) {
        try {
            Statement st = this.getDb().createStatement();
            String query = "DELETE FROM talkshow WHERE show_id=" + id;
            int r = st.executeUpdate(query);
            if (r > 0) {
                System.out.println("Talkshow silindi");
            } else {
                System.out.println("Silme başarısız");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Talkshow talkShow) {
        try {
            Statement st = this.getDb().createStatement();
            String query = "UPDATE talkshow SET show_adı='" + talkShow.getShow_adi() + 
                           "', showman_adı='" + talkShow.getShowman_adi() + 
                           
                           "', mekan='" + talkShow.getMekan() + 
                           "', tarih='" + talkShow.getTarih() + 
                           "' WHERE show_id=" + talkShow.getShow_id();
            int r = st.executeUpdate(query);
            if (r > 0) {
                System.out.println("Talkshow güncellendi");
            } else {
                System.out.println("Güncelleme başarısız");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Talkshow> list() {
        List<Talkshow> talkShowList = new ArrayList<>();
        try {
            Statement st = this.getDb().createStatement();
            String query = "SELECT * FROM talkshow";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                talkShowList.add(new Talkshow(
                        rs.getInt("show_id"),
                        rs.getString("show_adı"),
                        rs.getString("showman_adı"),
                        rs.getString("mekan"),
                        rs.getString("tarih")
                ));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return talkShowList;
    }
    
}
