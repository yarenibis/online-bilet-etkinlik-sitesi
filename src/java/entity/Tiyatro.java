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
public class Tiyatro extends Etkinlik {

    private String oyuncu;
    private int etkinlik_id;

    public Tiyatro(int id, String adı, String açıklama, Mekan mekan, String tarih_saat, String type, String oyuncu, int etkinlik_id) {
        super(id, adı, açıklama, mekan, tarih_saat, "Tiyatro");
        this.oyuncu = oyuncu;
        this.etkinlik_id = etkinlik_id;
    }

    public int getEtkinlik_id() {
        return etkinlik_id;
    }

    public void setEtkinlik_id(int etkinlik_id) {
        this.etkinlik_id = etkinlik_id;
    }

    public Tiyatro() {
    }

    public String getOyuncu() {
        return oyuncu;
    }

    public void setOyuncu(String oyuncu) {
        this.oyuncu = oyuncu;
    }

}
