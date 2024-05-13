/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author yaren
 */
public class Mekan {
    private int mekan_id;
    private String mekan_adi;
    private String adres;
    private int kapasite;
    
    public Mekan(){
        
    }

    public Mekan(int mekan_id, String mekan_adi, String adres, int kapasite) {
        this.mekan_id = mekan_id;
        this.mekan_adi = mekan_adi;
        this.adres = adres;
        this.kapasite = kapasite;
    }

    public Mekan(String mekan_adi, String adres, int kapasite) {
        this.mekan_adi = mekan_adi;
        this.adres = adres;
        this.kapasite = kapasite;
    }

    
    
    public int getMekan_id() {
        return mekan_id;
    }

    public void setMekan_id(int mekan_id) {
        this.mekan_id = mekan_id;
    }

    public String getMekan_adi() {
        return mekan_adi;
    }

    public void setMekan_adi(String mekan_adi) {
        this.mekan_adi = mekan_adi;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public int getKapasite() {
        return kapasite;
    }

    public void setKapasite(int kapasite) {
        this.kapasite = kapasite;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.mekan_id;
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
        final Mekan other = (Mekan) obj;
        return this.mekan_id == other.mekan_id;
    }

    @Override
    public String toString() {
        return  mekan_adi  ;
    }
    
    
    
}
