/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.beanImpl;

import com.kol.gf.dao.bean.ITraitementNonMedicamenteuxDAO;
import com.kol.gf.entities.TraitementNonMedicamenteux;
import com.miki.webapp.core.DaoImpl.BaseDaoBeanImpl;
import javax.ejb.Stateless;

/**
 *
 * @author anonymousghost
 */
@Stateless
public class TraitementNonMedicamenteuxDAO extends BaseDaoBeanImpl<TraitementNonMedicamenteux, Long> implements ITraitementNonMedicamenteuxDAO{
    
    public TraitementNonMedicamenteuxDAO(){
        super(TraitementNonMedicamenteux.class);
    }
    
}
