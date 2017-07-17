/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.service;

import com.kol.gf.entities.Intervenant;
import com.kol.gf.entities.RendezVous;
import com.miki.webapp.core.Service.BaseServiceBean;
import java.util.Date;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author kol
 */
@Local
public interface RendezVousServiceBeanLocal extends BaseServiceBean<RendezVous, Long>{
    public Map<Date,Integer> getSchedulerInfo(Intervenant intervenant);
}
