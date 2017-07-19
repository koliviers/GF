/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.beanImpl;

import com.kol.gf.dao.bean.Habitude_alimentaireDaoBeanLocal;
import com.kol.gf.entities.Habitude_alimentaire;
import com.kol.gf.entities.Habitude_alimentaireId;
import com.miki.webapp.core.DaoImpl.BaseDaoBeanImpl;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author kol
 */
@Stateless
public class Habitude_alimentaireDaoBean extends BaseDaoBeanImpl<Habitude_alimentaire, Habitude_alimentaireId> implements Habitude_alimentaireDaoBeanLocal {

    public Habitude_alimentaireDaoBean() {

        super(Habitude_alimentaire.class);
    }

    @Override
    public void supprimerHabitudeAlimentaire(Habitude_alimentaireId id) {
        try {
            Query query = this.em.createQuery("DELETE FROM Habitude_alimentaire t WHERE t.id = :hb");
            query.setParameter("hb", id);
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Echec de la suppression de l'habitude alimentaire");
        }

    }
}
