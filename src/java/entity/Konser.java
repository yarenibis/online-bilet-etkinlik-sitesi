/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.List;

public class Konser extends Etkinlik {

    private String sanatçı;
    private int etkinlik_id;

    public Konser() {
    }

    public Konser(int id, String adı, String açıklama, Mekan mekan, String tarih_saat, String type, String sanatçı, int etkinlik_id) {
        super(id, adı, açıklama, mekan, tarih_saat, "Konser");
        this.sanatçı = sanatçı;
        this.etkinlik_id = etkinlik_id;
    }

    public String getSanatçı() {
        return sanatçı;
    }

    public void setSanatçı(String sanatçı) {
        this.sanatçı = sanatçı;
    }

    public int getEtkinlik_id() {
        return etkinlik_id;
    }

    public void setEtkinlik_id(int etkinlik_id) {
        this.etkinlik_id = etkinlik_id;
    }

}
