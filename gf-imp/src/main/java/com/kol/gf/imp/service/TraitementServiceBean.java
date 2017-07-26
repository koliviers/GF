/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.dao.bean.TraitementDaoBeanLocal;
import com.kol.gf.entities.TraitementMedicamenteux;
import com.kol.gf.service.TraitementServiceBeanLocal;
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
public class TraitementServiceBean extends BaseServiceBeanImpl<TraitementMedicamenteux, Long> implements TraitementServiceBeanLocal {

    @EJB
    private TraitementDaoBeanLocal dao;

    @Override
    protected BaseDaoBean<TraitementMedicamenteux, Long> getDao() {
        return this.dao;

    }

}
