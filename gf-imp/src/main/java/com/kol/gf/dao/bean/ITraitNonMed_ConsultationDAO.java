/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.bean;

import com.kol.gf.entities.TraitNonMed_Consultation;
import com.kol.gf.entities.TraitNonMed_ConsultationId;
import com.miki.webapp.core.Dao.BaseDaoBean;
import javax.ejb.Local;

/**
 *
 * @author anonymousghost
 */
@Local
public interface ITraitNonMed_ConsultationDAO  extends BaseDaoBean<TraitNonMed_Consultation, TraitNonMed_ConsultationId>{
    public void supprimerTraitementNonMedicamenteux(TraitNonMed_ConsultationId id);
}