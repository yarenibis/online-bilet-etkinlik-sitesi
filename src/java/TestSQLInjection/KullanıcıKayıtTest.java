/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestSQLInjection;

import dao.KullanıcıDAO;
import entity.Kullanıcı;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class KullanıcıKayıtTest {
    public static void main(String[] args) {
        try {
            // Veritabanı bağlantısını kur
            KullanıcıDAO kullanıcıDAO = new KullanıcıDAO();
            // Yeni bir kullanıcı oluştur
            Kullanıcı yeniKullanıcı = new Kullanıcı();
            yeniKullanıcı.setAdı("Salih");
            yeniKullanıcı.setSoyadı("Yılmaz");
            yeniKullanıcı.setEmail("salii@gmail.com");
            yeniKullanıcı.setŞifre("123");

            // Yeni kullanıcıyı kaydet
            kullanıcıDAO.kullanıcıkayıt(yeniKullanıcı);

            // Kullanıcının veritabanına eklendiğini doğrula
            try (Statement st = kullanıcıDAO.connect().createStatement()) {
                ResultSet rs = st.executeQuery("SELECT * FROM kullanıcı WHERE email = 'salii@gmail.com'");
                if (rs.next()) {
                    System.out.println("Test durumu (kullanıcı kaydı): Basarili");
                } else {
                    System.out.println("Test durumu (kullanıcı kaydı): Basarisiz");
                }
            }

        } catch (SQLException ex) {
            System.out.println("Veritabanı bağlantısı başarısız: " + ex.getMessage());
        }
    }
}

