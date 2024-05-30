/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.BiletDAO;
import dao.KullanıcıDAO;
import dao.SinemaDAO;
import entity.Bilet;
import entity.Kullanıcı;
import entity.Sinema;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "sinemaBean")
@SessionScoped
public class SinemaBean extends BaseController<Sinema, SinemaDAO> implements Serializable {

   private Sinema entity;
    private SinemaDAO dao;
    private List<Sinema> list;
    
    private String searchKeyword;
    private List<Sinema> filteredSinemaList;
    
    private KullanıcıDAO kdao;
    private SinemaDAO edao;
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
    
    

     public SinemaBean() {
          super(Sinema.class, SinemaDAO.class);
    }

    public void clear() {
        entity = new Sinema();
    }

    public void create() {
        this.getDao().create(entity);
        entity = new Sinema();
    }

    public void update() {
        this.getDao().update(entity);
        entity = new Sinema();
    }

    public void delete(Sinema c) {
        this.getDao().delete(c);
        entity = new Sinema();
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

    public SinemaDAO getEdao() {
        if (this.edao == null) {
            this.edao = new SinemaDAO();
        }
        return edao;
    }

    public void setEdao(SinemaDAO edao) {
        this.edao = edao;
    }

    
    
    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public List<Sinema> getFilteredSinemaList() {
        return filteredSinemaList;
    }

    public void setFilteredSinemaList(List<Sinema> filteredSinemaList) {
        this.filteredSinemaList = filteredSinemaList;
    }

   

    public String selectSinema(Sinema sinema,Kullanıcı kullanıcı) {
        this.entity = sinema;  
         this.entity = this.getDao().findByID(entity.getSinema_id());
        int kullanıcı_id =this.getKdao().findByMail(kullanıcı.getEmail()).getKullanıcı_id();
        
        Bilet bilet = new Bilet();
        bilet.setEtkinlik_id(this.entity.getSinema_id()); 
        bilet.setKullanıcı_id(kullanıcı_id); 
       
        this.getBdao().createBilet(bilet);

        return "/kullanıcı/sinemaBilet";
    }

    
    public void searchSinema() {
        filteredSinemaList = new ArrayList<>(); 
        for (Sinema sinema : list) {
            if (sinema.getFilm_adi().contains(searchKeyword)) {
                filteredSinemaList.add(sinema); // Arama kriterlerine uyanları yeni listeye ekle
            }
        }
    }

    public Sinema getEntity() {
        if (this.entity == null) {
            entity = new Sinema();
        }
        return entity;
    }

    public void setEntity(Sinema entity) {
        this.entity = entity;
    }

    public SinemaDAO getDao() {
        if (this.dao == null) {
            dao = new SinemaDAO();
        }
        return dao;
    }

    public void setDao(SinemaDAO dao) {
        this.dao = dao;
    }

    public List<Sinema> getList() {
        this.list = this.getDao().list(page, pageSize);
        return list;
    }

    public void setList(List<Sinema> list) {
        this.list = list;
    }
}

