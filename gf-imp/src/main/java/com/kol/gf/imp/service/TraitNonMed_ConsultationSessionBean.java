/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.dao.bean.ITraitNonMed_ConsultationDAO;
import com.kol.gf.entities.TraitNonMed_Consultation;
import com.kol.gf.entities.TraitNonMed_ConsultationId;
import com.kol.gf.service.TraitNonMed_ConsultationSessionBeanLocal;
import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author anonymousghost
 */
@Stateless
public class TraitNonMed_ConsultationSessionBean extends BaseServiceBeanImpl<TraitNonMed_Consultation, TraitNonMed_ConsultationId> implements TraitNonMed_ConsultationSessionBeanLocal {

    @EJB
    private ITraitNonMed_ConsultationDAO dao;

    @Override
    protected BaseDaoBean<TraitNonMed_Consultation, TraitNonMed_ConsultationId> getDao() {
        return dao;
    }

    @Override
    public void supprimerTraitementNonMedicamenteux(TraitNonMed_ConsultationId id) {
        dao.supprimerTraitementNonMedicamenteux(id);
    }
    
    
    
}
