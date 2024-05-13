/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author yaren
 */
public class Yetki {
    private int yetki_id;
    private String yetki_adi;

    public Yetki() {
    }

    public Yetki(int yetki_id, String yetki_adi) {
        this.yetki_id = yetki_id;
        this.yetki_adi = yetki_adi;
    }

    public int getYetki_id() {
        return yetki_id;
    }

    public void setYetki_id(int yetki_id) {
        this.yetki_id = yetki_id;
    }

    public String getYetki_adi() {
        return yetki_adi;
    }

    public void setYetki_adi(String yetki_adi) {
        this.yetki_adi = yetki_adi;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.yetki_id;
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
        final Yetki other = (Yetki) obj;
        return this.yetki_id == other.yetki_id;
    }

    @Override
    public String toString() {
        return  yetki_adi ;
    }

    
    
}
