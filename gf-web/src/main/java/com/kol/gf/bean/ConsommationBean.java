/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

/**
 *
 * @author koliviers
 */
@ManagedBean(name = "consbean")
@ViewScoped
public class ConsommationBean implements Serializable{
    
    private List<SelectItem> categories;    
    private String selection;
    
    
     @PostConstruct
    public void init() {
        setCategories(new ArrayList<SelectItem>());
        SelectItemGroup group1 = new SelectItemGroup("Boisson");
        SelectItemGroup group2 = new SelectItemGroup("Huile");
        SelectItemGroup group3 = new SelectItemGroup("Sel");
        SelectItemGroup group4 = new SelectItemGroup("tabac");
        
        SelectItemGroup group11 = new SelectItemGroup("eau");
        SelectItemGroup group12 = new SelectItemGroup("alcool");
        
        SelectItemGroup group21 = new SelectItemGroup("huile de palme");
         SelectItemGroup group22 = new SelectItemGroup("Huile de coco");
         SelectItemGroup group23 = new SelectItemGroup("Huile normal");
        
        SelectItem option31 = new SelectItem("Option 3.1", "Option 3.1");
        SelectItem option32 = new SelectItem("Option 3.2", "Option 3.2");
        SelectItem option33 = new SelectItem("Option 3.3", "Option 3.3");
        SelectItem option34 = new SelectItem("Option 3.4", "Option 3.4");
        
        SelectItem option41 = new SelectItem("Option 4.1", "Option 4.1");
        
        SelectItem option111 = new SelectItem("Option 1.1.1");
        SelectItem option112 = new SelectItem("Option 1.1.2");
        group11.setSelectItems(new SelectItem[]{option111, option112});
        
        SelectItem option121 = new SelectItem("Option 1.2.1", "Option 1.2.1");
        SelectItem option122 = new SelectItem("Option 1.2.2", "Option 1.2.2");
        SelectItem option123 = new SelectItem("Option 1.2.3", "Option 1.2.3");
        group12.setSelectItems(new SelectItem[]{option121, option122, option123});
        
        SelectItem option211 = new SelectItem("Option 2.1.1", "Option 2.1.1");
        group21.setSelectItems(new SelectItem[]{option211});
        
        group1.setSelectItems(new SelectItem[]{group11, group12});
        group2.setSelectItems(new SelectItem[]{group21,group22,group23});
        group3.setSelectItems(new SelectItem[]{option31, option32, option33, option34});
        group4.setSelectItems(new SelectItem[]{option41});
        
        getCategories().add(group1);
        getCategories().add(group2);
        getCategories().add(group3);
        getCategories().add(group4);
    }

    /**
     * @return the categories
     */
    public List<SelectItem> getCategories() {
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(List<SelectItem> categories) {
        this.categories = categories;
    }

    /**
     * @return the selection
     */
    public String getSelection() {
        return selection;
    }

    /**
     * @param selection the selection to set
     */
    public void setSelection(String selection) {
        this.selection = selection;
    }
   

    
    
}
