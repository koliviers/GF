/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.bean;

import com.kol.gf.entities.TypeConsommation;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class TypeConsommationDaoBean extends GenericDaoBean<TypeConsommation, Long> implements TypeConsommationDaoBeanLocal{
    
    
    public TypeConsommationDaoBean(){
        super(TypeConsommation.class);
    }
    public TypeConsommationDaoBean(Class<TypeConsommation> entiClass)
    {
        this.entityClass=entiClass;
    }
}
