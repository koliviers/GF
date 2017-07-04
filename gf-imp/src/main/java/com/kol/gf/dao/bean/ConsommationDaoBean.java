/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.bean;

import com.kol.gf.entities.Consommation;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class ConsommationDaoBean extends GenericDaoBean<Consommation, Long> implements ConsommationDaoBeanLocal{
    
    public ConsommationDaoBean(){
        super(Consommation.class);
    }
    public ConsommationDaoBean(Class<Consommation> entiClass){
        this.entityClass=entiClass;
    }
}
