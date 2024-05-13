/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.KonserDAO;
import entity.Konser;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "konserBean")
@SessionScoped
public class KonserBean extends BaseController<Konser, KonserDAO> implements Serializable {

    private String searchKeyword;
    private Konser selectedKonser;
    private List<Konser> filteredKonserList;
    
     private Konser entity;
    private KonserDAO dao;
    private List<Konser> list;

    public Konser getEntity() {
        if(this.entity==null){
            this.entity=new Konser();
        }
        return entity;
    }

    public void setEntity(Konser entity) {
        this.entity = entity;
    }

    public KonserDAO getDao() {
        if(this.dao==null){
            this.dao=new KonserDAO();
        }
        return dao;
    }

    public void setDao(KonserDAO dao) {
        this.dao = dao;
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

    public KonserBean() {
        super(Konser.class, KonserDAO.class);
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public Konser getSelectedKonser() {
        return selectedKonser;
    }

    public void setSelectedKonser(Konser selectedKonser) {
        this.selectedKonser = selectedKonser;
    }

    public List<Konser> getFilteredKonserList() {
        return filteredKonserList;
    }

    public void setFilteredKonserList(List<Konser> filteredKonserList) {
        this.filteredKonserList = filteredKonserList;
    }

    public void searchKonser() {
        filteredKonserList = new ArrayList<>();
        for (Konser konser : getList()) {
            if (konser.getAdı().contains(searchKeyword)) {
                filteredKonserList.add(konser);
            }
        }
    }

    public String selectKonser(Konser konser) {
        setSelectedKonser(konser);
        return "/panel/bilet?faces-redirect=true";
    }

    public List<Konser> getList() {
        return getDao().list();
    }
}

