/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import dao.BiletDAO;
import dao.KullanıcıDAO;
import entity.Bilet;
import entity.Kullanıcı;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.application.NavigationHandler;
import jakarta.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



@Named(value = "biletBean")
@SessionScoped
public class BiletBean implements Serializable {

    
    private BiletDAO dao;
    private Bilet entity;
    private List<Bilet> biletlist;
    
    private KullanıcıDAO kdao;

    public KullanıcıDAO getKdao() {
        if(this.kdao==null){
            kdao=new KullanıcıDAO();
        }
        return kdao;
    }

    public void setKdao(KullanıcıDAO kdao) {
        this.kdao = kdao;
    }
    
    
    
     public BiletBean() {
    }
   
     
     
      public void clear() {
        entity = new Bilet();
    }

    public void create() {
        this.getDao().createBilet(entity);
        entity = new Bilet();
    }

    public void update() {
        this.getDao().updateBilet(entity);
        entity = new Bilet();
    }

    public void delete(Bilet c) {
        this.getDao().delete(c);
        entity = new Bilet();
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
        return "/user/bilet";
    }
    
public List<Bilet> biletlerim(Kullanıcı kullanıcı) throws IOException {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    if (this.getKdao().findByMail(kullanıcı.getEmail())!=null) {
        List<Bilet> biletListesi = this.getDao().findByUserID(kullanıcı.getKullanıcı_id());
        if (biletListesi != null && !biletListesi.isEmpty()) {
            return biletListesi;
        }
    } else  {
        // Kullanıcı giriş yapmamışsa, giriş sayfasına yönlendir
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Giriş yapmalısınız", "Lütfen giriş yapınız!"));
            NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
            navigationHandler.handleNavigation(facesContext, null, "/user/giris?faces-redirect=true");
            facesContext.renderResponse();
    }
    return null;
}




public String biletlerimsayfası(){
    return "/user/biletlerim";
}
   
   

    
}
