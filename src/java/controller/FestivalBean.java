/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.BiletDAO;
import dao.EtkinlikDAO;
import dao.FestivalDAO;
import dao.KullanıcıDAO;
import entity.Bilet;
import entity.Etkinlik;
import entity.Festival;
import entity.Kullanıcı;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "festivalBean")
@SessionScoped
public class FestivalBean extends BaseController<Festival, FestivalDAO> implements Serializable {


    private Festival entity;
    private FestivalDAO dao;
    private List<Festival> list;
    private EtkinlikDAO edao;
    
    private String searchKeyword;
    private List<Festival> filteredFestivalList;
    
    private KullanıcıDAO kdao;
    private BiletDAO bdao;
    
    
    private int page=1;
    private int pageSize=5;
    private int pageCount;
    
    public void next(){
        this.page++;
    }
    public void previous(){
        this.page--;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        this.pageCount=(int)Math.ceil(this.getDao().count()/(double)pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    
    

     public FestivalBean() {
          super(Festival.class, FestivalDAO.class);
    }

    public void clear() {
        entity = new Festival();
    }

    public void create() {
        this.getDao().create(entity);
        entity = new Festival();
    }

    public void update() {
        this.getDao().update(entity);
        entity = new Festival();
    }

    public void delete(Festival c) {
        this.getDao().delete(c);
        entity = new Festival();
    }
    


    public BiletDAO getBdao() {
        if (this.bdao == null) {
            bdao = new BiletDAO();
        }
        return bdao;
    }

    public void setBdao(BiletDAO bdao) {
        this.bdao = bdao;
    }

    public KullanıcıDAO getKdao() {
        if (this.kdao == null) {
            this.kdao = new KullanıcıDAO();
        }
        return kdao;
    }

    public void setKdao(KullanıcıDAO kdao) {
        this.kdao = kdao;
    }



    
    
    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public List<Festival> getFilteredFestivalList() {
        return filteredFestivalList;
    }

    public void setFilteredFestivalList(List<Festival> filteredFestivalList) {
        this.filteredFestivalList = filteredFestivalList;
    }

   
//    public String selectFestival(Festival festival, Kullanıcı kullanıcı) {
//    this.entity = festival;  
//    festival = this.getDao().findByID(entity.getEtkinlik_id());
//
//    Kullanıcı foundUser = this.getKdao().findByMail(kullanıcı.getEmail());
//
//    if (foundUser != null) {
//        int kullanıcı_id = foundUser.getKullanıcı_id();
//
//        Bilet bilet = new Bilet();
//        bilet.setEtkinlik_id(festival); 
//        bilet.setKullanıcı_id(foundUser);
//        
//        this.getBdao().createBilet(bilet);
//        System.out.println("********************************************"+festival.getEtkinlik_id());
//        System.out.println("********************************************"+bilet.getEtkinlik_id().getId());
//        return "/user/festivalBilet.xhtml";
//        
//    } else {
//        // Kullanıcı girişi yapılmamışsa hata mesajı göster
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata", "Etkinlik seçmek için giriş yapmalısınız!"));
//        return "/user/giris.xhtml"; 
//    }
//}

    public String selectFestival(Festival festival, Kullanıcı kullanıcı) {
    // Etkinlik ID'sini almak ve etkinlik nesnesini bulmak
    this.entity = this.getDao().findByID(festival.getEtkinlik_id());
    Etkinlik etkinlik= this.getEDao().findByID(entity.getEtkinlik_id());
    Kullanıcı foundUser = this.getKdao().findByMail(kullanıcı.getEmail());

    if (foundUser != null) {
        Bilet bilet = new Bilet();
        
        // Etkinlik nesnesini bilet nesnesine atayın
        bilet.setEtkinlik_id(etkinlik);
        
        // Kullanıcıyı bilet nesnesine atayın
        bilet.setKullanıcı_id(foundUser);

        // Bilet nesnesini veritabanına kaydedin
        this.getBdao().createBilet(bilet);

        System.out.println("********************************************" + etkinlik.getAdı());
        System.out.println("********************************************" + bilet.getEtkinlik_id().getId());
        
        // Başarılı bir şekilde bilet oluşturulduktan sonra yönlendirme yapılır
        return "/user/festivalBilet.xhtml";
    } else {
        // Kullanıcı girişi yapılmamışsa hata mesajı göster
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata", "Etkinlik seçmek için giriş yapmalısınız!"));
        return "/user/giris.xhtml";
    }
}

    



    
    public void searchFestival() {
        filteredFestivalList = new ArrayList<>(); 
        for (Festival etkinlik : list) {
            if (etkinlik.getAdı().toLowerCase().contains(searchKeyword.toLowerCase()) 
                    || etkinlik.getMekan().getMekan_adi().toLowerCase().contains(searchKeyword.toLowerCase())) {
                filteredFestivalList.add(etkinlik); // Arama kriterlerine uyanları yeni listeye ekle
            }
        }
    }

    public Festival getEntity() {
        if (this.entity == null) {
            entity = new Festival();
        }
        return entity;
    }

    public void setEntity(Festival entity) {
        this.entity = entity;
    }

    public FestivalDAO getDao() {
        if(this.dao==null){
            dao=new FestivalDAO();
        }
        return dao;
    }

    public void setDao(FestivalDAO dao) {
        this.dao = dao;
    }



    public List<Festival> getList() {
        this.list = this.getDao().list(page,pageSize);
        return list;
    }

    public void setList(List<Festival> list) {
        this.list = list;
    }
    
    public EtkinlikDAO getEDao() {
        if (this.edao == null) {
            edao = new EtkinlikDAO();
        }
        return edao;
    }

    public void setEDao(EtkinlikDAO edao) {
        this.edao = edao;
    }
    

}



