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
public class Talkshow {
    private int show_id;
    private String show_adi;
    private String showman_adi;
    private String mekan;
    private String tarih;

    public Talkshow(int show_id, String show_adi, String showman_adi,String mekan, String tarih) {
        this.show_id = show_id;
        this.show_adi = show_adi;
        this.showman_adi = showman_adi;
        this.mekan=mekan;
        this.tarih = tarih;
    }

    public Talkshow() {
    }

    public int getShow_id() {
        return show_id;
    }

    public void setShow_id(int show_id) {
        this.show_id = show_id;
    }

    public String getShow_adi() {
        return show_adi;
    }

    public void setShow_adi(String show_adi) {
        this.show_adi = show_adi;
    }

    public String getShowman_adi() {
        return showman_adi;
    }

    public void setShowman_adi(String showman_adi) {
        this.showman_adi = showman_adi;
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

