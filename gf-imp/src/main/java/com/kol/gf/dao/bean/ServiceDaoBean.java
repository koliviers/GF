/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.bean;

import com.kol.gf.entities.Services;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class ServiceDaoBean extends GenericDaoBean<Services, Long> implements ServiceDaoBeanLocal{
    
    
    public ServiceDaoBean(){
        super(Services.class);
    }
    
    public ServiceDaoBean(Class<Services> entityClass)
    {
        this.entityClass=entityClass;
    }
}
