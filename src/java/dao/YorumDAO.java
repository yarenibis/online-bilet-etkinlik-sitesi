package dao;

import entity.Yorum;
import java.sql.Connection;
import java.sql.Statement;
import util.DBConnection;

public class YorumDAO extends DBConnection {
    private Connection db;

    public void create(Yorum y) {
        try {
            Statement st = this.getDb().createStatement();
            String query = "insert into yorum(yorum_id, etkinlik_id, kullanici_id, icerik, tarih) values('" + y.getYorum_id() + "','" + y.getEtkinlik_id() + "','" + y.getKullanıcı_id() + "','" + y.getIçerik() + "','" + y.getTarih() + "')";
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Yorum y) {
        try {
            Statement st = this.getDb().createStatement();
            String query = "update yorum set etkinlik_id='" + y.getEtkinlik_id() + "', kullanici_id='" + y.getKullanıcı_id() + "', icerik='" + y.getIçerik() + "', tarih='" + y.getTarih() + "' where yorum_id=" + y.getYorum_id();
            int r = st.executeUpdate(query);
            if (r > 0) {
                System.out.println("Yorum güncellendi");
            } else {
                System.out.println("Güncelleme başarısız");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Yorum y) {
        try {
            Statement st = this.getDb().createStatement();
            String query = "delete from yorum where yorum_id=" + y.getYorum_id();
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

   
private Connection getDb(){
   if(this.db==null){
     this.db=this.connect();
}
return db;
  

}

}

