/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.service.TraitementMedicamenteuxSessionBeanLocal;
import com.kol.gf.dao.bean.ITraitementMedicamenteuxDAO;
import com.kol.gf.entities.TraitementMedicamenteux;
import com.kol.gf.entities.TraitementMedicamenteuxId;
import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author anonymousghost
 */
@Stateless
public class TraitementMedicamenteuxSessionBean extends BaseServiceBeanImpl<TraitementMedicamenteux, TraitementMedicamenteuxId> implements TraitementMedicamenteuxSessionBeanLocal {

    @EJB
    private ITraitementMedicamenteuxDAO dao;

    @Override
    protected BaseDaoBean<TraitementMedicamenteux, TraitementMedicamenteuxId> getDao() {
       return dao;
    }

    @Override
    public void supprimerTraitementMedicamenteux(TraitementMedicamenteuxId id) {
        dao.supprimerTraitementMedicamenteux(id);
    }
    
    
}
