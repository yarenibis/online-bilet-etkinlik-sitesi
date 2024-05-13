/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package converter;

import dao.YetkiDAO;
import entity.Kullanıcı;
import entity.Yetki;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value="yetkiconverter")
public class yetkiConverter implements Converter {
private YetkiDAO yetkidao;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        int id=Integer.valueOf(string);
        return this.getYetkidao().findByID(id);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        Yetki k=(Yetki) t;
        return String.valueOf(k.getYetki_id());
        
    
    }

    public YetkiDAO getYetkidao() {
        if(this.yetkidao==null){
            yetkidao=new YetkiDAO();
        }
        return yetkidao;
    }

    public void setYetkidao(YetkiDAO yetkidao) {
        this.yetkidao = yetkidao;
    }
    
    
    
}
