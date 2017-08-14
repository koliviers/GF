/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.converters;

import com.kol.gf.entities.ExamenParaclinique;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

/**
 *
 * @author anonymousghost
 */
@FacesConverter(value = "com.kol.converters.picklistExamenParacliniqueConverter")
public class PicklistExamenParacliniqueConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Object ret = null;
        if (component instanceof PickList) {
            Object dualList = ((PickList) component).getValue();
            DualListModel dl = (DualListModel) dualList;
            for (Object o : dl.getSource()) {
                String id = "" + ((ExamenParaclinique) o);
                if (value.equals(id)) {
                    ret = o;
                    break;
                }
            }
            if (ret == null) {
                for (Object o1 : dl.getTarget()) {
                    String id1 = "" + ((ExamenParaclinique) o1);
                    if (value.equals(id1)) {
                        ret = o1;
                        break;
                    }
                }
            }
        }
        return ret;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        return String.valueOf(value);
    }

}
