/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Bilet;
import entity.Etkinlik;
import entity.Kullanıcı;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

/**
 *
 * @author yaren
 */
public class BiletDAO extends DBConnection {

    private Connection db;
    private KullanıcıDAO kdao;
    private EtkinlikDAO edao;

    public EtkinlikDAO getEdao() {
        if(edao==null){
            edao=new EtkinlikDAO();
        }
        return edao;
    }

    public void setEdao(EtkinlikDAO edao) {
        this.edao = edao;
    }
    
    

    public KullanıcıDAO getKdao() {
        if(kdao==null){
            kdao=new KullanıcıDAO();
        }
        return kdao;
    }

    public void setKdao(KullanıcıDAO kdao) {
        this.kdao = kdao;
    }
    
    

    public List<Bilet> getBiletList() {
        List<Bilet> biletlist = new ArrayList<>();
        String query = "SELECT * FROM bilet";
        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query); ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Etkinlik e=this.getEdao().findByID(rs.getInt("etkinlik_id"));
                Kullanıcı k=this.getKdao().findByID(rs.getInt("kullanıcı_id"));
                biletlist.add(new Bilet(
                        rs.getInt("bilet_id"),
                        e,
                        k
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return biletlist;
    }

    public void createBilet(Bilet bilet) {
        String query = "INSERT INTO bilet (etkinlik_id, kullanıcı_id) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, bilet.getEtkinlik_id().getId());
            preparedStatement.setInt(2, bilet.getKullanıcı_id().getKullanıcı_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 
    
    
    public void delete(Bilet c) {
        String query = "DELETE FROM bilet WHERE bilet_id = ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, c.getBilet_id());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateBilet(Bilet b) {
        String query = "UPDATE bilet SET etkinlik_id = ?, kullanıcı_id = ? WHERE bilet_id = ?";
        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, b.getEtkinlik_id().getId());
            preparedStatement.setInt(2, b.getKullanıcı_id().getKullanıcı_id());
            preparedStatement.setInt(3, b.getBilet_id());
            int r = preparedStatement.executeUpdate();
            if (r > 0) {
                System.out.println("Bilet güncellendi");
            } else {
                System.out.println("Güncelleme başarısız");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
     public Bilet findByID(int id) {
        Bilet c = null;
        String query = "SELECT * FROM bilet WHERE kullanıcı_id = ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Etkinlik e=this.getEdao().findByID(rs.getInt("etkinlik_id"));
                Kullanıcı k=this.getKdao().findByID(rs.getInt("kullanıcı_id"));
                c = new Bilet(rs.getInt("bilet_id"), e, k);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }
     
     
     
      public List<Bilet> findByUserID(int kullanıcıId) {
        List<Bilet> biletList = new ArrayList<>();
        String query = "SELECT * FROM bilet WHERE kullanıcı_id = ?";

        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, kullanıcıId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
               Etkinlik e=this.getEdao().findByID(rs.getInt("etkinlik_id"));
                Kullanıcı k=this.getKdao().findByID(rs.getInt("kullanıcı_id"));
                Bilet bilet = new Bilet(rs.getInt("bilet_id"), e, k);
                biletList.add(bilet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return biletList;
    }
     
     
     



     
     
    
    private Connection getDb() {
        if (this.db == null) {
            this.db = this.getConnection();
        }
        return db;
    }
}
