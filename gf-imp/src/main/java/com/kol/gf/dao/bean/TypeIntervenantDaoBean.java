/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.bean;

import com.kol.gf.entities.TypeIntervenant;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class TypeIntervenantDaoBean extends GenericDaoBean<TypeIntervenant, Long> implements TypeIntervenantDaoBeanLocal{

    public TypeIntervenantDaoBean() {
        super(TypeIntervenant.class);
    }

    public TypeIntervenantDaoBean(Class<TypeIntervenant> entiClass){
        this.entityClass=entiClass;
    }
    
}
