/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.List;

/**
 *
 * @author yaren
 */
public class Kullanıcı {
    private int kullanıcı_id;
    private String adı;
    private String soyadı;
    private String email;
    private String şifre;
    private List<Yetki> yetkiler;
    

    public Kullanıcı() {
    }

    public Kullanıcı(String email, String şifre) {
        this.email = email;
        this.şifre = şifre;
    }

    public Kullanıcı(int kullanıcı_id, String adı, String soyadı, String email, String şifre,List<Yetki> yetkiler) {
        this.kullanıcı_id = kullanıcı_id;
        this.adı = adı;
        this.soyadı = soyadı;
        this.email = email;
        this.şifre = şifre;
        this.yetkiler=yetkiler;
    }
      public Kullanıcı(int kullanıcı_id, String adı, String soyadı, String email, String şifre) {
        this.kullanıcı_id = kullanıcı_id;
        this.adı = adı;
        this.soyadı = soyadı;
        this.email = email;
        this.şifre = şifre;
    
    }

    public Kullanıcı(String adı, String soyadı, String email, String şifre) {
        this.adı = adı;
        this.soyadı = soyadı;
        this.email = email;
        this.şifre = şifre;
    }

   

    
    
    public int getKullanıcı_id() {
        return kullanıcı_id;
    }

    public void setKullanıcı_id(int kullanıcı_id) {
        this.kullanıcı_id = kullanıcı_id;
    }

    public String getAdı() {
        return adı;
    }

    public void setAdı(String adı) {
        this.adı = adı;
    }

    public String getSoyadı() {
        return soyadı;
    }

    public void setSoyadı(String soyadı) {
        this.soyadı = soyadı;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getŞifre() {
        return şifre;
    }

    public void setŞifre(String şifre) {
        this.şifre = şifre;
    }

    public List<Yetki> getYetkiler() {
        return yetkiler;
    }

    public void setYetkiler(List<Yetki> yetkiler) {
        this.yetkiler = yetkiler;
    }

    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.kullanıcı_id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kullanıcı other = (Kullanıcı) obj;
        return this.kullanıcı_id == other.kullanıcı_id;
    }
    
    
    
}
