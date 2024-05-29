/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import dao.BiletDAO;
import entity.Bilet;
import entity.Kullanıcı;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;



@Named(value = "biletBean")
@SessionScoped
public class BiletBean implements Serializable {

    
    private BiletDAO dao;
    private Bilet entity;
    private List<Bilet> biletlist;
    
    
     public BiletBean() {
    }
   
    
    public void update(){
       this.getDao().updateBilet(entity);
    }
    
    public BiletDAO getDao() {
        if(this.dao==null){
            this.dao=new BiletDAO();
        }
        return dao;
    }

    public void setDao(BiletDAO dao) {
        this.dao = dao;
    }

    public Bilet getEntity() {
        if(this.entity==null){
            this.entity=new Bilet();
        }
        return entity;
    }

    public void setEntity(Bilet entity) {
        this.entity = entity;
    }

    public List<Bilet> getBiletlist() {
        this.biletlist=this.getDao().getBiletList();
        return biletlist;
    }

    public void setBiletlist(List<Bilet> notelist) {
        this.biletlist = notelist;
    }
    
    public String biletoluştur(){
        return "/kullanıcı/bilet-succeed";
    }


    
}
