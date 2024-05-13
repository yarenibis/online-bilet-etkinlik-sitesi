/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import dao.MekanDAO;
import entity.Mekan;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author yaren
 */
@Named(value = "mekanBean")
@SessionScoped
public class MekanBean implements Serializable {

    private Mekan entity;
    private MekanDAO dao;
    private List<Mekan> list;
    
    
    
    
    public MekanBean() {
    }

    
    public void clear(){
        entity=new Mekan();
    }
    public void create(){
        this.getDao().MekanOluştur(entity);
        entity=new Mekan();
    }
    
    public void update(){
        this.getDao().update(entity);
        entity=new Mekan();
    }
    
    public void delete(Mekan c){
        this.getDao().delete(c);
        entity=new Mekan();
    }
    
    
    public Mekan getEntity() {
        if(this.entity==null){
            entity=new Mekan();
        }
        return entity;
    }

    public void setEntity(Mekan entity) {
        this.entity = entity;
    }

    public MekanDAO getDao() {
        if(this.dao==null){
            dao=new MekanDAO();
        }
        return dao;
    }
  
    
    public void setDao(MekanDAO dao) {
        this.dao = dao;
    }

    public List<Mekan> getList() {
        this.list=this.getDao().getMekanList();
        return list;
    }

    public void setList(List<Mekan> list) {
        this.list = list;
    }
    
}
