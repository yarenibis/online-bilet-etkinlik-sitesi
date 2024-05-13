/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.TiyatroDAO;
import entity.Tiyatro;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "tiyatroBean")
@SessionScoped
public class TiyatroBean extends BaseController<Tiyatro, TiyatroDAO> implements Serializable {

    private String searchKeyword;
    private Tiyatro selectedTiyatro;
    private List<Tiyatro> filteredTiyatroList;
    
     private Tiyatro entity;
    private TiyatroDAO dao;
    private List<Tiyatro> list;

    public Tiyatro getEntity() {
        if(this.entity==null){
            this.entity=new Tiyatro();
        }
        return entity;
    }

    public void setEntity(Tiyatro entity) {
        this.entity = entity;
    }

    public TiyatroDAO getDao() {
        if(this.dao==null){
            this.dao=new TiyatroDAO();
        }
        return dao;
    }

    public void setDao(TiyatroDAO dao) {
        this.dao = dao;
    }

  
 public void clear() {
        entity = new Tiyatro();
    }

    public void create() {
        this.getDao().create(entity);
        entity = new Tiyatro();
    }

    public void update() {
        this.getDao().update(entity);
        entity = new Tiyatro();
    }

    public void delete(Tiyatro c) {
        this.getDao().delete(c);
        entity = new Tiyatro();
    }
  
    
    
    

    public TiyatroBean() {
        super(Tiyatro.class, TiyatroDAO.class);
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public Tiyatro getSelectedTiyatro() {
        return selectedTiyatro;
    }

    public void setSelectedTiyatro(Tiyatro selectedTiyatro) {
        this.selectedTiyatro = selectedTiyatro;
    }

    public List<Tiyatro> getFilteredTiyatroList() {
        return filteredTiyatroList;
    }

    public void setFilteredTiyatroList(List<Tiyatro> filteredTiyatroList) {
        this.filteredTiyatroList = filteredTiyatroList;
    }

    public void searchTiyatro() {
        filteredTiyatroList = new ArrayList<>();
        for (Tiyatro tiyatro : getList()) {
            if (tiyatro.getAdı().contains(searchKeyword)) {
                filteredTiyatroList.add(tiyatro);
            }
        }
    }

    public String selectTiyatro(Tiyatro tiyatro) {
        setSelectedTiyatro(tiyatro);
        return "/panel/bilet?faces-redirect=true";
    }

    public List<Tiyatro> getList() {
        return getDao().list();
    }
}

