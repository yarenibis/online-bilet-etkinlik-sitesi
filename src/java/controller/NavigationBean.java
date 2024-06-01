/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author yaren
 */


@Named(value = "navigationBean")
@SessionScoped
public class NavigationBean implements Serializable {

//    private Map<String, String> pages;
//    
//    public NavigationBean() {
//        pages = new HashMap<>();
//        // Varsayılan olarak ilk sayfa aktif olsun
//        pages.put("page1", "active");
//    }
//
//    public Map<String, String> getPages() {
//        return pages;
//    }
//
//    public void setPages(Map<String, String> pages) {
//        this.pages = pages;
//    }
//    
//    public String goToPage(String page) {
//        // Tüm sayfaların aktiflik durumunu sıfırla
//        for (String key : pages.keySet()) {
//            pages.put(key, null);
//        }
//        // Belirtilen sayfayı aktif yap
//        pages.put(page, "active");
//        // Sayfaya yönlendir
//        return page + "?faces-redirect=true";
//    }
     private static final long serialVersionUID = 1L;
    private String activePage; // Aktif sayfa bilgisini saklamak için kullanılacak değişken

    public NavigationBean() {
        activePage = "/user/etkinlik"; // Varsayılan olarak ilk sayfa aktif olsun
    }

    public String getActivePage() {
        return activePage;
    }

    public void setActivePage(String activePage) {
        this.activePage = activePage;
    }

    public String goToPage(String page) {
        activePage = page; // Tıklanan butonun sayfa adını aktif sayfa olarak ayarla
        return page + "?faces-redirect=true";
    }

    public String getButtonStyle(String page) {
        return activePage.equals(page) ? "nav-link btn btn-link active" : "nav-link btn btn-link";
    }
    
   

    
}

 
    

