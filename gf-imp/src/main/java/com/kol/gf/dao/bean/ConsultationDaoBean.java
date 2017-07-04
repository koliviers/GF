/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.bean;

import com.kol.gf.entities.Consultation;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class ConsultationDaoBean extends GenericDaoBean<Consultation, Long> implements ConsultationDaoBeanLocal{
    
    public ConsultationDaoBean(){
        super(Consultation.class);
    }
    
    public ConsultationDaoBean(Class<Consultation> entClass){
        this.entityClass=entClass;
    }
    
}
