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
public class Tiyatro {
    private int id;
    private String adı;
    private String tarih;
    private String mekan;
    private String oyuncu;

    public Tiyatro(int id, String adı, String mekan,String oyuncu ,String tarih) {
        this.id = id;
        this.adı = adı;
        this.tarih = tarih;
        this.mekan = mekan;
        this.oyuncu = oyuncu;
    }

    public Tiyatro() {
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

    public String getOyuncu() {
        return oyuncu;
    }

    public void setOyuncu(String oyuncu) {
        this.oyuncu = oyuncu;
    }


    
    
    
}

