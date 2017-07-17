/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.beanImpl;


import com.kol.gf.dao.bean.RendezVousDaoBeanLocal;
import com.kol.gf.entities.Intervenant;
import com.kol.gf.entities.RendezVous;
import com.miki.webapp.core.DaoImpl.BaseDaoBeanImpl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author kol
 */
@Stateless
public class RendezVousDaoBean extends BaseDaoBeanImpl<RendezVous, Long> implements RendezVousDaoBeanLocal{

    public RendezVousDaoBean() {
        
        super(RendezVous.class);
    }
    
    public Map<Date,Integer> getSchedulerInfo(Intervenant intervenant){
        Query query = this.em.createQuery("SELECT t from RendezVous t where t.intervenant = :inter");
        query.setParameter("inter", intervenant);
        List<RendezVous> rendezVousIntervenant = query.getResultList();
        
        Map<Date,Integer> listeRetour = new HashMap<>();
        
        Map<Date, List<RendezVous>> filtreParDate = rendezVousIntervenant.stream()
                                                    .collect(Collectors.groupingBy(RendezVous::getDateRdvFiltre));
        
        for(Map.Entry<Date, List<RendezVous>> mp : filtreParDate.entrySet()){
            listeRetour.put(mp.getKey(), mp.getValue().size());
        }
        
        return listeRetour;
    }
  
    
}
