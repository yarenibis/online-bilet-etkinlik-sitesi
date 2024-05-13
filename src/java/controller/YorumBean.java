/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import dao.YorumDAO;
import entity.Yorum;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author yaren
 */
@Named(value = "yorumBean")
@SessionScoped
public class YorumBean implements Serializable {

    private Yorum entity;
    private YorumDAO dao;
    private List<Yorum> list;
    
    
    
    
    public YorumBean() {
    }

    
   
   
    
    
    public Yorum getEntity() {
        if(this.entity==null){
            entity=new Yorum();
        }
        return entity;
    }

    public void setEntity(Yorum entity) {
        this.entity = entity;
    }

    public YorumDAO getDao() {
        if(this.dao==null){
            dao=new YorumDAO();
        }
        return dao;
    }
  
    
    public void setDao(YorumDAO dao) {
        this.dao = dao;
    }

  
    
}

