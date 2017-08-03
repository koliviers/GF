/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.beanImpl;

import com.kol.gf.dao.bean.ITraitNonMed_ConsultationDAO;
import com.kol.gf.entities.TraitNonMed_Consultation;
import com.kol.gf.entities.TraitNonMed_ConsultationId;
import com.miki.webapp.core.DaoImpl.BaseDaoBeanImpl;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author anonymousghost
 */
@Stateless
public class TraitNonMedic_ConsultationDAO extends BaseDaoBeanImpl<TraitNonMed_Consultation, TraitNonMed_ConsultationId> implements ITraitNonMed_ConsultationDAO{
    
    public TraitNonMedic_ConsultationDAO(){
        super(TraitNonMed_Consultation.class);
    }
    
    @Override
    public void supprimerTraitementNonMedicamenteux(TraitNonMed_ConsultationId id) {
        try {
            Query query = this.em.createQuery("DELETE FROM TraitNonMed_Consultation t WHERE t.id = :hb");
            query.setParameter("hb", id);
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Echec de la suppression du traitement non medicamenteux");
        }

    }
    
}
