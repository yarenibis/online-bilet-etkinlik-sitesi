/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import dao.KullanıcıDAO;
import entity.Kullanıcı;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;



/**
 *
 * @author yaren
 */
@Named(value = "kullanıcıBean")
@SessionScoped
public class KullanıcıBean implements Serializable {

    private Kullanıcı entity;
    private KullanıcıDAO dao;
    private List<Kullanıcı> list;

   
    
    private String email;
    private String şifre;
   
    
   public void clear(){
        entity=new Kullanıcı();
    }
   
   
    public void kullanıcıkayıt(){
         this.getDao().kullanıcıkayıt(entity);
         entity=new Kullanıcı();
     }
    
    public void create(){
        this.getDao().create(entity);
         entity=new Kullanıcı();
    }
    
    public void update(){
        this.getDao().adminupdate(entity);
        entity=new Kullanıcı();
    }
    
    public void delete(Kullanıcı c){
        this.getDao().admindelete(c);
        entity=new Kullanıcı();
    }
     
     
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

    public String getŞifre() {
        return şifre;
    }

    public void setŞifre(String şifre) {
        this.şifre = şifre;
    }
  
    
    public KullanıcıBean() {
    }
   

 
    public String giriş(Kullanıcı selectedkullanıcı) {
         if (this.getDao().kullanıcıGirişi(entity.getEmail(), entity.getŞifre())) {
        this.entity = this.getDao().findByMail(entity.getEmail());
        return "/kullanıcı/etkinlik";
    } else {
        return "/kullanıcı/kayıt";
    }
    }
     
    
    public Kullanıcı getEntity() {
        if(this.entity==null){
            entity=new Kullanıcı();
            
        }
        return entity;
    }

    public void setEntity(Kullanıcı entity) {
        this.entity = entity;
    }

    public KullanıcıDAO getDao() {
        if(this.dao==null){
            dao=new KullanıcıDAO();
        }
        return dao;
    }

    public void setDao(KullanıcıDAO dao) {
        this.dao = dao;
    }


   
    
    public List<Kullanıcı> getList() {
        this.list=this.getDao().getList();
        return list;
    }

    public void setList(List<Kullanıcı> list) {
        this.list = list;
    }
    
    
}
