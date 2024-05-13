/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.TalkShowDAO;
import entity.Talkshow;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "talkShowBean")
@SessionScoped
public class TalkShowBean extends BaseController<Talkshow, TalkShowDAO> implements Serializable {

    private String searchKeyword;
    private Talkshow selectedTalkshow;
    private List<Talkshow> filteredTalkshowList;
    
     private Talkshow entity;
    private TalkShowDAO dao;
    private List<Talkshow> list;

    public Talkshow getEntity() {
        if(this.entity==null){
            this.entity=new Talkshow();
        }
        return entity;
    }

    public void setEntity(Talkshow entity) {
        this.entity = entity;
    }

    public TalkShowDAO getDao() {
        if(this.dao==null){
            this.dao=new TalkShowDAO();
        }
        return dao;
    }

    public void setDao(TalkShowDAO dao) {
        this.dao = dao;
    }

  
 public void clear() {
        entity = new Talkshow();
    }

    public void create() {
        this.getDao().create(entity);
        entity = new Talkshow();
    }

    public void update() {
        this.getDao().update(entity);
        entity = new Talkshow();
    }

    public void delete(Talkshow c) {
        this.getDao().delete(c);
        entity = new Talkshow();
    }
  

    public TalkShowBean() {
        super(Talkshow.class, TalkShowDAO.class);
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public Talkshow getSelectedTalkshow() {
        return selectedTalkshow;
    }

    public void setSelectedTalkshow(Talkshow selectedTalkshow) {
        this.selectedTalkshow = selectedTalkshow;
    }

    public List<Talkshow> getFilteredTalkshowList() {
        return filteredTalkshowList;
    }

    public void setFilteredTalkshowList(List<Talkshow> filteredTalkshowList) {
        this.filteredTalkshowList = filteredTalkshowList;
    }

    public void searchTalkshow() {
        filteredTalkshowList = new ArrayList<>();
        for (Talkshow talkShow : getList()) {
            if (talkShow.getShow_adi().contains(searchKeyword)) {
                filteredTalkshowList.add(talkShow);
            }
        }
    }

    public String selectTalkshow(Talkshow talkShow) {
        setSelectedTalkshow(talkShow);
        return "/panel/bilet?faces-redirect=true";
    }

    public List<Talkshow> getList() {
        return getDao().list();
    }
}

