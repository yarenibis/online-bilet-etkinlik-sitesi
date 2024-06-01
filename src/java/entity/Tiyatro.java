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
public class Tiyatro {
    private int id;
    private String adı;
    private Mekan mekan;
    private String oyuncu;
    private String tarih;
    
    
    List<Kullanıcı> klist;
    
    public Tiyatro(int id, String adı, Mekan mekan, String oyuncu, String tarih) {
        this.id = id;
        this.adı = adı;
        this.mekan = mekan;
        this.oyuncu = oyuncu;
        this.tarih = tarih;
    }

    public Tiyatro(int id, String adı, Mekan mekan, String oyuncu, String tarih, List<Kullanıcı> klist) {
        this.id = id;
        this.adı = adı;
        this.mekan = mekan;
        this.oyuncu = oyuncu;
        this.tarih = tarih;
        this.klist = klist;
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

    public Mekan getMekan() {
        return mekan;
    }

    public void setMekan(Mekan mekan) {
        this.mekan = mekan;
    }

    public String getOyuncu() {
        return oyuncu;
    }

    public void setOyuncu(String oyuncu) {
        this.oyuncu = oyuncu;
    }

    public List<Kullanıcı> getKlist() {
        return klist;
    }

    public void setKlist(List<Kullanıcı> klist) {
        this.klist = klist;
    }


    
    
    
}

