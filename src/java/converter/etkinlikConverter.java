/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package converter;

import dao.EtkinlikDAO;
import entity.Etkinlik;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value="etkinlikconverter")
public class etkinlikConverter implements Converter{

    private EtkinlikDAO etkinlikdao;

    public EtkinlikDAO getEtkinlikdao() {
        if(etkinlikdao==null){
            etkinlikdao=new EtkinlikDAO();
        }
        return etkinlikdao;
    }

    public void setEtkinlikdao(EtkinlikDAO etkinlikdao) {
        this.etkinlikdao = etkinlikdao;
    }
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        int id=Integer.valueOf(string);
        Etkinlik m=this.getEtkinlikdao().findByID(id);
        return m;
            }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        Etkinlik m=(Etkinlik) t;
       return String.valueOf(m.getId());
          }
    
}