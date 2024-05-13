/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package converter;

import dao.MekanDAO;
import entity.Mekan;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author yaren
 */
@FacesConverter(value="mekanconverter")
public class mekanConverter implements Converter{

    private MekanDAO mekandao;

    public MekanDAO getMekandao() {
        if(mekandao==null){
            mekandao=new MekanDAO();
        }
        return mekandao;
    }

    public void setMekandao(MekanDAO mekandao) {
        this.mekandao = mekandao;
    }
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        int id=Integer.valueOf(string);
        Mekan m=this.getMekandao().findByID(id);
        return m;
            }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        Mekan m=(Mekan) t;
       return String.valueOf(m.getMekan_id());
          }
    
}
