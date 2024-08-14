/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author yaren
 */
public class Admin {
     private int id;
    private String adı;
    private String soyadı;
    private String email;
    private String şifre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Admin(int id, String adı, String soyadı, String email, String şifre) {
        this.id = id;
        this.adı = adı;
        this.soyadı = soyadı;
        this.email = email;
        this.şifre = şifre;
    }

    public Admin(String adı, String soyadı, String email, String şifre) {
        this.adı = adı;
        this.soyadı = soyadı;
        this.email = email;
        this.şifre = şifre;
    }

    public Admin() {
    }
    

   
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
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
        final Admin other = (Admin) obj;
        return this.id == other.id;
    }
}
