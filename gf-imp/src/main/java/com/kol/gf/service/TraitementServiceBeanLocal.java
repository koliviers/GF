/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.service;

import com.kol.gf.entities.TraitementMedicamenteux;
import com.miki.webapp.core.Service.BaseServiceBean;
import java.io.Serializable;
import javax.ejb.Local;

/**
 *
 * @author koliviers
 */
@Local
public interface TraitementServiceBeanLocal extends BaseServiceBean<TraitementMedicamenteux, Long>{
    
}
