/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.bean;

import com.kol.gf.entities.TypeHabitude;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class TypeHabitudeDaoBean extends GenericDaoBean<TypeHabitude, Long> implements TypeHabitudeDaoBeanLocal{
    
public TypeHabitudeDaoBean(){
    super(TypeHabitude.class);
}    
    public TypeHabitudeDaoBean(Class<TypeHabitude> entity){
        this.entityClass=entity;
    }
}