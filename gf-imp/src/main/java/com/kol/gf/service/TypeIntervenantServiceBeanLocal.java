/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.service;

import com.kol.gf.entities.TypeIntervenant;
import com.miki.webapp.core.Service.BaseServiceBean;
import javax.ejb.Local;

/**
 *
 * @author kol
 */
@Local
public interface TypeIntervenantServiceBeanLocal extends BaseServiceBean<TypeIntervenant, Long>{
    
}
