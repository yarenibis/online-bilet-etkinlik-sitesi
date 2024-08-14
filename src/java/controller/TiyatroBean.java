/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.BiletDAO;
import dao.EtkinlikDAO;
import dao.KullanıcıDAO;
import dao.TiyatroDAO;
import entity.Bilet;
import entity.Etkinlik;
import entity.Kullanıcı;
import entity.Tiyatro;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "tiyatroBean")
@SessionScoped
public class TiyatroBean extends BaseController<Tiyatro, TiyatroDAO> implements Serializable {

    private Tiyatro entity;
    private TiyatroDAO dao;
    private List<Tiyatro> list;
    
    private String searchKeyword;
    private List<Tiyatro> filteredTiyatroList;
    
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
    
    

     public TiyatroBean() {
         super(Tiyatro.class, TiyatroDAO.class);
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
        if(this.edao==null){
            edao=new EtkinlikDAO();
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

    public List<Tiyatro> getFilteredTiyatroList() {
        return filteredTiyatroList;
    }

    public void setFilteredTiyatroList(List<Tiyatro> filteredTiyatroList) {
        this.filteredTiyatroList = filteredTiyatroList;
    }

      public String selectTiyatro(Tiyatro tiyatro, Kullanıcı kullanıcı) {
    // Etkinlik ID'sini almak ve etkinlik nesnesini bulmak
    this.entity = this.getDao().findByID(tiyatro.getEtkinlik_id());
    Etkinlik etkinlik= this.getEdao().findByID(entity.getEtkinlik_id());
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
        return "/user/tiyatroBilet.xhtml";
    } else {
        // Kullanıcı girişi yapılmamışsa hata mesajı göster
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata", "Etkinlik seçmek için giriş yapmalısınız!"));
        return "/user/giris.xhtml";
    }
}

    
    public void searchTiyatro() {
        filteredTiyatroList = new ArrayList<>(); 
        for (Tiyatro tiyatro : list) {
            if (tiyatro.getAdı().toLowerCase().contains(searchKeyword.toLowerCase())
                    || tiyatro.getMekan().getMekan_adi().toLowerCase().contains(searchKeyword.toLowerCase())
                    || tiyatro.getOyuncu().toLowerCase().contains(searchKeyword.toLowerCase())) {
                filteredTiyatroList.add(tiyatro); // Arama kriterlerine uyanları yeni listeye ekle
            }
        }
    }

    public Tiyatro getEntity() {
        if (this.entity == null) {
            entity = new Tiyatro();
        }
        return entity;
    }

    public void setEntity(Tiyatro entity) {
        this.entity = entity;
    }

    public TiyatroDAO getDao() {
        if (this.dao == null) {
            dao = new TiyatroDAO();
        }
        return dao;
    }

    public void setDao(TiyatroDAO dao) {
        this.dao = dao;
    }

    public List<Tiyatro> getList() {
        this.list = this.getDao().list(page, pageSize);
        return list;
    }

    public void setList(List<Tiyatro> list) {
        this.list = list;
    }
}

