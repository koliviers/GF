/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miki.webapp.journal.utils;

import com.kol.gf.entities.ExamenParaclinique;
import com.miki.webapp.core.Service.BaseServiceBean;
import java.io.Serializable;
import javax.ejb.Local;

/**
 *
 * @author anonymousghost
 */
@Local
public interface ExamenParacliniqueSessionBeanLocal extends BaseServiceBean<ExamenParaclinique, Long>{
    
}
