/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.service;

import com.kol.gf.entities.TraitementMedicamenteux;
import com.kol.gf.entities.TraitementMedicamenteuxId;
import com.miki.webapp.core.Service.BaseServiceBean;
import javax.ejb.Local;

/**
 *
 * @author anonymousghost
 */
@Local
public interface TraitementMedicamenteuxSessionBeanLocal extends BaseServiceBean<TraitementMedicamenteux, TraitementMedicamenteuxId>{
    public void supprimerTraitementMedicamenteux(TraitementMedicamenteuxId id);
}
