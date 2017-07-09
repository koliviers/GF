/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.dao.bean.DecesDaoBeanLocal;
import com.kol.gf.entities.Deces;
import com.kol.gf.service.DecesServiceBeanLocal;
import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author koliviers
 */
@Stateless
public class DecesServiceBean extends BaseServiceBeanImpl<Deces, Long> implements DecesServiceBeanLocal{

    @EJB
    
    private DecesDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBean<Deces, Long> getDao() {
       return this.dao; 
    }
    
}
