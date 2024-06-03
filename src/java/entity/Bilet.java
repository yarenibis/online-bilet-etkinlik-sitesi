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
    private Etkinlik etkinlik_id;
    private Kullanıcı kullanıcı_id;


    public Bilet() {
    }

    public Bilet(int bilet_id, Etkinlik etkinlik_id, Kullanıcı kullanıcı_id) {
        this.bilet_id = bilet_id;
        this.etkinlik_id = etkinlik_id;
        this.kullanıcı_id = kullanıcı_id;
    }

     public Bilet( Etkinlik etkinlik_id, Kullanıcı kullanıcı_id) {
        this.etkinlik_id = etkinlik_id;
        this.kullanıcı_id = kullanıcı_id;
    }
   
   
    
    public Bilet(Etkinlik etkinlik_id){
        this.etkinlik_id=etkinlik_id;
    }
    
    public int getBilet_id() {
        return bilet_id;
    }

    public void setBilet_id(int bilet_id) {
        this.bilet_id = bilet_id;
    }

    public Etkinlik getEtkinlik_id() {
        return etkinlik_id;
    }

    public void setEtkinlik_id(Etkinlik etkinlik_id) {
        this.etkinlik_id = etkinlik_id;
    }

    public Kullanıcı getKullanıcı_id() {
        return kullanıcı_id;
    }

    public void setKullanıcı_id(Kullanıcı kullanıcı_id) {
        this.kullanıcı_id = kullanıcı_id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.bilet_id;
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
        final Bilet other = (Bilet) obj;
        return this.bilet_id == other.bilet_id;
    }

  
    
}
