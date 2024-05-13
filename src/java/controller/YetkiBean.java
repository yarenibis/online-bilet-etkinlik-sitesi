/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.YetkiDAO;
import entity.Yetki;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "yetkiBean")
@SessionScoped
public class YetkiBean implements Serializable {

    private Yetki entity;
    private YetkiDAO dao;
    private List<Yetki> list;
    
    
    public YetkiBean() {
    }

    
    public void clear(){
        entity=new Yetki();
    }
    public void create(){
        this.getDao().create(entity);
        entity=new Yetki();
    }
    
    public void update(){
        this.getDao().update(entity);
        entity=new Yetki();
    }
    
    public void delete(){
        this.getDao().delete(entity);
        entity=new Yetki();
    }
    
    
    public Yetki getEntity() {
        if(this.entity==null){
            entity=new Yetki();
        }
        return entity;
    }

    public void setEntity(Yetki entity) {
        this.entity = entity;
    }

    public YetkiDAO getDao() {
        if(this.dao==null){
            dao=new YetkiDAO();
        }
        return dao;
    }
  
    
    public void setDao(YetkiDAO dao) {
        this.dao = dao;
    }

    public List<Yetki> getList() {
        this.list=this.getDao().getYetkiList();
        return list;
    }

    public void setList(List<Yetki> list) {
        this.list = list;
    }
    
}

