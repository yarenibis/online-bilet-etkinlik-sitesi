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
public class Sinema extends Etkinlik {

    private int salon_no;
    private int etkinlik_id;

    public Sinema() {

    }

    public Sinema(int id, String adı, String açıklama, Mekan mekan, String tarih_saat, String type, int salon_no, int etkinlik_id) {
        super(id, adı, açıklama, mekan, tarih_saat, "Sinema");
        this.salon_no = salon_no;
        this.etkinlik_id = etkinlik_id;
    }

    public int getSalon_no() {
        return salon_no;
    }

    public void setSalon_no(int salon_no) {
        this.salon_no = salon_no;
    }

    public int getEtkinlik_id() {
        return etkinlik_id;
    }

    public void setEtkinlik_id(int etkinlik_id) {
        this.etkinlik_id = etkinlik_id;
    }

}
