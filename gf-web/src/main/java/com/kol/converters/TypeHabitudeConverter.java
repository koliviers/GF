/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.converters;

import com.kol.gf.entities.TypeHabitude;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author anonymousghost
 */
@FacesConverter("typeHabitudeConverterId")
public class TypeHabitudeConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        TypeHabitude typeH = (TypeHabitude) value;
        return typeH.getLabel();
    }
    
}
