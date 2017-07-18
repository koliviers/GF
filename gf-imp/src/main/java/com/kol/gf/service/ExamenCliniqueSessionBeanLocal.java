/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.service;

import com.kol.gf.entities.ExamenClinique;
import com.miki.webapp.core.Service.BaseServiceBean;
import java.io.Serializable;
import javax.ejb.Local;

/**
 *
 * @author anonymousghost
 */
@Local
public interface ExamenCliniqueSessionBeanLocal extends BaseServiceBean<ExamenClinique, Long>{
    
}
