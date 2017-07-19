/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.service;

import com.kol.gf.entities.Habitude_alimentaire;
import com.kol.gf.entities.Habitude_alimentaireId;
import com.miki.webapp.core.Service.BaseServiceBean;
import javax.ejb.Local;

/**
 *
 * @author kol
 */
@Local
public interface Habitude_alimentaireServiceBeanLocal extends BaseServiceBean<Habitude_alimentaire, Habitude_alimentaireId>{
    public void supprimerHabitudeAlimentaire(Habitude_alimentaireId id);
}
