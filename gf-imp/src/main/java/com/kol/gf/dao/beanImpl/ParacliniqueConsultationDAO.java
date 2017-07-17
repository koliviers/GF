/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.beanImpl;
import com.kol.gf.dao.bean.IParacliniqueConsultationDAO;
import com.kol.gf.entities.ParacliniqueConsultation;
import com.kol.gf.entities.ParacliniqueConsultationId;
import com.miki.webapp.core.DaoImpl.BaseDaoBeanImpl;
import javax.ejb.Stateless;

/**
 *
 * @author anonymousghost
 */



@Stateless
public class ParacliniqueConsultationDAO extends BaseDaoBeanImpl<ParacliniqueConsultation, ParacliniqueConsultationId> implements IParacliniqueConsultationDAO{

    public ParacliniqueConsultationDAO() {
        super(ParacliniqueConsultation.class);
    }
    
    
}
