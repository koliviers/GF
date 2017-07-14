/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.dao.bean.GenericDAOBeanLocal;
import com.kol.gf.dao.bean.PathologieDaoBeanLocal;
import com.kol.gf.entities.Pathologie;
import com.kol.gf.service.PathologieServiceBeanLocal;
import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class PathologieServiceBean extends BaseServiceBeanImpl<Pathologie, Long> implements PathologieServiceBeanLocal {

    @EJB
    private PathologieDaoBeanLocal dao;

    @Override
    protected BaseDaoBean<Pathologie, Long> getDao() {
        return dao;
    }


}
