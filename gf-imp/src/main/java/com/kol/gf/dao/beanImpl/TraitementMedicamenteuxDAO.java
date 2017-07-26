/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.beanImpl;

import com.kol.gf.dao.bean.ITraitementMedicamenteuxDAO;
import com.kol.gf.entities.TraitementMedicamenteux;
import com.kol.gf.entities.TraitementMedicamenteuxId;
import com.miki.webapp.core.DaoImpl.BaseDaoBeanImpl;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author anonymousghost
 */
@Stateless
public class TraitementMedicamenteuxDAO extends BaseDaoBeanImpl<TraitementMedicamenteux, TraitementMedicamenteuxId> implements ITraitementMedicamenteuxDAO{

    public TraitementMedicamenteuxDAO() {
        super(TraitementMedicamenteux.class);
    }
    
    @Override
    public void supprimerTraitementMedicamenteux(TraitementMedicamenteuxId id) {
        try {
            Query query = this.em.createQuery("DELETE FROM TraitementMedicamenteux t WHERE t.id = :hb");
            query.setParameter("hb", id);
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Echec de la suppression du traitement medicamenteux");
        }

    }
    
}
