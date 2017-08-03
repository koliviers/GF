/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.service;

import com.kol.gf.entities.TraitNonMed_Consultation;
import com.kol.gf.entities.TraitNonMed_ConsultationId;
import com.miki.webapp.core.Service.BaseServiceBean;
import javax.ejb.Local;

/**
 *
 * @author anonymousghost
 */
@Local
public interface TraitNonMed_ConsultationSessionBeanLocal extends BaseServiceBean<TraitNonMed_Consultation, TraitNonMed_ConsultationId>{
    
    public void supprimerTraitementNonMedicamenteux(TraitNonMed_ConsultationId id);
}
