/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package converter;

import dao.KullanıcıDAO;
import entity.Kullanıcı;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value="kullanıcıconverter")
public class kullanıcıConverter implements Converter{

    private KullanıcıDAO kullanıcıdao;

    public KullanıcıDAO getKullanıcıdao() {
        if(kullanıcıdao==null){
            kullanıcıdao=new KullanıcıDAO();
        }
        return kullanıcıdao;
    }

    public void setKullanıcıdao(KullanıcıDAO kullanıcıdao) {
        this.kullanıcıdao = kullanıcıdao;
    }
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        int id=Integer.valueOf(string);
        Kullanıcı m=this.getKullanıcıdao().findByID(id);
        return m;
            }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        Kullanıcı m=(Kullanıcı) t;
       return String.valueOf(m.getKullanıcı_id());
          }
    
}
