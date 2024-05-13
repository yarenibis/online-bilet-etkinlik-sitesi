/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Etkinlik;
import entity.Kullanıcı;
import entity.Mekan;
import entity.Yetki;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

/**
 *
 * @author yaren
 */
public class EtkinlikDAO extends DBConnection{
     private Connection db;
     private MekanDAO mekandao;
      private YetkiDAO yetkidao;



    public MekanDAO getMekandao() {
        if(mekandao==null){
            mekandao=new MekanDAO();
        }
        return mekandao;
    }

 
    public Connection getDb() {
        if(this.db==null){
            db=this.connect();
            
        }
        return db;
    }
     
    
    public List<Etkinlik> getEtkinlikList(int page,int pageSize) {
        List<Etkinlik> etkinlikList = new ArrayList();
        int start=(page-1)*pageSize;
        try {
            Statement st = this.getDb().createStatement();
            String query = "select * from etkinlik order by etkinlik_id asc LIMIT "+pageSize+" OFFSET "+start+"";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Mekan m=this.getMekandao().findByID(rs.getInt("mekan_id"));
                etkinlikList.add(new Etkinlik(rs.getInt("etkinlik_id"),
                        rs.getString("etkinlik_adı"),
                        rs.getString("açıklama"),
                        m,
                        rs.getString("tarih_saat")
                ));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return etkinlikList;
    }
    
    
      public int count() {
          int count=0;
        try {
            Statement st = this.getDb().createStatement();
            String query = "select count(etkinlik_id) as total from etkinlik ";
            ResultSet rs = st.executeQuery(query);
            rs.next();
            count=rs.getInt("total");
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }
    
    
    
//     public List<Etkinlik> adminGetetkinlik() {
//        List<Etkinlik> etkinlikList = new ArrayList();
//        try {
//            Statement st = this.getDb().createStatement();
//            String query = "select * from etkinlik";
//            ResultSet rs = st.executeQuery(query);
//            while (rs.next()) {
//                Mekan m=this.getMekandao().findByID(rs.getInt("mekan_id"));
//                etkinlikList.add(new Etkinlik(rs.getInt("etkinlik_id"),
//                        rs.getString("etkinlik_adı"),
//                        rs.getString("açıklama"),
//                        m,
//                        rs.getString("tarih_saat"),
//                        this.admingetkullanıcılar(rs.getInt("etkinlik_id"))
//                ));
//            }
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//        return etkinlikList;
//    }
    
     
     
//    public List<Kullanıcı> admingetkullanıcılar(int etkinlik_id) {
//        List<Kullanıcı> kullist = new ArrayList();
//        try {
//            Statement st = this.connect().createStatement();
//            String query = "select * from kullanıcı where kullanıcı_id in(select kullanıcı_id from katılımcı_bilgisi where etkinlik_id="+etkinlik_id+")";
//
//            ResultSet rs = st.executeQuery(query);
//
//            while (rs.next()) {
//                Yetki y = this.getYetkidao().findByID(rs.getInt("yetki_id"));
//                kullist.add(new Kullanıcı(rs.getInt("kullanıcı_id")
//                        , rs.getString("adı")
//                        , rs.getString("soyadı")
//                        , rs.getString("email")
//                        , rs.getString("şifre")
//                        , y));
//            }
//
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//        return kullist;
//    }
//    
    
    
    //CREATE İŞLEMİ
    public void EtkinlikOluştur(Etkinlik e){
        try{
           Statement st=this.getDb().createStatement();
           String query="insert into etkinlik(etkinlik_adı,açıklama,mekan_id,tarih_saat) values('"+e.getAdı()+"','"+e.getAçıklama()+"','"+e.getMekan().getMekan_id()+"','"+e.getTarih_saat()+"')";
           st.executeUpdate(query);
            
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
   
    
    public void admincreate(Etkinlik e){
        try{
           Statement st=this.getDb().createStatement();
           String query="insert into etkinlik(etkinlik_adı,açıklama,mekan_id,tarih_saat) values('"+e.getAdı()+"','"+e.getAçıklama()+"','"+e.getMekan().getMekan_id()+"','"+e.getTarih_saat()+"')";
           st.executeUpdate(query);
           ResultSet rs=st.executeQuery("select max(etkinlik_id) as mid from etkinlik");
           rs.next();
           
           int et_id=rs.getInt("mid");
           
           for(Kullanıcı k:e.getKlist()){
               query="insert into katılımcı_bilgisi(etkinlik_id,kullanıcı_id) values("+et_id+","+k.getKullanıcı_id()+")";
               st.executeUpdate(query);
           }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
 
     public void adminupdate(Etkinlik c){
         try{
           Statement st=this.connect().createStatement();
           String query="update etkinlik set etkinlik_adı='"+c.getAdı()+"',açıklama='"+c.getAçıklama()+"',mekan_id='"+c.getMekan().getMekan_id()+"',tarih_saat='"+c.getTarih_saat()+"' where etkinlik_id="+c.getId();
           
           st.executeUpdate(query);
           st.executeUpdate("delete  from katılımcı_bilgisi where etkinlik_id= "+c.getId());
           
         
           
           for(Kullanıcı k:c.getKlist()){
               query="insert into katılımcı_bilgisi(etkinlik_id,kullanıcı_id) values("+c.getId()+","+k.getKullanıcı_id()+")";
               st.executeUpdate(query);
           }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
     
     public void admindelete(Etkinlik c){
         try{
           Statement st=this.connect().createStatement();
           st.executeUpdate("delete  from katılımcı_bilgisi where etkinlik_id= "+c.getId());
           String query="delete from etkinlik where etkinlik_id="+c.getId();
           
           st.executeUpdate(query);
            
           
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
     
     
     
    public void delete(Etkinlik c){
         try{
           Statement st=this.connect().createStatement();
           String query="delete from etkinlik where etkinlik_id="+c.getId();
           
           st.executeUpdate(query);
           
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
    public void update(Etkinlik c){
         try{
           Statement st=this.connect().createStatement();
           String query="update etkinlik set etkinlik_adı='"+c.getAdı()+"',açıklama='"+c.getAçıklama()+"',mekan_id='"+c.getMekan().getMekan_id()+"',tarih_saat='"+c.getTarih_saat()+"' where etkinlik_id="+c.getId();
           
           st.executeUpdate(query);
           
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
   
    
    public Etkinlik findByID(int id) {
        Etkinlik c = null;
        try {
            String query = "SELECT * FROM etkinlik WHERE etkinlik_id=?";
            PreparedStatement preparedStatement = this.connect().prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Mekan y=this.getMekandao().findByID(rs.getInt("mekan_id"));
                c = new Etkinlik(rs.getInt("etkinlik_id"), rs.getString("etkinlik_adı"), rs.getString("açıklama"),y , rs.getString("tarih_saat"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }
    

}
