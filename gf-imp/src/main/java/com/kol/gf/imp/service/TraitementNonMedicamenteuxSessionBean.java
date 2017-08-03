/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.dao.bean.ITraitementNonMedicamenteuxDAO;
import com.kol.gf.entities.TraitementNonMedicamenteux;
import com.kol.gf.service.TraitementNonMedicamenteuxSessionBeanLocal;
import com.miki.webapp.core.Dao.BaseDaoBean;
import com.miki.webapp.core.ServiceImpl.BaseServiceBeanImpl;
import javax.ejb.Stateless;

/**
 *
 * @author anonymousghost
 */
@Stateless
public class TraitementNonMedicamenteuxSessionBean extends BaseServiceBeanImpl<TraitementNonMedicamenteux, Long> implements TraitementNonMedicamenteuxSessionBeanLocal {

    private ITraitementNonMedicamenteuxDAO dao;

    @Override
    protected BaseDaoBean<TraitementNonMedicamenteux, Long> getDao() {
        return dao;
    }
    
    
}
