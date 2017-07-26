/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.dao.bean.ICategorieDAO;
import com.kol.gf.entities.Categorie;
import com.kol.gf.service.CategorieSessionBeanLocal;
import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author anonymousghost
 */
@Stateless
public class CategorieSessionBean extends BaseServiceBeanImpl<Categorie, Long> implements CategorieSessionBeanLocal {
    
    @EJB
    private ICategorieDAO dao;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    protected BaseDaoBean<Categorie, Long> getDao() {
        return dao;
    }
}
