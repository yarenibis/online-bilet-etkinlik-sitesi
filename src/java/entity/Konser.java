/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;


public class Konser {
    private int id;
    private String adı;
    private String tarih;
    private String mekan;
    private String sanatçı;

    
    
    public Konser() {
    }
    
   
    public Konser(int id ,String adı,String mekan,String tarih, String sanatçı ) {
        this.id = id;
        this.adı = adı;
        this.tarih = tarih;
        this.mekan = mekan;
        this.sanatçı = sanatçı;
    }

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

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getMekan() {
        return mekan;
    }

    public void setMekan(String mekan) {
        this.mekan = mekan;
    }

    public String getSanatçı() {
        return sanatçı;
    }

    public void setSanatçı(String sanatçı) {
        this.sanatçı = sanatçı;
    }


    
    
    
    
}

