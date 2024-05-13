/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author yaren
 */
public class Sinema {
    private int sinema_id;
    private String film_adi;
    private int salon_no;
    
    public Sinema(){
        
    }

    public Sinema(int sinema_id, String film_adi, int salon_no) {
        this.sinema_id = sinema_id;
        this.film_adi = film_adi;
        this.salon_no = salon_no;
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

 
    
    
    
    
    
}

