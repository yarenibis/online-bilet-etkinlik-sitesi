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
public class Talkshow extends Etkinlik {

    private String showman_adi;
    private int etkinlik_id;

    public Talkshow(int id, String adı, String açıklama, Mekan mekan, String tarih_saat, String type, String showman_adi, int etkinlik_id) {
        super(id, adı, açıklama, mekan, tarih_saat, "Talkshow");
        this.showman_adi = showman_adi;
        this.etkinlik_id = etkinlik_id;
    }

    public int getEtkinlik_id() {
        return etkinlik_id;
    }

    public void setEtkinlik_id(int etkinlik_id) {
        this.etkinlik_id = etkinlik_id;
    }

    public Talkshow() {
    }

    public String getShowman_adi() {
        return showman_adi;
    }

    public void setShowman_adi(String showman_adi) {
        this.showman_adi = showman_adi;
    }
}
