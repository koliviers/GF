/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.bean;

import com.kol.gf.entities.Consommation;
import javax.ejb.Local;

/**
 *
 * @author kol
 */
@Local
public interface ConsommationDaoBeanLocal extends GenericDAOBeanLocal<Consommation, Long>{
    
}