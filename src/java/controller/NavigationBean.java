/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;


@Named(value = "navigationBean")
@SessionScoped
public class NavigationBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String activePage; // Aktif sayfa bilgisini saklamak için kullanılacak değişken

    public NavigationBean() {
        activePage = "/index"; // Varsayılan olarak ilk sayfa aktif olsun
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

 


