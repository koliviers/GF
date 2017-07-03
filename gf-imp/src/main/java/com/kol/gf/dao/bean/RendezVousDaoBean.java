/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.bean;

import com.kol.gf.entities.RendezVous;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class RendezVousDaoBean extends GenericDaoBean<RendezVous, Long> implements RendezVousDaoBeanLocal{

    public RendezVousDaoBean() {
        
        super(RendezVous.class);
    }
   public RendezVousDaoBean(Class<RendezVous> entiClass){
       this.entityClass=entiClass;
   }
           
    
    
}
