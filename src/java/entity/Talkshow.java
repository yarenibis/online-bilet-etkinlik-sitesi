/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;
import java.util.List;

/**
 *
 * @author yaren
 */
public class Talkshow {
    private int show_id;
    private String show_adi;
    private String showman_adi;
    private Mekan mekan;
    private String tarih;
    
    List<Kullanıcı> klist;
    

    public Talkshow(int show_id, String show_adi, String showman_adi,Mekan mekan, String tarih) {
        this.show_id = show_id;
        this.show_adi = show_adi;
        this.showman_adi = showman_adi;
        this.mekan=mekan;
        this.tarih = tarih;
    }
    
    public Talkshow(int show_id, String show_adi, String showman_adi,Mekan mekan, String tarih,List<Kullanıcı> klist) {
        this.show_id = show_id;
        this.show_adi = show_adi;
        this.showman_adi = showman_adi;
        this.mekan=mekan;
        this.tarih = tarih;
        this.klist=klist;
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

    public Mekan getMekan() {
        return mekan;
    }

    public void setMekan(Mekan mekan) {
        this.mekan = mekan;
    }

    public List<Kullanıcı> getKlist() {
        return klist;
    }

    public void setKlist(List<Kullanıcı> klist) {
        this.klist = klist;
    }
    
    
}

