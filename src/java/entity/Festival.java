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
public class Festival {
    private int festival_id;
    private String festival_adi;
    private Mekan mekan;
    private String tarih;
    
    private List<Kullanıcı> klist;
    
    public Festival(){
        
    }

    public Festival(int festival_id, String festival_adi,Mekan mekan, String tarih) {
        this.festival_id = festival_id;
        this.festival_adi = festival_adi;
        this.mekan=mekan;
        this.tarih = tarih;
    }

public Festival(int festival_id, String festival_adi,Mekan mekan, String tarih,List<Kullanıcı> klist) {
        this.festival_id = festival_id;
        this.festival_adi = festival_adi;
        this.mekan=mekan;
        this.tarih = tarih;
        this.klist=klist;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.festival_id;
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
        final Festival other = (Festival) obj;
        return this.festival_id == other.festival_id;
    }

    
    
    
    
    
}

