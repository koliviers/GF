/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miki.webapp.miki.securite.Service;


import com.miki.webapp.core.Service.BaseServiceBean;
import com.miki.webapp.miki.securite.entities.Poste;
import javax.ejb.Local;

/**
 *
 * @author Mikel
 */
@Local
public interface PosteSessionBeanLocal extends BaseServiceBean<Poste, Long>{
    
}
