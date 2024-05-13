/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.FestivalDAO;
import entity.Festival;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "festivalBean")
@SessionScoped
public class FestivalBean extends BaseController<Festival, FestivalDAO> implements Serializable {

    private String searchKeyword;
    private Festival selectedFestival;
    private List<Festival> filteredFestivalList;

    
     private Festival entity;
    private FestivalDAO dao;
    private List<Festival> list;

    public Festival getEntity() {
        if(this.entity==null){
            this.entity=new Festival();
        }
        return entity;
    }

    public void setEntity(Festival entity) {
        this.entity = entity;
    }

    public FestivalDAO getDao() {
        if(this.dao==null){
            this.dao=new FestivalDAO();
        }
        return dao;
    }

    public void setDao(FestivalDAO dao) {
        this.dao = dao;
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
  
    public FestivalBean() {
        super(Festival.class, FestivalDAO.class);
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public Festival getSelectedFestival() {
        return selectedFestival;
    }

    public void setSelectedFestival(Festival selectedFestival) {
        this.selectedFestival = selectedFestival;
    }

    public List<Festival> getFilteredFestivalList() {
        return filteredFestivalList;
    }

    public void setFilteredFestivalList(List<Festival> filteredFestivalList) {
        this.filteredFestivalList = filteredFestivalList;
    }

    public void searchFestival() {
        filteredFestivalList = new ArrayList<>();
        for (Festival festival : getList()) {
            if (festival.getFestival_adi().contains(searchKeyword)) {
                filteredFestivalList.add(festival);
            }
        }
    }

    public String selectFestival(Festival festival) {
        setSelectedFestival(festival);
        return "/panel/bilet?faces-redirect=true";
    }

    public List<Festival> getList() {
        return getDao().list();
    }
}

