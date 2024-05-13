/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import dao.BiletDAO;
import dao.EtkinlikDAO;
import dao.KullanıcıDAO;
import entity.Bilet;
import entity.Etkinlik;
import entity.Kullanıcı;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "etkinlikBean")
@SessionScoped
public class EtkinlikBean implements Serializable {

    private Etkinlik entity;
    private EtkinlikDAO dao;
    private List<Etkinlik> list;
    
    private String searchKeyword;
    private List<Etkinlik> filteredEtkinlikList;
    
    private KullanıcıDAO kdao;
    private EtkinlikDAO edao;
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
    
    

     public EtkinlikBean() {
    }

    public void clear() {
        entity = new Etkinlik();
    }

    public void create() {
        this.getDao().EtkinlikOluştur(entity);
        entity = new Etkinlik();
    }

    public void update() {
        this.getDao().update(entity);
        entity = new Etkinlik();
    }

    public void delete(Etkinlik c) {
        this.getDao().delete(c);
        entity = new Etkinlik();
    }
    

    public void adminupdate() {
        this.getDao().adminupdate(entity);
        entity = new Etkinlik();
    }

    public void admindelete(Etkinlik c) {
        this.getDao().admindelete(c);
        entity = new Etkinlik();
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

    public EtkinlikDAO getEdao() {
        if (this.edao == null) {
            this.edao = new EtkinlikDAO();
        }
        return edao;
    }

    public void setEdao(EtkinlikDAO edao) {
        this.edao = edao;
    }

    
    
    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public List<Etkinlik> getFilteredEtkinlikList() {
        return filteredEtkinlikList;
    }

    public void setFilteredEtkinlikList(List<Etkinlik> filteredEtkinlikList) {
        this.filteredEtkinlikList = filteredEtkinlikList;
    }

   

    public String selectEtkinlik(Etkinlik etkinlik,Kullanıcı kullanıcı) {
        this.entity = etkinlik;  
        int etkinlik_id = this.getEdao().findByID(this.entity.getId()).getId();
        int kullanıcı_id =this.getKdao().findByMail(kullanıcı.getEmail()).getKullanıcı_id();
        
        Bilet bilet = new Bilet();
        bilet.setEtkinlik_id(etkinlik_id); 
        bilet.setKullanıcı_id(kullanıcı_id); 
       
        this.getBdao().createBilet(bilet);

        return "/panel/bilet";
    }

    
     
    
    
    public void searchEtkinlik() {
        filteredEtkinlikList = new ArrayList<>(); 
        for (Etkinlik etkinlik : list) {
            if (etkinlik.getAdı().contains(searchKeyword) || etkinlik.getAçıklama().contains(searchKeyword)) {
                filteredEtkinlikList.add(etkinlik); // Arama kriterlerine uyanları yeni listeye ekle
            }
        }
    }

    public Etkinlik getEntity() {
        if (this.entity == null) {
            entity = new Etkinlik();
        }
        return entity;
    }

    public void setEntity(Etkinlik entity) {
        this.entity = entity;
    }

    public EtkinlikDAO getDao() {
        if (this.dao == null) {
            dao = new EtkinlikDAO();
        }
        return dao;
    }

    public void setDao(EtkinlikDAO dao) {
        this.dao = dao;
    }

    public List<Etkinlik> getList() {
        this.list = this.getDao().getEtkinlikList(page,pageSize);
        return list;
    }

    public void setList(List<Etkinlik> list) {
        this.list = list;
    }

}
