package controller;

import dao.KullanıcıDAO;
import entity.Kullanıcı;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named(value = "kullanıcıBean")
@SessionScoped
public class KullanıcıBean implements Serializable {

    private Kullanıcı entity;
    private KullanıcıDAO dao;
    private List<Kullanıcı> list;

    public KullanıcıBean() {
        entity = new Kullanıcı();
    }

    public void clear() {
        entity = new Kullanıcı();
    }

    public void kullanıcıkayıt() {
        this.getDao().kullanıcıkayıt(entity);
        entity = new Kullanıcı();
    }

    public void create() {
        this.getDao().create(entity);
        entity = new Kullanıcı();
    }

    public void update() {
        this.getDao().adminupdate(entity);
        entity = new Kullanıcı();
    }

    public void delete(Kullanıcı c) {
        this.getDao().admindelete(c);
        entity = new Kullanıcı();
    }

    public String giriş() {
        Kullanıcı existingUser = this.getDao().findByMail(entity.getEmail());
       
        if (existingUser == null) {
            // Kullanıcı mail adresi kayıtlı değilse kayıt sayfasına yönlendir
            return "/kullanıcı/kayıt";
        } else if (!existingUser.getŞifre().equals(entity.getŞifre())) {
            // Şifre yanlışsa hata mesajı göster
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Şifre yanlış.", null));
            return null;
        }
        // Giriş başarılıysa, kullanıcıyı etkinlik sayfasına yönlendir
         this.entity=this.getDao().findByMail(this.getEmail());
         return "/kullanıcı/etkinlik";
    }


    
    
    public Kullanıcı getEntity() {
        if (this.entity == null) {
            entity = new Kullanıcı();
        }
        return entity;
    }

    public void setEntity(Kullanıcı entity) {
        this.entity = entity;
    }

    public KullanıcıDAO getDao() {
        if (this.dao == null) {
            dao = new KullanıcıDAO();
        }
        return dao;
    }

    public void setDao(KullanıcıDAO dao) {
        this.dao = dao;
    }

    public List<Kullanıcı> getList() {
        this.list = this.getDao().getList();
        return list;
    }

    public void setList(List<Kullanıcı> list) {
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
