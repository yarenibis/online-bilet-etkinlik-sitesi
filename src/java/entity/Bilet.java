/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author yaren
 */
public class Bilet {
    private int bilet_id;
    private int etkinlik_id;
    private int kullanıcı_id;


    public Bilet() {
    }

    public Bilet(int bilet_id, int etkinlik_id, int kullanıcı_id) {
        this.bilet_id = bilet_id;
        this.etkinlik_id = etkinlik_id;
        this.kullanıcı_id = kullanıcı_id;
    }

     public Bilet( int etkinlik_id, int kullanıcı_id) {
        this.etkinlik_id = etkinlik_id;
        this.kullanıcı_id = kullanıcı_id;
    }
   
   
    
    public Bilet(int etkinlik_id){
        this.etkinlik_id=etkinlik_id;
    }
    
    public int getBilet_id() {
        return bilet_id;
    }

    public void setBilet_id(int bilet_id) {
        this.bilet_id = bilet_id;
    }

    public int getEtkinlik_id() {
        return etkinlik_id;
    }

    public void setEtkinlik_id(int etkinlik_id) {
        this.etkinlik_id = etkinlik_id;
    }

    public int getKullanıcı_id() {
        return kullanıcı_id;
    }

    public void setKullanıcı_id(int kullanıcı_id) {
        this.kullanıcı_id = kullanıcı_id;
    }

  
    
}
