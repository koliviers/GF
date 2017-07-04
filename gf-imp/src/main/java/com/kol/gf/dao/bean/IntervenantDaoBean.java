/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.bean;

import com.kol.gf.entities.Intervenant;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class IntervenantDaoBean extends GenericDaoBean<Intervenant, Long> implements IntervenantDaoBeanLocal{
    
    
    public IntervenantDaoBean(){
        
        super(Intervenant.class);
    }
    public IntervenantDaoBean(Class<Intervenant> entiClass)
    {
        this.entityClass=entiClass;
    }
}