/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.List;



public class Konser {
    private int id;
    private String adı;
    private String tarih;
    private Mekan mekan;
    private String sanatçı;

    List<Kullanıcı> klist;
    
    
    public Konser() {
    }
    
   
    public Konser(int id ,String adı,Mekan mekan,String tarih, String sanatçı ) {
        this.id = id;
        this.adı = adı;
        this.tarih = tarih;
        this.mekan = mekan;
        this.sanatçı = sanatçı;
    }
    
    public Konser(String adı,Mekan mekan,String tarih, String sanatçı ) {
        this.adı = adı;
        this.tarih = tarih;
        this.mekan = mekan;
        this.sanatçı = sanatçı;
    }
    
     public Konser(int id ,String adı,Mekan mekan,String tarih, String sanatçı,List<Kullanıcı> klist ) {
        this.id = id;
        this.adı = adı;
        this.tarih = tarih;
        this.mekan = mekan;
        this.sanatçı = sanatçı;
        this.klist=klist;
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

    public String getSanatçı() {
        return sanatçı;
    }

    public void setSanatçı(String sanatçı) {
        this.sanatçı = sanatçı;
    }

    public List<Kullanıcı> getKlist() {
        return klist;
    }

    public void setKlist(List<Kullanıcı> klist) {
        this.klist = klist;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.id;
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
        final Konser other = (Konser) obj;
        return this.id == other.id;
    }


    
    
    
    
}

