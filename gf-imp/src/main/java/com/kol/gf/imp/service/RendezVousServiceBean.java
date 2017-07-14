/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.dao.bean.RendezVousDaoBeanLocal;
import com.kol.gf.entities.Patient_intervenantid;
import com.kol.gf.entities.RendezVous;
import com.kol.gf.service.RendezVousServiceBeanLocal;
import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class RendezVousServiceBean extends BaseServiceBeanImpl<RendezVous, Patient_intervenantid> implements RendezVousServiceBeanLocal {

    @EJB
    private RendezVousDaoBeanLocal dao;

    @Override
    protected BaseDaoBean<RendezVous, Patient_intervenantid> getDao() {
        return dao;
    }

   

}
