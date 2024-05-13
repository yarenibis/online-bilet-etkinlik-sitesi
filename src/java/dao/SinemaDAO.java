/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Sinema;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class SinemaDAO extends DBConnection implements Etkinlik_islem<Sinema> {

    private Connection db;

    private Connection getDb() {
        if (this.db == null) {
            this.db = this.connect();
        }
        return db;
    }

    @Override
    public void create(Sinema sinema) {
        try {
            Statement st = this.getDb().createStatement();
            String query = "INSERT INTO sinema(film_adı, salon_no) VALUES('"
                    + sinema.getFilm_adi() + "',"
                    + sinema.getSalon_no() + ")";
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(Sinema id) {
        try {
            Statement st = this.getDb().createStatement();
            String query = "DELETE FROM sinema WHERE sinema_id=" + id;
            int r = st.executeUpdate(query);
            if (r > 0) {
                System.out.println("Sinema silindi");
            } else {
                System.out.println("Silme başarısız");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Sinema sinema) {
        try {
            Statement st = this.getDb().createStatement();
            String query = "UPDATE sinema SET film_adı='" + sinema.getFilm_adi()
                    + "', salon_no=" + sinema.getSalon_no()
                    + " WHERE sinema_id=" + sinema.getSinema_id();
            int r = st.executeUpdate(query);
            if (r > 0) {
                System.out.println("Sinema güncellendi");
            } else {
                System.out.println("Güncelleme başarısız");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Sinema> list() {
        List<Sinema> sinemaList = new ArrayList<>();
        try {
            Statement st = this.getDb().createStatement();
            String query = "SELECT * FROM sinema";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                sinemaList.add(new Sinema(
                        rs.getInt("sinema_id"),
                        rs.getString("film_adı"),
                        rs.getInt("salon_no")
                ));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return sinemaList;
    }
    
     public Sinema findByName(String film_adi){
       Sinema c=null;
         try{
           Statement st=this.connect().createStatement();
           String query="select * from sinema where film_adı="+film_adi;
           
           ResultSet rs=st.executeQuery(query);
           while(rs.next()){
               c=new Sinema(rs.getInt("sinema_id"),rs.getString("film_adı"),rs.getInt("salon_no"));
           }
           
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return c;
    }

}

