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

    private Map<String, String> pages;
    
    public NavigationBean() {
        pages = new HashMap<>();
        // Varsayılan olarak ilk sayfa aktif olsun
        pages.put("page1", "active");
    }

    public Map<String, String> getPages() {
        return pages;
    }

    public void setPages(Map<String, String> pages) {
        this.pages = pages;
    }
    
    public String goToPage(String page) {
        // Tüm sayfaların aktiflik durumunu sıfırla
        for (String key : pages.keySet()) {
            pages.put(key, null);
        }
        // Belirtilen sayfayı aktif yap
        pages.put(page, "active");
        // Sayfaya yönlendir
        return page + "?faces-redirect=true";
    }
    
    
   

    
}

 
    

