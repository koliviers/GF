/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.dao.bean.ClasseTheurapetiqueDaoBeanLocal;
import com.kol.gf.entities.ClasseTherapeutique;
import com.kol.gf.service.ClasseTheurapetiqueServiceBeanLocal;
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
public class ClasseTheurapetiqueServiceBean extends BaseServiceBeanImpl<ClasseTherapeutique, Long> implements ClasseTheurapetiqueServiceBeanLocal {

    @EJB
    private ClasseTheurapetiqueDaoBeanLocal dao;

    @Override
    protected BaseDaoBean<ClasseTherapeutique, Long> getDao() {

        return this.dao;
    }

}
