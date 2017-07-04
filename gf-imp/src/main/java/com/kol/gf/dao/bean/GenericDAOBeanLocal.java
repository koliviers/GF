/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.bean;

import com.kol.gf.utils.FilterParams;
import com.kol.gf.utils.SortParams;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author kol
 */


public interface GenericDAOBeanLocal<E extends Serializable, ID> {

     /**
     * Persiste  l'objet passé en paramètre.
     * 
     * @param e L'objet à persister.
     */
    void addOne(E e);
    
    E updateOne(E e);
    
    void deleteOne(E e);
    
    void deleteOne(ID id);
    
    void deleteAll();
    
    E getOne(ID id);
    
    List<E> getAll();
    
//    List<E> getAll(String sortProperty, boolean sortAsc);
    
    List<E> getAll(SortParams sortParams);
    
    List<E> getAll(SortParams sortParams, FilterParams filterParams);
    
//    List<E> getAll(int first, int size, String sortProperty, boolean sortAsc);
    
    List<E> getAll(int first, int size, SortParams sortParams);
    
    List<E> getAll(int first, int size, SortParams sortParams, FilterParams filterParams);
    
    Long count();
    
    /**
     * Vérifie si l'objet entité dont l'identifiant est passé en paramètre 
     * existe dans l'unité de persistence.
     * 
     * @param id L'identifiant de l'objet entité à vérifier.
     * @return <code>true</code> si l'objet existe.
     */
    boolean exists(ID id);

}
