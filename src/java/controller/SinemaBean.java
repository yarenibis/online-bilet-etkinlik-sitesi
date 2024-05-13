/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SinemaDAO;
import entity.Sinema;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "sinemaBean")
@SessionScoped
public class SinemaBean extends BaseController<Sinema, SinemaDAO> implements Serializable {

    private String searchKeyword;
    private Sinema selectedSinema;
    private List<Sinema> filteredSinemaList;
    
     private Sinema entity;
    private SinemaDAO dao;
    private List<Sinema> list;

    public Sinema getEntity() {
        if(this.entity==null){
            this.entity=new Sinema();
        }
        return entity;
    }

    public void setEntity(Sinema entity) {
        this.entity = entity;
    }

    public SinemaDAO getDao() {
        if(this.dao==null){
            this.dao=new SinemaDAO();
        }
        return dao;
    }

    public void setDao(SinemaDAO dao) {
        this.dao = dao;
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
  

    public SinemaBean() {
        super(Sinema.class, SinemaDAO.class);
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public Sinema getSelectedSinema() {
        return selectedSinema;
    }

    public void setSelectedSinema(Sinema selectedSinema) {
        this.selectedSinema = selectedSinema;
    }

    public List<Sinema> getFilteredSinemaList() {
        return filteredSinemaList;
    }

    public void setFilteredSinemaList(List<Sinema> filteredSinemaList) {
        this.filteredSinemaList = filteredSinemaList;
    }

    public void searchSinema() {
        filteredSinemaList = new ArrayList<>();
        for (Sinema sinema : getList()) {
            if (sinema.getFilm_adi().contains(searchKeyword)) {
                filteredSinemaList.add(sinema);
            }
        }
    }

    public String selectSinema(Sinema sinema) {
        setSelectedSinema(sinema);
        return "/panel/bilet?faces-redirect=true";
    }

    public List<Sinema> getList() {
        return getDao().list();
    }
}

