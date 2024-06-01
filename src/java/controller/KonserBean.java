/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.BiletDAO;
import dao.KonserDAO;
import dao.KullanıcıDAO;
import entity.Bilet;
import entity.Konser;
import entity.Kullanıcı;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "konserBean")
@SessionScoped
public class KonserBean extends BaseController<Konser, KonserDAO> implements Serializable {

   private Konser entity;
    private KonserDAO dao;
    private List<Konser> list;
    
    private String searchKeyword;
    private List<Konser> filteredKonserList;
    
    private KullanıcıDAO kdao;
    private KonserDAO edao;
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
    
    

     public KonserBean() {
          super(Konser.class, KonserDAO.class);
    }

    public void clear() {
        entity = new Konser();
    }

    public void create() {
        this.getDao().create(entity);
        entity = new Konser();
    }

    public void update() {
        this.getDao().update(entity);
        entity = new Konser();
    }

    public void delete(Konser c) {
        this.getDao().delete(c);
        entity = new Konser();
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

    public KonserDAO getEdao() {
        if (this.edao == null) {
            this.edao = new KonserDAO();
        }
        return edao;
    }

    public void setEdao(KonserDAO edao) {
        this.edao = edao;
    }

    
    
    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public List<Konser> getFilteredKonserList() {
        return filteredKonserList;
    }

    public void setFilteredKonserList(List<Konser> filteredKonserList) {
        this.filteredKonserList = filteredKonserList;
    }

   

    public String selectKonser(Konser konser,Kullanıcı kullanıcı) {
        this.entity = konser;  
        this.entity = this.getDao().findByID(entity.getId());
        if(this.getKdao().findByMail(kullanıcı.getEmail())!=null){
        int kullanıcı_id =this.getKdao().findByMail(kullanıcı.getEmail()).getKullanıcı_id();
        
       
        Bilet bilet = new Bilet();
        bilet.setEtkinlik_id(this.entity.getId()); 
        bilet.setKullanıcı_id(kullanıcı_id); 
       
        this.getBdao().createBilet(bilet);

        return "/user/konser-detay";
        }
       // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata", "Etkinlik seçmek için giriş yapmalısınız!"));
        return "/user/giris"; 
    }

    
    public void searchKonser() {
        filteredKonserList = new ArrayList<>(); 
        for (Konser konser : list) {
            if (konser.getAdı().toLowerCase().contains(searchKeyword.toLowerCase()) || konser.getMekan().getMekan_adi().toLowerCase().contains(searchKeyword.toLowerCase())
                    || konser.getSanatçı().toLowerCase().contains(searchKeyword.toLowerCase())
                    || konser.getTarih().toLowerCase().contains(searchKeyword.toLowerCase())) {
                filteredKonserList.add(konser); // Arama kriterlerine uyanları yeni listeye ekle
            }
        }
    }

    public Konser getEntity() {
        if (this.entity == null) {
            entity = new Konser();
        }
        return entity;
    }

    public void setEntity(Konser entity) {
        this.entity = entity;
    }

    public KonserDAO getDao() {
        if (this.dao == null) {
            dao = new KonserDAO();
        }
        return dao;
    }

    public void setDao(KonserDAO dao) {
        this.dao = dao;
    }

    public List<Konser> getList() {
        this.list = this.getDao().list(page, pageSize);
        return list;
    }

    public void setList(List<Konser> list) {
        this.list = list;
    }
}

