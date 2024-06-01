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
public class Sinema {
    private int sinema_id;
    private String film_adi;
    private int salon_no;
    private Mekan mekan;
    private String tarih;
    
    List<Kullanıcı> klist;
    
    public Sinema(){
        
    }

    public Sinema(int sinema_id, String film_adi, int salon_no,Mekan mekan,String tarih) {
        this.sinema_id = sinema_id;
        this.film_adi = film_adi;
        this.salon_no = salon_no;
        this.mekan=mekan;
        this.tarih=tarih;
    }
    
    public Sinema(int sinema_id, String film_adi, int salon_no,Mekan mekan,String tarih,List<Kullanıcı> klist) {
        this.sinema_id = sinema_id;
        this.film_adi = film_adi;
        this.salon_no = salon_no;
        this.mekan=mekan;
        this.tarih=tarih;
        this.klist=klist;
    }

    public int getSinema_id() {
        return sinema_id;
    }

    public void setSinema_id(int sinema_id) {
        this.sinema_id = sinema_id;
    }

    public String getFilm_adi() {
        return film_adi;
    }

    public void setFilm_adi(String film_adi) {
        this.film_adi = film_adi;
    }

    public int getSalon_no() {
        return salon_no;
    }

    public void setSalon_no(int salon_no) {
        this.salon_no = salon_no;
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

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

 
    
    
    
    
    
}

