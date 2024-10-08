/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.List;

public class Etkinlik {
    private int id;
    private String adı;
    private String açıklama;
    private Mekan mekan;
    private String tarih_saat;
    private String type;
  
   
    
    public Etkinlik(){
        
    }

    public Etkinlik(int id, String adı, String açıklama, Mekan mekan, String tarih_saat, String type) {
        this.id = id;
        this.adı = adı;
        this.açıklama = açıklama;
        this.mekan= mekan;
        this.tarih_saat = tarih_saat;
        this.type=type;
    }
    
    public Etkinlik(int id, String adı, String açıklama, Mekan mekan, String tarih_saat) {
        this.id = id;
        this.adı = adı;
        this.açıklama = açıklama;
        this.mekan= mekan;
        this.tarih_saat = tarih_saat;
    }

    public Etkinlik(String adı, String açıklama, Mekan mekan, String tarih_saat,String type) {
        this.adı = adı;
        this.açıklama = açıklama;
        this.mekan = mekan;
        this.tarih_saat = tarih_saat;
        this.type=type;
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

    public String getAçıklama() {
        return açıklama;
    }

    public void setAçıklama(String açıklama) {
        this.açıklama = açıklama;
    }

    public Mekan getMekan() {
        return mekan;
    }

    public void setMekan(Mekan mekan) {
        this.mekan = mekan;
    }

    

    public String getTarih_saat() {
        return tarih_saat;
    }

    public void setTarih_saat(String tarih_saat) {
        this.tarih_saat = tarih_saat;
    }

  

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    

    @Override
    public String toString() {
        return adı ;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
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
        final Etkinlik other = (Etkinlik) obj;
        return this.id == other.id;
    }

   
    
 
}
