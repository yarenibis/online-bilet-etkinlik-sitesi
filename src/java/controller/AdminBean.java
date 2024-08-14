/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.AdminDAO;
import entity.Admin;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author yaren
 */
@Named(value = "adminBean")
@SessionScoped
public class AdminBean implements Serializable{
     private Admin entity;
    private AdminDAO dao;
    private List<Admin> list;

    private Admin selectedkullanıcı;

    public Admin getSelectedkullanıcı() {
        return selectedkullanıcı;
    }

    public void setSelectedkullanıcı(Admin selectedkullanıcı) {
        this.selectedkullanıcı = selectedkullanıcı;
    }

    public AdminBean() {
        
    }

       public String giriş() {
           Admin kullanıcı = this.getDao().findByMail(entity.getEmail());
         if (this.getDao().adminGirişi(entity.getEmail(), entity.getŞifre())) {
        this.entity = this.getDao().findByMail(entity.getEmail());
       return "/panel/admin-etkinlik?faces-redirect=true";
        
         }else{
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata", "Yanlış şifre!"));
        return null; // Aynı sayfada kalmak için null döndürüyoruz
    }
    }
    
  

    public Admin getEntity() {
        if (this.entity == null) {
            entity = new Admin();
        }
        return entity;
    }

    public void setEntity(Admin entity) {
        this.entity = entity;
    }

    public AdminDAO getDao() {
        if (this.dao == null) {
            dao = new AdminDAO();
        }
        return dao;
    }

    public void setDao(AdminDAO dao) {
        this.dao = dao;
    }

    public List<Admin> getList() {
        this.list = this.getDao().getList();
        return list;
    }

    public void setList(List<Admin> list) {
        this.list = list;
    }

    public String getEmail() {
        return entity.getEmail();
    }

    public void setEmail(String email) {
        entity.setEmail(email);
    }

    public String getŞifre() {
        return entity.getŞifre();
    }

    public void setŞifre(String şifre) {
        entity.setŞifre(şifre);
    }
}
