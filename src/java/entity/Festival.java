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
public class Festival {
    private int festival_id;
    private String festival_adi;
    private String mekan;
    private String tarih;
    
    public Festival(){
        
    }

    public Festival(int festival_id, String festival_adi,String mekan, String tarih) {
        this.festival_id = festival_id;
        this.festival_adi = festival_adi;
        this.mekan=mekan;
        this.tarih = tarih;
    }



    public int getFestival_id() {
        return festival_id;
    }

    public void setFestival_id(int festival_id) {
        this.festival_id = festival_id;
    }

    public String getFestival_adi() {
        return festival_adi;
    }

    public void setFestival_adi(String festival_adi) {
        this.festival_adi = festival_adi;
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

    
    
    
    
    
}

