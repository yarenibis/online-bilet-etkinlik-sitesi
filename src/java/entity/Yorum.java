/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author yaren
 */
public class Yorum {
    
    private int yorum_id;
    private Etkinlik etkinlik_id;
    private Kullanıcı kullanıcı_id;
    private String içerik;
    private Date tarih;

    public Yorum() {
    }

    public Yorum(int yorum_id, Etkinlik etkinlik_id, Kullanıcı kullanıcı_id, String içerik, Date tarih) {
        this.yorum_id = yorum_id;
        this.etkinlik_id = etkinlik_id;
        this.kullanıcı_id = kullanıcı_id;
        this.içerik = içerik;
        this.tarih = tarih;
    }

    public int getYorum_id() {
        return yorum_id;
    }

    public void setYorum_id(int yorum_id) {
        this.yorum_id = yorum_id;
    }

    public Etkinlik getEtkinlik_id() {
        return etkinlik_id;
    }

    public void setEtkinlik_id(Etkinlik etkinlik_id) {
        this.etkinlik_id = etkinlik_id;
    }

    public Kullanıcı getKullanıcı_id() {
        return kullanıcı_id;
    }

    public void setKullanıcı_id(Kullanıcı kullanıcı_id) {
        this.kullanıcı_id = kullanıcı_id;
    }

    public String getIçerik() {
        return içerik;
    }

    public void setIçerik(String içerik) {
        this.içerik = içerik;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }
    
    
    
}

