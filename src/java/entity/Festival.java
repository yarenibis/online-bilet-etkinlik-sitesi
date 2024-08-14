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
public class Festival extends Etkinlik{
   private int etkinlik_id;
   
    public Festival(){
        
    }
    public Festival(int id, String adı, String açıklama, Mekan mekan, String tarih_saat, String type,int etkinlik_id) {
        super(id,adı,açıklama,mekan,tarih_saat,"Festival");
     this.etkinlik_id=etkinlik_id;
    }

    public int getEtkinlik_id() {
        return etkinlik_id;
    }

    public void setEtkinlik_id(int etkinlik_id) {
        this.etkinlik_id = etkinlik_id;
    }

    
}

